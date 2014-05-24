/**
 * $id$ Copyright 2014 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.medicalRecord;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
import cn.edu.jlu.ccst.firstaidoflove.functions.ResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;

/**
 * medicalRecords.getInfo接口的封装<br>
 * 
 * @author hecao (he.cao@Aid-inc.com)
 * 
 */
public class MedicalRecordsGetResponseBean extends ResponseBean
{
	/**
	 * user数组
	 */
	private ArrayList<MedicalRecord>	medicalRecords;

	/**
	 * 构造UsersGetInfoResponseBean对象
	 * 
	 * @param response
	 *            返回的数据 json格式
	 * @throws AidException
	 */
	public MedicalRecordsGetResponseBean(String response)
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
				medicalRecords = new ArrayList<MedicalRecord>();
				int size = array.length();
				for (int i = 0; i < size; i++)
				{
					MedicalRecord info = new MedicalRecord();
					info.parse(array.optJSONObject(i));
					if (info != null)
					{
						medicalRecords.add(info);
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

	public ArrayList<MedicalRecord> getMedicalRecords()
	{
		return medicalRecords;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		if (medicalRecords != null)
		{
			for (MedicalRecord info : medicalRecords)
			{
				sb.append(info.toString()).append("\r\n");
			}
		}
		return sb.toString();
	}
}
