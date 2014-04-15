/**
 * $id$ Copyright 2011-2012 Renren Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.login;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import cn.edu.jlu.ccst.firstaidoflove.functions.ResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;

/**
 * users.getInfo接口的封装<br>
 * 
 * @author hecao (he.cao@renren-inc.com)
 * 
 */
public class LoginResponseBean extends ResponseBean
{
	private Login	login	= null;

	public LoginResponseBean(String response)
	{
		super(response);
		if (response == null)
		{
			return;
		}
		try
		{
			JSONObject jsonObject = new JSONObject(response);
			Log.i("json", jsonObject.toString());
			if (jsonObject != null)
			{
				login = new Login();
				login.parse(jsonObject);
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		catch (AidException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @return the login
	 */
	public Login getLogin()
	{
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(Login login)
	{
		this.login = login;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LoginResponseBean [login=" + login + "]";
	}
}
