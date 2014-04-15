/**
 * $id$ Copyright 2011-2012 Renren Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
import cn.edu.jlu.ccst.firstaidoflove.functions.ResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;

/**
 * accidents.getInfo接口的封装<br>
 * 
 * @author hecao (he.cao@renren-inc.com)
 * 
 */
public class AccidentsGetResponseBean extends ResponseBean
{
	/**
	 * user数组
	 */
	private ArrayList<Accident>	accidents;

	/**
	 * 构造UsersGetInfoResponseBean对象
	 * 
	 * @param response
	 *            返回的数据 json格式
	 * @throws AidException
	 */
	public AccidentsGetResponseBean(String response)
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
				accidents = new ArrayList<Accident>();
				int size = array.length();
				for (int i = 0; i < size; i++)
				{
					Accident info = new Accident();
					info.parse(array.optJSONObject(i));
					if (info != null)
					{
						accidents.add(info);
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

	public ArrayList<Accident> getAccidents()
	{
		return accidents;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		if (accidents != null)
		{
			for (Accident info : accidents)
			{
				sb.append(info.toString()).append("\r\n");
			}
		}
		return sb.toString();
	}
}
