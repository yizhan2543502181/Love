/**
 * $id$ Copyright 2011-2012 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.password;

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
public class PasswordSetRequestParam extends RequestParam
{
	private static final String	METHOD	      = "set_password";
	/**
	 * 所有字段
	 */
	public static final String	FIELDS_ALL	  = Constant.KEY_UID;
	/**
	 * 默认字段<br>
	 * 不添加fields参数也按此字段返回
	 */
	public static final String	FIELD_DEFAULT	= PasswordSetRequestParam.FIELDS_ALL;
	/**
	 * 需要获取的字段
	 */
	private String	            fields	      = PasswordSetRequestParam.FIELD_DEFAULT;
	private Password	            patient	      = null;

	public PasswordSetRequestParam()
	{
		super();
	}

	public PasswordSetRequestParam(Password patient)
	{
		super();
		this.patient = patient;
	}

	public PasswordSetRequestParam(String fields, Password patient)
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

	public Password getPatient()
	{
		return patient;
	}

	public void setPatient(Password patient)
	{
		this.patient = patient;
	}

	@Override
	public Bundle getParams() throws AidException
	{
		Bundle parameters = new Bundle();
		parameters.putString("method", PasswordSetRequestParam.METHOD);
		if (fields != null)
		{
			parameters.putString("fields", fields);
		}
		if (null != patient)
		{
			parameters.putString("values", patient.toString2());
		}
		return parameters;
	}
}
