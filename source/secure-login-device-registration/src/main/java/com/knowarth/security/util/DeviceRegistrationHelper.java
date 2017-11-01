package com.knowarth.security.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

public class DeviceRegistrationHelper {

	private static final Log LOG = LogFactoryUtil.getLog(DeviceRegistrationHelper.class.getName());
	
	private DeviceRegistrationHelper(){
		
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
	 * This method return uuid 
	 * @return uuid
	 */
	public static String generateKey(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	
}
