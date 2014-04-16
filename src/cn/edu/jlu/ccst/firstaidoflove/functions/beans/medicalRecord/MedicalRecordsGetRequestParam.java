/**
 * $id$ Copyright 2011-2012 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.medicalRecord;

import android.os.Bundle;
import cn.edu.jlu.ccst.firstaidoflove.functions.RequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;

/**
 * 
 * @author hecao (he.cao@aid-inc.com) users.getInfo接口的请求参数
 * 
 * 
 */
public class MedicalRecordsGetRequestParam extends RequestParam
{
	private static final String	METHOD			= "get_medicalRecords";
	/**
	 * 所有字段
	 */
	public static final String	FIELDS_ALL		= Constant.KEY_PID
														+ ","
														+ Constant.KEY_PNAME
														+ ","
														+ Constant.KEY_SEX
														+ ","
														+ Constant.KEY_AGE
														+ ","
														+ Constant.KEY_RESULT
														+ ","
														+ Constant.KEY_TIME
														+ ","
														+ Constant.KEY_BLOOD_PRESSURE
														+ ","
														+ Constant.KEY_BLOOD_SUGAR
														+ ","
														+ Constant.KEY_HEART_RATE;
	/**
	 * 默认字段<br>
	 * 不添加fields参数也按此字段返回
	 */
	public static final String	FIELD_DEFAULT	= MedicalRecordsGetRequestParam.FIELDS_ALL;
	/**
	 * 用户
	 */
	private String				uid;
	/**
	 * 需要获取的监护对象的id
	 */
	private String				pid;
	/**
	 * 需要获取的字段
	 */
	private String				fields			= MedicalRecordsGetRequestParam.FIELD_DEFAULT;

	public MedicalRecordsGetRequestParam(String uid, String pid)
	{
		super();
		this.uid = uid;
		this.pid = pid;
	}

	/**
	 * 构造一个users.getInfo接口请求参数
	 * 
	 * @param uids
	 *            需要获取的用户uid的数组
	 * @param fields
	 *            需要获取的字段
	 */
	public MedicalRecordsGetRequestParam(String uid, String pid, String fields)
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

	/**
	 * @return the pid
	 */
	public String getPid()
	{
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
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
		parameters.putString("method", MedicalRecordsGetRequestParam.METHOD);
		if (fields != null)
		{
			parameters.putString("fields", fields);
		}
		if (uid != null)
		{
			parameters.putString(Constant.KEY_UID, uid);
		}
		if (pid != null)
		{
			parameters.putString(Constant.KEY_PID, pid);
		}
		return parameters;
	}
}
