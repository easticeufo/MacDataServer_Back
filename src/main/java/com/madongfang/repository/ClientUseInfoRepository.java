package com.madongfang.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madongfang.entity.ClientUseInfo;

public interface ClientUseInfoRepository extends JpaRepository<ClientUseInfo, Integer> {

	public ClientUseInfo findFirstByStatusAndStartTimeBefore(int status, Date startTime);
	
	public ClientUseInfo findByHardwareCodeId(int hardwareCodeId);
}
