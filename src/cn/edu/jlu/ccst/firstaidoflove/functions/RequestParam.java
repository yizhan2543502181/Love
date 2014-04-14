/**
 * $id$ Copyright 2011-2012 Renren Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions;

import android.os.Bundle;
import android.text.TextUtils;
import cn.edu.jlu.ccst.firstaidoflove.util.AidError;
import cn.edu.jlu.ccst.firstaidoflove.util.AidException;

/**
 * 
 * 开放平台各Api接口请求参数的抽象类
 * 
 * @author Shaofeng Wang (shaofeng.wang@renren-inc.com)
 * 
 */
public abstract class RequestParam
{
	public abstract Bundle getParams() throws AidException;

	public void checkNullParams(String... params) throws AidException
	{
		for (String param : params)
		{
			if (TextUtils.isEmpty(param))
			{
				String errorMsg = "required parameter MUST NOT be null";
				throw new AidException(AidError.ERROR_CODE_NULL_PARAMETER,
						errorMsg, errorMsg);
			}
		}
	}
}
