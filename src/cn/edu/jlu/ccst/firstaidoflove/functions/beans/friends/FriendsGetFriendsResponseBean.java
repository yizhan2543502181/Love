/**
 * $id$ Copyright 2011-2012 Renren Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.friends;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.jlu.ccst.firstaidoflove.functions.ResponseBean;

/**
 * 
 * @author hecao (he.cao@renren-inc.com)
 * 
 *         Friends.getFriends接口返回封装
 * 
 */
public class FriendsGetFriendsResponseBean extends ResponseBean
{

	/**
	 * 好友列表
	 */
	private ArrayList<Friend>	friendList;

	public FriendsGetFriendsResponseBean()
	{
		super(null);
	}

	public FriendsGetFriendsResponseBean(String response)
	{
		super(response);
		if (response == null)
		{
			return;
		}

		try
		{
			JSONArray array = new JSONArray(response);
			if (array != null)
			{
				friendList = new ArrayList<Friend>();
				int size = array.length();
				JSONObject jsonObject = null;
				for (int i = 0; i < size; i++)
				{
					jsonObject = array.optJSONObject(i);
					if (jsonObject != null)
					{
						friendList.add(new Friend(jsonObject));
					}
				}
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

	}

	public ArrayList<Friend> getFriendList()
	{
		return friendList;
	}

	public void setFriendList(ArrayList<Friend> friendList)
	{
		this.friendList = friendList;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		if (friendList != null)
		{
			for (Friend f : friendList)
			{
				sb.append(f.toString()).append("\r\n");
			}
		}
		return sb.toString();
	}

}
