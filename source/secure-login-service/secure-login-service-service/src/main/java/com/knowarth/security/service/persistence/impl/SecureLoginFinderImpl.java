package com.knowarth.security.service.persistence.impl;

import java.util.List;

import com.knowarth.security.service.persistence.SecureLoginFinder;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class SecureLoginFinderImpl extends SecureLoginFinderBaseImpl implements SecureLoginFinder{
	public List<Object[]> getUserWithSecureLoginRegKey(int start,int end,String searchText) {
		Session session = null;
		List<Object[]> userSecureLoginLst = null;
		try{
			session = this.openSession();
			String sqlQuery =StringPool.BLANK;
				if(Validator.isNotNull(searchText)){
					String userIdChek=StringPool.BLANK;
					long convertedUserId = GetterUtil.getLong(searchText, 0);
					if(convertedUserId >0){
						userIdChek = "or userId ="+convertedUserId;
					}
					sqlQuery = " select user.emailAddress, user.screenName, user.userId, sl.registrationKey "+
							" from user_ user left join sls_securelogin sl on user.userId=sl.userId where user.status=0 "+
							" and ( emailAddress like '%"+searchText+"%' or screenName like '"+searchText+"' "+userIdChek+"  )  limit ?, ?";
				}else{
					sqlQuery = " select user.emailAddress, user.screenName, user.userId, sl.registrationKey "+
							" from user_ user left join sls_securelogin sl on user.userId=sl.userId where user.status=0 limit ?, ?";
				}
					


			SQLQuery queryObject = session.createSQLQuery(sqlQuery.toString());

			QueryPos pos = QueryPos.getInstance(queryObject);
			if(start >= 1){
				pos.add(start-1);
			}else{
				pos.add(0);
			}
			pos.add(end-start);

			userSecureLoginLst = queryObject.list();
		}finally{
			if(Validator.isNotNull(session)){
				this.closeSession(session);
			}
		}
		return userSecureLoginLst;
	}
}
