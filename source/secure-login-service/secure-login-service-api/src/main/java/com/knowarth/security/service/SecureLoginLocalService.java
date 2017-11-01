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

package com.knowarth.security.service;

import aQute.bnd.annotation.ProviderType;

import com.knowarth.security.model.SecureLogin;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for SecureLogin. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see SecureLoginLocalServiceUtil
 * @see com.knowarth.security.service.base.SecureLoginLocalServiceBaseImpl
 * @see com.knowarth.security.service.impl.SecureLoginLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SecureLoginLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SecureLoginLocalServiceUtil} to access the secure login local service. Add custom service methods to {@link com.knowarth.security.service.impl.SecureLoginLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the secure login to the database. Also notifies the appropriate model listeners.
	*
	* @param secureLogin the secure login
	* @return the secure login that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SecureLogin addSecureLogin(SecureLogin secureLogin);

	/**
	* Creates a new secure login with the primary key. Does not add the secure login to the database.
	*
	* @param secureLoginId the primary key for the new secure login
	* @return the new secure login
	*/
	public SecureLogin createSecureLogin(long secureLoginId);

	/**
	* This method will return SecureLogin object for given userId
	*
	* @param userId
	* @param registrationKey
	* @return SecureLogin
	*/
	public SecureLogin createSecureLogin(long userId,
		java.lang.String registrationKey);

	/**
	* Deletes the secure login from the database. Also notifies the appropriate model listeners.
	*
	* @param secureLogin the secure login
	* @return the secure login that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public SecureLogin deleteSecureLogin(SecureLogin secureLogin);

	/**
	* Deletes the secure login with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param secureLoginId the primary key of the secure login
	* @return the secure login that was removed
	* @throws PortalException if a secure login with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public SecureLogin deleteSecureLogin(long secureLoginId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SecureLogin fetchSecureLogin(long secureLoginId);

	/**
	* Returns the secure login with the primary key.
	*
	* @param secureLoginId the primary key of the secure login
	* @return the secure login
	* @throws PortalException if a secure login with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SecureLogin getSecureLogin(long secureLoginId)
		throws PortalException;

	/**
	* This method will returns the secure login instance based on user's email address
	*
	* @param companyId
	* @param emailAddress
	* @return
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SecureLogin getSecureLoginByEmailAddress(long companyId,
		java.lang.String emailAddress);

	/**
	* This method will return SecureLogin object for given Security Key
	*
	* @param registrationKey
	* @return SecureLogin
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SecureLogin getSecureLoginByRegistrationKey(
		java.lang.String registrationKey);

	/**
	* This method will return SecureLogin object for given userId
	*
	* @param userId
	* @return SecureLogin
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SecureLogin getSecureLoginByUserId(long userId);

	/**
	* Updates the secure login in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param secureLogin the secure login
	* @return the secure login that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SecureLogin updateSecureLogin(SecureLogin secureLogin);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of secure logins.
	*
	* @return the number of secure logins
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSecureLoginsCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.knowarth.security.model.impl.SecureLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.knowarth.security.model.impl.SecureLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns a range of all the secure logins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.knowarth.security.model.impl.SecureLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of secure logins
	* @param end the upper bound of the range of secure logins (not inclusive)
	* @return the range of secure logins
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SecureLogin> getSecureLogins(int start, int end);

	/**
	* This method will retun secure login info for all active users
	*
	* @param start
	* @param end
	* @return List<Object[]>
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<java.lang.Object[]> getUserSecureLoginInfo(int start, int end,
		java.lang.String searchText);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	/**
	* This method will return count of all active users in the system.
	*
	* @return no of active users.
	* @throws SystemException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getUserSecureLoginCount();
}