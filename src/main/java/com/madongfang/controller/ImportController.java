package com.madongfang.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.madongfang.api.ReturnApi;
import com.madongfang.service.ImportService;

@RestController
@RequestMapping(value="/api/import")
public class ImportController {

	@PostMapping(value="/hardwareCode")
	public ReturnApi importHardwareCode(@RequestParam("hardwareCodeFile") MultipartFile hardwareCodeFile) throws IOException
	{
		return importService.importHardwareCode(hardwareCodeFile);
	}
	
	@PostMapping(value="/appleID")
	public ReturnApi importAppleID(@RequestParam("appleIDFile") MultipartFile appleIDFile) throws IOException
	{
		return importService.importAppleID(appleIDFile);
	}
	
	@PostMapping(value="/phoneNumber")
	public ReturnApi importPhoneNumber(@RequestParam("phoneNumberFile") MultipartFile phoneNumberFile) throws IOException
	{
		return importService.importPhoneNumber(phoneNumberFile);
	}
	
	@Autowired
	private ImportService importService;
}
