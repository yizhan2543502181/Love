/**
 * $id$ Copyright 2011-2012 Renren Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.users;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
import cn.edu.jlu.ccst.firstaidoflove.functions.ResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;

/**
 * users.getInfo接口的封装<br>
 * 
 * @author hecao (he.cao@renren-inc.com)
 * 
 */
public class UsersGetInfoResponseBean extends ResponseBean
{
	/**
	 * user数组
	 */
	private ArrayList<UserInfo>	users;

	/**
	 * 构造UsersGetInfoResponseBean对象
	 * 
	 * @param response
	 *            返回的数据 json格式
	 * @throws AidException
	 */
	public UsersGetInfoResponseBean(String response)
	{
		super(response);
		if (response == null)
		{
			return;
		}
		try
		{
			JSONArray array = new JSONArray(response);
			Log.i("json", array.toString());
			if (array != null)
			{
				users = new ArrayList<UserInfo>();
				int size = array.length();
				for (int i = 0; i < size; i++)
				{
					UserInfo info = new UserInfo();
					info.parse(array.optJSONObject(i));
					if (info != null)
					{
						users.add(info);
					}
				}
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

	public ArrayList<UserInfo> getUsersInfo()
	{
		return users;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		if (users != null)
		{
			for (UserInfo info : users)
			{
				sb.append(info.toString()).append("\r\n");
			}
		}
		return sb.toString();
	}
}
