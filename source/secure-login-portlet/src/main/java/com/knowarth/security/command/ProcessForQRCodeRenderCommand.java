package com.knowarth.security.command;

import com.knowarth.security.constants.SecureLoginRegistrationPortletKeys;
import com.knowarth.security.util.SecureLoginConstants;
import com.knowarth.security.util.SecureLoginHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.PasswordTrackerLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		 property = {
						 "javax.portlet.name="+SecureLoginRegistrationPortletKeys.SECURE_LOGIN_RENDER_COMMAND,
						 "mvc.command.name=processForQRCode"
			 		}, service = MVCRenderCommand.class
		  )
public class ProcessForQRCodeRenderCommand implements MVCRenderCommand{
	private final Log LOG = LogFactoryUtil.getLog(ProcessForQRCodeRenderCommand.class.getName());
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String userName = ParamUtil.get(renderRequest,SecureLoginConstants.USER_NAME,StringPool.BLANK);
		String password = ParamUtil.get(renderRequest, SecureLoginConstants.PASSWORD,StringPool.BLANK);
		String userErrorLabel = StringPool.BLANK;
		String userNotRegLabel = StringPool.BLANK;
		String authType = CompanyConstants.AUTH_TYPE_EA;
		
		try {
			authType = themeDisplay.getCompany().getAuthType();
		} catch (SystemException e) {
			LOG.error("error occured while getting auth type "+e+" "+e.getMessage());
		}
		
		if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
			userErrorLabel= SecureLoginConstants.LABEL_CUSTOM_SECURE_LOGIN_EA;
			userNotRegLabel = SecureLoginConstants.LABEL_CUSTOM_NOT_REGISTER_LOGIN_EA;;
		}
		else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
			userErrorLabel= SecureLoginConstants.LABEL_CUSTOM_SECURE_LOGIN_SN;
			userNotRegLabel = SecureLoginConstants.LABEL_CUSTOM_NOT_REGISTER_LOGIN_SN;
		}
		else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
			userErrorLabel= SecureLoginConstants.LABEL_CUSTOM_SECURE_LOGIN_ID;
			userNotRegLabel = SecureLoginConstants.LABEL_CUSTOM_NOT_REGISTER_LOGIN_ID;
		}
		
		// Hide default Error Message
		SessionErrors.add(renderRequest, "error-key");
		SessionMessages.add(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
		// Hide default Success Message
		SessionMessages.add(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);
		
		if(Validator.isNotNull(userName) && Validator.isNotNull(password)){
			User user = SecureLoginHelper.getUser(userName, themeDisplay.getCompany());
			
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
					if(SecureLoginHelper.generateTokenToEncodeQRCode(user.getUserId(),renderRequest,renderResponse,themeDisplay,userName)){
						renderRequest.setAttribute("validUser", Boolean.TRUE);
						renderRequest.setAttribute(SecureLoginConstants.USER_NAME, userName);
						renderRequest.setAttribute("authType", authType);
						renderRequest.setAttribute("userId", user.getUserId());
						renderRequest.setAttribute("refreshPageUrl", themeDisplay.getURLHome());
					}else{
						renderRequest.setAttribute("errorMessage",SecureLoginHelper.getLabel(themeDisplay.getLocale(),userNotRegLabel,userName));
						SessionErrors.add(renderRequest,"error.reg.key.not.present");
					}
					return "/"+SecureLoginConstants.PAGE_USER_SECURE_LOGIN_QR_CODE+ ".jsp";
					
				}else{//Not a valid user
					renderRequest.setAttribute("authType",authType);
					renderRequest.setAttribute("isLoggedIn",false);
					SessionErrors.add(renderRequest, "invalid-credential");
					renderRequest.setAttribute("errorMessage",userErrorLabel);
					return "/"+SecureLoginConstants.PAGE_USER_SECURE_LOGIN_USER_NAME+ ".jsp";
				}
				
			}else{
				renderRequest.setAttribute("authType",authType);
				SessionErrors.add(renderRequest, "invalid-credential");
				renderRequest.setAttribute("errorMessage",userErrorLabel);
				renderRequest.setAttribute("isLoggedIn",false);
				return "/"+SecureLoginConstants.PAGE_USER_SECURE_LOGIN_USER_NAME+ ".jsp";
			}
		}else{
			renderRequest.setAttribute("authType",authType);
			SessionErrors.add(renderRequest, "invalid-credential");
			renderRequest.setAttribute("errorMessage","user.name.password.msg");
			renderRequest.setAttribute("isLoggedIn",false);
			return "/"+SecureLoginConstants.PAGE_USER_SECURE_LOGIN_USER_NAME+ ".jsp";
		}
	}

}
