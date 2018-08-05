package com.madongfang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madongfang.api.PhoneNumberApi;
import com.madongfang.service.PhoneNumberService;

@RestController
@RequestMapping(value="/api/phoneNumbers")
public class PhoneNumberController {

	@GetMapping
	public List<PhoneNumberApi> getPhoneNumbers() {
		return phoneNumberService.getPhoneNumbers();
	}
	
	@Autowired
	private PhoneNumberService phoneNumberService;
}
