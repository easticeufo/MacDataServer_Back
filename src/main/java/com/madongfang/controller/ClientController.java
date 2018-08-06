package com.madongfang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madongfang.api.SendInfoApi;
import com.madongfang.service.ClientService;

@RestController
@RequestMapping(value="/api/client")
public class ClientController {

	@PostMapping(value="/obtainSendInfo")
	public SendInfoApi obtainSendInfo()
	{
		return clientService.obtainSendInfo();
	}
	
	@Autowired
	private ClientService clientService;
}
