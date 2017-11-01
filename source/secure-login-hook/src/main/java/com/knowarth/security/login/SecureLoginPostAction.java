package com.knowarth.security.login;

import org.osgi.service.component.annotations.Component;

import com.knowarth.security.model.SecureLogin;
import com.knowarth.security.service.SecureLoginLocalServiceUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;


@Component(
		immediate = true, property = {"key=login.events.post"},
		service = LifecycleAction.class
	)


public class SecureLoginPostAction implements LifecycleAction{
	private static final Log LOG = LogFactoryUtil.getLog(SecureLoginPostAction.class.getName());
	
	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {
		
		User user = null;
		try {
			user = PortalUtil.getUser(lifecycleEvent.getRequest());
		} catch (PortalException pe) {
			LOG.error("error occurd while getting user after post login "+pe);
		} catch (SystemException se) {
			LOG.error("error occurd while getting user after post login "+se);
		}
		
		if(Validator.isNotNull(user)){
			try {
				SecureLogin secureLogin = SecureLoginLocalServiceUtil.getSecureLoginByUserId(user.getUserId());
				if(Validator.isNotNull(secureLogin)){
					secureLogin.setQrCodeToken(null);
					secureLogin.setQrCodeTokenDate(null);
					secureLogin.setEncodeToken(null);
					secureLogin.setEncodeTokenDate(null);
					secureLogin.setTokenValidated(null);
					
					SecureLoginLocalServiceUtil.updateSecureLogin(secureLogin);
				}
			} catch (SystemException se) {
				LOG.error("error occurd while getting secure login "+se);
			}
		}
		
	}

}
