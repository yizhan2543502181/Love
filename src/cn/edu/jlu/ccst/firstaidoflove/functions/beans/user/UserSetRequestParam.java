/**
 * $id$ Copyright 2014 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.user;

import android.os.Bundle;
import cn.edu.jlu.ccst.firstaidoflove.functions.RequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;

/**
 * 
 * @author hecao (he.cao@aid-inc.com) users.getInfo接口的请求参数
 * 
 * 
 */
public class UserSetRequestParam extends RequestParam
{
	private static final String	METHOD			= "set_user_info";
	/**
	 * 所有字段
	 */
	public static final String	FIELDS_ALL		= Constant.KEY_UID
														+ ","
														+ Constant.KEY_UNAME
														+ ","
														+ Constant.KEY_PID
														+ ","
														+ Constant.KEY_PNAME
														+ ","
														+ Constant.KEY_SEX
														+ ","
														+ Constant.KEY_AGE
														+ ","
														+ Constant.KEY_JOB
														+ ","
														+ Constant.KEY_RELATIONSHIP
														+ ","
														+ Constant.KEY_HOME_ADDRESS
														+ ","
														+ Constant.KEY_WORK_ADDRESS
														+ ","
														+ Constant.KEY_MOBILE_PHONE_NUM
														+ ","
														+ Constant.KEY_HOME_PHONE_NUM
														+ ","
														+ Constant.KEY_WORK_PHONE_NUM
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
		parameters.putString(Constant.KEY_METHOD, UserSetRequestParam.METHOD);
		if (fields != null)
		{
			parameters.putString(Constant.KEY_FIELDS, fields);
		}
		if (null != user)
		{
			parameters.putString(Constant.KEY_VALUES, user.toString2());
		}
		return parameters;
	}
}
