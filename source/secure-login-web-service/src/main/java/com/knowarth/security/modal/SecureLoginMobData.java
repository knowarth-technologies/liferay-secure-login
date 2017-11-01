package com.knowarth.security.modal;

import java.io.Serializable;

public class SecureLoginMobData implements Serializable{


	private String encodedTokenFromMob;
	private String emailAddress;

	public String getEncodedTokenFromMob() {
		return encodedTokenFromMob;
	}

	public void setEncodedTokenFromMob(String encodedTokenFromMob) {
		this.encodedTokenFromMob = encodedTokenFromMob;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
}
