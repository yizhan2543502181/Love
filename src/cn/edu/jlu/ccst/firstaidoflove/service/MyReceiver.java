package cn.edu.jlu.ccst.firstaidoflove.service;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cn.edu.jlu.ccst.firstaidoflove.activity.main.FragmentPageNowAccident;
import cn.edu.jlu.ccst.firstaidoflove.activity.main.MainActivity;
import cn.edu.jlu.ccst.firstaidoflove.functions.AccidentArriveListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidError;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.Accident;
import cn.edu.jlu.ccst.firstaidoflove.util.JPushUtil;
import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则： 1) 默认用户会打开主界面 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver
{
	private static final String	TAG	= "JPush";

	@Override
	public void onReceive(Context context, Intent intent)
	{
		Bundle bundle = intent.getExtras();
		Log.d(MyReceiver.TAG, "[MyReceiver] onReceive - " + intent.getAction()
				+ ", extras: " + MyReceiver.printBundle(bundle));
		if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction()))
		{
			String regId = bundle
					.getString(JPushInterface.EXTRA_REGISTRATION_ID);
			Log.d(MyReceiver.TAG, "[MyReceiver] 接收Registration Id : " + regId);
			// send the Registration Id to your server...
		}
		else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
				.getAction()))
		{
			Log.d(MyReceiver.TAG,
					"[MyReceiver] 接收到推送下来的自定义消息: "
							+ bundle.getString(JPushInterface.EXTRA_MESSAGE));
			processCustomMessage(context, bundle);
		}
		else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent
				.getAction()))
		{
			MyReceiver.dealMessage(bundle.getString("cn.jpush.android.ALERT"),
					FragmentPageNowAccident.accidentArriveListener);
			Log.d(MyReceiver.TAG, "[MyReceiver] 接收到推送下来的通知");
			int notifactionId = bundle
					.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
			Log.d(MyReceiver.TAG, "[MyReceiver] 接收到推送下来的通知的ID: "
					+ notifactionId);
		}
		else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
				.getAction()))
		{
			Log.d(MyReceiver.TAG, "[MyReceiver] 用户点击打开了通知");
			if (MainActivity.isForeground)
			{
				MainActivity.changTab(2);
				return;
			}
			JPushInterface.reportNotificationOpened(context,
					bundle.getString(JPushInterface.EXTRA_MSG_ID));
			// 打开自定义的Activity
			Intent i = new Intent(context, MainActivity.class);
			bundle.putString("JPush", "JPush");
			i.putExtras(bundle);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
		}
		else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent
				.getAction()))
		{
			Log.d(MyReceiver.TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: "
					+ bundle.getString(JPushInterface.EXTRA_EXTRA));
			// 在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
		}
		else
		{
			Log.d(MyReceiver.TAG,
					"[MyReceiver] Unhandled intent - " + intent.getAction());
		}
	}

	private static void dealMessage(String message,
			AccidentArriveListener listener)
	{
		try
		{
			JSONObject jObject = new JSONObject(message);
			Accident accident = new Accident();
			accident.parse(jObject);
			listener.onArrive(accident);
		}
		catch (Exception e)
		{
			listener.onError(new AidError(e.getMessage()));
		}
	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle)
	{
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet())
		{
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID))
			{
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}
			else
			{
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}

	// send msg to MainActivity
	private void processCustomMessage(Context context, Bundle bundle)
	{
		if (MainActivity.isForeground)
		{
			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
			if (!JPushUtil.isEmpty(extras))
			{
				try
				{
					JSONObject extraJson = new JSONObject(extras);
					if ((null != extraJson) && (extraJson.length() > 0))
					{
						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
					}
				}
				catch (JSONException e)
				{}
			}
			context.sendBroadcast(msgIntent);
		}
	}
}
