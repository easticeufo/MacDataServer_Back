package com.madongfang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madongfang.entity.HardwareCode;

public interface HardwareCodeRepository extends JpaRepository<HardwareCode, Integer> {

	public HardwareCode findFirstByUsedFalse();
	
	public HardwareCode findBySerialNumber(String serialNumber);
}
