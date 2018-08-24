package com.madongfang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClientUseInfo {

	public static final int STATUS_NEW = 0;
	
	public static final int STATUS_START = 1;
	
	public static final int STATUS_USED = 2;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAppleIDId() {
		return appleIDId;
	}

	public void setAppleIDId(int appleIDId) {
		this.appleIDId = appleIDId;
	}

	public int getHardwareCodeId() {
		return hardwareCodeId;
	}

	public void setHardwareCodeId(int hardwareCodeId) {
		this.hardwareCodeId = hardwareCodeId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true)
	private int appleIDId;
	
	@Column(unique=true)
	private int hardwareCodeId;
	
	private int status;
	
	private Date startTime;
}
