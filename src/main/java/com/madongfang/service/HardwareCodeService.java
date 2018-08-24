package com.madongfang.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madongfang.api.HardwareCodeApi;
import com.madongfang.entity.HardwareCode;
import com.madongfang.repository.HardwareCodeRepository;

@Service
public class HardwareCodeService {

	public static HardwareCodeApi hardwareCode2Api(HardwareCode hardwareCode) {
		HardwareCodeApi hardwareCodeApi = new HardwareCodeApi();
		hardwareCodeApi.setBoardId(hardwareCode.getBoardId());
		hardwareCodeApi.setBoardSerialNumber(hardwareCode.getBoardSerialNumber());
		hardwareCodeApi.setId(hardwareCode.getId());
		hardwareCodeApi.setProductName(hardwareCode.getProductName());
		hardwareCodeApi.setRom(hardwareCode.getRom());
		hardwareCodeApi.setSerialNumber(hardwareCode.getSerialNumber());
		hardwareCodeApi.setSmUuid(hardwareCode.getSmUuid());
		hardwareCodeApi.setUsed(hardwareCode.isUsed());
		return hardwareCodeApi;
	}
	
	public List<HardwareCodeApi> getHardwareCodes() {
		List<HardwareCodeApi> hardwareCodeApis = new LinkedList<>();
		
		for (HardwareCode hardwareCode : hardwareCodeRepository.findAll()) {
			hardwareCodeApis.add(hardwareCode2Api(hardwareCode));
		}
		
		return hardwareCodeApis;
	}
	
	@Autowired
	private HardwareCodeRepository hardwareCodeRepository;
}
