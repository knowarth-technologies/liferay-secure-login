package com.knowarth.security.portlet;

import com.google.zxing.WriterException;
import com.knowarth.security.constants.DeviceRegistrationPortletKeys;
import com.knowarth.security.model.SecureLogin;
import com.knowarth.security.service.SecureLoginLocalServiceUtil;
import com.knowarth.security.util.DeviceRegistrationConstants;
import com.knowarth.security.util.DeviceRegistrationHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.PasswordTrackerLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.OutputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author rajan.bhatt
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Device Registration Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + DeviceRegistrationPortletKeys.DeviceRegistration,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class DeviceRegistrationPortlet extends MVCPortlet {
	private static final Log LOG = LogFactoryUtil.getLog(DeviceRegistrationPortlet.class.getName());
	  @Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		  long userId = PortalUtil.getUserId(renderRequest);
		  
		  String authType = CompanyConstants.AUTH_TYPE_EA;
			
			try {
				authType = PortalUtil.getCompany(renderRequest).getAuthType();
			} catch (SystemException se) {
				LOG.error("error occured while getting auth type "+se+" "+se.getMessage());
			} catch (PortalException pe) {
				LOG.error("error occured while getting auth type "+pe+" "+pe.getMessage());
			}
		  
		  renderRequest.setAttribute("authType", authType);
		  String returnPage = ParamUtil.getString(renderRequest, "jspPage",StringPool.BLANK);
		  if(userId > 0){ //logged in user
			  renderRequest.setAttribute("userId", userId);
			  renderRequest.setAttribute("isValidCredentials", Boolean.TRUE.booleanValue());
			  this.viewTemplate = "/"+DeviceRegistrationConstants.PAGE_DEVICE_REG_QR_CODE+".jsp";
		  }else{ // non logged in user
			  if(StringPool.BLANK.equalsIgnoreCase(returnPage)){
				  renderRequest.setAttribute("isValidCredentials", Boolean.FALSE.booleanValue());
				  this.viewTemplate = "/"+DeviceRegistrationConstants.PAGE_DEVICE_REG_CREDENTIALS+".jsp";
			  }else{
				  this.viewTemplate = returnPage;
			  }
		  }
		  
		super.render(renderRequest, renderResponse);
	  }
	  
	  @Override
	  public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {

	    String registrationKey = StringPool.BLANK;
	    SecureLogin secureLogin = null;
	    long userId = ParamUtil.getLong(resourceRequest, "userId",0l);
	    
	    User user = null;
	    
	    if(userId >0){
	    	try {
				user = UserLocalServiceUtil.getUser(userId);
			} catch (PortalException pe) {
				LOG.error("Error occured while getting user object from userId "+pe.getMessage()+pe);
			} catch (SystemException se) {
				LOG.error("Error occured while getting user object from userId "+se.getMessage()+se);
			}
	    }

	    if (Validator.isNotNull(user)) {
	    		secureLogin = SecureLoginLocalServiceUtil.getSecureLoginByUserId(user.getUserId());
		    	if (Validator.isNull(secureLogin) || Validator.isNotNull(secureLogin.getRegistrationKey())) {
		    		registrationKey = DeviceRegistrationHelper.generateKey();
		    		secureLogin = SecureLoginLocalServiceUtil.createSecureLogin(user.getUserId(),registrationKey);
		    	} 
		    	
		    	registrationKey = getDeviceRegistrationDetail(secureLogin, resourceRequest);
		    	
		      try {
		    	  resourceResponse.setContentType("image/png");
		    	  resourceResponse.setProperty("Pragma", "no-cache");
		    	  resourceResponse.setProperty("Cache-Control", "no-cache");
		    	  OutputStream out = resourceResponse.getPortletOutputStream();
		    	  
		    	  byte[] bytes = DeviceRegistrationHelper.generateQRCode(registrationKey);
		    	  out.write(bytes);
		    	  out.flush();
		      } catch (WriterException e) {
		    	  LOG.error("Writer Exception occured while generating QR code" + e);
		      }
	    }
	  }
	  
	  /**
	   * This method generate registration detail that includes host and user registration key.
	   * @param secureLogin
	   * @return registration detail - registration key and host name
	   */
	  private String getDeviceRegistrationDetail(SecureLogin secureLogin,ResourceRequest resourceRequest){
		  String registrationDetail = StringPool.BLANK;
		  
		  if(Validator.isNotNull(secureLogin)){
			  String regKey = secureLogin.getRegistrationKey();
			  String host = PortalUtil.getPortalURL(resourceRequest);
			  long userId = secureLogin.getUserId();
			  
			  JSONObject jsonObj =  JSONFactoryUtil.createJSONObject();
			  jsonObj.put("regKey", regKey);
			  jsonObj.put("host", host);
			  if(userId >0){
				  try {
					User user = UserLocalServiceUtil.getUser(userId);
					if(Validator.isNotNull(user)){
						jsonObj.put("emailAddress", user.getEmailAddress());
					}
				} catch (PortalException pe) {
					LOG.error("error occured while fetching user "+pe);
				} catch (SystemException se) {
					LOG.error("error occured while fetching user "+se);
				}
			  }
			  registrationDetail = jsonObj.toString();
		  }
		  
		  return registrationDetail;
	  }
	  
	  public void processRegistration(ActionRequest request,ActionResponse response){
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			String userName = ParamUtil.get(request,"userName",StringPool.BLANK);
			String password = ParamUtil.get(request, "password",StringPool.BLANK);
			
			String returnPage = StringPool.BLANK;
			String userNotExistError = StringPool.BLANK;
			String authType = CompanyConstants.AUTH_TYPE_EA;
			
			try {
				authType = themeDisplay.getCompany().getAuthType();
			} catch (SystemException e) {
				LOG.error("error occured while getting auth type "+e+" "+e.getMessage());
			}
			
			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				userNotExistError= DeviceRegistrationConstants.LABEL_USER_NOT_EXIST_EA;
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				userNotExistError= DeviceRegistrationConstants.LABEL_USER_NOT_EXIST_SN;
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				userNotExistError= DeviceRegistrationConstants.LABEL_USER_NOT_EXIST_ID;
			}
			
			if(Validator.isNotNull(userName) && Validator.isNotNull(password)){
				User user = DeviceRegistrationHelper.getUser(userName, themeDisplay.getCompany());
				
				boolean validUser =false;
				if(Validator.isNotNull(user)){
					try {
						validUser = PasswordTrackerLocalServiceUtil.isSameAsCurrentPassword(user.getUserId(),password);
					} catch (PortalException e) {
						LOG.error("PortalException occured while checking the user password "+e.getMessage()+e);
					} catch (SystemException e) {
						LOG.error("SystemException occured while checking the user password "+e.getMessage()+e);
					}
					
					if(validUser){//User is available
						request.setAttribute("userId", user.getUserId());
						request.setAttribute("isValidCredentials", Boolean.TRUE.booleanValue());
						returnPage =DeviceRegistrationConstants.PAGE_DEVICE_REG_QR_CODE;
					}else{//Not a valid user
						request.setAttribute("authType",authType);
						SessionErrors.add(request, "invalid-credential");
						request.setAttribute("errorMessage",DeviceRegistrationConstants.LABEL_USER_CREDENTIALS_INVALID );
						returnPage = DeviceRegistrationConstants.PAGE_DEVICE_REG_CREDENTIALS;
					}
					
				}else{
					request.setAttribute("authType",authType);
					SessionErrors.add(request, "invalid-credential");
					request.setAttribute("errorMessage",LanguageUtil.format(themeDisplay.getLocale(),LanguageUtil.get(PortalUtil.getHttpServletRequest(request),userNotExistError),new Object[]{userName}));
					returnPage = DeviceRegistrationConstants.PAGE_DEVICE_REG_CREDENTIALS;
				}
			}else{
				request.setAttribute("authType",authType);
				SessionErrors.add(request, "invalid-credential");
				request.setAttribute("errorMessage",DeviceRegistrationConstants.LABEL_USER_NAME_PASS_MSG);
				returnPage=DeviceRegistrationConstants.PAGE_DEVICE_REG_CREDENTIALS;
			}
			
			// Hide default Error Message
			SessionErrors.add(request, "error-key");
			SessionMessages.add(request, PortalUtil.getPortletId(request) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			// Hide default Success Message
			SessionMessages.add(request, PortalUtil.getPortletId(request) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);
			
			response.setRenderParameter("jspPage", "/"+returnPage+".jsp");
		}  
}