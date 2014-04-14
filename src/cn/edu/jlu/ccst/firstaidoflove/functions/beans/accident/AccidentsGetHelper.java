/**
 * $id$ Copyright 2011-2012 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident;

import java.util.concurrent.Executor;

import android.os.Bundle;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractRequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.Aid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.login.LoginResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.util.AidError;
import cn.edu.jlu.ccst.firstaidoflove.util.AidException;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

/**
 * 
 * @author hecao (he.cao@aid-inc.com) users.getInfo接口 助手类
 * 
 */
public class AccidentsGetHelper
{
	/**
	 * aid对象
	 */
	private Aid	aid;

	public AccidentsGetHelper(Aid aid)
	{
		this.aid = aid;
	}

	/**
	 * 同步调用users.getInfo接口<br>
	 * 
	 * @param param
	 *            请求对象
	 * @return 返回{@link LoginResponseBean}对象
	 * @throws AidException
	 */
	public AccidentsGetResponseBean getAccidents(AccidentsGetRequestParam param)
			throws AidException, Throwable
	{
		Bundle parameters = param.getParams();
		AccidentsGetResponseBean usersBean = null;
		try
		{
			String response = aid.requestJSON(parameters);
			if (response != null)
			{
				Util.checkResponse(response, Aid.RESPONSE_FORMAT_JSON);
			}
			else
			{
				Util.logger("null response");
				throw new AidException(AidError.ERROR_CODE_UNKNOWN_ERROR,
						"null response", "null response");
			}
			usersBean = new AccidentsGetResponseBean(response);
		}
		catch (RuntimeException re)
		{
			Util.logger("runtime exception " + re.getMessage());
			throw new Throwable(re);
		}
		return usersBean;
	}

	/**
	 * 异步方法调用users.getInfo接口<br>
	 * 
	 * @param pool
	 *            线程池
	 * @param param
	 *            请求对象
	 * @param listener
	 *            回调
	 */
	public void asyncGetAccidents(Executor pool,
			final AccidentsGetRequestParam param,
			final AbstractRequestListener<AccidentsGetResponseBean> listener)
	{
		pool.execute(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					AccidentsGetResponseBean usersBean = getAccidents(param);
					if (listener != null)
					{
						listener.onComplete(usersBean);
					}
				}
				catch (AidException e)
				{
					Util.logger("aid exception " + e.getMessage());
					if (listener != null)
					{
						listener.onError(new AidError(e.getMessage()));
						e.printStackTrace();
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
