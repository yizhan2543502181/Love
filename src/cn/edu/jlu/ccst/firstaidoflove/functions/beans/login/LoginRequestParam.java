/**
 * $id$ Copyright 2011-2012 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.login;

import android.os.Bundle;
import cn.edu.jlu.ccst.firstaidoflove.functions.RequestParam;
import cn.edu.jlu.ccst.firstaidoflove.util.AidException;

/**
 * 
 * @author hecao (he.cao@aid-inc.com) users.getInfo接口的请求参数
 * 
 * 
 */
public class LoginRequestParam extends RequestParam
{
	private static final String	METHOD			= "login";
	public static final String	LOGIN_NAME		= "login_name";
	public static final String	LOGIN_PASSWORD	= "login_password";
	/**
	 * 登录的名字，如邮箱、手机号
	 */
	private String				loginName		= null;
	/**
	 * 登录的密码
	 */
	private String				loginPassword	= null;

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
		parameters.putString("method", LoginRequestParam.METHOD);
		if (loginName != null)
		{
			parameters.putString(LoginRequestParam.LOGIN_NAME, loginName);
		}
		if (loginPassword != null)
		{
			parameters.putString(LoginRequestParam.LOGIN_PASSWORD,
					loginPassword);
		}
		return parameters;
	}
}
