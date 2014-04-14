package cn.edu.jlu.ccst.firstaidoflove.functions.beans;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.os.Bundle;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractRequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.RequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.AccidentsGetHelper;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.AccidentsGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.AccidentsGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.login.LoginHelper;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.login.LoginRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.login.LoginResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserGetHelper;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.util.AidError;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

/**
 * 对人人的请求封装成异步。注意：使用该类调用人人接口时，不能在Listener中直接更新UI控件。
 * 
 * @see Aid
 * @see RequestListener
 * 
 * @author yong.li@opi-corp.com
 * 
 */
public class AsyncAid
{
	private Aid			aid;
	private Executor	pool;

	public AsyncAid(Aid aid)
	{
		this.aid = aid;
		pool = Executors.newFixedThreadPool(10);
	}

	/**
	 * 退出登录
	 * 
	 * @param context
	 * @param listener
	 *            注意监听器中不在主线程中执行，所以不能在监听器中直接更新UI代码。
	 */
	public void logout(final RequestListener listener)
	{
		pool.execute(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					String resp = aid.logout();
					listener.onComplete(resp);
				}
				catch (Throwable e)
				{
					listener.onFault(e);
				}
			}
		});
	}

	/**
	 * 调用 人人 APIs，服务器的响应是JSON串。
	 * 
	 * 人人 APIs 详细信息见 http://wiki.dev.aid.com/wiki/API
	 * 
	 * @param parameters
	 *            注意监听器中不在主线程中执行，所以不能在监听器中直接更新UI代码。
	 * @param listener
	 */
	public void requestJSON(Bundle parameters, RequestListener listener)
	{
		request(parameters, listener, Aid.RESPONSE_FORMAT_JSON);
	}

	/**
	 * 调用 人人 APIs。
	 * 
	 * 人人 APIs 详细信息见 http://wiki.dev.aid.com/wiki/API
	 * 
	 * @param parameters
	 * @param listener
	 *            注意监听器中不在主线程中执行，所以不能在监听器中直接更新UI代码。
	 * @param format
	 *            return data format (json or xml)
	 */
	private void request(final Bundle parameters,
			final RequestListener listener, final String format)
	{
		pool.execute(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					String resp = "";
					if ("json".equalsIgnoreCase(format))
					{
						resp = aid.requestJSON(parameters);
					}
					AidError aidError = Util.parseAidError(resp, format);
					if (aidError != null)
					{
						listener.onAidError(aidError);
					}
					else
					{
						listener.onComplete(resp);
					}
				}
				catch (Throwable e)
				{
					listener.onFault(e);
				}
			}
		});
	}

	/**
	 * 异步登录
	 * 
	 * @param name
	 * @param password
	 * @param listener
	 */
	public void login(LoginRequestParam param,
			AbstractRequestListener<LoginResponseBean> listener)
	{
		new LoginHelper(aid).asyncLogin(pool, param, listener);
	}

	/**
	 * users.getInfo接口<br>
	 * http://wiki.dev.aid.com/wiki/Users.getInfo
	 * 
	 * @see Aid.getUsersInfo
	 * 
	 * @param param
	 *            请求参数
	 * @see Aid.getUsersInfo
	 */
	public void getUserInfo(UserGetRequestParam param,
			AbstractRequestListener<UserGetResponseBean> listener)
	{
		new UserGetHelper(aid).asyncGetUsersInfo(pool, param, listener);
	}

	/**
	 * 异步登录
	 * 
	 * @param name
	 * @param password
	 * @param listener
	 */
	public void getRecentAccidents(AccidentsGetRequestParam param,
			AbstractRequestListener<AccidentsGetResponseBean> listener)
	{
		new AccidentsGetHelper(aid).asyncGetAccidents(pool, param, listener);
	}
}
