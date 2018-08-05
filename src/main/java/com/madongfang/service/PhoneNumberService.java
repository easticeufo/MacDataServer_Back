package com.madongfang.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madongfang.api.PhoneNumberApi;
import com.madongfang.entity.PhoneNumber;
import com.madongfang.repository.PhoneNumberRepository;

@Service
public class PhoneNumberService {

	public static PhoneNumberApi phoneNumber2Api(PhoneNumber phoneNumber) {
		PhoneNumberApi phoneNumberApi =  new PhoneNumberApi();
		phoneNumberApi.setId(phoneNumber.getId());
		phoneNumberApi.setNumber(phoneNumber.getNumber());
		phoneNumberApi.setUseCount(phoneNumber.getUseCount());
		return phoneNumberApi;
	}
	
	public List<PhoneNumberApi> getPhoneNumbers() {
		List<PhoneNumberApi> phoneNumberApis = new LinkedList<>();
		
		for (PhoneNumber phoneNumber : phoneNumberRepository.findAll()) {
			phoneNumberApis.add(phoneNumber2Api(phoneNumber));
		}
		
		return phoneNumberApis;
	}
	
	@Autowired
	private PhoneNumberRepository phoneNumberRepository;
}
