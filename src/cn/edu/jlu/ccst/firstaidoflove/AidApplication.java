package cn.edu.jlu.ccst.firstaidoflove;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.Aid;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;

public class AidApplication extends Application
{
	private static AidApplication	mInstance		= null;
	public boolean					m_bKeyRight		= true;
	public BMapManager				mBMapManager	= null;

	@Override
	public void onCreate()
	{
		super.onCreate();
		AidApplication.mInstance = this;
		initEngineManager(this);
	}

	public void initEngineManager(Context context)
	{
		if (mBMapManager == null)
		{
			mBMapManager = new BMapManager(context);
		}
		if (!mBMapManager.init(new MyGeneralListener()))
		{
			Log.e(Aid.AID_LABEL, "BMapManager  初始化错误!");
		}
	}

	public static AidApplication getInstance()
	{
		return AidApplication.mInstance;
	}

	public AidApplication()
	{
		// TODO Auto-generated constructor stub
	}

	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
	public static class MyGeneralListener implements MKGeneralListener
	{
		@Override
		public void onGetNetworkState(int iError)
		{
			if (iError == MKEvent.ERROR_NETWORK_CONNECT)
			{
				Log.e(Aid.AID_LABEL, "您的网络出错啦！");
			}
			else if (iError == MKEvent.ERROR_NETWORK_DATA)
			{
				Log.e(Aid.AID_LABEL, "百度地图获取数据异常，请重试!");
			}
			// ...
		}

		@Override
		public void onGetPermissionState(int iError)
		{
			// 非零值表示key验证未通过
			if (iError != 0)
			{
				Log.e(Aid.AID_LABEL,
						"请在 AidApplication.java文件输入正确的授权Key,并检查您的网络连接是否正常！error: "
								+ iError);
				// 授权Key错误：
				AidApplication.getInstance().m_bKeyRight = false;
			}
			else
			{
				Log.e(Aid.AID_LABEL, "key认证成功");
				AidApplication.getInstance().m_bKeyRight = true;
			}
		}
	}
}
