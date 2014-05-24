package cn.edu.jlu.ccst.firstaidoflove.activity;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTabHost;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.jlu.ccst.firstaidoflove.BaseActivity;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.fragment.FragmentPage1;
import cn.edu.jlu.ccst.firstaidoflove.fragment.FragmentPage2;
import cn.edu.jlu.ccst.firstaidoflove.fragment.FragmentPage4;
import cn.edu.jlu.ccst.firstaidoflove.fragment.FragmentPage3;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;
import cn.edu.jlu.ccst.firstaidoflove.util.JPushUtil;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * @author yangyu 功能描述：自定义TabHost
 */
@SuppressLint("HandlerLeak")
public class MainActivity extends BaseActivity
{
	public static boolean			isForeground		= false;
	private static final int		MENU_EXIT			= Menu.FIRST - 1;
	private static final int		MENU_ABOUT			= Menu.FIRST;
	// 定义FragmentTabHost对象
	private static FragmentTabHost	mTabHost;
	// 定义一个布局
	private LayoutInflater			layoutInflater;
	// 定义数组来存放Fragment界面
	@SuppressWarnings("rawtypes")
	private Class					fragmentArray[]		= {
			FragmentPage1.class, FragmentPage2.class, FragmentPage3.class,
			FragmentPage4.class						};
	// 定义数组来存放按钮图片
	private int						mImageViewArray[]	= {
			R.drawable.tab_home_btn, R.drawable.tab_square_btn,
			R.drawable.tab_message_btn, R.drawable.tab_more_btn };
	// Tab选项卡的文字
	private String					mTextviewArray[]	= { "概览", "查询", "事故",
			"更多"										};
	// 退出标记
	private static Boolean			isExit				= false;
	Timer							tExit				= new Timer();
	// 用户类型便于以后分类推送
	private String					UserTag				= "old,heart_disease,high_blood_pressure";
	private static boolean			isSetAliasOk		= false;
	private Object					lock				= new Object();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_tab_layout);
		JPushInterface.init(getApplicationContext()); // 初始化 JPush
		JPushInterface.resumePush(getApplicationContext());
		initView();
		registerMessageReceiver(); // used for receive msg
	}

	/**
	 * 初始化组件
	 */
	private void initView()
	{
		// 实例化布局对象
		layoutInflater = LayoutInflater.from(this);
		// 实例化TabHost对象，得到TabHost
		MainActivity.mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		MainActivity.mTabHost.setup(this, getSupportFragmentManager(),
				R.id.realtabcontent);
		// 得到fragment的个数
		int count = fragmentArray.length;
		for (int i = 0; i < count; i++)
		{
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = MainActivity.mTabHost.newTabSpec(
					mTextviewArray[i]).setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			MainActivity.mTabHost.addTab(tabSpec, fragmentArray[i], null);
			// 设置Tab按钮的背景
			MainActivity.mTabHost.getTabWidget().getChildAt(i)
					.setBackgroundResource(R.drawable.selector_tab_background);
		}
		Bundle bundle = intent.getExtras();
		if (null != bundle && null != bundle.getString("JPush"))
		{// 假如是推送消息的话
			MainActivity.mTabHost.setCurrentTab(2);
		}
		// 定时发送
		Executors.newFixedThreadPool(1).execute(loopSetAliasAndTagRunnable);
	}

	/**
	 * 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index)
	{
		View view = layoutInflater.inflate(R.layout.main_tab_item_view, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);
		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(mTextviewArray[index]);
		return view;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		menu.add(0, MainActivity.MENU_EXIT, 0,
				getResources().getText(R.string.MENU_EXIT));
		menu.add(0, MainActivity.MENU_ABOUT, 0,
				getResources().getText(R.string.MENU_ABOUT));
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		super.onMenuItemSelected(featureId, item);
		switch (item.getItemId())
		{
		case MENU_EXIT:
			finish();
			break;
		case MENU_ABOUT:
			alertAbout();
			break;
		}
		return true;
	}

	/** 弹出关于对话框 */
	private void alertAbout()
	{
		new AlertDialog.Builder(MainActivity.this)
				.setTitle(R.string.MENU_ABOUT)
				.setMessage(R.string.aboutInfo)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(
									DialogInterface dialoginterface, int i)
							{}
						}).show();
	}

	TimerTask	task	= new TimerTask() {
							@Override
							public void run()
							{
								MainActivity.isExit = false;
							}
						};

	// 重写按下“后退”键时所做的操作
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		System.out.println("TabHost_Index.java onKeyDown");
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			exit();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void exit()
	{
		if (MainActivity.isExit == false)
		{
			MainActivity.isExit = true;
			Toast.makeText(MainActivity.this, "再按一次后退键退出应用程序",
					Toast.LENGTH_SHORT).show();
			// 定义计划任务，根据参数的不同可以完成以下种类的工作：
			// 在固定时间执行某任务，在固定时间开始重复执行某任务，重复时间间隔可控，在延迟多久后执行某任务，在延迟多久后重复执行某任务，重复时间间隔可控
			task = null;
			task = new TimerTask() {
				@Override
				public void run()
				{
					MainActivity.isExit = false;
				}
			};
			tExit.schedule(task, 2000);
		}
		else
		{
			finish();
		}
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	// 下面是JPush的相关代码
	// for receive customer msg from jpush server
	private MessageReceiver		mMessageReceiver;
	public static final String	MESSAGE_RECEIVED_ACTION	= "cn.edu.jlu.ccst.firstaidoflove.fragment.MESSAGE_RECEIVED_ACTION";
	public static final String	KEY_TITLE				= "title";
	public static final String	KEY_MESSAGE				= "message";
	public static final String	KEY_EXTRAS				= "extras";

	public void registerMessageReceiver()
	{
		mMessageReceiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(MainActivity.MESSAGE_RECEIVED_ACTION);
		registerReceiver(mMessageReceiver, filter);
	}

	public class MessageReceiver extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			if (MainActivity.MESSAGE_RECEIVED_ACTION.equals(intent.getAction()))
			{
				String messge = intent.getStringExtra(MainActivity.KEY_MESSAGE);
				String extras = intent.getStringExtra(MainActivity.KEY_EXTRAS);
				StringBuilder showMsg = new StringBuilder();
				showMsg.append(MainActivity.KEY_MESSAGE + " : " + messge + "\n");
				if (!JPushUtil.isEmpty(extras))
				{
					showMsg.append(MainActivity.KEY_EXTRAS + " : " + extras
							+ "\n");
				}
				Util.alert(MainActivity.this, showMsg.toString());
			}
		}
	}

	private void setTag()
	{
		// ","隔开的多个 转换成 Set
		String[] sArray = UserTag.split(",");
		Set<String> tagSet = new LinkedHashSet<String>();
		for (String sTagItme : sArray)
		{
			tagSet.add(sTagItme);
		}
		// 调用JPush API设置Tag
		mHandler.sendMessage(mHandler.obtainMessage(MainActivity.MSG_SET_TAGS,
				tagSet));
	}

	private void setAlias()
	{
		// String alias = String.valueOf(Aid.getUserInstance().getUid());
		String alias = String.valueOf(222222222);
		if (TextUtils.isEmpty(alias))
		{
			Log.e(Constant.AID_LABEL, "alias即uid为空");
			return;
		}
		// 调用JPush API设置Alias
		mHandler.sendMessage(mHandler.obtainMessage(MainActivity.MSG_SET_ALIAS,
				alias));
	}

	private static final int		MSG_SET_ALIAS				= 1001;
	private static final int		MSG_SET_TAGS				= 1002;
	private final Handler			mHandler					= new Handler() {
																	@SuppressWarnings("unchecked")
																	@Override
																	public void handleMessage(
																			android.os.Message msg)
																	{
																		super.handleMessage(msg);
																		switch (msg.what)
																		{
																		case MSG_SET_ALIAS:
																			Log.d(Constant.AID_LABEL,
																					"Set alias in handler.");
																			JPushInterface
																					.setAliasAndTags(
																							getApplicationContext(),
																							(String) msg.obj,
																							null,
																							mAliasCallback);
																			break;
																		case MSG_SET_TAGS:
																			Log.d(Constant.AID_LABEL,
																					"Set tags in handler.");
																			JPushInterface
																					.setAliasAndTags(
																							getApplicationContext(),
																							null,
																							(Set<String>) msg.obj,
																							mTagsCallback);
																			break;
																		default:
																			Log.i(Constant.AID_LABEL,
																					"Unhandled msg - "
																							+ msg.what);
																		}
																	}
																};
	private final TagAliasCallback	mAliasCallback				= new TagAliasCallback() {
																	@Override
																	public void gotResult(
																			int code,
																			String alias,
																			Set<String> tags)
																	{
																		String logs;
																		switch (code)
																		{
																		case 0:
																			synchronized (lock)
																			{
																				MainActivity.isSetAliasOk = true;
																			}
																			logs = "Set tag and alias success";
																			Log.i(Constant.AID_LABEL,
																					logs);
																			break;
																		case 6002:
																			logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
																			Log.i(Constant.AID_LABEL,
																					logs);
																			if (Util.isNetworkConnected(getApplicationContext()))
																			{
																				mHandler.sendMessageDelayed(
																						mHandler.obtainMessage(
																								MainActivity.MSG_SET_ALIAS,
																								alias),
																						1000 * 60);
																			}
																			else
																			{
																				Log.i(Constant.AID_LABEL,
																						"No network");
																			}
																			break;
																		default:
																			logs = "Failed with errorCode = "
																					+ code;
																			Log.e(Constant.AID_LABEL,
																					logs);
																		}
																		Log.i(Constant.LOG_TAG,
																				logs);
																	}
																};
	private final TagAliasCallback	mTagsCallback				= new TagAliasCallback() {
																	@Override
																	public void gotResult(
																			int code,
																			String alias,
																			Set<String> tags)
																	{
																		String logs;
																		switch (code)
																		{
																		case 0:
																			logs = "Set tag and alias success";
																			Log.i(Constant.AID_LABEL,
																					logs);
																			break;
																		case 6002:
																			logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
																			Log.i(Constant.AID_LABEL,
																					logs);
																			if (Util.isNetworkConnected(getApplicationContext()))
																			{
																				mHandler.sendMessageDelayed(
																						mHandler.obtainMessage(
																								MainActivity.MSG_SET_TAGS,
																								tags),
																						1000 * 60);
																			}
																			else
																			{
																				Log.i(Constant.AID_LABEL,
																						"No network");
																			}
																			break;
																		default:
																			logs = "Failed with errorCode = "
																					+ code;
																			Log.e(Constant.AID_LABEL,
																					logs);
																		}
																		Log.i(Constant.LOG_TAG,
																				logs);
																	}
																};
	private final Runnable			loopSetAliasAndTagRunnable	= new Runnable() {
																	@Override
																	public void run()
																	{
																		boolean temp;
																		while (true)
																		{
																			synchronized (lock)
																			{
																				temp = MainActivity.isSetAliasOk;
																			}
																			if (!temp)
																			{
																				if (Util.isNetworkConnected(getApplicationContext()))
																				{
																					setAlias();
																					setTag();
																				}
																			}
																			try
																			{
																				Thread.sleep(10000);
																			}
																			catch (InterruptedException e)
																			{
																				// TODO Auto-generated catch block
																				e.printStackTrace();
																			}
																		}
																	}
																};

	@Override
	protected void onResume()
	{
		MainActivity.isForeground = true;
		super.onResume();
	}

	@Override
	protected void onPause()
	{
		MainActivity.isForeground = false;
		super.onPause();
	}

	@Override
	protected void onStop()
	{
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy()
	{
		unregisterReceiver(mMessageReceiver);
		super.onDestroy();
	}

	public static void changTab(int index)
	{
		MainActivity.mTabHost.setCurrentTab(index);
	}
}
