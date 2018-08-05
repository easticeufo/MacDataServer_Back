package com.madongfang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madongfang.api.AppleIDApi;
import com.madongfang.service.AppleIDService;

@RestController
@RequestMapping(value="/api/appleIDs")
public class AppleIDController {

	@GetMapping
	public List<AppleIDApi> getAppleIDs()
	{
		return appleIDService.getAppleIDs();
	}
	
	@Autowired
	private AppleIDService appleIDService;
}
