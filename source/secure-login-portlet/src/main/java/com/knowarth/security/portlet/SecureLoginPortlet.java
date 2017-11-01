package com.knowarth.security.portlet;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.knowarth.security.constants.SecureLoginRegistrationPortletKeys;
import com.knowarth.security.model.SecureLogin;
import com.knowarth.security.service.SecureLoginLocalServiceUtil;
import com.knowarth.security.util.SecureLoginConstants;
import com.knowarth.security.util.SecureLoginHelper;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author rajan.bhatt
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Secure Login Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.name=" + SecureLoginRegistrationPortletKeys.SECURE_LOGIN_RENDER_COMMAND,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class SecureLoginPortlet extends MVCPortlet {
	private final Log LOG = LogFactoryUtil.getLog(SecureLoginPortlet.class.getName());
	
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String authType = CompanyConstants.AUTH_TYPE_EA;

		User loggedInUser = themeDisplay.getUser();
		if(Validator.isNull(loggedInUser) || loggedInUser.isDefaultUser()==true){
			try {
				authType = themeDisplay.getCompany().getAuthType();
			} catch (SystemException e) {
				LOG.error("error occured while getting auth type "+e+" "+e.getMessage());
			}

			String keyForUserNameAndPassword=StringPool.BLANK;

			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				keyForUserNameAndPassword= SecureLoginConstants.LABEL_CUSTOM_SECURE_LOGIN_EA;
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				keyForUserNameAndPassword= SecureLoginConstants.LABEL_CUSTOM_SECURE_LOGIN_SN;
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				keyForUserNameAndPassword= SecureLoginConstants.LABEL_CUSTOM_SECURE_LOGIN_ID;
			}
			
			String msgForUserName = SecureLoginHelper.getLabel(themeDisplay.getLocale(),keyForUserNameAndPassword,"");
			
			renderRequest.setAttribute("authType",authType);
			renderRequest.setAttribute("msgForUserName",msgForUserName);
			renderRequest.setAttribute("isLoggedIn",Boolean.FALSE.booleanValue());
		}else{
			renderRequest.setAttribute("userFullName",loggedInUser.getFullName());
			renderRequest.setAttribute("isLoggedIn",Boolean.TRUE.booleanValue());
		}
		this.viewTemplate = "/"+SecureLoginConstants.PAGE_USER_SECURE_LOGIN_USER_NAME+".jsp";
		super.render(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		String action = ParamUtil.getString(resourceRequest, "action");
		long userId=0L;
		String userName = ParamUtil.get(resourceRequest, SecureLoginConstants.USER_NAME, "");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User user = SecureLoginHelper.getUser(userName, themeDisplay.getCompany());
		if(Validator.isNotNull(user)){
			userId= user.getUserId();
		}
		SecureLogin secureLogin  = SecureLoginLocalServiceUtil.getSecureLoginByUserId(userId);
		
		if(SecureLoginConstants.CHECK_TOKEN_IS_VALIDATED.equalsIgnoreCase(action)){
			JSONObject returnMsg = JSONFactoryUtil.createJSONObject();
			
			if(Validator.isNotNull(secureLogin)){
				Date currentDate = new Date();
				Date qrCodeTokenDate = secureLogin.getQrCodeTokenDate();
				int secondElapsedAfterQRCodeGenerate = 0;
				if(Validator.isNotNull(qrCodeTokenDate)){
					secondElapsedAfterQRCodeGenerate =(int)(currentDate.getTime() - qrCodeTokenDate.getTime())/1000;
				}
				if(Boolean.TRUE.toString().equalsIgnoreCase(secureLogin.getTokenValidated())){
					if(secondElapsedAfterQRCodeGenerate > SecureLoginConstants.NO_OF_SECOND_QR_TOKEN_IS_VALID){
						returnMsg.put(SecureLoginConstants.IS_SECURE_LOGIN_TOKEN_VALIDATED, Boolean.FALSE.booleanValue());
						returnMsg.put(SecureLoginConstants.DO_POLLING, Boolean.FALSE.booleanValue());
						returnMsg.put(SecureLoginConstants.LOGIN_MSG,LanguageUtil.get(PortalUtil.getHttpServletRequest(resourceRequest), "error.polling.token.expire"));
					}else{
						returnMsg.put(SecureLoginConstants.IS_SECURE_LOGIN_TOKEN_VALIDATED, Boolean.TRUE.booleanValue());
						returnMsg.put(SecureLoginConstants.DO_POLLING, Boolean.FALSE.booleanValue());
						returnMsg.put(SecureLoginConstants.LOGIN_MSG,LanguageUtil.get(PortalUtil.getHttpServletRequest(resourceRequest), "polling.token.validated.successfully"));
					}
				}else{
					if(secondElapsedAfterQRCodeGenerate > SecureLoginConstants.NO_OF_SECOND_QR_TOKEN_IS_VALID){
						returnMsg.put(SecureLoginConstants.IS_SECURE_LOGIN_TOKEN_VALIDATED, Boolean.FALSE.booleanValue());
						returnMsg.put(SecureLoginConstants.DO_POLLING, Boolean.FALSE.booleanValue());
						returnMsg.put(SecureLoginConstants.LOGIN_MSG,LanguageUtil.get(PortalUtil.getHttpServletRequest(resourceRequest), "error.polling.token.expire"));
					}else{
						returnMsg.put(SecureLoginConstants.IS_SECURE_LOGIN_TOKEN_VALIDATED, Boolean.FALSE.booleanValue());
						returnMsg.put(SecureLoginConstants.DO_POLLING, Boolean.TRUE.booleanValue());
						returnMsg.put(SecureLoginConstants.LOGIN_MSG,"");
					}
				}
			}else{
				returnMsg.put(SecureLoginConstants.IS_SECURE_LOGIN_TOKEN_VALIDATED, Boolean.FALSE.booleanValue());
				returnMsg.put(SecureLoginConstants.DO_POLLING, Boolean.FALSE.booleanValue());
				returnMsg.put(SecureLoginConstants.LOGIN_MSG,"Not a valid user.");
			}
			
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(returnMsg.toString());
		}else if(SecureLoginConstants.GENERATE_QR_CODE.equalsIgnoreCase(action)){
			String qrCodeStr = SecureLoginHelper.generateRandomNo(25);
			if(Validator.isNotNull(user)){
			
				secureLogin.setQrCodeToken(qrCodeStr);
				secureLogin.setQrCodeTokenDate(new Date());
			
				try {
					SecureLoginLocalServiceUtil.updateSecureLogin(secureLogin);
					File file = FileUtil.createTempFile();
					
					Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
					hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
					
					// Now with zxing version 3.2.1 you could change border size (white border size to just 1)
					hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
					hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		 
					QRCodeWriter qrCodeWriter = new QRCodeWriter();
					BitMatrix byteMatrix = qrCodeWriter.encode(SecureLoginHelper.getQRCodeJSON(secureLogin), BarcodeFormat.QR_CODE, 250,250, hintMap);
					int crunchifyWidth = byteMatrix.getWidth();
					BufferedImage image = new BufferedImage(crunchifyWidth, crunchifyWidth,BufferedImage.TYPE_INT_RGB);
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
					resourceResponse.setContentType("image/png"); 
					resourceResponse.setProperty("Pragma", "no-cache"); 
					resourceResponse.setProperty("Cache-Control", "no-cache"); 
					OutputStream out = resourceResponse.getPortletOutputStream();
					ImageIO.write(image, "png", file);
					
					byte[] bytes = FileUtil.getBytes(file);
					out.write(bytes);
					out.flush();
					FileUtil.delete(file);
				} catch (WriterException we) {
					LOG.error("Writer Exception occured while generating QR code for user name "+userName+" "+we);
				} catch (IOException ioe) {
					LOG.error("IOException occured while generating QR code for user name "+userName+" "+ioe);
				}catch (SystemException se) {
					LOG.error("SystemException occured while generating QR code for user name "+userName+" "+se);
				}
			}
		}
	}
}