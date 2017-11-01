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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SecureLoginLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SecureLoginLocalService
 * @generated
 */
@ProviderType
public class SecureLoginLocalServiceWrapper implements SecureLoginLocalService,
	ServiceWrapper<SecureLoginLocalService> {
	public SecureLoginLocalServiceWrapper(
		SecureLoginLocalService secureLoginLocalService) {
		_secureLoginLocalService = secureLoginLocalService;
	}

	/**
	* Adds the secure login to the database. Also notifies the appropriate model listeners.
	*
	* @param secureLogin the secure login
	* @return the secure login that was added
	*/
	@Override
	public com.knowarth.security.model.SecureLogin addSecureLogin(
		com.knowarth.security.model.SecureLogin secureLogin) {
		return _secureLoginLocalService.addSecureLogin(secureLogin);
	}

	/**
	* Creates a new secure login with the primary key. Does not add the secure login to the database.
	*
	* @param secureLoginId the primary key for the new secure login
	* @return the new secure login
	*/
	@Override
	public com.knowarth.security.model.SecureLogin createSecureLogin(
		long secureLoginId) {
		return _secureLoginLocalService.createSecureLogin(secureLoginId);
	}

	/**
	* This method will return SecureLogin object for given userId
	*
	* @param userId
	* @param registrationKey
	* @return SecureLogin
	*/
	@Override
	public com.knowarth.security.model.SecureLogin createSecureLogin(
		long userId, java.lang.String registrationKey) {
		return _secureLoginLocalService.createSecureLogin(userId,
			registrationKey);
	}

	/**
	* Deletes the secure login from the database. Also notifies the appropriate model listeners.
	*
	* @param secureLogin the secure login
	* @return the secure login that was removed
	*/
	@Override
	public com.knowarth.security.model.SecureLogin deleteSecureLogin(
		com.knowarth.security.model.SecureLogin secureLogin) {
		return _secureLoginLocalService.deleteSecureLogin(secureLogin);
	}

	/**
	* Deletes the secure login with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param secureLoginId the primary key of the secure login
	* @return the secure login that was removed
	* @throws PortalException if a secure login with the primary key could not be found
	*/
	@Override
	public com.knowarth.security.model.SecureLogin deleteSecureLogin(
		long secureLoginId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _secureLoginLocalService.deleteSecureLogin(secureLoginId);
	}

	@Override
	public com.knowarth.security.model.SecureLogin fetchSecureLogin(
		long secureLoginId) {
		return _secureLoginLocalService.fetchSecureLogin(secureLoginId);
	}

	/**
	* Returns the secure login with the primary key.
	*
	* @param secureLoginId the primary key of the secure login
	* @return the secure login
	* @throws PortalException if a secure login with the primary key could not be found
	*/
	@Override
	public com.knowarth.security.model.SecureLogin getSecureLogin(
		long secureLoginId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _secureLoginLocalService.getSecureLogin(secureLoginId);
	}

	/**
	* This method will returns the secure login instance based on user's email address
	*
	* @param companyId
	* @param emailAddress
	* @return
	*/
	@Override
	public com.knowarth.security.model.SecureLogin getSecureLoginByEmailAddress(
		long companyId, java.lang.String emailAddress) {
		return _secureLoginLocalService.getSecureLoginByEmailAddress(companyId,
			emailAddress);
	}

	/**
	* This method will return SecureLogin object for given Security Key
	*
	* @param registrationKey
	* @return SecureLogin
	*/
	@Override
	public com.knowarth.security.model.SecureLogin getSecureLoginByRegistrationKey(
		java.lang.String registrationKey) {
		return _secureLoginLocalService.getSecureLoginByRegistrationKey(registrationKey);
	}

	/**
	* This method will return SecureLogin object for given userId
	*
	* @param userId
	* @return SecureLogin
	*/
	@Override
	public com.knowarth.security.model.SecureLogin getSecureLoginByUserId(
		long userId) {
		return _secureLoginLocalService.getSecureLoginByUserId(userId);
	}

	/**
	* Updates the secure login in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param secureLogin the secure login
	* @return the secure login that was updated
	*/
	@Override
	public com.knowarth.security.model.SecureLogin updateSecureLogin(
		com.knowarth.security.model.SecureLogin secureLogin) {
		return _secureLoginLocalService.updateSecureLogin(secureLogin);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _secureLoginLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _secureLoginLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _secureLoginLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _secureLoginLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _secureLoginLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of secure logins.
	*
	* @return the number of secure logins
	*/
	@Override
	public int getSecureLoginsCount() {
		return _secureLoginLocalService.getSecureLoginsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _secureLoginLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _secureLoginLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _secureLoginLocalService.dynamicQuery(dynamicQuery, start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _secureLoginLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

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
	@Override
	public java.util.List<com.knowarth.security.model.SecureLogin> getSecureLogins(
		int start, int end) {
		return _secureLoginLocalService.getSecureLogins(start, end);
	}

	/**
	* This method will retun secure login info for all active users
	*
	* @param start
	* @param end
	* @return List<Object[]>
	*/
	@Override
	public java.util.List<java.lang.Object[]> getUserSecureLoginInfo(
		int start, int end, java.lang.String searchText) {
		return _secureLoginLocalService.getUserSecureLoginInfo(start, end,
			searchText);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _secureLoginLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _secureLoginLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* This method will return count of all active users in the system.
	*
	* @return no of active users.
	* @throws SystemException
	*/
	@Override
	public long getUserSecureLoginCount() {
		return _secureLoginLocalService.getUserSecureLoginCount();
	}

	@Override
	public SecureLoginLocalService getWrappedService() {
		return _secureLoginLocalService;
	}

	@Override
	public void setWrappedService(
		SecureLoginLocalService secureLoginLocalService) {
		_secureLoginLocalService = secureLoginLocalService;
	}

	private SecureLoginLocalService _secureLoginLocalService;
}