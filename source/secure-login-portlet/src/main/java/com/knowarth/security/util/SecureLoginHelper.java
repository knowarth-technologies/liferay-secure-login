package com.knowarth.security.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.knowarth.security.model.SecureLogin;
import com.knowarth.security.service.SecureLoginLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class SecureLoginHelper {

	private static final Log LOG = LogFactoryUtil.getLog(SecureLoginHelper.class.getName());
	
	private SecureLoginHelper(){
		
	}
	/**
	 * This method convert list of object array into list of UserSecureLoginVO
	 * @param userSecureLoginLst
	 * @return List<UserSecureLoginVO>
	 */
	public static List<UserSecureLoginVO> getUserSecureLoginInfo(List<Object[]> userSecureLoginLst){
		List<UserSecureLoginVO> userSecureLoginVOLst = new ArrayList<UserSecureLoginVO>();
		
		UserSecureLoginVO userSecureLoginVO = null;
		if(Validator.isNotNull(userSecureLoginLst) && !userSecureLoginLst.isEmpty()){
			for(Object[] userSecureLoginObj : userSecureLoginLst){
				userSecureLoginVO = new UserSecureLoginVO();
				userSecureLoginVO.setEmailAddress(GetterUtil.getString(userSecureLoginObj[0]));
				userSecureLoginVO.setScreenName(GetterUtil.getString(userSecureLoginObj[1]));
				userSecureLoginVO.setUserId(GetterUtil.getLong(userSecureLoginObj[2]));
				userSecureLoginVO.setRegistrationKey(GetterUtil.getString(userSecureLoginObj[3]));
				
				userSecureLoginVOLst.add(userSecureLoginVO);
			}
		}
		
		return userSecureLoginVOLst;
	}
	
	/**
	 * This method will generate QR code for given string as input.
	 * @param qrCodeStr - input string
	 * @return Bytes of image of QR code
	 * @throws WriterException
	 * @throws IOException
	 */
	public static byte[] generateQRCode(String qrCodeStr) throws WriterException,IOException {
		
		File tempFile = FileUtil.createTempFile();
		
		Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
		hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		// Now with zxing version 3.2.1 you could change border size (white border size to just 1)
		hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix;

		byteMatrix = qrCodeWriter.encode(qrCodeStr, BarcodeFormat.QR_CODE, 250, 250, hintMap);
		int crunchifyWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(crunchifyWidth, crunchifyWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, crunchifyWidth, crunchifyWidth);
		graphics.setColor(Color.BLACK);

		for (int iIndex = 0; iIndex < crunchifyWidth; iIndex++) {
		  for (int jIndex = 0; jIndex < crunchifyWidth; jIndex++) {
		    if (byteMatrix.get(iIndex, jIndex)) {
		      graphics.fillRect(iIndex, jIndex, 1, 1);
		    }
		  }
		}
		
		ImageIO.write(image, "png", tempFile);
		byte[] bytes = FileUtil.getBytes(tempFile);
		FileUtil.delete(tempFile);
		
		return bytes;
	}
	
	/**
	 * This method will be used to get user from given userName
	 * @param userName
	 * @param company
	 * @return User
	 */
	public static User getUser(String userName,Company company){
		User validUser = null;
		String authType = CompanyConstants.AUTH_TYPE_EA;
		try {
			authType = company.getAuthType();
		} catch (SystemException e) {
			LOG.error("error occured while getting auth type "+e+" "+e.getMessage());
		}
		try{
			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				validUser = UserLocalServiceUtil.getUserByEmailAddress(company.getCompanyId(), userName);
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				validUser = UserLocalServiceUtil.getUserByScreenName(company.getCompanyId(), userName);
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				long userId = GetterUtil.getLong(userName);
				validUser = UserLocalServiceUtil.getUser(userId);
			}
		}catch(SystemException se){
			LOG.error("System Exception occured while verifing user based on user name "+userName+" "+se);
		}catch(PortalException pe){
			LOG.error("Portal Exception occured while verifing user based on user name "+userName+" "+pe);
		}
		return validUser;
	}
	
	/**
	 * This method will return user details with all msg for given companyId and userName
	 * @param companyId
	 * @param userName
	 * @return Map<String,User>
	 */
	public static Map<String,Object> getUserDetail(long companyId, String userName){
		Map<String,Object> userDetailMap = new HashMap<String, Object>();
		User validUser = null;
		
		String loginMsg = StringPool.BLANK;
		String userNotExist = StringPool.BLANK;
		String userNotRegistered = StringPool.BLANK;
		String errorMsgKey = StringPool.BLANK;
		
		
		
		try{
			String authType = StringPool.BLANK;
			Company currentCompany = CompanyLocalServiceUtil.getCompany(companyId);
			
			if(Validator.isNotNull(currentCompany)){
				authType = currentCompany.getAuthType();
				if (CompanyConstants.AUTH_TYPE_EA.equalsIgnoreCase(authType)) {
					loginMsg = "user.login.ea.msg";
					userNotExist="error.user.ea.not.exist";
					userNotRegistered = "error.user.ea.not.registered";
					errorMsgKey="error.invalid.login.ea";
					validUser = UserLocalServiceUtil.getUserByEmailAddress(companyId, userName);
				}
				else if (CompanyConstants.AUTH_TYPE_SN.equalsIgnoreCase(authType)) {
					loginMsg = "user.login.sn.msg";
					userNotExist="error.user.ea.not.exist";
					userNotRegistered = "error.user.sn.not.registered";
					errorMsgKey="error.invalid.login.sn";
					validUser = UserLocalServiceUtil.getUserByScreenName(companyId, userName);
				}
				else if (CompanyConstants.AUTH_TYPE_ID.equalsIgnoreCase(authType)) {
					loginMsg = "user.login.id.msg";
					userNotExist="error.user.ea.not.exist";
					userNotRegistered = "error.user.id.not.registered";
					errorMsgKey="error.invalid.login.id";
					long userId = GetterUtil.getLong(userName);
					validUser = UserLocalServiceUtil.getUser(userId);
				}
			}
		}catch(SystemException se){
			LOG.error("System Exception occured while verifing user based on user name "+userName+" "+se);
		}catch(PortalException pe){
			LOG.error("Portal Exception occured while verifing user based on user name "+userName+" "+pe);
		}
		
		userDetailMap.put(SecureLoginConstants.USER_DETAIL_MAP_KEY_LOGIN_MSG, loginMsg);
		userDetailMap.put(SecureLoginConstants.USER_DETAIL_MAP_KEY_USER_NOT_EXIST, loginMsg);
		userDetailMap.put(SecureLoginConstants.USER_DETAIL_MAP_KEY_USER_REG_NOT_DONE, loginMsg);
		userDetailMap.put(SecureLoginConstants.USER_DETAIL_MAP_KEY_USER_REG_NOT_DONE, loginMsg);
		
		return userDetailMap;
	}
	
	/**
	 * This method used to get label Using Language Properties 
	 * @param locale
	 * @param Object
	 * @param params
	 * @return
	 */
	public static String getLabel(Locale locale,String key,List<Object> params){
		return LanguageUtil.format(locale, key,params);
	}
	
	/**
	 * This method used to get label Using Language Properties 
	 * @param locale
	 * @param Object
	 * @param param
	 * @return
	 */
	public static String getLabel(Locale locale,String key,Object param){
		return LanguageUtil.format(locale, key,param);
	}
	
	/**
	 * This method return uuid 
	 * @return uuid
	 */
	public static String generateKey(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	/**
	 * This method is used to generate random no based on given length
	 * @param length
	 * @return random no.
	 */
	public static String generateRandomNo(int length){
		Random random = new Random();
		char[] charSetAtoZ1to9 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		
	    char[] result = new char[length];
	    for (int index = 0; index < result.length; index++) {
	    	
	        int randomCharIndex = random.nextInt(charSetAtoZ1to9.length);
	        result[index] = charSetAtoZ1to9[randomCharIndex];
	    }
	    return String.valueOf(result);
	}
	
	/**
	 * This method generate QR Code json string based on security token and qr code.
	 * @param secureLogin
	 * @return
	 */
	public static String getQRCodeJSON(SecureLogin secureLogin){
		String qrCodeJson = StringPool.BLANK;
		
		if(Validator.isNotNull(secureLogin)){
			JSONObject jsonObj =  JSONFactoryUtil.createJSONObject();
			  jsonObj.put("securityToken", secureLogin.getEncodeToken());
			  jsonObj.put("qrCode", secureLogin.getQrCodeToken());
			  qrCodeJson = jsonObj.toString();
		}
		return qrCodeJson;
	}
	
	/**
	 * This method is used to generate token to encode QR code.
	 * 
	 * @param userId
	 * @param request
	 * @param response
	 * @param themeDisplay
	 * @param userName
	 * @return isTokenGenerated
	 */
	public static boolean generateTokenToEncodeQRCode(long userId,PortletRequest request,PortletResponse response,ThemeDisplay themeDisplay,String userName){
		boolean isTokenGenerated = false;
		SecureLogin secureLogin = SecureLoginLocalServiceUtil.getSecureLoginByUserId(userId);
		 
		if(Validator.isNotNull(secureLogin)){
			secureLogin.setEncodeToken(SecureLoginHelper.generateRandomNo(15));
			secureLogin.setEncodeTokenDate(new Date());
			secureLogin.setQrCodeTokenDate(new Date());
			try {
				SecureLoginLocalServiceUtil.updateSecureLogin(secureLogin);
				isTokenGenerated = true;
			} catch (SystemException se) {
				LOG.error("System Exception occured while generating token for encode QR code "+se+ " "+se.getMessage());
			}
		}
		
		return isTokenGenerated;
	}
	
	/**
	 * This method will do process for pagination.
	 * @param request
	 * @param model
	 * @param searchTexr
	 */
	public static void processPagination(RenderRequest request, String searchText) {
		int currentPage = ParamUtil.getInteger(request, "cur",1);
		int recPerPage = ParamUtil.getInteger(request, "delta",SecureLoginConstants.DEFAULT_RECORDS_PER_PAGE);
		
		int end = 	currentPage * recPerPage;
		int start = end - recPerPage + 1; 
		
		List<UserSecureLoginVO> userSecureLoginVOLst = SecureLoginHelper.getUserSecureLoginInfo(SecureLoginLocalServiceUtil.getUserSecureLoginInfo(start, end,searchText));
		request.setAttribute("userSecureLoginVOLst",userSecureLoginVOLst);
		request.setAttribute("totalUserSecureRecords",SecureLoginLocalServiceUtil.getUserSecureLoginCount());
	}
	
	
}
