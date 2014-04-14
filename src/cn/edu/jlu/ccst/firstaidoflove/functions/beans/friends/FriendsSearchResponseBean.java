/**
 * $id$ Copyright 2011-2012 Renren Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.friends;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import cn.edu.jlu.ccst.firstaidoflove.functions.ResponseBean;

/**
 * 
 * @author hecao (he.cao@renren-inc.com)
 * 
 *         Friends.getFriends接口返回封装
 * 
 */
public class FriendsSearchResponseBean extends ResponseBean implements Parcelable
{

	private static final String	KEY_TOTAL	= "total";
	private static final String	KEY_FRIEND	= "friends";
	/**
	 * 好友总数
	 */
	private int					total		= 0;
	/**
	 * 用户列表
	 */
	private ArrayList<Friend>	friendList	= new ArrayList<Friend>();

	public FriendsSearchResponseBean(String response)
	{
		super(response);
		if (response == null)
		{
			return;
		}
		JSONObject object = null;
		try
		{
			object = new JSONObject(response);
			total = object.optInt(KEY_TOTAL);
			JSONArray array = object.optJSONArray(KEY_FRIEND);
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

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
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

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(KEY_TOTAL, total);
		dest.writeBundle(bundle);

		dest.writeTypedList(friendList);

	}

	public static final Parcelable.Creator<FriendsSearchResponseBean>	CREATOR	= new Parcelable.Creator<FriendsSearchResponseBean>() {
																					@Override
																					public FriendsSearchResponseBean createFromParcel(
																							Parcel in)
																					{
																						return new FriendsSearchResponseBean(
																								in);
																					}

																					@Override
																					public FriendsSearchResponseBean[] newArray(
																							int size)
																					{
																						return new FriendsSearchResponseBean[size];
																					}
																				};

	/**
	 * 序列化构造函数
	 * 
	 * @param in
	 */
	public FriendsSearchResponseBean(Parcel in)
	{
		super(null);
		Bundle bundle = in.readBundle();
		Log.e("who1", in + "");
		Log.e("who2", friendList + "");
		Log.e("who3", Friend.CREATOR + "");

		in.readTypedList(friendList, Friend.CREATOR);

		if (bundle.containsKey(KEY_TOTAL))
		{
			total = bundle.getInt(KEY_TOTAL);
		}
	}

}
