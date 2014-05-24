/**
 * $id$ Copyright 2014 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.hotAddress;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
import cn.edu.jlu.ccst.firstaidoflove.functions.ResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;

/**
 * trajectories.getInfo接口的封装<br>
 * 
 * @author hecao (he.cao@Aid-inc.com)
 * 
 */
public class TrajectoriesGetResponseBean extends ResponseBean
{
	/**
	 * user数组
	 */
	private ArrayList<HotAddress>	trajectories;

	/**
	 * 构造UsersGetInfoResponseBean对象
	 * 
	 * @param response
	 *            返回的数据 json格式
	 * @throws AidException
	 */
	public TrajectoriesGetResponseBean(String response)
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
				trajectories = new ArrayList<HotAddress>();
				int size = array.length();
				for (int i = 0; i < size; i++)
				{
					HotAddress info = new HotAddress();
					info.parse(array.optJSONObject(i));
					if (info != null)
					{
						trajectories.add(info);
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

	public ArrayList<HotAddress> getTrajectories()
	{
		return trajectories;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		if (trajectories != null)
		{
			for (HotAddress info : trajectories)
			{
				sb.append(info.toString()).append("\r\n");
			}
		}
		return sb.toString();
	}
}
