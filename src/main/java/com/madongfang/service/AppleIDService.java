package com.madongfang.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madongfang.api.AppleIDApi;
import com.madongfang.entity.AppleID;
import com.madongfang.repository.AppleIDRepository;

@Service
public class AppleIDService {

	public static AppleIDApi appleID2Api(AppleID appleID) {
		AppleIDApi appleIDApi = new AppleIDApi();
		appleIDApi.setAccount(appleID.getAccount());
		appleIDApi.setId(appleID.getId());
		appleIDApi.setPassword(appleID.getPassword());
		appleIDApi.setUsed(appleID.isUsed());
		return appleIDApi;
	}

	public List<AppleIDApi> getAppleIDs()
	{
		List<AppleIDApi> appleIDApis = new LinkedList<>();
		for (AppleID appleID: appleIDRepository.findAll()) {
			appleIDApis.add(appleID2Api(appleID));
		}
		
		return appleIDApis;
	}
	
	@Autowired
	private AppleIDRepository appleIDRepository;
}
