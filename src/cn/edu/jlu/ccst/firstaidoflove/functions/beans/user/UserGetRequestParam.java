/**
 * $id$ Copyright 2011-2012 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.user;

import android.os.Bundle;
import cn.edu.jlu.ccst.firstaidoflove.functions.RequestParam;
import cn.edu.jlu.ccst.firstaidoflove.util.AidException;

/**
 * 
 * @author hecao (he.cao@aid-inc.com) users.getInfo接口的请求参数
 * 
 * 
 */
public class UserGetRequestParam extends RequestParam
{
	private static final String	METHOD			= "user.getInfo";
	/**
	 * 所有字段
	 */
	public static final String	FIELDS_ALL		= User.KEY_UID
														+ ","
														+ User.KEY_UNAME
														+ ","
														+ User.KEY_PID
														+ ","
														+ User.KEY_PNAME
														+ ","
														+ User.KEY_SEX
														+ ","
														+ User.KEY_AGE
														+ ","
														+ User.KEY_JOB
														+ ","
														+ User.KEY_RELATIONSHIP
														+ ","
														+ User.KEY_HOME_ADDRESS
														+ ","
														+ User.KEY_WORK_ADDRESS
														+ ","
														+ User.KEY_MOBILE_PHONE_NUM
														+ ","
														+ User.KEY_HOME_PHONE_NUM
														+ ","
														+ User.KEY_WORK_PHONE_NUM
														+ ",";
	/**
	 * 默认字段<br>
	 * 不添加fields参数也按此字段返回
	 */
	public static final String	FIELD_DEFAULT	= UserGetRequestParam.FIELDS_ALL;
	/**
	 * 需要获取的用户uid的数组
	 */
	private String				uid;
	/**
	 * 需要获取的字段
	 */
	private String				fields			= UserGetRequestParam.FIELD_DEFAULT;

	/**
	 * 构造一个users.getInfo接口请求参数
	 * 
	 * @param uids
	 *            需要获取的用户uid的数组
	 */
	public UserGetRequestParam(String uid)
	{
		this.uid = uid;
	}

	/**
	 * 构造一个users.getInfo接口请求参数
	 * 
	 * @param uids
	 *            需要获取的用户uid的数组
	 * @param fields
	 *            需要获取的字段
	 */
	public UserGetRequestParam(String uid, String fields)
	{
		this.uid = uid;
		setFields(fields);
	}

	/**
	 * 获取uids
	 * 
	 * @return
	 */
	public String getUid()
	{
		return uid;
	}

	/**
	 * 设置uids
	 * 
	 * @param uids
	 */
	public void setUid(String uid)
	{
		this.uid = uid;
	}

	/**
	 * 获取fields
	 * 
	 * @return
	 */
	public String getFields()
	{
		return fields;
	}

	/**
	 * 设置fields
	 * 
	 * @param fields
	 */
	public void setFields(String fields)
	{
		this.fields = fields;
	}

	@Override
	public Bundle getParams() throws AidException
	{
		Bundle parameters = new Bundle();
		parameters.putString("method", UserGetRequestParam.METHOD);
		if (fields != null)
		{
			parameters.putString("fields", fields);
		}
		if (uid != null)
		{
			parameters.putString("uid", uid);
		}
		return parameters;
	}
}
