package com.madongfang.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.madongfang.util.TcpUtil.Callback;

@Service
public class MacSerialService implements Callback {

	@Override
	public byte[] dataProcess(byte[] receiveDatas) {
		logger.debug("receiveDatas.length={},receiveDatasString={}", receiveDatas.length, new String(receiveDatas));
		
		return new byte[0];
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

}
