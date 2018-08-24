package com.madongfang.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.madongfang.api.ClientUseInfoApi;
import com.madongfang.api.PhoneNumberApi;
import com.madongfang.api.ReturnApi;
import com.madongfang.entity.AppleID;
import com.madongfang.entity.ClientUseInfo;
import com.madongfang.entity.HardwareCode;
import com.madongfang.entity.PhoneNumber;
import com.madongfang.exception.HttpNotFoundException;
import com.madongfang.repository.AppleIDRepository;
import com.madongfang.repository.ClientUseInfoRepository;
import com.madongfang.repository.HardwareCodeRepository;
import com.madongfang.repository.PhoneNumberRepository;

@Service
public class ClientService {

	public static ClientUseInfoApi clientUseInfo2Api(ClientUseInfo clientUseInfo) {
		ClientUseInfoApi clientUseInfoApi = new ClientUseInfoApi();
		clientUseInfoApi.setId(clientUseInfo.getId());
		clientUseInfoApi.setStartTime(clientUseInfo.getStartTime());
		clientUseInfoApi.setStatus(clientUseInfo.getStatus());
		return clientUseInfoApi;
	}
	
	public ClientUseInfoApi getUseInfo(String serialNumber) {
		HardwareCode hardwareCode = hardwareCodeRepository.findBySerialNumber(serialNumber);
		if (hardwareCode == null)
		{
			logger.warn("无效的序列号: serialNumber={}", serialNumber);
			throw new HttpNotFoundException(new ReturnApi(-1, "无效的序列号"));
		}
		
		ClientUseInfo clientUseInfo = clientUseInfoRepository.findByHardwareCodeId(hardwareCode.getId());
		if (clientUseInfo == null)
		{
			logger.warn("未找到相应的clientUseInfo: hardwareCodeId={}", hardwareCode.getId());
			throw new HttpNotFoundException(new ReturnApi(-2, "未找到相应的clientUseInfo"));
		}
		
		AppleID appleID = appleIDRepository.findOne(clientUseInfo.getAppleIDId());
		if (appleID == null)
		{
			logger.warn("未找到相应的appleID: clientUseInfoId={}", clientUseInfo.getId());
			throw new HttpNotFoundException(new ReturnApi(-3, "未找到相应的appleID"));
		}
		
		ClientUseInfoApi clientUseInfoApi = clientUseInfo2Api(clientUseInfo);
		clientUseInfoApi.setAppleID(AppleIDService.appleID2Api(appleID));
		clientUseInfoApi.setHardwareCode(HardwareCodeService.hardwareCode2Api(hardwareCode));
		
		return clientUseInfoApi;
	}
	
	public ClientUseInfoApi setUseInfoStatus(ClientUseInfoApi clientUseInfoApi) {
		ClientUseInfo clientUseInfo = clientUseInfoRepository.findOne(clientUseInfoApi.getId());
		if (clientUseInfo == null)
		{
			logger.warn("未找到相应的clientUseInfo: clientUseInfoId={}", clientUseInfoApi.getId());
			throw new HttpNotFoundException(new ReturnApi(-1, "未找到相应的clientUseInfo"));
		}
		
		if (clientUseInfoApi.getStatus() != null)
		{
			clientUseInfo.setStatus(clientUseInfoApi.getStatus());
			if (clientUseInfo.getStatus() == ClientUseInfo.STATUS_START)
			{
				clientUseInfo.setStartTime(new Date());
			}
		}
		clientUseInfoRepository.save(clientUseInfo);
		
		return clientUseInfo2Api(clientUseInfo);
	}
	
	public List<PhoneNumberApi> obtainPhoneNumbers(Pageable pageable) {
		List<PhoneNumberApi> phoneNumbers = new LinkedList<>();
		for (PhoneNumber phoneNumber : phoneNumberRepository.findAll(pageable)) {
			phoneNumbers.add(PhoneNumberService.phoneNumber2Api(phoneNumber));
			phoneNumber.setUseCount(phoneNumber.getUseCount() + 1);
			phoneNumberRepository.save(phoneNumber);
		}
		
		return phoneNumbers;
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HardwareCodeRepository hardwareCodeRepository;
	
	@Autowired
	private AppleIDRepository appleIDRepository;
	
	@Autowired
	private PhoneNumberRepository phoneNumberRepository;
	
	@Autowired
	private ClientUseInfoRepository clientUseInfoRepository;
}
