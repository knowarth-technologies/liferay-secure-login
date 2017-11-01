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

package com.knowarth.security.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link SecureLogin}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SecureLogin
 * @generated
 */
@ProviderType
public class SecureLoginWrapper implements SecureLogin,
	ModelWrapper<SecureLogin> {
	public SecureLoginWrapper(SecureLogin secureLogin) {
		_secureLogin = secureLogin;
	}

	@Override
	public Class<?> getModelClass() {
		return SecureLogin.class;
	}

	@Override
	public String getModelClassName() {
		return SecureLogin.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("secureLoginId", getSecureLoginId());
		attributes.put("userId", getUserId());
		attributes.put("registrationKey", getRegistrationKey());
		attributes.put("encodeToken", getEncodeToken());
		attributes.put("encodeTokenDate", getEncodeTokenDate());
		attributes.put("qrCodeToken", getQrCodeToken());
		attributes.put("qrCodeTokenDate", getQrCodeTokenDate());
		attributes.put("tokenValidated", getTokenValidated());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long secureLoginId = (Long)attributes.get("secureLoginId");

		if (secureLoginId != null) {
			setSecureLoginId(secureLoginId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String registrationKey = (String)attributes.get("registrationKey");

		if (registrationKey != null) {
			setRegistrationKey(registrationKey);
		}

		String encodeToken = (String)attributes.get("encodeToken");

		if (encodeToken != null) {
			setEncodeToken(encodeToken);
		}

		Date encodeTokenDate = (Date)attributes.get("encodeTokenDate");

		if (encodeTokenDate != null) {
			setEncodeTokenDate(encodeTokenDate);
		}

		String qrCodeToken = (String)attributes.get("qrCodeToken");

		if (qrCodeToken != null) {
			setQrCodeToken(qrCodeToken);
		}

		Date qrCodeTokenDate = (Date)attributes.get("qrCodeTokenDate");

		if (qrCodeTokenDate != null) {
			setQrCodeTokenDate(qrCodeTokenDate);
		}

		String tokenValidated = (String)attributes.get("tokenValidated");

		if (tokenValidated != null) {
			setTokenValidated(tokenValidated);
		}
	}

	@Override
	public SecureLogin toEscapedModel() {
		return new SecureLoginWrapper(_secureLogin.toEscapedModel());
	}

	@Override
	public SecureLogin toUnescapedModel() {
		return new SecureLoginWrapper(_secureLogin.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _secureLogin.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _secureLogin.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _secureLogin.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _secureLogin.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SecureLogin> toCacheModel() {
		return _secureLogin.toCacheModel();
	}

	@Override
	public int compareTo(SecureLogin secureLogin) {
		return _secureLogin.compareTo(secureLogin);
	}

	@Override
	public int hashCode() {
		return _secureLogin.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _secureLogin.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SecureLoginWrapper((SecureLogin)_secureLogin.clone());
	}

	/**
	* Returns the encode token of this secure login.
	*
	* @return the encode token of this secure login
	*/
	@Override
	public java.lang.String getEncodeToken() {
		return _secureLogin.getEncodeToken();
	}

	/**
	* Returns the qr code token of this secure login.
	*
	* @return the qr code token of this secure login
	*/
	@Override
	public java.lang.String getQrCodeToken() {
		return _secureLogin.getQrCodeToken();
	}

	/**
	* Returns the registration key of this secure login.
	*
	* @return the registration key of this secure login
	*/
	@Override
	public java.lang.String getRegistrationKey() {
		return _secureLogin.getRegistrationKey();
	}

	/**
	* Returns the token validated of this secure login.
	*
	* @return the token validated of this secure login
	*/
	@Override
	public java.lang.String getTokenValidated() {
		return _secureLogin.getTokenValidated();
	}

	/**
	* Returns the user uuid of this secure login.
	*
	* @return the user uuid of this secure login
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _secureLogin.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _secureLogin.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _secureLogin.toXmlString();
	}

	/**
	* Returns the encode token date of this secure login.
	*
	* @return the encode token date of this secure login
	*/
	@Override
	public Date getEncodeTokenDate() {
		return _secureLogin.getEncodeTokenDate();
	}

	/**
	* Returns the qr code token date of this secure login.
	*
	* @return the qr code token date of this secure login
	*/
	@Override
	public Date getQrCodeTokenDate() {
		return _secureLogin.getQrCodeTokenDate();
	}

	/**
	* Returns the primary key of this secure login.
	*
	* @return the primary key of this secure login
	*/
	@Override
	public long getPrimaryKey() {
		return _secureLogin.getPrimaryKey();
	}

	/**
	* Returns the secure login ID of this secure login.
	*
	* @return the secure login ID of this secure login
	*/
	@Override
	public long getSecureLoginId() {
		return _secureLogin.getSecureLoginId();
	}

	/**
	* Returns the user ID of this secure login.
	*
	* @return the user ID of this secure login
	*/
	@Override
	public long getUserId() {
		return _secureLogin.getUserId();
	}

	@Override
	public void persist() {
		_secureLogin.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_secureLogin.setCachedModel(cachedModel);
	}

	/**
	* Sets the encode token of this secure login.
	*
	* @param encodeToken the encode token of this secure login
	*/
	@Override
	public void setEncodeToken(java.lang.String encodeToken) {
		_secureLogin.setEncodeToken(encodeToken);
	}

	/**
	* Sets the encode token date of this secure login.
	*
	* @param encodeTokenDate the encode token date of this secure login
	*/
	@Override
	public void setEncodeTokenDate(Date encodeTokenDate) {
		_secureLogin.setEncodeTokenDate(encodeTokenDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_secureLogin.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_secureLogin.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_secureLogin.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_secureLogin.setNew(n);
	}

	/**
	* Sets the primary key of this secure login.
	*
	* @param primaryKey the primary key of this secure login
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_secureLogin.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_secureLogin.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the qr code token of this secure login.
	*
	* @param qrCodeToken the qr code token of this secure login
	*/
	@Override
	public void setQrCodeToken(java.lang.String qrCodeToken) {
		_secureLogin.setQrCodeToken(qrCodeToken);
	}

	/**
	* Sets the qr code token date of this secure login.
	*
	* @param qrCodeTokenDate the qr code token date of this secure login
	*/
	@Override
	public void setQrCodeTokenDate(Date qrCodeTokenDate) {
		_secureLogin.setQrCodeTokenDate(qrCodeTokenDate);
	}

	/**
	* Sets the registration key of this secure login.
	*
	* @param registrationKey the registration key of this secure login
	*/
	@Override
	public void setRegistrationKey(java.lang.String registrationKey) {
		_secureLogin.setRegistrationKey(registrationKey);
	}

	/**
	* Sets the secure login ID of this secure login.
	*
	* @param secureLoginId the secure login ID of this secure login
	*/
	@Override
	public void setSecureLoginId(long secureLoginId) {
		_secureLogin.setSecureLoginId(secureLoginId);
	}

	/**
	* Sets the token validated of this secure login.
	*
	* @param tokenValidated the token validated of this secure login
	*/
	@Override
	public void setTokenValidated(java.lang.String tokenValidated) {
		_secureLogin.setTokenValidated(tokenValidated);
	}

	/**
	* Sets the user ID of this secure login.
	*
	* @param userId the user ID of this secure login
	*/
	@Override
	public void setUserId(long userId) {
		_secureLogin.setUserId(userId);
	}

	/**
	* Sets the user uuid of this secure login.
	*
	* @param userUuid the user uuid of this secure login
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_secureLogin.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SecureLoginWrapper)) {
			return false;
		}

		SecureLoginWrapper secureLoginWrapper = (SecureLoginWrapper)obj;

		if (Objects.equals(_secureLogin, secureLoginWrapper._secureLogin)) {
			return true;
		}

		return false;
	}

	@Override
	public SecureLogin getWrappedModel() {
		return _secureLogin;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _secureLogin.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _secureLogin.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_secureLogin.resetOriginalValues();
	}

	private final SecureLogin _secureLogin;
}