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
public class UserSetRequestParam extends RequestParam
{
	private static final String	METHOD			= "user.setInfo";
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
	public static final String	FIELD_DEFAULT	= UserSetRequestParam.FIELDS_ALL;
	/**
	 * 需要获取的字段
	 */
	private String				fields			= UserSetRequestParam.FIELD_DEFAULT;
	private User				user			= null;

	public UserSetRequestParam()
	{
		super();
	}

	public UserSetRequestParam(User user)
	{
		super();
		this.user = user;
	}

	public UserSetRequestParam(String fields, User user)
	{
		super();
		this.fields = fields;
		this.user = user;
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

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Override
	public Bundle getParams() throws AidException
	{
		Bundle parameters = new Bundle();
		parameters.putString("method", UserSetRequestParam.METHOD);
		if (fields != null)
		{
			parameters.putString("fields", fields);
		}
		if (null != user)
		{
			parameters.putString("values", user.toString2());
		}
		return parameters;
	}
}