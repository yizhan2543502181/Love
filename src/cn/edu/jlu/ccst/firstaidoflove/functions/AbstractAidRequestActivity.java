/**
 * $id$ Copyright 2011-2012 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.Aid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.User;

/**
 * 请求窗口的父类，其中包含了对Renren对象的初始化<br>
 * 使用前，需要将Renren对象已Parcelable序列化方式传入intent中，例如
 * 
 * Intent intent = new Intent(xxxActivity, ARRActivity.class);<br>
 * Bundle bundle = new Bundle();<br>
 * bundle.putParcelable(Aid.AID_LABEL, aid);<br>
 * intent.putExtras(bundle);<br>
 * startActivity(intent); <br>
 * 
 * 或：
 * 
 * Intent intent = new Intent(xxxActivity, ARRActivity.class);<br>
 * intent.putExtra(Aid.AID_LABEL, this); activity.startActivity(intent);
 * 
 * <p>
 * 也可调用Renren提供的startRenrenRequestActivity方法，如：<br>
 * Intent intent = new Intent(xxxActivity, ARRActivity.class);<br>
 * aid.startRenrenRequestActivity(xxxActivity, intent);<br>
 * 该方法会辅助完整Renren以Parcel方式传入Activity的操作
 * 
 * @author Shaofeng Wang (shaofeng.wang@aid-inc.com)
 * 
 */
public class AbstractAidRequestActivity extends FragmentActivity
{
	/**
	 * 用于发送请求的对象
	 */
	protected Aid				aid;
	protected User				currentUser;
	protected Intent			intent;
	/**
	 * 异步操作显示进度框
	 */
	protected ProgressDialog	progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		intent = getIntent();
		initAid();
	}

	/**
	 * 初始化Renren对象
	 */
	private void initAid()
	{
		aid = Aid.getInstance();
		aid.init(this);
		currentUser = Aid.getUserInstance();
	}

	@Override
	protected void onDestroy()
	{
		/*
		 * 解决横竖屏切换时的FC bug 在Activity销毁的时候，进度框如果不显示销毁则会继续存在，当再次调用进度框的dismiss方法时会出现view not attached to window manager异常
		 */
		finishProgress();
		super.onDestroy();
	}

	/**
	 * 显示异步上传时的等待进度框
	 * 
	 * @param message
	 *            进度框内容文字
	 */
	protected void showProgress(String message)
	{
		progressDialog = new ProgressDialog(this);
		progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		progressDialog.setMessage(message);
		progressDialog.show();
	}

	/**
	 * 结束进度框
	 * 
	 * @param progress
	 */
	protected void finishProgress()
	{
		if (progressDialog != null)
		{
			progressDialog.dismiss();
		}
	}
}
