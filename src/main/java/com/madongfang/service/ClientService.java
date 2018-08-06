package com.madongfang.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madongfang.api.PhoneNumberApi;
import com.madongfang.api.ReturnApi;
import com.madongfang.api.SendInfoApi;
import com.madongfang.entity.AppleID;
import com.madongfang.entity.PhoneNumber;
import com.madongfang.exception.HttpNotFoundException;
import com.madongfang.repository.AppleIDRepository;
import com.madongfang.repository.PhoneNumberRepository;

@Service
public class ClientService {

	public SendInfoApi obtainSendInfo() {
		SendInfoApi sendInfoApi = new SendInfoApi();
		AppleID appleID = appleIDRepository.findFirstByUsedFalse();
		if (appleID == null)
		{
			throw new HttpNotFoundException(new ReturnApi(-1, "有效的AppleID不存在或已用完"));
		}
		sendInfoApi.setAppleID(AppleIDService.appleID2Api(appleID));
		appleID.setUsed(true);
		appleIDRepository.save(appleID);
		
		List<PhoneNumberApi> phoneNumbers = new LinkedList<>();
		for (PhoneNumber phoneNumber : phoneNumberRepository.findFirst89ByOrderByUseCountAsc()) {
			phoneNumbers.add(PhoneNumberService.phoneNumber2Api(phoneNumber));
			phoneNumber.setUseCount(phoneNumber.getUseCount() + 1);
			phoneNumberRepository.save(phoneNumber);
		}
		sendInfoApi.setPhoneNumbers(phoneNumbers);
		
		return sendInfoApi;
	}
	
	@Autowired
	private AppleIDRepository appleIDRepository;
	
	@Autowired
	private PhoneNumberRepository phoneNumberRepository;
}
