/**
 * $id$ Copyright 2011-2012 Renren Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.password;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import cn.edu.jlu.ccst.firstaidoflove.functions.ResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.ResultCode;

/**
 * ResultCodes.getInfo接口的封装<br>
 * 
 * @author hecao (he.cao@renren-inc.com)
 * 
 */
public class PasswordSetResponseBean extends ResponseBean
{
	public static int	SUCCESS	= 0;
	public static int	FAILED	= 1;
	/**
	 * ResultCode
	 */
	private ResultCode	resultCode;

	/**
	 * 构造ResultCodesGetInfoResponseBean对象
	 * 
	 * @param response
	 *            返回的数据 json格式
	 * @throws AidException
	 */
	public PasswordSetResponseBean(String response)
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
				resultCode = new ResultCode();
				resultCode.parse(jsonObject);
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

	public ResultCode getResultCode()
	{
		return resultCode;
	}

	@Override
	public String toString()
	{
		return "ResultCodeSetResponseBean [resultCode=" + resultCode + "]";
	}
}
