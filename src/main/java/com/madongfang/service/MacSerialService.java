package com.madongfang.service;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madongfang.entity.AppleID;
import com.madongfang.entity.ClientUseInfo;
import com.madongfang.entity.HardwareCode;
import com.madongfang.repository.AppleIDRepository;
import com.madongfang.repository.ClientUseInfoRepository;
import com.madongfang.repository.HardwareCodeRepository;
import com.madongfang.util.TcpUtil.Callback;

@Service
public class MacSerialService implements Callback {

	@Override
	public byte[] dataProcess(byte[] receiveDatas) {
		logger.debug("receiveDatas.length={},receiveDatas={}", receiveDatas.length, Arrays.toString(receiveDatas));
		
		byte[] sendDatas = "error".getBytes();
		
		String[] recvStrs = new String(receiveDatas).split(":");
		
		if (recvStrs.length < 1)
		{
			logger.warn("receiveDatas format error");
			return sendDatas;
		}
		
		String cmd = recvStrs[0];
		String[] args = new String[recvStrs.length - 1];
		for (int i = 0; i < args.length; i++)
		{
			args[i] = recvStrs[i + 1];
		}
		
		switch (cmd) {
		case "useInfo":
			sendDatas = getOrCreateUseInfo(args).getBytes();
			break;

		default:
			logger.warn("unknow command:cmd={}", cmd);
			break;
		}
		
		return sendDatas;
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HardwareCodeRepository hardwareCodeRepository;
	
	@Autowired
	private AppleIDRepository appleIDRepository;
	
	@Autowired
	private ClientUseInfoRepository clientUseInfoRepository;
	
	private String getOrCreateUseInfo(String[] args)
	{
		HardwareCode hardwareCode = null;
		
		Date startTime = new Date();
		startTime.setTime(startTime.getTime() - 50 * 60 * 60 * 1000);
		ClientUseInfo clientUseInfo = clientUseInfoRepository.findFirstByStatusAndStartTimeBefore(ClientUseInfo.STATUS_START, startTime);
		if (clientUseInfo != null) // 已经存在静置50小时的AppleID和硬件码
		{
			clientUseInfo.setStatus(ClientUseInfo.STATUS_USED);
			clientUseInfoRepository.save(clientUseInfo);
			
			hardwareCode = hardwareCodeRepository.findOne(clientUseInfo.getHardwareCodeId());
			if (hardwareCode == null)
			{
				logger.info("There is no unused hardware code");
				return "";
			}
		}
		else // 还不存在静置50小时的AppleID和硬件码
		{
			if (args.length < 1)
			{
				logger.warn("createUseInfo args error");
				return "";
			}
			String smUuid = args[0];
			
			hardwareCode = hardwareCodeRepository.findFirstByUsedFalse();
			if (hardwareCode == null)
			{
				logger.info("There is no unused hardware code");
				return "";
			}
			
			AppleID appleID = appleIDRepository.findFirstByUsedFalse();
			if (appleID == null)
			{
				logger.info("There is no unused appleID");
				return "";
			}

			hardwareCode.setUsed(true);
			hardwareCode.setSmUuid(smUuid);
			hardwareCodeRepository.save(hardwareCode);
			
			appleID.setUsed(true);
			appleIDRepository.save(appleID);
			
			clientUseInfo = new ClientUseInfo();
			clientUseInfo.setAppleIDId(appleID.getId());
			clientUseInfo.setHardwareCodeId(hardwareCode.getId());
			clientUseInfo.setStartTime(null);
			clientUseInfo.setStatus(ClientUseInfo.STATUS_NEW);
			clientUseInfoRepository.save(clientUseInfo);
		}
		
		return String.format("%s:%s:%s:%s:%s:%s", hardwareCode.getRom(), hardwareCode.getBoardSerialNumber(), 
				hardwareCode.getSerialNumber(), hardwareCode.getSmUuid(), hardwareCode.getBoardId(), hardwareCode.getProductName());
	}

}
