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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for SecureLogin. This utility wraps
 * {@link com.knowarth.security.service.impl.SecureLoginLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SecureLoginLocalService
 * @see com.knowarth.security.service.base.SecureLoginLocalServiceBaseImpl
 * @see com.knowarth.security.service.impl.SecureLoginLocalServiceImpl
 * @generated
 */
@ProviderType
public class SecureLoginLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.knowarth.security.service.impl.SecureLoginLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the secure login to the database. Also notifies the appropriate model listeners.
	*
	* @param secureLogin the secure login
	* @return the secure login that was added
	*/
	public static com.knowarth.security.model.SecureLogin addSecureLogin(
		com.knowarth.security.model.SecureLogin secureLogin) {
		return getService().addSecureLogin(secureLogin);
	}

	/**
	* Creates a new secure login with the primary key. Does not add the secure login to the database.
	*
	* @param secureLoginId the primary key for the new secure login
	* @return the new secure login
	*/
	public static com.knowarth.security.model.SecureLogin createSecureLogin(
		long secureLoginId) {
		return getService().createSecureLogin(secureLoginId);
	}

	/**
	* This method will return SecureLogin object for given userId
	*
	* @param userId
	* @param registrationKey
	* @return SecureLogin
	*/
	public static com.knowarth.security.model.SecureLogin createSecureLogin(
		long userId, java.lang.String registrationKey) {
		return getService().createSecureLogin(userId, registrationKey);
	}

	/**
	* Deletes the secure login from the database. Also notifies the appropriate model listeners.
	*
	* @param secureLogin the secure login
	* @return the secure login that was removed
	*/
	public static com.knowarth.security.model.SecureLogin deleteSecureLogin(
		com.knowarth.security.model.SecureLogin secureLogin) {
		return getService().deleteSecureLogin(secureLogin);
	}

	/**
	* Deletes the secure login with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param secureLoginId the primary key of the secure login
	* @return the secure login that was removed
	* @throws PortalException if a secure login with the primary key could not be found
	*/
	public static com.knowarth.security.model.SecureLogin deleteSecureLogin(
		long secureLoginId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSecureLogin(secureLoginId);
	}

	public static com.knowarth.security.model.SecureLogin fetchSecureLogin(
		long secureLoginId) {
		return getService().fetchSecureLogin(secureLoginId);
	}

	/**
	* Returns the secure login with the primary key.
	*
	* @param secureLoginId the primary key of the secure login
	* @return the secure login
	* @throws PortalException if a secure login with the primary key could not be found
	*/
	public static com.knowarth.security.model.SecureLogin getSecureLogin(
		long secureLoginId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSecureLogin(secureLoginId);
	}

	/**
	* This method will returns the secure login instance based on user's email address
	*
	* @param companyId
	* @param emailAddress
	* @return
	*/
	public static com.knowarth.security.model.SecureLogin getSecureLoginByEmailAddress(
		long companyId, java.lang.String emailAddress) {
		return getService().getSecureLoginByEmailAddress(companyId, emailAddress);
	}

	/**
	* This method will return SecureLogin object for given Security Key
	*
	* @param registrationKey
	* @return SecureLogin
	*/
	public static com.knowarth.security.model.SecureLogin getSecureLoginByRegistrationKey(
		java.lang.String registrationKey) {
		return getService().getSecureLoginByRegistrationKey(registrationKey);
	}

	/**
	* This method will return SecureLogin object for given userId
	*
	* @param userId
	* @return SecureLogin
	*/
	public static com.knowarth.security.model.SecureLogin getSecureLoginByUserId(
		long userId) {
		return getService().getSecureLoginByUserId(userId);
	}

	/**
	* Updates the secure login in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param secureLogin the secure login
	* @return the secure login that was updated
	*/
	public static com.knowarth.security.model.SecureLogin updateSecureLogin(
		com.knowarth.security.model.SecureLogin secureLogin) {
		return getService().updateSecureLogin(secureLogin);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of secure logins.
	*
	* @return the number of secure logins
	*/
	public static int getSecureLoginsCount() {
		return getService().getSecureLoginsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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
	public static java.util.List<com.knowarth.security.model.SecureLogin> getSecureLogins(
		int start, int end) {
		return getService().getSecureLogins(start, end);
	}

	/**
	* This method will retun secure login info for all active users
	*
	* @param start
	* @param end
	* @return List<Object[]>
	*/
	public static java.util.List<java.lang.Object[]> getUserSecureLoginInfo(
		int start, int end, java.lang.String searchText) {
		return getService().getUserSecureLoginInfo(start, end, searchText);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* This method will return count of all active users in the system.
	*
	* @return no of active users.
	* @throws SystemException
	*/
	public static long getUserSecureLoginCount() {
		return getService().getUserSecureLoginCount();
	}

	public static SecureLoginLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SecureLoginLocalService, SecureLoginLocalService> _serviceTracker =
		ServiceTrackerFactory.open(SecureLoginLocalService.class);
}