/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.knowarth.security.service.impl;


import java.util.List;

import com.knowarth.security.exception.NoSuchSecureLoginException;
import com.knowarth.security.model.SecureLogin;
import com.knowarth.security.service.base.SecureLoginLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the secure login local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.knowarth.security.service.SecureLoginLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SecureLoginLocalServiceBaseImpl
 * @see com.knowarth.security.service.SecureLoginLocalServiceUtil
 */
@ProviderType
public class SecureLoginLocalServiceImpl extends SecureLoginLocalServiceBaseImpl {
private final Log _log = LogFactoryUtil.getLog(SecureLoginLocalServiceImpl.class.getName());
	
	/**
	 * This method will return SecureLogin object for given Security Key
	 * @param registrationKey
	 * @return SecureLogin
	 */
	public SecureLogin getSecureLoginByRegistrationKey(String registrationKey){
		SecureLogin secureLogin = null;
		try {
			secureLogin = this.secureLoginPersistence.findByRegistrationKey(registrationKey);
		} catch (NoSuchSecureLoginException e) {
			_log.error("No secure login found with key "+registrationKey+" "+e);
		} catch (SystemException e) {
			_log.error("error occured while getting secure login by registration key "+registrationKey +" "+e+" "+e.getMessage());	
		}
		return secureLogin;
	}
	
	/**
	 * This method will returns the secure login instance based on user's email address
	 * @param companyId
	 * @param emailAddress
	 * @return
	 */
	public SecureLogin getSecureLoginByEmailAddress(long companyId,String emailAddress){
		SecureLogin secureLogin = null;
		
		try {
			long userId = UserLocalServiceUtil.getUserIdByEmailAddress(companyId, emailAddress);
			if(userId >0){
				secureLogin = secureLoginLocalService.getSecureLoginByUserId(userId);
			}
		} catch (PortalException pe) {
			_log.error("error occured while fetching user "+pe);
		} catch (SystemException se) {
			_log.error("error occured while fetching user "+se);
		}
		
		return secureLogin;
	}
	
	/**
	 * This method will return SecureLogin object for given userId
	 * @param userId
	 * @return SecureLogin
	 */
	public SecureLogin getSecureLoginByUserId(long userId){
		SecureLogin secureLogin = null;
		try {
			secureLogin =  this.secureLoginPersistence.findBySecureLoginByUserId(userId);
		}catch (NoSuchSecureLoginException e) {
			_log.debug("No secure login found with user id "+userId+" "+e);
		} catch (SystemException e) {
			_log.error("error occured while getting secure login by registration user id "+userId +" "+e+" "+e.getMessage());	
		}
		return secureLogin;
	}
	
	/**
	 * This method will return SecureLogin object for given userId
	 * @param userId
	 * @param registrationKey
	 * @return SecureLogin
	 */
	public SecureLogin createSecureLogin(long userId,String registrationKey){
		SecureLogin secureLogin = null;
		try {
			secureLogin =  this.secureLoginPersistence.findBySecureLoginByUserId(userId);
		}catch (Exception e) {
			_log.debug("No secure login found with user id "+userId+" "+e);
		}
		
		if(Validator.isNull(secureLogin)){
			try {
				secureLogin = secureLoginLocalService.createSecureLogin(counterLocalService.increment(SecureLogin.class.getName()));
				secureLogin.setUserId(userId);
				secureLogin.setRegistrationKey(registrationKey);
			
				secureLogin = secureLoginLocalService.updateSecureLogin(secureLogin);
			} catch (SystemException e) {
				_log.debug("Error occured during create secure login with user id "+userId+" "+e);
			}
		}
		return secureLogin;
	}	
	
	/**
	 * This method will retun secure login info for all active users
	 * @param start
	 * @param end
	 * @return List<Object[]>
	 */
	public List<Object[]> getUserSecureLoginInfo(int start, int end,String searchText){
		return secureLoginFinder.getUserWithSecureLoginRegKey(start, end,searchText);
	}
	
	/**
	 * This method will return count of all active users in the system.
	 * @return no of active users.
	 * @throws SystemException
	 */
	public long getUserSecureLoginCount(){
		long userSecureLoginCount=0;
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class,PortalClassLoaderUtil.getClassLoader());
		Criterion criteria = RestrictionsFactoryUtil.eq("status",0);
		dynamicQuery.add(criteria);
		try {
			userSecureLoginCount =  userLocalService.dynamicQueryCount(dynamicQuery);
		} catch (SystemException e) {
			_log.error("Error occured while getting total user secure login count "+e+" "+e.getMessage());
		}
		return userSecureLoginCount;
	}	
}