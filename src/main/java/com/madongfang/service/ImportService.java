package com.madongfang.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.madongfang.api.ReturnApi;
import com.madongfang.entity.AppleID;
import com.madongfang.entity.HardwareCode;
import com.madongfang.entity.PhoneNumber;
import com.madongfang.exception.HttpBadRequestException;
import com.madongfang.repository.AppleIDRepository;
import com.madongfang.repository.HardwareCodeRepository;
import com.madongfang.repository.PhoneNumberRepository;

@Service
public class ImportService {

	public ReturnApi importHardwareCode(MultipartFile hardwareCodeFile) throws IOException {
		if (hardwareCodeFile.isEmpty())
		{
			throw new HttpBadRequestException(new ReturnApi(-1, "文件为空"));
		}
		
		List<HardwareCode> hardwareCodes = new LinkedList<>();
		
		InputStream in = hardwareCodeFile.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		String line = bufferedReader.readLine();
		while (line != null)
		{
			logger.debug("readLine={}", line);
			line = line.trim();
			if (line.startsWith(":"))
			{
				line = line.substring(1);
			}
			String[] columns = line.split(":");
			int columnNum = columns.length;
			if (columnNum >= 5)
			{
				HardwareCode hardwareCode = new HardwareCode();
				hardwareCode.setUsed(false);
				hardwareCode.setRom(columns[0]);
				hardwareCode.setBoardSerialNumber(columns[1]);
				hardwareCode.setSerialNumber(columns[2]);
				hardwareCode.setBoardId(columns[3]);
				hardwareCode.setProductName(columns[4]);
				hardwareCodes.add(hardwareCode);
			}
			else
			{
				logger.warn("readLine format error:readLine={}", line);
			}
			
			line = bufferedReader.readLine();
		}
		
		hardwareCodeRepository.save(hardwareCodes);
		
		return new ReturnApi(0, "OK");
	}
	
	public ReturnApi importAppleID(MultipartFile appleIDFile) throws IOException {
		if (appleIDFile.isEmpty())
		{
			throw new HttpBadRequestException(new ReturnApi(-1, "文件为空"));
		}
		
		List<AppleID> appleIDs = new LinkedList<>();
		
		InputStream in = appleIDFile.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		String line = bufferedReader.readLine();
		while (line != null)
		{
			logger.debug("readLine={}", line);
			line = line.trim();
			String[] columns = line.split("----");
			int columnNum = columns.length;
			if (columnNum >= 2)
			{
				AppleID appleID = new AppleID();
				appleID.setAccount(columns[0]);
				appleID.setPassword(columns[1]);
				appleID.setUsed(false);
				appleIDs.add(appleID);
			}
			else
			{
				logger.warn("readLine format error:readLine={}", line);
			}
			
			line = bufferedReader.readLine();
		}
		
		appleIDRepository.save(appleIDs);
		
		return new ReturnApi(0, "OK");
	}
	
	public ReturnApi importPhoneNumber(MultipartFile phoneNumberFile) throws IOException {
		if (phoneNumberFile.isEmpty())
		{
			throw new HttpBadRequestException(new ReturnApi(-1, "文件为空"));
		}
		
		List<PhoneNumber> phoneNumbers = new LinkedList<>();
		
		InputStream in = phoneNumberFile.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		String line = bufferedReader.readLine();
		while (line != null)
		{
			logger.debug("readLine={}", line);
			line = line.trim();
			if (line.length() > 0)
			{
				PhoneNumber phoneNumber = new PhoneNumber();
				phoneNumber.setNumber(line);
				phoneNumber.setUseCount(0);
				phoneNumbers.add(phoneNumber);
			}
			else
			{
				logger.warn("readLine format error:readLine={}", line);
			}
			
			line = bufferedReader.readLine();
		}
		
		phoneNumberRepository.save(phoneNumbers);
		
		return new ReturnApi(0, "OK");
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HardwareCodeRepository hardwareCodeRepository;
	
	@Autowired
	private AppleIDRepository appleIDRepository;
	
	@Autowired
	private PhoneNumberRepository phoneNumberRepository;
}
