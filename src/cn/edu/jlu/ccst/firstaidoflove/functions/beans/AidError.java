/*
 * Copyright 2010 Renren, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans;

/**
 * 封装服务器返回的错误结果
 * 
 * @author yong.li@opi-corp.com
 */
public class AidError extends RuntimeException
{
	private static final long	serialVersionUID					= 1L;
	/** 错误码：参数为空 */
	public static final int		ERROR_CODE_NULL_PARAMETER			= -1;
	/**
	 * 错误码：参数超出了限制 <br>
	 * 例如，传入的状态长度超过了140个字符.
	 */
	public static final int		ERROR_CODE_PARAMETER_EXTENDS_LIMIT	= -2;
	/** 错误码：非法参数 */
	public static final int		ERROR_CODE_ILLEGAL_PARAMETER		= -3;
	/** 错误码：Access Token为空或Session Key为空 */
	public static final int		ERROR_CODE_TOKEN_ERROR				= -4;
	/** 错误码：无法解析服务器响应字符串 */
	public static final int		ERROR_CODE_UNABLE_PARSE_RESPONSE	= -5;
	/** 错误码：操作被取消 */
	public static final int		ERROR_CODE_OPERATION_CANCELLED		= -6;
	/** 错误码：身份/权限验证失败 */
	public static final int		ERROR_CODE_AUTH_FAILED				= -7;
	/** 错误码：验证过程被取消 */
	public static final int		ERROR_CODE_AUTH_CANCELLED			= -8;
	/** 错误码：未知错误 */
	public static final int		ERROR_CODE_UNKNOWN_ERROR			= -9;
	/** 错误码：初始化失败 */
	public static final int		ERROR_RENREN_INIT_ERROR				= -10;
	/**
	 * 服务器返回的错误代码，详细信息见： http://wiki.dev.renren.com/wiki/API%E9%94%99%E8%AF%AF%E4 %BB%A3%E7%A0%81%E6%9F%A5%E8%AF%A2
	 */
	private int					errorCode;
	/** 原始响应URL */
	private String				orgResponse;

	public AidError()
	{
		super();
	}

	public AidError(String errorMessage)
	{
		super(errorMessage);
	}

	public AidError(int errorCode, String errorMessage, String orgResponse)
	{
		super(errorMessage);
		this.errorCode = errorCode;
		this.orgResponse = orgResponse;
	}

	public String getOrgResponse()
	{
		return orgResponse;
	}

	public int getErrorCode()
	{
		return errorCode;
	}

	@Override
	public String toString()
	{
		return "errorCode:" + errorCode + "\nerrorMessage:" + getMessage()
				+ "\norgResponse:" + orgResponse;
	}

	/**
	 * 将服务器返回的errorMessage转换成SDK定义的易于理解的字符串
	 * 
	 * @param errorCode
	 *            服务器返回的错误代码
	 * @param errorMessage
	 *            服务器返回的错误字符串，和错误代码一一对应
	 * @return
	 */
	public static String interpretErrorMessage(int errorCode,
			String errorMessage)
	{
		switch (errorCode)
		{
		// 登录失败
		case 101:
			errorMessage = "";
			break;
		case 102:
			errorMessage = "其他错误";
			break;
		default:
			break;
		}
		return errorMessage;
	}
}