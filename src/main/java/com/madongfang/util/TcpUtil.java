package com.madongfang.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TcpUtil implements Runnable {
	
	public void startServer(int port, Callback callback) {
		if (!tcpServerStart)
		{
			this.port = port;
			this.callback = callback;
			tcpServerStart = true;
			new Thread(this).start();
		}
		else 
		{
			logger.warn("Tcp Server Has Already Started");
		}
	}

	@Override
	public void run() {
		logger.info("Tcp Server Start");
		
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(port);
			Socket socket = null;
			
			while (true)
			{
				socket = serverSocket.accept();
				
				new clientCommunicationThread(socket, callback).start();
			}
			
		} catch (IOException e) {
			logger.error("catch IOException:", e);
		} finally {
			tcpServerStart = false;
			logger.info("Tcp Server Stop");
			try {
				if (serverSocket != null)
				{
					serverSocket.close();
				}
			} catch (IOException e) {
				logger.error("catch IOException:", e);
			}
		}
	}
	
	public static interface Callback {
		public byte[] dataProcess(byte[] receiveDatas);
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private boolean tcpServerStart = false;
	
	private int port;
	
	private Callback callback;
	
	private static class clientCommunicationThread extends Thread {

		public clientCommunicationThread(Socket socket, Callback callback) {
			super();
			this.socket = socket;
			this.callback = callback;
		}

		@Override
		public void run() {
			logger.info("clientCommunicationThread start");
			InetAddress address = socket.getInetAddress();
			logger.info("client address: {}", address.getHostAddress());
			
			InputStream in = null;
			OutputStream out = null;
			try 
			{
				in = socket.getInputStream();
				out = socket.getOutputStream();
				
				while (true)
				{
					byte[] head = readn(in, 4);
					int datalen = (head[3] & 0xFF) | ((head[2] & 0xFF) << 8) | ((head[1] & 0xFF) << 16) | ((head[0] & 0xFF) << 24);
					byte[] sendDatas = callback.dataProcess(readn(in, datalen));
					if (sendDatas != null)
					{
						int sendlen = sendDatas.length;
						head[0] = (byte)((sendlen >> 24) & 0xFF);
						head[1] = (byte)((sendlen >> 16) & 0xFF);
						head[2] = (byte)((sendlen >> 8) & 0xFF);
						head[3] = (byte)(sendlen & 0xFF);
						out.write(head);
						out.write(sendDatas);
					}
				}
			} 
			catch (IOException e) 
			{
				logger.warn("TCP disconnect");
				logger.debug("IOException:", e);
			}
			finally 
			{
				try {
					if (in != null)
					{
						in.close();
					}
					
					if (out != null)
					{
						out.close();
					}
				} catch (IOException e) {
					logger.error("IOException:", e);
				}
				logger.info("clientCommunicationThread stop");
			}
			
		}
		
		private final Logger logger = LoggerFactory.getLogger(getClass());
		
		private Socket socket;
		
		private Callback callback;
		
		private byte[] readn(InputStream in, int datalen) throws IOException
		{
			byte[] dataBuffer = new byte[datalen];
			int off = 0;
			int len = datalen;
			while (len > 0)
			{
				int readlen = in.read(dataBuffer, off, len);
				if (readlen < 0)
				{
					logger.warn("inputStream.read < 0");
					throw new IOException("inputStream.read < 0");
				}
				off += readlen;
				len -= readlen;
			}
			
			return dataBuffer;
		}
	}

}
