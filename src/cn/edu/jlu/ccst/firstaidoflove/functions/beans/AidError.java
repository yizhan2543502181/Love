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
	private static final long	serialVersionUID	= 1L;
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
		return "AidError [errorCode=" + errorCode + ", orgResponse="
				+ orgResponse + "]";
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
			errorMessage = "用户名或密码不正确！";
			break;
		case 102:
			errorMessage = "网络出问题了！";
			break;
		default:
			break;
		}
		return errorMessage;
	}
}
