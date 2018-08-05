package com.madongfang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madongfang.entity.AppleID;

public interface AppleIDRepository extends JpaRepository<AppleID, Integer> {

	public AppleID findFirstByUsedFalse();
}
