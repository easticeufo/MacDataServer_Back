package com.madongfang.api;

import java.util.List;

public class SendInfoApi {

	public AppleIDApi getAppleID() {
		return appleID;
	}

	public void setAppleID(AppleIDApi appleID) {
		this.appleID = appleID;
	}

	public List<PhoneNumberApi> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumberApi> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	private AppleIDApi appleID;
	
	private List<PhoneNumberApi> phoneNumbers;
}
