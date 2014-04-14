/**
 * $id$ Copyright 2011-2012 Renren Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.user;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import cn.edu.jlu.ccst.firstaidoflove.functions.ResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.util.AidException;

/**
 * users.getInfo接口的封装<br>
 * 
 * @author hecao (he.cao@renren-inc.com)
 * 
 */
public class UserGetResponseBean extends ResponseBean
{
	/**
	 * user
	 */
	private User	user;

	/**
	 * 构造UsersGetInfoResponseBean对象
	 * 
	 * @param response
	 *            返回的数据 json格式
	 * @throws AidException
	 */
	public UserGetResponseBean(String response)
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
				user = new User();
				user.parse(jsonObject);
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

	public User getUser()
	{
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LoginResponseBean [user=" + user + "]";
	}
}
