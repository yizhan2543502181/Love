/**
 * $id$ Copyright 2011-2012 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.friends;

import java.util.concurrent.Executor;

import android.os.Bundle;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractRequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.Aid;
import cn.edu.jlu.ccst.firstaidoflove.util.AidError;
import cn.edu.jlu.ccst.firstaidoflove.util.AidException;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

/**
 * 
 * @author hecao (he.cao@aid-inc.com) friends相关接口
 * 
 */
public class FriendsHelper
{
	private Aid	aid;

	public FriendsHelper(Aid aid)
	{
		this.aid = aid;
	}

	/**
	 * 同步调用 friends.get接口
	 * 
	 * @param param
	 *            请求参数
	 * @return 返回{@link FriendsGetResponseBean}对象
	 * @throws AidException
	 * @throws Throwable
	 */
	public FriendsGetResponseBean getFriends(FriendsGetRequestParam param)
			throws AidException, Throwable
	{
		Bundle parameters = param.getParams();
		FriendsGetResponseBean responseBean = null;
		try
		{
			String response = aid.requestJSON(parameters);
			if (response != null)
			{
				Util.checkResponse(response, Aid.RESPONSE_FORMAT_JSON);
				responseBean = new FriendsGetResponseBean(response);
				return responseBean;
			}
			else
			{
				Util.logger("null response");
				throw new AidException(AidError.ERROR_CODE_UNKNOWN_ERROR,
						"null response", "null response");
			}
		}
		catch (RuntimeException re)
		{
			Util.logger("runtime exception" + re.getMessage());
			throw new Throwable(re);
		}
	}

	/**
	 * 异步调用friends.get接口
	 * 
	 * @param pool
	 *            线程池
	 * @param param
	 *            请求参数
	 * @param listener
	 *            请求回调
	 */
	public void asyncGetFriends(Executor pool,
			final FriendsGetRequestParam param,
			final AbstractRequestListener<FriendsGetResponseBean> listener)
	{
		pool.execute(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					FriendsGetResponseBean bean = getFriends(param);
					if (listener != null)
					{
						listener.onComplete(bean);
					}
				}
				catch (AidException e)
				{
					Util.logger("aid exception " + e.getMessage());
					if (listener != null)
					{
						listener.onError(new AidError(e.getErrorCode(), e
								.getMessage(), e.getMessage()));
					}
				}
				catch (Throwable e)
				{
					Util.logger("on fault " + e.getMessage());
					if (listener != null)
					{
						listener.onFault(e);
					}
				}
			}
		});
	}

	/**
	 * 同步调用friends.getFriends接口
	 * 
	 * @param param
	 *            请求参数
	 * @return 返回{@link FriendsGetFriendsResponseBean}对象
	 * @throws AidException
	 * @throws Throwable
	 */
	public FriendsGetFriendsResponseBean getFriends(
			FriendsGetFriendsRequestParam param) throws AidException, Throwable
	{
		Bundle parameters = param.getParams();
		FriendsGetFriendsResponseBean responseBean = null;
		try
		{
			String response = aid.requestJSON(parameters);
			if (response != null)
			{
				Util.checkResponse(response, Aid.RESPONSE_FORMAT_JSON);
				responseBean = new FriendsGetFriendsResponseBean(response);
				return responseBean;
			}
			else
			{
				Util.logger("null response");
				throw new AidException(AidError.ERROR_CODE_UNKNOWN_ERROR,
						"null response", "null response");
			}
		}
		catch (RuntimeException re)
		{
			Util.logger("runtime exception" + re.getMessage());
			throw new Throwable(re);
		}
	}

	/**
	 * 异步调用friends.getFriends接口
	 * 
	 * @param pool
	 *            线程池
	 * @param param
	 *            请求参数
	 * @param listener
	 *            请求回调
	 */
	public void asyncGetFriends(
			Executor pool,
			final FriendsGetFriendsRequestParam param,
			final AbstractRequestListener<FriendsGetFriendsResponseBean> listener)
	{
		pool.execute(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					FriendsGetFriendsResponseBean bean = getFriends(param);
					if (listener != null)
					{
						listener.onComplete(bean);
					}
				}
				catch (AidException e)
				{
					Util.logger("aid exception " + e.getMessage());
					if (listener != null)
					{
						listener.onError(new AidError(e.getErrorCode(), e
								.getMessage(), e.getMessage()));
					}
				}
				catch (Throwable e)
				{
					Util.logger("on fault " + e.getMessage());
					if (listener != null)
					{
						listener.onFault(e);
					}
				}
			}
		});
	}

	/**
	 * 同步调用friends.search接口
	 * 
	 * @param param
	 *            请求参数
	 * @return 返回{@link FriendsSearchResponseBean}对象
	 * @throws AidException
	 * @throws Throwable
	 */
	public FriendsSearchResponseBean searchFriends(
			FriendsSearchRequestParam param) throws AidException, Throwable
	{
		Bundle parameters = param.getParams();
		FriendsSearchResponseBean responseBean = null;
		try
		{
			String response = aid.requestJSON(parameters);
			if (response != null)
			{
				Util.checkResponse(response, Aid.RESPONSE_FORMAT_JSON);
				responseBean = new FriendsSearchResponseBean(response);
				return responseBean;
			}
			else
			{
				Util.logger("null response");
				throw new AidException(AidError.ERROR_CODE_UNKNOWN_ERROR,
						"null response", "null response");
			}
		}
		catch (RuntimeException re)
		{
			Util.logger("runtime exception" + re.getMessage());
			throw new Throwable(re);
		}
	}

	/**
	 * 异步调用friends.search接口
	 * 
	 * @param pool
	 *            线程池
	 * @param param
	 *            请求参数
	 * @param listener
	 *            请求回调
	 */
	public void asyncSearchFriends(Executor pool,
			final FriendsSearchRequestParam param,
			final AbstractRequestListener<FriendsSearchResponseBean> listener)
	{
		pool.execute(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					FriendsSearchResponseBean bean = searchFriends(param);
					if (listener != null)
					{
						listener.onComplete(bean);
					}
				}
				catch (AidException e)
				{
					Util.logger("aid exception " + e.getMessage());
					if (listener != null)
					{
						listener.onError(new AidError(e.getErrorCode(), e
								.getMessage(), e.getMessage()));
					}
				}
				catch (Throwable e)
				{
					Util.logger("on fault " + e.getMessage());
					if (listener != null)
					{
						listener.onFault(e);
					}
				}
			}
		});
	}
}
