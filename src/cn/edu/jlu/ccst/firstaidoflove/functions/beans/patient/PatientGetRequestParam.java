/**
 * $id$ Copyright 2014 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient;

import android.os.Bundle;
import cn.edu.jlu.ccst.firstaidoflove.functions.RequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.AccidentsGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;

/**
 * 
 * @author hecao (he.cao@aid-inc.com) patients.getInfo接口的请求参数
 * 
 * 
 */
public class PatientGetRequestParam extends RequestParam
{
	private static final String	METHOD	      = "get_patient_info";
	/**
	 * 所有字段
	 */
	public static final String	FIELDS_ALL	  = Constant.KEY_UID + ","
	                                                  + Constant.KEY_PID;
	/**
	 * 默认字段<br>
	 * 不添加fields参数也按此字段返回
	 */
	public static final String	FIELD_DEFAULT	= AccidentsGetRequestParam.FIELDS_ALL;
	/**
	 * 需要获取的用户uid的数组
	 */
	private String	            uid;
	/**
	 * 监护对象的id
	 */
	private String	            pid;
	/**
	 * 需要获取的字段
	 */
	private String	            fields	      = PatientGetRequestParam.FIELD_DEFAULT;

	/**
	 * 构造一个patients.getInfo接口请求参数
	 * 
	 * @param uids
	 *            需要获取的用户uid的数组
	 */
	public PatientGetRequestParam(String uid, String pid)
	{
		this.uid = uid;
		this.pid = pid;
	}

	/**
	 * 构造一个patients.getInfo接口请求参数
	 * 
	 * @param uids
	 *            需要获取的用户uid的数组
	 * @param fields
	 *            需要获取的字段
	 */
	public PatientGetRequestParam(String uid, String pid, String fields)
	{
		this.uid = uid;
		this.pid = pid;
		setFields(fields);
	}

	/**
	 * 获取uids
	 * 
	 * @return
	 */
	public String getUid()
	{
		return uid;
	}

	/**
	 * 设置uids
	 * 
	 * @param uids
	 */
	public void setUid(String uid)
	{
		this.uid = uid;
	}

	public String getPid()
	{
		return pid;
	}

	public void setPid(String pid)
	{
		this.pid = pid;
	}

	/**
	 * 获取fields
	 * 
	 * @return
	 */
	public String getFields()
	{
		return fields;
	}

	/**
	 * 设置fields
	 * 
	 * @param fields
	 */
	public void setFields(String fields)
	{
		this.fields = fields;
	}

	@Override
	public Bundle getParams() throws AidException
	{
		Bundle parameters = new Bundle();
		parameters.putString(Constant.KEY_METHOD, PatientGetRequestParam.METHOD);
		if (fields != null)
		{
			parameters.putString(Constant.KEY_FIELDS, fields);
		}
		if (uid != null)
		{
			parameters.putString(Constant.KEY_UID, uid);
		}
		if (uid != null)
		{
			parameters.putString(Constant.KEY_PID, pid);
		}
		return parameters;
	}
}
