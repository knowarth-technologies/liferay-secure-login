package com.knowarth.security.login;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.SystemException;
import org.osgi.service.component.annotations.Component;

import com.knowarth.security.model.SecureLogin;
import com.knowarth.security.service.SecureLoginLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auto.login.AutoLogin;
import com.liferay.portal.kernel.security.auto.login.BaseAutoLogin;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author darshan.gandhi
 */
@Component(immediate = true, service = AutoLogin.class)
public class SecureAutoLogin extends BaseAutoLogin {
	private static final Log LOG = LogFactoryUtil.getLog(SecureAutoLogin.class.getName());
	private static final int NO_OF_SECOND_QR_TOKEN_IS_VALID=60;
	
	@Override
	protected String[] doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] credentials = null;
		User user = null;
		String userName = ParamUtil.get(request, "userName", StringPool.BLANK);
		if(Validator.isNotNull(userName)){
			user = getUser(userName,request);
		}
		if(Validator.isNotNull(user)){
			long userId= user.getUserId();
			SecureLogin secureLogin = SecureLoginLocalServiceUtil.getSecureLoginByUserId(userId);
			if(Validator.isNotNull(secureLogin)){
				Date currentDate = new Date();
				Date qrCodeTokenDate = secureLogin.getQrCodeTokenDate();
				int secondElapsedAfterQRCodeGenerate = 0;
				if(Validator.isNotNull(qrCodeTokenDate)){
					secondElapsedAfterQRCodeGenerate =(int)(currentDate.getTime() - qrCodeTokenDate.getTime())/1000;
				}
				if(secondElapsedAfterQRCodeGenerate < NO_OF_SECOND_QR_TOKEN_IS_VALID && Boolean.TRUE.toString().equalsIgnoreCase(secureLogin.getTokenValidated())){
					credentials = new String[] {String.valueOf(user.getUserId()), user.getPassword(), Boolean.FALSE.toString()
					};
				}
			}
		}
		return credentials;
	}
	
	/**
	 * This method will be used to get user from given userName
	 * @param userName
	 * @param company
	 * @return User
	 */
	private User getUser(String userName,HttpServletRequest request){
		User validUser = null;
		long companyId=0L;
		String authType = CompanyConstants.AUTH_TYPE_EA;
		try {
			Company company = PortalUtil.getCompany(request);
			companyId = company.getCompanyId();
			authType = company.getAuthType();
		} catch (SystemException e) {
			LOG.error("error occured while getting auth type "+e+" "+e.getMessage());
		}catch (PortalException e) {
			LOG.error("error occured while getting auth type "+e+" "+e.getMessage());
		}
		try{
			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				validUser = UserLocalServiceUtil.getUserByEmailAddress(companyId, userName);
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				validUser = UserLocalServiceUtil.getUserByScreenName(companyId, userName);
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				long userId = GetterUtil.getLong(userName);
				validUser = UserLocalServiceUtil.getUser(userId);
			}
		}catch(SystemException se){
			LOG.error("error occured while verifing user based on user name "+userName+" "+se);
		}catch(PortalException pe){
			LOG.error("error occured while verifing user based on user name "+userName+" "+pe);
		}
		return validUser;
	}

}