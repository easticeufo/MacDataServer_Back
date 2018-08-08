package com.madongfang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
	
	@GetMapping(params="page")
	public Page<PhoneNumberApi> getPhoneNumbers(@PageableDefault(size=100) Pageable pageable)
	{
		return phoneNumberService.getPhoneNumbers(pageable);
	}
	
	@Autowired
	private PhoneNumberService phoneNumberService;
}
