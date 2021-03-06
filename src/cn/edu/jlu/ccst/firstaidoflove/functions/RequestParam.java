/**
 * $id$ Copyright 2014 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions;

import android.os.Bundle;
import android.text.TextUtils;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;

/**
 * 
 * 开放平台各Api接口请求参数的抽象类
 * 
 * @author Shaofeng Wang (shaofeng.wang@Aid-inc.com)
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
				throw new AidException(Constant.ERROR_CODE_NULL_PARAMETER,
						errorMsg, errorMsg);
			}
		}
	}
}
