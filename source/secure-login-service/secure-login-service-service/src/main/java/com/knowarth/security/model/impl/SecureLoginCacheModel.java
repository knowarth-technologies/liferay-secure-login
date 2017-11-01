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

package com.knowarth.security.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.knowarth.security.model.SecureLogin;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SecureLogin in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SecureLogin
 * @generated
 */
@ProviderType
public class SecureLoginCacheModel implements CacheModel<SecureLogin>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SecureLoginCacheModel)) {
			return false;
		}

		SecureLoginCacheModel secureLoginCacheModel = (SecureLoginCacheModel)obj;

		if (secureLoginId == secureLoginCacheModel.secureLoginId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, secureLoginId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{secureLoginId=");
		sb.append(secureLoginId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", registrationKey=");
		sb.append(registrationKey);
		sb.append(", encodeToken=");
		sb.append(encodeToken);
		sb.append(", encodeTokenDate=");
		sb.append(encodeTokenDate);
		sb.append(", qrCodeToken=");
		sb.append(qrCodeToken);
		sb.append(", qrCodeTokenDate=");
		sb.append(qrCodeTokenDate);
		sb.append(", tokenValidated=");
		sb.append(tokenValidated);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SecureLogin toEntityModel() {
		SecureLoginImpl secureLoginImpl = new SecureLoginImpl();

		secureLoginImpl.setSecureLoginId(secureLoginId);
		secureLoginImpl.setUserId(userId);

		if (registrationKey == null) {
			secureLoginImpl.setRegistrationKey(StringPool.BLANK);
		}
		else {
			secureLoginImpl.setRegistrationKey(registrationKey);
		}

		if (encodeToken == null) {
			secureLoginImpl.setEncodeToken(StringPool.BLANK);
		}
		else {
			secureLoginImpl.setEncodeToken(encodeToken);
		}

		if (encodeTokenDate == Long.MIN_VALUE) {
			secureLoginImpl.setEncodeTokenDate(null);
		}
		else {
			secureLoginImpl.setEncodeTokenDate(new Date(encodeTokenDate));
		}

		if (qrCodeToken == null) {
			secureLoginImpl.setQrCodeToken(StringPool.BLANK);
		}
		else {
			secureLoginImpl.setQrCodeToken(qrCodeToken);
		}

		if (qrCodeTokenDate == Long.MIN_VALUE) {
			secureLoginImpl.setQrCodeTokenDate(null);
		}
		else {
			secureLoginImpl.setQrCodeTokenDate(new Date(qrCodeTokenDate));
		}

		if (tokenValidated == null) {
			secureLoginImpl.setTokenValidated(StringPool.BLANK);
		}
		else {
			secureLoginImpl.setTokenValidated(tokenValidated);
		}

		secureLoginImpl.resetOriginalValues();

		return secureLoginImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		secureLoginId = objectInput.readLong();

		userId = objectInput.readLong();
		registrationKey = objectInput.readUTF();
		encodeToken = objectInput.readUTF();
		encodeTokenDate = objectInput.readLong();
		qrCodeToken = objectInput.readUTF();
		qrCodeTokenDate = objectInput.readLong();
		tokenValidated = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(secureLoginId);

		objectOutput.writeLong(userId);

		if (registrationKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(registrationKey);
		}

		if (encodeToken == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(encodeToken);
		}

		objectOutput.writeLong(encodeTokenDate);

		if (qrCodeToken == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(qrCodeToken);
		}

		objectOutput.writeLong(qrCodeTokenDate);

		if (tokenValidated == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(tokenValidated);
		}
	}

	public long secureLoginId;
	public long userId;
	public String registrationKey;
	public String encodeToken;
	public long encodeTokenDate;
	public String qrCodeToken;
	public long qrCodeTokenDate;
	public String tokenValidated;
}