/**
 * $id$ Copyright 2014 Renren Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import cn.edu.jlu.ccst.firstaidoflove.functions.ResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;

/**
 * patients.getInfo接口的封装<br>
 * 
 * @author hecao (he.cao@renren-inc.com)
 * 
 */
public class PatientGetResponseBean extends ResponseBean
{
	/**
	 * patient
	 */
	private Patient	patient;

	/**
	 * 构造PatientsGetInfoResponseBean对象
	 * 
	 * @param response
	 *            返回的数据 json格式
	 * @throws AidException
	 */
	public PatientGetResponseBean(String response)
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
				patient = new Patient();
				patient.parse(jsonObject);
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

	public Patient getPatient()
	{
		return patient;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LoginResponseBean [patient=" + patient + "]";
	}
}
