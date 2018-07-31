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
	
	@Autowired
	private ImportService importService;
}
