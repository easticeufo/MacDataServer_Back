package com.madongfang.api;

public class HardwareCodeApi {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}

	private Integer id;
	
	private String rom;
	
	private String serialNumber;
	
	private String boardSerialNumber;
	
	private String boardId;
	
	private String productName;
	
	private Boolean used;
}
