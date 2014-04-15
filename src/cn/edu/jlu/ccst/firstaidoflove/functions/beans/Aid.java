package cn.edu.jlu.ccst.firstaidoflove.functions.beans;

import java.util.Set;
import java.util.TreeSet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.User;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

public class Aid implements Parcelable
{
	private static Aid				instance	= null;
	private static String			sessionKey	= "1111111111111111111";
	private static User				user		= null;
	private Context					context		= null;
	@SuppressLint("UseValueOf")
	private static final Integer	lock		= new Integer(0);

	public Aid()
	{
		super();
	}

	public static synchronized void initInstance()
	{
		if (null == Aid.instance)
		{
			Aid.instance = new Aid();
		}
	}

	public static synchronized Aid getInstance()
	{
		Aid.initInstance();
		return Aid.instance;
	}

	public static synchronized User getUserInstance()
	{
		return Aid.user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public static void setUser(User user)
	{
		Aid.user = user;
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

	public void init(Context context)
	{
		this.context = context;
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
	 * 调用 人人 APIs
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
		if (isSessionKeyValid())
		{
			parameters.putString("session_key", Aid.sessionKey);
		}
		prepareParams(parameters);
		logRequest(parameters);
		String response = Util.openUrl(Constant.RESTSERVER_URL, "POST",
				parameters);
		logResponse(parameters.getString("method"), response);
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
			String method = params.getString("method");
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
		Bundle bundle = in.readBundle();
		if (null != bundle && bundle.containsKey(Constant.SHARE_SESSION_KEY))
		{
			Aid.sessionKey = bundle.getString(Constant.SHARE_SESSION_KEY);
		}
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
		Bundle bundle = new Bundle();
		if (Aid.sessionKey != null)
		{
			bundle.putString(Constant.SHARE_SESSION_KEY, Aid.sessionKey);
		}
		bundle.writeToParcel(dest, flags);
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
	public String logout()
	{
		Util.clearCookies(context);
		clearPersistSession();
		return "true";
	}

	void clearPersistSession()
	{
		Editor editor = context.getSharedPreferences(Constant.SHARE_CONFIG,
				Context.MODE_PRIVATE).edit();
		editor.remove(Constant.SHARE_SESSION_KEY);
		editor.commit();
		synchronized (Aid.lock)
		{
			Aid.user = null;
			Aid.sessionKey = "";
		}
	}

	public void savePersistSession()
	{
		Editor editor = context.getSharedPreferences(Constant.SHARE_CONFIG,
				Context.MODE_PRIVATE).edit();
		editor.putString(Constant.SHARE_SESSION_KEY, Aid.sessionKey);
		editor.commit();
	}

	private String parseContentType(String fileName)
	{
		String contentType = "image/jpg";
		fileName = fileName.toLowerCase();
		if (fileName.endsWith(".jpg"))
		{
			contentType = "image/jpg";
		}
		else if (fileName.endsWith(".png"))
		{
			contentType = "image/png";
		}
		else if (fileName.endsWith(".jpeg"))
		{
			contentType = "image/jpeg";
		}
		else if (fileName.endsWith(".gif"))
		{
			contentType = "image/gif";
		}
		else if (fileName.endsWith(".bmp"))
		{
			contentType = "image/bmp";
		}
		else
		{
			throw new RuntimeException("不支持的文件类型'" + fileName + "'(或没有文件扩展名)");
		}
		return contentType;
	}
}
