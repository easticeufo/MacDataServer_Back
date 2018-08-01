package com.madongfang.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madongfang.entity.HardwareCode;
import com.madongfang.repository.HardwareCodeRepository;
import com.madongfang.util.TcpUtil.Callback;

@Service
public class MacSerialService implements Callback {

	@Override
	public byte[] dataProcess(byte[] receiveDatas) {
		logger.debug("receiveDatas.length={},receiveDatas={}", receiveDatas.length, Arrays.toString(receiveDatas));
		
		byte[] sendDatas = null;
		
		String cmd = new String(receiveDatas);
		
		switch (cmd) {
		case "5codes":
			sendDatas = obtainHardwareCode().getBytes();
			break;

		default:
			logger.warn("unknow command:cmd={}", cmd);
			sendDatas = new byte[0];
			break;
		}
		
		return sendDatas;
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HardwareCodeRepository hardwareCodeRepository;
	
	private String obtainHardwareCode()
	{
		HardwareCode hardwareCode = hardwareCodeRepository.findFirstByUsedFalse();
		if (hardwareCode == null)
		{
			logger.info("There is no unused hardware code");
			return "";
		}
		else
		{
			hardwareCode.setUsed(true);
			hardwareCodeRepository.save(hardwareCode);
			return String.format("%s:%s:%s:%s:%s", hardwareCode.getRom(), hardwareCode.getBoardSerialNumber(), 
					hardwareCode.getSerialNumber(), hardwareCode.getBoardId(), hardwareCode.getProductName());
		}
	}

}
