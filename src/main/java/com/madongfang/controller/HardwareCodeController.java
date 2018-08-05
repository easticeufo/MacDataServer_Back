package com.madongfang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madongfang.api.HardwareCodeApi;
import com.madongfang.service.HardwareCodeService;

@RestController
@RequestMapping(value="/api/hardwareCodes")
public class HardwareCodeController {

	@GetMapping
	public List<HardwareCodeApi> getHardwareCodes()
	{
		return hardwareCodeService.getHardwareCodes();
	}
	
	@Autowired
	private HardwareCodeService hardwareCodeService;
}
