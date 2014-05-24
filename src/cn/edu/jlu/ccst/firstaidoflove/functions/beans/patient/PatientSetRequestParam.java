/**
 * $id$ Copyright 2014 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient;

import android.os.Bundle;
import cn.edu.jlu.ccst.firstaidoflove.functions.RequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;

/**
 * 
 * @author hecao (he.cao@aid-inc.com) patients.getInfo接口的请求参数
 * 
 * 
 */
public class PatientSetRequestParam extends RequestParam
{
	private static final String	METHOD			= "set_patient_info";
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
														+ Constant.KEY_JOB
														+ ","
														+ Constant.KEY_HOME_ADDRESS
														+ ","
														+ Constant.KEY_WORK_ADDRESS
														+ ","
														+ Constant.KEY_MOBILE_PHONE_NUM
														+ ","
														+ Constant.KEY_HOME_PHONE_NUM
														+ ","
														+ Constant.KEY_WORK_PHONE_NUM;
	/**
	 * 默认字段<br>
	 * 不添加fields参数也按此字段返回
	 */
	public static final String	FIELD_DEFAULT	= PatientSetRequestParam.FIELDS_ALL;
	/**
	 * 需要获取的字段
	 */
	private String				fields			= PatientSetRequestParam.FIELD_DEFAULT;
	private Patient				patient			= null;

	public PatientSetRequestParam()
	{
		super();
	}

	public PatientSetRequestParam(Patient patient)
	{
		super();
		this.patient = patient;
	}

	public PatientSetRequestParam(String fields, Patient patient)
	{
		super();
		this.fields = fields;
		this.patient = patient;
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

	public Patient getPatient()
	{
		return patient;
	}

	public void setPatient(Patient patient)
	{
		this.patient = patient;
	}

	@Override
	public Bundle getParams() throws AidException
	{
		Bundle parameters = new Bundle();
		parameters
				.putString(Constant.KEY_METHOD, PatientSetRequestParam.METHOD);
		if (null != patient)
		{
			parameters.putAll(patient.getParams());
		}
		return parameters;
	}
}
