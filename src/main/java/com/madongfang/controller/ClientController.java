package com.madongfang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madongfang.api.ClientUseInfoApi;
import com.madongfang.api.PhoneNumberApi;
import com.madongfang.service.ClientService;

@RestController
@RequestMapping(value="/api/client")
public class ClientController {

	@GetMapping(value="/useInfo")
	public ClientUseInfoApi getUseInfo(@RequestParam String serialNumber)
	{
		return clientService.getUseInfo(serialNumber);
	}
	
	@PutMapping(value="/useInfo")
	public ClientUseInfoApi setUseInfoStatus(@RequestBody ClientUseInfoApi clientUseInfoApi)
	{
		return clientService.setUseInfoStatus(clientUseInfoApi);
	}
	
	@PostMapping(value="/obtainPhoneNumbers")
	public List<PhoneNumberApi> obtainPhoneNumbers(@PageableDefault(size=100, sort={"useCount", "id"}) Pageable pageable)
	{
		return clientService.obtainPhoneNumbers(pageable);
	}
	
	@Autowired
	private ClientService clientService;
}
