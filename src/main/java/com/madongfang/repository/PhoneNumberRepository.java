package com.madongfang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madongfang.entity.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {

	public List<PhoneNumber> findFirst89ByOrderByUseCountAsc();
}
