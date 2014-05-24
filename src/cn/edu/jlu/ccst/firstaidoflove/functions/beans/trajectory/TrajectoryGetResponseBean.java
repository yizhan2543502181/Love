/**
 * $id$ Copyright 2014 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import cn.edu.jlu.ccst.firstaidoflove.functions.ResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;

/**
 * trajectory.getInfo接口的封装<br>
 * 
 * @author hecao (he.cao@Aid-inc.com)
 * 
 */
public class TrajectoryGetResponseBean extends ResponseBean
{
	/**
	 * user数组
	 */
	private Trajectory	trajectory;

	/**
	 * 构造UsersGetInfoResponseBean对象
	 * 
	 * @param response
	 *            返回的数据 json格式
	 * @throws AidException
	 */
	public TrajectoryGetResponseBean(String response)
	{
		super(response);
		if (response == null)
		{
			return;
		}
		try
		{
			JSONObject jObject = new JSONObject(response);
			Log.i("json", jObject.toString());
			trajectory.parse(jObject);
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

	public Trajectory getTrajectory()
	{
		return trajectory;
	}

	@Override
	public String toString()
	{
		return "TrajectoryGetResponseBean [trajectory=" + trajectory + "]";
	}
}
