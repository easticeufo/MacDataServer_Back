package com.madongfang.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HardwareCode {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRom() {
		return rom;
	}

	public void setRom(String rom) {
		this.rom = rom;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getBoardSerialNumber() {
		return boardSerialNumber;
	}

	public void setBoardSerialNumber(String boardSerialNumber) {
		this.boardSerialNumber = boardSerialNumber;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String rom;
	
	private String serialNumber;
	
	private String boardSerialNumber;
	
	private String boardId;
	
	private String productName;
	
	private boolean used;
}
