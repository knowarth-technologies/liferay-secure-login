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

package com.knowarth.security.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.knowarth.security.exception.NoSuchSecureLoginException;
import com.knowarth.security.model.SecureLogin;
import com.knowarth.security.model.impl.SecureLoginImpl;
import com.knowarth.security.model.impl.SecureLoginModelImpl;
import com.knowarth.security.service.persistence.SecureLoginPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the secure login service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SecureLoginPersistence
 * @see com.knowarth.security.service.persistence.SecureLoginUtil
 * @generated
 */
@ProviderType
public class SecureLoginPersistenceImpl extends BasePersistenceImpl<SecureLogin>
	implements SecureLoginPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SecureLoginUtil} to access the secure login persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SecureLoginImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
			SecureLoginModelImpl.FINDER_CACHE_ENABLED, SecureLoginImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
			SecureLoginModelImpl.FINDER_CACHE_ENABLED, SecureLoginImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
			SecureLoginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_SECURELOGINBYUSERID = new FinderPath(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
			SecureLoginModelImpl.FINDER_CACHE_ENABLED, SecureLoginImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchBySecureLoginByUserId",
			new String[] { Long.class.getName() },
			SecureLoginModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SECURELOGINBYUSERID = new FinderPath(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
			SecureLoginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySecureLoginByUserId", new String[] { Long.class.getName() });

	/**
	 * Returns the secure login where userId = &#63; or throws a {@link NoSuchSecureLoginException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching secure login
	 * @throws NoSuchSecureLoginException if a matching secure login could not be found
	 */
	@Override
	public SecureLogin findBySecureLoginByUserId(long userId)
		throws NoSuchSecureLoginException {
		SecureLogin secureLogin = fetchBySecureLoginByUserId(userId);

		if (secureLogin == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSecureLoginException(msg.toString());
		}

		return secureLogin;
	}

	/**
	 * Returns the secure login where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching secure login, or <code>null</code> if a matching secure login could not be found
	 */
	@Override
	public SecureLogin fetchBySecureLoginByUserId(long userId) {
		return fetchBySecureLoginByUserId(userId, true);
	}

	/**
	 * Returns the secure login where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching secure login, or <code>null</code> if a matching secure login could not be found
	 */
	@Override
	public SecureLogin fetchBySecureLoginByUserId(long userId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { userId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_SECURELOGINBYUSERID,
					finderArgs, this);
		}

		if (result instanceof SecureLogin) {
			SecureLogin secureLogin = (SecureLogin)result;

			if ((userId != secureLogin.getUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SECURELOGIN_WHERE);

			query.append(_FINDER_COLUMN_SECURELOGINBYUSERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				List<SecureLogin> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_SECURELOGINBYUSERID,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"SecureLoginPersistenceImpl.fetchBySecureLoginByUserId(long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					SecureLogin secureLogin = list.get(0);

					result = secureLogin;

					cacheResult(secureLogin);

					if ((secureLogin.getUserId() != userId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_SECURELOGINBYUSERID,
							finderArgs, secureLogin);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_SECURELOGINBYUSERID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (SecureLogin)result;
		}
	}

	/**
	 * Removes the secure login where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the secure login that was removed
	 */
	@Override
	public SecureLogin removeBySecureLoginByUserId(long userId)
		throws NoSuchSecureLoginException {
		SecureLogin secureLogin = findBySecureLoginByUserId(userId);

		return remove(secureLogin);
	}

	/**
	 * Returns the number of secure logins where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching secure logins
	 */
	@Override
	public int countBySecureLoginByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SECURELOGINBYUSERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SECURELOGIN_WHERE);

			query.append(_FINDER_COLUMN_SECURELOGINBYUSERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SECURELOGINBYUSERID_USERID_2 = "secureLogin.userId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_REGISTRATIONKEY = new FinderPath(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
			SecureLoginModelImpl.FINDER_CACHE_ENABLED, SecureLoginImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByRegistrationKey",
			new String[] { String.class.getName() },
			SecureLoginModelImpl.REGISTRATIONKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_REGISTRATIONKEY = new FinderPath(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
			SecureLoginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByRegistrationKey", new String[] { String.class.getName() });

	/**
	 * Returns the secure login where registrationKey = &#63; or throws a {@link NoSuchSecureLoginException} if it could not be found.
	 *
	 * @param registrationKey the registration key
	 * @return the matching secure login
	 * @throws NoSuchSecureLoginException if a matching secure login could not be found
	 */
	@Override
	public SecureLogin findByRegistrationKey(String registrationKey)
		throws NoSuchSecureLoginException {
		SecureLogin secureLogin = fetchByRegistrationKey(registrationKey);

		if (secureLogin == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("registrationKey=");
			msg.append(registrationKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSecureLoginException(msg.toString());
		}

		return secureLogin;
	}

	/**
	 * Returns the secure login where registrationKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param registrationKey the registration key
	 * @return the matching secure login, or <code>null</code> if a matching secure login could not be found
	 */
	@Override
	public SecureLogin fetchByRegistrationKey(String registrationKey) {
		return fetchByRegistrationKey(registrationKey, true);
	}

	/**
	 * Returns the secure login where registrationKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param registrationKey the registration key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching secure login, or <code>null</code> if a matching secure login could not be found
	 */
	@Override
	public SecureLogin fetchByRegistrationKey(String registrationKey,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { registrationKey };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_REGISTRATIONKEY,
					finderArgs, this);
		}

		if (result instanceof SecureLogin) {
			SecureLogin secureLogin = (SecureLogin)result;

			if (!Objects.equals(registrationKey,
						secureLogin.getRegistrationKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SECURELOGIN_WHERE);

			boolean bindRegistrationKey = false;

			if (registrationKey == null) {
				query.append(_FINDER_COLUMN_REGISTRATIONKEY_REGISTRATIONKEY_1);
			}
			else if (registrationKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_REGISTRATIONKEY_REGISTRATIONKEY_3);
			}
			else {
				bindRegistrationKey = true;

				query.append(_FINDER_COLUMN_REGISTRATIONKEY_REGISTRATIONKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRegistrationKey) {
					qPos.add(registrationKey);
				}

				List<SecureLogin> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_REGISTRATIONKEY,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"SecureLoginPersistenceImpl.fetchByRegistrationKey(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					SecureLogin secureLogin = list.get(0);

					result = secureLogin;

					cacheResult(secureLogin);

					if ((secureLogin.getRegistrationKey() == null) ||
							!secureLogin.getRegistrationKey()
											.equals(registrationKey)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_REGISTRATIONKEY,
							finderArgs, secureLogin);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_REGISTRATIONKEY,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (SecureLogin)result;
		}
	}

	/**
	 * Removes the secure login where registrationKey = &#63; from the database.
	 *
	 * @param registrationKey the registration key
	 * @return the secure login that was removed
	 */
	@Override
	public SecureLogin removeByRegistrationKey(String registrationKey)
		throws NoSuchSecureLoginException {
		SecureLogin secureLogin = findByRegistrationKey(registrationKey);

		return remove(secureLogin);
	}

	/**
	 * Returns the number of secure logins where registrationKey = &#63;.
	 *
	 * @param registrationKey the registration key
	 * @return the number of matching secure logins
	 */
	@Override
	public int countByRegistrationKey(String registrationKey) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_REGISTRATIONKEY;

		Object[] finderArgs = new Object[] { registrationKey };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SECURELOGIN_WHERE);

			boolean bindRegistrationKey = false;

			if (registrationKey == null) {
				query.append(_FINDER_COLUMN_REGISTRATIONKEY_REGISTRATIONKEY_1);
			}
			else if (registrationKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_REGISTRATIONKEY_REGISTRATIONKEY_3);
			}
			else {
				bindRegistrationKey = true;

				query.append(_FINDER_COLUMN_REGISTRATIONKEY_REGISTRATIONKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRegistrationKey) {
					qPos.add(registrationKey);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_REGISTRATIONKEY_REGISTRATIONKEY_1 =
		"secureLogin.registrationKey IS NULL";
	private static final String _FINDER_COLUMN_REGISTRATIONKEY_REGISTRATIONKEY_2 =
		"secureLogin.registrationKey = ?";
	private static final String _FINDER_COLUMN_REGISTRATIONKEY_REGISTRATIONKEY_3 =
		"(secureLogin.registrationKey IS NULL OR secureLogin.registrationKey = '')";

	public SecureLoginPersistenceImpl() {
		setModelClass(SecureLogin.class);
	}

	/**
	 * Caches the secure login in the entity cache if it is enabled.
	 *
	 * @param secureLogin the secure login
	 */
	@Override
	public void cacheResult(SecureLogin secureLogin) {
		entityCache.putResult(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
			SecureLoginImpl.class, secureLogin.getPrimaryKey(), secureLogin);

		finderCache.putResult(FINDER_PATH_FETCH_BY_SECURELOGINBYUSERID,
			new Object[] { secureLogin.getUserId() }, secureLogin);

		finderCache.putResult(FINDER_PATH_FETCH_BY_REGISTRATIONKEY,
			new Object[] { secureLogin.getRegistrationKey() }, secureLogin);

		secureLogin.resetOriginalValues();
	}

	/**
	 * Caches the secure logins in the entity cache if it is enabled.
	 *
	 * @param secureLogins the secure logins
	 */
	@Override
	public void cacheResult(List<SecureLogin> secureLogins) {
		for (SecureLogin secureLogin : secureLogins) {
			if (entityCache.getResult(
						SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
						SecureLoginImpl.class, secureLogin.getPrimaryKey()) == null) {
				cacheResult(secureLogin);
			}
			else {
				secureLogin.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all secure logins.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SecureLoginImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the secure login.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SecureLogin secureLogin) {
		entityCache.removeResult(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
			SecureLoginImpl.class, secureLogin.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SecureLoginModelImpl)secureLogin);
	}

	@Override
	public void clearCache(List<SecureLogin> secureLogins) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SecureLogin secureLogin : secureLogins) {
			entityCache.removeResult(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
				SecureLoginImpl.class, secureLogin.getPrimaryKey());

			clearUniqueFindersCache((SecureLoginModelImpl)secureLogin);
		}
	}

	protected void cacheUniqueFindersCache(
		SecureLoginModelImpl secureLoginModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] { secureLoginModelImpl.getUserId() };

			finderCache.putResult(FINDER_PATH_COUNT_BY_SECURELOGINBYUSERID,
				args, Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_SECURELOGINBYUSERID,
				args, secureLoginModelImpl);

			args = new Object[] { secureLoginModelImpl.getRegistrationKey() };

			finderCache.putResult(FINDER_PATH_COUNT_BY_REGISTRATIONKEY, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_REGISTRATIONKEY, args,
				secureLoginModelImpl);
		}
		else {
			if ((secureLoginModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SECURELOGINBYUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { secureLoginModelImpl.getUserId() };

				finderCache.putResult(FINDER_PATH_COUNT_BY_SECURELOGINBYUSERID,
					args, Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_SECURELOGINBYUSERID,
					args, secureLoginModelImpl);
			}

			if ((secureLoginModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_REGISTRATIONKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						secureLoginModelImpl.getRegistrationKey()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_REGISTRATIONKEY,
					args, Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_REGISTRATIONKEY,
					args, secureLoginModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		SecureLoginModelImpl secureLoginModelImpl) {
		Object[] args = new Object[] { secureLoginModelImpl.getUserId() };

		finderCache.removeResult(FINDER_PATH_COUNT_BY_SECURELOGINBYUSERID, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_SECURELOGINBYUSERID, args);

		if ((secureLoginModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SECURELOGINBYUSERID.getColumnBitmask()) != 0) {
			args = new Object[] { secureLoginModelImpl.getOriginalUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SECURELOGINBYUSERID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SECURELOGINBYUSERID,
				args);
		}

		args = new Object[] { secureLoginModelImpl.getRegistrationKey() };

		finderCache.removeResult(FINDER_PATH_COUNT_BY_REGISTRATIONKEY, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_REGISTRATIONKEY, args);

		if ((secureLoginModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_REGISTRATIONKEY.getColumnBitmask()) != 0) {
			args = new Object[] {
					secureLoginModelImpl.getOriginalRegistrationKey()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_REGISTRATIONKEY, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_REGISTRATIONKEY, args);
		}
	}

	/**
	 * Creates a new secure login with the primary key. Does not add the secure login to the database.
	 *
	 * @param secureLoginId the primary key for the new secure login
	 * @return the new secure login
	 */
	@Override
	public SecureLogin create(long secureLoginId) {
		SecureLogin secureLogin = new SecureLoginImpl();

		secureLogin.setNew(true);
		secureLogin.setPrimaryKey(secureLoginId);

		return secureLogin;
	}

	/**
	 * Removes the secure login with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param secureLoginId the primary key of the secure login
	 * @return the secure login that was removed
	 * @throws NoSuchSecureLoginException if a secure login with the primary key could not be found
	 */
	@Override
	public SecureLogin remove(long secureLoginId)
		throws NoSuchSecureLoginException {
		return remove((Serializable)secureLoginId);
	}

	/**
	 * Removes the secure login with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the secure login
	 * @return the secure login that was removed
	 * @throws NoSuchSecureLoginException if a secure login with the primary key could not be found
	 */
	@Override
	public SecureLogin remove(Serializable primaryKey)
		throws NoSuchSecureLoginException {
		Session session = null;

		try {
			session = openSession();

			SecureLogin secureLogin = (SecureLogin)session.get(SecureLoginImpl.class,
					primaryKey);

			if (secureLogin == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSecureLoginException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(secureLogin);
		}
		catch (NoSuchSecureLoginException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected SecureLogin removeImpl(SecureLogin secureLogin) {
		secureLogin = toUnwrappedModel(secureLogin);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(secureLogin)) {
				secureLogin = (SecureLogin)session.get(SecureLoginImpl.class,
						secureLogin.getPrimaryKeyObj());
			}

			if (secureLogin != null) {
				session.delete(secureLogin);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (secureLogin != null) {
			clearCache(secureLogin);
		}

		return secureLogin;
	}

	@Override
	public SecureLogin updateImpl(SecureLogin secureLogin) {
		secureLogin = toUnwrappedModel(secureLogin);

		boolean isNew = secureLogin.isNew();

		SecureLoginModelImpl secureLoginModelImpl = (SecureLoginModelImpl)secureLogin;

		Session session = null;

		try {
			session = openSession();

			if (secureLogin.isNew()) {
				session.save(secureLogin);

				secureLogin.setNew(false);
			}
			else {
				secureLogin = (SecureLogin)session.merge(secureLogin);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SecureLoginModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
			SecureLoginImpl.class, secureLogin.getPrimaryKey(), secureLogin,
			false);

		clearUniqueFindersCache(secureLoginModelImpl);
		cacheUniqueFindersCache(secureLoginModelImpl, isNew);

		secureLogin.resetOriginalValues();

		return secureLogin;
	}

	protected SecureLogin toUnwrappedModel(SecureLogin secureLogin) {
		if (secureLogin instanceof SecureLoginImpl) {
			return secureLogin;
		}

		SecureLoginImpl secureLoginImpl = new SecureLoginImpl();

		secureLoginImpl.setNew(secureLogin.isNew());
		secureLoginImpl.setPrimaryKey(secureLogin.getPrimaryKey());

		secureLoginImpl.setSecureLoginId(secureLogin.getSecureLoginId());
		secureLoginImpl.setUserId(secureLogin.getUserId());
		secureLoginImpl.setRegistrationKey(secureLogin.getRegistrationKey());
		secureLoginImpl.setEncodeToken(secureLogin.getEncodeToken());
		secureLoginImpl.setEncodeTokenDate(secureLogin.getEncodeTokenDate());
		secureLoginImpl.setQrCodeToken(secureLogin.getQrCodeToken());
		secureLoginImpl.setQrCodeTokenDate(secureLogin.getQrCodeTokenDate());
		secureLoginImpl.setTokenValidated(secureLogin.getTokenValidated());

		return secureLoginImpl;
	}

	/**
	 * Returns the secure login with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the secure login
	 * @return the secure login
	 * @throws NoSuchSecureLoginException if a secure login with the primary key could not be found
	 */
	@Override
	public SecureLogin findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSecureLoginException {
		SecureLogin secureLogin = fetchByPrimaryKey(primaryKey);

		if (secureLogin == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSecureLoginException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return secureLogin;
	}

	/**
	 * Returns the secure login with the primary key or throws a {@link NoSuchSecureLoginException} if it could not be found.
	 *
	 * @param secureLoginId the primary key of the secure login
	 * @return the secure login
	 * @throws NoSuchSecureLoginException if a secure login with the primary key could not be found
	 */
	@Override
	public SecureLogin findByPrimaryKey(long secureLoginId)
		throws NoSuchSecureLoginException {
		return findByPrimaryKey((Serializable)secureLoginId);
	}

	/**
	 * Returns the secure login with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the secure login
	 * @return the secure login, or <code>null</code> if a secure login with the primary key could not be found
	 */
	@Override
	public SecureLogin fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
				SecureLoginImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SecureLogin secureLogin = (SecureLogin)serializable;

		if (secureLogin == null) {
			Session session = null;

			try {
				session = openSession();

				secureLogin = (SecureLogin)session.get(SecureLoginImpl.class,
						primaryKey);

				if (secureLogin != null) {
					cacheResult(secureLogin);
				}
				else {
					entityCache.putResult(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
						SecureLoginImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
					SecureLoginImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return secureLogin;
	}

	/**
	 * Returns the secure login with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param secureLoginId the primary key of the secure login
	 * @return the secure login, or <code>null</code> if a secure login with the primary key could not be found
	 */
	@Override
	public SecureLogin fetchByPrimaryKey(long secureLoginId) {
		return fetchByPrimaryKey((Serializable)secureLoginId);
	}

	@Override
	public Map<Serializable, SecureLogin> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SecureLogin> map = new HashMap<Serializable, SecureLogin>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SecureLogin secureLogin = fetchByPrimaryKey(primaryKey);

			if (secureLogin != null) {
				map.put(primaryKey, secureLogin);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
					SecureLoginImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SecureLogin)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SECURELOGIN_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (SecureLogin secureLogin : (List<SecureLogin>)q.list()) {
				map.put(secureLogin.getPrimaryKeyObj(), secureLogin);

				cacheResult(secureLogin);

				uncachedPrimaryKeys.remove(secureLogin.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SecureLoginModelImpl.ENTITY_CACHE_ENABLED,
					SecureLoginImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the secure logins.
	 *
	 * @return the secure logins
	 */
	@Override
	public List<SecureLogin> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<SecureLogin> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<SecureLogin> findAll(int start, int end,
		OrderByComparator<SecureLogin> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<SecureLogin> findAll(int start, int end,
		OrderByComparator<SecureLogin> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<SecureLogin> list = null;

		if (retrieveFromCache) {
			list = (List<SecureLogin>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SECURELOGIN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SECURELOGIN;

				if (pagination) {
					sql = sql.concat(SecureLoginModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SecureLogin>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SecureLogin>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the secure logins from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SecureLogin secureLogin : findAll()) {
			remove(secureLogin);
		}
	}

	/**
	 * Returns the number of secure logins.
	 *
	 * @return the number of secure logins
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SECURELOGIN);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SecureLoginModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the secure login persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SecureLoginImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SECURELOGIN = "SELECT secureLogin FROM SecureLogin secureLogin";
	private static final String _SQL_SELECT_SECURELOGIN_WHERE_PKS_IN = "SELECT secureLogin FROM SecureLogin secureLogin WHERE secureLoginId IN (";
	private static final String _SQL_SELECT_SECURELOGIN_WHERE = "SELECT secureLogin FROM SecureLogin secureLogin WHERE ";
	private static final String _SQL_COUNT_SECURELOGIN = "SELECT COUNT(secureLogin) FROM SecureLogin secureLogin";
	private static final String _SQL_COUNT_SECURELOGIN_WHERE = "SELECT COUNT(secureLogin) FROM SecureLogin secureLogin WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "secureLogin.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SecureLogin exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SecureLogin exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SecureLoginPersistenceImpl.class);
}