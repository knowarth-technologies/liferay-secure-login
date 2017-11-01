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

package com.knowarth.security.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.knowarth.security.model.SecureLogin;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the secure login service. This utility wraps {@link com.knowarth.security.service.persistence.impl.SecureLoginPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SecureLoginPersistence
 * @see com.knowarth.security.service.persistence.impl.SecureLoginPersistenceImpl
 * @generated
 */
@ProviderType
public class SecureLoginUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(SecureLogin secureLogin) {
		getPersistence().clearCache(secureLogin);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SecureLogin> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SecureLogin> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SecureLogin> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SecureLogin> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SecureLogin update(SecureLogin secureLogin) {
		return getPersistence().update(secureLogin);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SecureLogin update(SecureLogin secureLogin,
		ServiceContext serviceContext) {
		return getPersistence().update(secureLogin, serviceContext);
	}

	/**
	* Returns the secure login where userId = &#63; or throws a {@link NoSuchSecureLoginException} if it could not be found.
	*
	* @param userId the user ID
	* @return the matching secure login
	* @throws NoSuchSecureLoginException if a matching secure login could not be found
	*/
	public static SecureLogin findBySecureLoginByUserId(long userId)
		throws com.knowarth.security.exception.NoSuchSecureLoginException {
		return getPersistence().findBySecureLoginByUserId(userId);
	}

	/**
	* Returns the secure login where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @return the matching secure login, or <code>null</code> if a matching secure login could not be found
	*/
	public static SecureLogin fetchBySecureLoginByUserId(long userId) {
		return getPersistence().fetchBySecureLoginByUserId(userId);
	}

	/**
	* Returns the secure login where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching secure login, or <code>null</code> if a matching secure login could not be found
	*/
	public static SecureLogin fetchBySecureLoginByUserId(long userId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchBySecureLoginByUserId(userId, retrieveFromCache);
	}

	/**
	* Removes the secure login where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @return the secure login that was removed
	*/
	public static SecureLogin removeBySecureLoginByUserId(long userId)
		throws com.knowarth.security.exception.NoSuchSecureLoginException {
		return getPersistence().removeBySecureLoginByUserId(userId);
	}

	/**
	* Returns the number of secure logins where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching secure logins
	*/
	public static int countBySecureLoginByUserId(long userId) {
		return getPersistence().countBySecureLoginByUserId(userId);
	}

	/**
	* Returns the secure login where registrationKey = &#63; or throws a {@link NoSuchSecureLoginException} if it could not be found.
	*
	* @param registrationKey the registration key
	* @return the matching secure login
	* @throws NoSuchSecureLoginException if a matching secure login could not be found
	*/
	public static SecureLogin findByRegistrationKey(
		java.lang.String registrationKey)
		throws com.knowarth.security.exception.NoSuchSecureLoginException {
		return getPersistence().findByRegistrationKey(registrationKey);
	}

	/**
	* Returns the secure login where registrationKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param registrationKey the registration key
	* @return the matching secure login, or <code>null</code> if a matching secure login could not be found
	*/
	public static SecureLogin fetchByRegistrationKey(
		java.lang.String registrationKey) {
		return getPersistence().fetchByRegistrationKey(registrationKey);
	}

	/**
	* Returns the secure login where registrationKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param registrationKey the registration key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching secure login, or <code>null</code> if a matching secure login could not be found
	*/
	public static SecureLogin fetchByRegistrationKey(
		java.lang.String registrationKey, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByRegistrationKey(registrationKey, retrieveFromCache);
	}

	/**
	* Removes the secure login where registrationKey = &#63; from the database.
	*
	* @param registrationKey the registration key
	* @return the secure login that was removed
	*/
	public static SecureLogin removeByRegistrationKey(
		java.lang.String registrationKey)
		throws com.knowarth.security.exception.NoSuchSecureLoginException {
		return getPersistence().removeByRegistrationKey(registrationKey);
	}

	/**
	* Returns the number of secure logins where registrationKey = &#63;.
	*
	* @param registrationKey the registration key
	* @return the number of matching secure logins
	*/
	public static int countByRegistrationKey(java.lang.String registrationKey) {
		return getPersistence().countByRegistrationKey(registrationKey);
	}

	/**
	* Caches the secure login in the entity cache if it is enabled.
	*
	* @param secureLogin the secure login
	*/
	public static void cacheResult(SecureLogin secureLogin) {
		getPersistence().cacheResult(secureLogin);
	}

	/**
	* Caches the secure logins in the entity cache if it is enabled.
	*
	* @param secureLogins the secure logins
	*/
	public static void cacheResult(List<SecureLogin> secureLogins) {
		getPersistence().cacheResult(secureLogins);
	}

	/**
	* Creates a new secure login with the primary key. Does not add the secure login to the database.
	*
	* @param secureLoginId the primary key for the new secure login
	* @return the new secure login
	*/
	public static SecureLogin create(long secureLoginId) {
		return getPersistence().create(secureLoginId);
	}

	/**
	* Removes the secure login with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param secureLoginId the primary key of the secure login
	* @return the secure login that was removed
	* @throws NoSuchSecureLoginException if a secure login with the primary key could not be found
	*/
	public static SecureLogin remove(long secureLoginId)
		throws com.knowarth.security.exception.NoSuchSecureLoginException {
		return getPersistence().remove(secureLoginId);
	}

	public static SecureLogin updateImpl(SecureLogin secureLogin) {
		return getPersistence().updateImpl(secureLogin);
	}

	/**
	* Returns the secure login with the primary key or throws a {@link NoSuchSecureLoginException} if it could not be found.
	*
	* @param secureLoginId the primary key of the secure login
	* @return the secure login
	* @throws NoSuchSecureLoginException if a secure login with the primary key could not be found
	*/
	public static SecureLogin findByPrimaryKey(long secureLoginId)
		throws com.knowarth.security.exception.NoSuchSecureLoginException {
		return getPersistence().findByPrimaryKey(secureLoginId);
	}

	/**
	* Returns the secure login with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param secureLoginId the primary key of the secure login
	* @return the secure login, or <code>null</code> if a secure login with the primary key could not be found
	*/
	public static SecureLogin fetchByPrimaryKey(long secureLoginId) {
		return getPersistence().fetchByPrimaryKey(secureLoginId);
	}

	public static java.util.Map<java.io.Serializable, SecureLogin> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the secure logins.
	*
	* @return the secure logins
	*/
	public static List<SecureLogin> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the secure logins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecureLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of secure logins
	* @param end the upper bound of the range of secure logins (not inclusive)
	* @return the range of secure logins
	*/
	public static List<SecureLogin> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the secure logins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecureLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of secure logins
	* @param end the upper bound of the range of secure logins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of secure logins
	*/
	public static List<SecureLogin> findAll(int start, int end,
		OrderByComparator<SecureLogin> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the secure logins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecureLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of secure logins
	* @param end the upper bound of the range of secure logins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of secure logins
	*/
	public static List<SecureLogin> findAll(int start, int end,
		OrderByComparator<SecureLogin> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the secure logins from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of secure logins.
	*
	* @return the number of secure logins
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SecureLoginPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SecureLoginPersistence, SecureLoginPersistence> _serviceTracker =
		ServiceTrackerFactory.open(SecureLoginPersistence.class);
}