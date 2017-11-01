package com.knowarth.security.util;

public interface SecureLoginConstants {

	//Pagination constatns
	public final int DEFAULT_RECORDS_PER_PAGE = 10;
	
	
	//Page constants for registration.
	public final String PAGE_USER_SECURE_LOGIN_REG_KEY = "registration-key";
	
	
	//Page constants for custom secure login.
	public final String PAGE_USER_SECURE_LOGIN_USER_NAME = "username";
	public final String PAGE_USER_SECURE_LOGIN_QR_CODE = "qrcode";
	
	/* Page constants for device registration  */
	public final String PAGE_DEVICE_REG_QR_CODE = "qrcode";
	public final String PAGE_DEVICE_REG_CREDENTIALS = "credentials";
	
	/* Secure Login toekn polling flags*/
	public final String IS_SECURE_LOGIN_TOKEN_VALIDATED = "isSecureLoginTokenValidated";
	public final String DO_POLLING = "doPolling";
	public final String LOGIN_MSG = "loginMsg";
	
	/* User detail map keys */
	public final String USER_DETAIL_MAP_KEY_LOGIN_MSG="loginMessage";
	public final String USER_DETAIL_MAP_KEY_USER_NOT_EXIST="userNotExist";
	public final String USER_DETAIL_MAP_KEY_USER_REG_NOT_DONE="userRegistrationNotDone";
	public final String USER_DETAIL_MAP_KEY_CUR_USER="currentUserin";
	
	public final String LABEL_CUSTOM_SECURE_LOGIN_EA = "user.login.ea.msg";
	public final String LABEL_CUSTOM_SECURE_LOGIN_SN ="user.login.sn.msg";
	public final String LABEL_CUSTOM_SECURE_LOGIN_ID ="user.login.id.msg";
	
	public final String LABEL_USER_NOT_EXIST_EA = "error.user.ea.not.exist";
	public final String LABEL_USER_NOT_EXIST_SN = "error.user.sn.not.exist";
	public final String LABEL_USER_NOT_EXIST_ID = "error.user.id.not.exist";
	
	/*user not register Label*/
	public final String LABEL_CUSTOM_NOT_REGISTER_LOGIN_EA = "error.user.ea.not.registered";
	public final String LABEL_CUSTOM_NOT_REGISTER_LOGIN_SN = "error.user.sn.not.registered";
	public final String LABEL_CUSTOM_NOT_REGISTER_LOGIN_ID = "error.user.id.not.registered";

	public final String CHECK_TOKEN_IS_VALIDATED = "checkIfTokenIsValidated";
	public final String GENERATE_QR_CODE = "generateQRCode";
	
	public static final int NO_OF_SECOND_QR_TOKEN_IS_VALID=60;
	public static final String USER_NAME = "userName";
	public static final String PASSWORD = "password";
}


