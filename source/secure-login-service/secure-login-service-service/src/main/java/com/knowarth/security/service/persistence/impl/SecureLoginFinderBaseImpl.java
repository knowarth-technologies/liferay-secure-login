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

import com.knowarth.security.model.SecureLogin;
import com.knowarth.security.service.persistence.SecureLoginPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SecureLoginFinderBaseImpl extends BasePersistenceImpl<SecureLogin> {
	/**
	 * Returns the secure login persistence.
	 *
	 * @return the secure login persistence
	 */
	public SecureLoginPersistence getSecureLoginPersistence() {
		return secureLoginPersistence;
	}

	/**
	 * Sets the secure login persistence.
	 *
	 * @param secureLoginPersistence the secure login persistence
	 */
	public void setSecureLoginPersistence(
		SecureLoginPersistence secureLoginPersistence) {
		this.secureLoginPersistence = secureLoginPersistence;
	}

	@BeanReference(type = SecureLoginPersistence.class)
	protected SecureLoginPersistence secureLoginPersistence;
}