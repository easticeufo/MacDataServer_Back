package com.madongfang.api;

import java.util.Date;

public class ClientUseInfoApi {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public AppleIDApi getAppleID() {
		return appleID;
	}

	public void setAppleID(AppleIDApi appleID) {
		this.appleID = appleID;
	}

	public HardwareCodeApi getHardwareCode() {
		return hardwareCode;
	}

	public void setHardwareCode(HardwareCodeApi hardwareCode) {
		this.hardwareCode = hardwareCode;
	}

	private int id;
	
	private Integer status;
	
	private Date startTime;
	
	private AppleIDApi appleID;
	
	private HardwareCodeApi hardwareCode;
}
