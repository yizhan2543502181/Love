/**
 * $id$ Copyright 2014 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.password;

import android.os.Bundle;
import cn.edu.jlu.ccst.firstaidoflove.functions.RequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;

/**
 * 
 * @author yizhan (415939252@qq.com) passwrod设置接口的请求参数
 * 
 * 
 */
public class PasswordSetRequestParam extends RequestParam
{
	private static final String	METHOD			= "set_password";
	/**
	 * 所有字段
	 */
	public static final String	FIELDS_ALL		= Constant.KEY_UID
														+ ","
														+ Constant.KEY_OLD_PASSWORD
														+ ","
														+ Constant.KEY_NEW_PASSWORD;
	/**
	 * 默认字段<br>
	 * 不添加fields参数也按此字段返回
	 */
	public static final String	FIELD_DEFAULT	= PasswordSetRequestParam.FIELDS_ALL;
	/**
	 * 需要获取的字段
	 */
	private String				fields			= PasswordSetRequestParam.FIELD_DEFAULT;
	private Password			password		= null;

	public PasswordSetRequestParam()
	{
		super();
	}

	public PasswordSetRequestParam(Password password)
	{
		super();
		this.password = password;
	}

	public PasswordSetRequestParam(String fields, Password password)
	{
		super();
		this.fields = fields;
		this.password = password;
	}

	public String getFields()
	{
		return fields;
	}

	public void setFields(String fields)
	{
		this.fields = fields;
	}

	public Password getPassword()
	{
		return password;
	}

	public void setPassword(Password password)
	{
		this.password = password;
	}

	@Override
	public Bundle getParams() throws AidException
	{
		Bundle parameters = new Bundle();
		parameters.putString(Constant.KEY_METHOD, PasswordSetRequestParam.METHOD);
		if (fields != null)
		{
			parameters.putString(Constant.KEY_FIELDS, fields);
		}
		if (null != password)
		{
			parameters.putString(Constant.KEY_VALUES, password.toString2());
		}
		return parameters;
	}
}
