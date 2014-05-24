package cn.edu.jlu.ccst.firstaidoflove.functions.beans;

import java.util.Set;
import java.util.TreeSet;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.User;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

public class Aid implements Parcelable
{
	private static Aid		instance	= null;
	private static User		user		= null;
	private static Context	context		= null;

	public Aid()
	{
		super();
	}

	public static void init(Context context)
	{
		Aid.context = context;
		Aid.initInstance(context);
		Aid.initUserInstance(context);
	}

	public static synchronized void initInstance(Context context)
	{
		if (null == Aid.instance)
		{
			Aid.instance = new Aid();
		}
	}

	public static synchronized void initUserInstance(Context context)
	{
		if (null == Aid.user)
		{
			Aid.user = Util.readUser(Aid.context);
		}
	}

	public static synchronized Aid getInstance(Context context)
	{
		Aid.context = context;
		Aid.initInstance(context);
		Aid.initUserInstance(context);
		return Aid.instance;
	}

	public static synchronized User getUserInstance(Context context)
	{
		Aid.context = context;
		Aid.initInstance(context);
		Aid.initUserInstance(context);
		return Aid.user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public static void saveUser(User user)
	{
		Aid.user = user;
		Util.writeUser(Aid.context, user);
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUid(Long uid)
	{
		User user = new User();
		user.setUid(uid);
		Aid.user = user;
	}

	public Long getCurrentUid()
	{
		if (null != Aid.user)
		{
			return Aid.user.getUid();
		}
		return (long) -1;
	}

	/**
	 * 调用 爱的监护者 APIs
	 * 
	 * @param parameters
	 * @return 返回结果为Json格式
	 */
	public String requestJSON(Bundle parameters)
	{
		return request(parameters, "json");
	}

	private String request(Bundle parameters, String format)
	{
		parameters.putString("format", format);
		prepareParams(parameters);
		logRequest(parameters);
		String response = Util.openUrl(Constant.RESTSERVER_URL, "POST",
				parameters);
		logResponse(parameters.getString(Constant.KEY_METHOD), response);
		return response;
	}

	/**
	 * 判断sessionKey是否有效。
	 * 
	 * @return boolean
	 */
	public boolean isSessionKeyValid()
	{
		return true;
	}

	private void prepareParams(Bundle params)
	{
		params.putString("VERSION", Constant.VERSION);
		StringBuffer sb = new StringBuffer();
		Set<String> keys = new TreeSet<String>(params.keySet());
		for (String key : keys)
		{
			sb.append(key);
			sb.append("=");
			sb.append(params.getString(key));
		}
		params.putString("sig", Util.md5(sb.toString()));
	}

	/**
	 * 记录请求log
	 */
	private void logRequest(Bundle params)
	{
		if (params != null)
		{
			String method = params.getString(Constant.KEY_METHOD);
			if (method != null)
			{
				StringBuffer sb = new StringBuffer();
				sb.append("method=").append(method).append("&")
						.append(params.toString());
				Log.i(Constant.LOG_TAG, sb.toString());
			}
			else
			{
				Log.i(Constant.LOG_TAG, params.toString());
			}
		}
	}

	/**
	 * 记录响应log
	 * 
	 * @param response
	 */
	private void logResponse(String method, String response)
	{
		if (method != null && response != null)
		{
			StringBuffer sb = new StringBuffer();
			sb.append("method=").append(method).append("&").append(response);
			Log.i(Constant.LOG_TAG, sb.toString());
		}
	}

	public Aid(Parcel in)
	{
		Aid.user = User.CREATOR.createFromParcel(in);
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		Aid.user.writeToParcel(dest, flags);
	}

	public static final Parcelable.Creator<Aid>	CREATOR	= new Parcelable.Creator<Aid>() {
															@Override
															public Aid createFromParcel(
																	Parcel in)
															{
																return new Aid(
																		in);
															}

															@Override
															public Aid[] newArray(
																	int size)
															{
																return new Aid[size];
															}
														};

	/**
	 * 退出登录
	 * 
	 * @param context
	 * @return
	 */
	public void logout()
	{
		Aid.user = null;
		Aid.instance = null;
		Util.clearCookies(Aid.context);
		Util.clearUser(Aid.context);
	}
}
