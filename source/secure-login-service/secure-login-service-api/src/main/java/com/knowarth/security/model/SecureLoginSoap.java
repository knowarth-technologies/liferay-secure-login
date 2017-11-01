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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class SecureLoginSoap implements Serializable {
	public static SecureLoginSoap toSoapModel(SecureLogin model) {
		SecureLoginSoap soapModel = new SecureLoginSoap();

		soapModel.setSecureLoginId(model.getSecureLoginId());
		soapModel.setUserId(model.getUserId());
		soapModel.setRegistrationKey(model.getRegistrationKey());
		soapModel.setEncodeToken(model.getEncodeToken());
		soapModel.setEncodeTokenDate(model.getEncodeTokenDate());
		soapModel.setQrCodeToken(model.getQrCodeToken());
		soapModel.setQrCodeTokenDate(model.getQrCodeTokenDate());
		soapModel.setTokenValidated(model.getTokenValidated());

		return soapModel;
	}

	public static SecureLoginSoap[] toSoapModels(SecureLogin[] models) {
		SecureLoginSoap[] soapModels = new SecureLoginSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SecureLoginSoap[][] toSoapModels(SecureLogin[][] models) {
		SecureLoginSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SecureLoginSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SecureLoginSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SecureLoginSoap[] toSoapModels(List<SecureLogin> models) {
		List<SecureLoginSoap> soapModels = new ArrayList<SecureLoginSoap>(models.size());

		for (SecureLogin model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SecureLoginSoap[soapModels.size()]);
	}

	public SecureLoginSoap() {
	}

	public long getPrimaryKey() {
		return _secureLoginId;
	}

	public void setPrimaryKey(long pk) {
		setSecureLoginId(pk);
	}

	public long getSecureLoginId() {
		return _secureLoginId;
	}

	public void setSecureLoginId(long secureLoginId) {
		_secureLoginId = secureLoginId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getRegistrationKey() {
		return _registrationKey;
	}

	public void setRegistrationKey(String registrationKey) {
		_registrationKey = registrationKey;
	}

	public String getEncodeToken() {
		return _encodeToken;
	}

	public void setEncodeToken(String encodeToken) {
		_encodeToken = encodeToken;
	}

	public Date getEncodeTokenDate() {
		return _encodeTokenDate;
	}

	public void setEncodeTokenDate(Date encodeTokenDate) {
		_encodeTokenDate = encodeTokenDate;
	}

	public String getQrCodeToken() {
		return _qrCodeToken;
	}

	public void setQrCodeToken(String qrCodeToken) {
		_qrCodeToken = qrCodeToken;
	}

	public Date getQrCodeTokenDate() {
		return _qrCodeTokenDate;
	}

	public void setQrCodeTokenDate(Date qrCodeTokenDate) {
		_qrCodeTokenDate = qrCodeTokenDate;
	}

	public String getTokenValidated() {
		return _tokenValidated;
	}

	public void setTokenValidated(String tokenValidated) {
		_tokenValidated = tokenValidated;
	}

	private long _secureLoginId;
	private long _userId;
	private String _registrationKey;
	private String _encodeToken;
	private Date _encodeTokenDate;
	private String _qrCodeToken;
	private Date _qrCodeTokenDate;
	private String _tokenValidated;
}