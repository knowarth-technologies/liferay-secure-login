package com.knowarth.security.util;

public class UserSecureLoginVO {

	private String emailAddress;
	private String screenName;
	private long userId;
	private String registrationKey;
	
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getRegistrationKey() {
		return registrationKey;
	}
	public void setRegistrationKey(String registrationKey) {
		this.registrationKey = registrationKey;
	}
	
	
	
	
}
