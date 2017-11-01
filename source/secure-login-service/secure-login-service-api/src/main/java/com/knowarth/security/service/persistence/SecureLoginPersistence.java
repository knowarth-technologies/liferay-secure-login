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

import com.knowarth.security.exception.NoSuchSecureLoginException;
import com.knowarth.security.model.SecureLogin;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the secure login service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.knowarth.security.service.persistence.impl.SecureLoginPersistenceImpl
 * @see SecureLoginUtil
 * @generated
 */
@ProviderType
public interface SecureLoginPersistence extends BasePersistence<SecureLogin> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SecureLoginUtil} to access the secure login persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the secure login where userId = &#63; or throws a {@link NoSuchSecureLoginException} if it could not be found.
	*
	* @param userId the user ID
	* @return the matching secure login
	* @throws NoSuchSecureLoginException if a matching secure login could not be found
	*/
	public SecureLogin findBySecureLoginByUserId(long userId)
		throws NoSuchSecureLoginException;

	/**
	* Returns the secure login where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @return the matching secure login, or <code>null</code> if a matching secure login could not be found
	*/
	public SecureLogin fetchBySecureLoginByUserId(long userId);

	/**
	* Returns the secure login where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching secure login, or <code>null</code> if a matching secure login could not be found
	*/
	public SecureLogin fetchBySecureLoginByUserId(long userId,
		boolean retrieveFromCache);

	/**
	* Removes the secure login where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @return the secure login that was removed
	*/
	public SecureLogin removeBySecureLoginByUserId(long userId)
		throws NoSuchSecureLoginException;

	/**
	* Returns the number of secure logins where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching secure logins
	*/
	public int countBySecureLoginByUserId(long userId);

	/**
	* Returns the secure login where registrationKey = &#63; or throws a {@link NoSuchSecureLoginException} if it could not be found.
	*
	* @param registrationKey the registration key
	* @return the matching secure login
	* @throws NoSuchSecureLoginException if a matching secure login could not be found
	*/
	public SecureLogin findByRegistrationKey(java.lang.String registrationKey)
		throws NoSuchSecureLoginException;

	/**
	* Returns the secure login where registrationKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param registrationKey the registration key
	* @return the matching secure login, or <code>null</code> if a matching secure login could not be found
	*/
	public SecureLogin fetchByRegistrationKey(java.lang.String registrationKey);

	/**
	* Returns the secure login where registrationKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param registrationKey the registration key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching secure login, or <code>null</code> if a matching secure login could not be found
	*/
	public SecureLogin fetchByRegistrationKey(
		java.lang.String registrationKey, boolean retrieveFromCache);

	/**
	* Removes the secure login where registrationKey = &#63; from the database.
	*
	* @param registrationKey the registration key
	* @return the secure login that was removed
	*/
	public SecureLogin removeByRegistrationKey(java.lang.String registrationKey)
		throws NoSuchSecureLoginException;

	/**
	* Returns the number of secure logins where registrationKey = &#63;.
	*
	* @param registrationKey the registration key
	* @return the number of matching secure logins
	*/
	public int countByRegistrationKey(java.lang.String registrationKey);

	/**
	* Caches the secure login in the entity cache if it is enabled.
	*
	* @param secureLogin the secure login
	*/
	public void cacheResult(SecureLogin secureLogin);

	/**
	* Caches the secure logins in the entity cache if it is enabled.
	*
	* @param secureLogins the secure logins
	*/
	public void cacheResult(java.util.List<SecureLogin> secureLogins);

	/**
	* Creates a new secure login with the primary key. Does not add the secure login to the database.
	*
	* @param secureLoginId the primary key for the new secure login
	* @return the new secure login
	*/
	public SecureLogin create(long secureLoginId);

	/**
	* Removes the secure login with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param secureLoginId the primary key of the secure login
	* @return the secure login that was removed
	* @throws NoSuchSecureLoginException if a secure login with the primary key could not be found
	*/
	public SecureLogin remove(long secureLoginId)
		throws NoSuchSecureLoginException;

	public SecureLogin updateImpl(SecureLogin secureLogin);

	/**
	* Returns the secure login with the primary key or throws a {@link NoSuchSecureLoginException} if it could not be found.
	*
	* @param secureLoginId the primary key of the secure login
	* @return the secure login
	* @throws NoSuchSecureLoginException if a secure login with the primary key could not be found
	*/
	public SecureLogin findByPrimaryKey(long secureLoginId)
		throws NoSuchSecureLoginException;

	/**
	* Returns the secure login with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param secureLoginId the primary key of the secure login
	* @return the secure login, or <code>null</code> if a secure login with the primary key could not be found
	*/
	public SecureLogin fetchByPrimaryKey(long secureLoginId);

	@Override
	public java.util.Map<java.io.Serializable, SecureLogin> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the secure logins.
	*
	* @return the secure logins
	*/
	public java.util.List<SecureLogin> findAll();

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
	public java.util.List<SecureLogin> findAll(int start, int end);

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
	public java.util.List<SecureLogin> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SecureLogin> orderByComparator);

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
	public java.util.List<SecureLogin> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SecureLogin> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the secure logins from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of secure logins.
	*
	* @return the number of secure logins
	*/
	public int countAll();
}