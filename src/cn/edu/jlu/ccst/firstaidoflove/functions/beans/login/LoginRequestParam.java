/**
 * $id$ Copyright 2014 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.login;

import android.os.Bundle;
import cn.edu.jlu.ccst.firstaidoflove.functions.RequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.AccidentsGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;

/**
 * 
 * @author hecao (he.cao@aid-inc.com) users.getInfo接口的请求参数
 * 
 * 
 */
public class LoginRequestParam extends RequestParam
{
	private static final String	METHOD			= "login";
	/**
	 * 所有字段
	 */
	public static final String	FIELDS_ALL		= Constant.KEY_LOGIN_NAME
														+ ","
														+ Constant.KEY_LOGIN_PASSWORD;
	/**
	 * 默认字段<br>
	 * 不添加fields参数也按此字段返回
	 */
	public static final String	FIELD_DEFAULT	= AccidentsGetRequestParam.FIELDS_ALL;
	/**
	 * 登录的名字，如邮箱、手机号
	 */
	private String				loginName		= null;
	/**
	 * 登录的密码
	 */
	private String				loginPassword	= null;
	/**
	 * 需要获取的字段
	 */
	private String				fields			= AccidentsGetRequestParam.FIELD_DEFAULT;

	public LoginRequestParam(String loginName, String loginPassword)
	{
		super();
		this.loginName = loginName;
		this.loginPassword = loginPassword;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName()
	{
		return loginName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

	/**
	 * @return the loginPassword
	 */
	public String getLoginPassword()
	{
		return loginPassword;
	}

	/**
	 * @param loginPassword
	 *            the loginPassword to set
	 */
	public void setLoginPassword(String loginPassword)
	{
		this.loginPassword = loginPassword;
	}

	@Override
	public Bundle getParams() throws AidException
	{
		Bundle parameters = new Bundle();
		parameters.putString(Constant.KEY_METHOD, LoginRequestParam.METHOD);
		if (loginName != null)
		{
			parameters.putString(Constant.KEY_LOGIN_NAME, loginName);
		}
		if (loginPassword != null)
		{
			parameters.putString(Constant.KEY_LOGIN_PASSWORD, loginPassword);
		}
		return parameters;
	}
}
