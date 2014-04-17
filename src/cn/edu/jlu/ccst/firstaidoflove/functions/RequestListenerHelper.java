/**
 * $id$ Copyright 2011-2012 Renren Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidError;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;

/**
 * AsyncRenren的方法只能接受一个RequestListener; 通过RequestListenerHelper可以在AsyncRenren方法中加入多个监听器；提供RequestListener缺省实现。
 * 
 * @author 李勇(yong.li@opi-corp.com) 2010-7-15
 */
public class RequestListenerHelper implements RequestListener
{
	private List<RequestListener>	listeners	= new ArrayList<RequestListener>();

	public void addRequestListener(RequestListener dialogListener)
	{
		listeners.add(dialogListener);
	}

	public boolean removeRequestListener(RequestListener requestListener)
	{
		return listeners.remove(requestListener);
	}

	@Override
	public void onComplete(String response)
	{
		for (RequestListener listener : listeners)
		{
			listener.onComplete(response);
		}
	}

	@Override
	public void onAidError(AidError renrenError)
	{
		for (RequestListener listener : listeners)
		{
			listener.onAidError(renrenError);
		}
	}

	@Override
	public void onFault(Throwable fault)
	{
		for (RequestListener listener : listeners)
		{
			listener.onFault(fault);
		}
	}

	/**
	 * 该类可以当作RequestListener接口的适配器来用；继承该类可以只实现RequestListener接口中你感兴趣的方法。
	 * 
	 * @author 李勇(yong.li@opi-corp.com) 2010-7-16
	 * 
	 */
	public static class DefaultRequestListener implements RequestListener
	{
		@Override
		public void onComplete(String response)
		{
			Log.i(Constant.LOG_TAG, "DefaultRequestListener.onComplete response:"
					+ response);
		}

		@Override
		public void onAidError(AidError renrenError)
		{
			Log.w(Constant.LOG_TAG,
					"DefaultRequestListener.onRenrenError renrenError:"
							+ renrenError);
		}

		@Override
		public void onFault(Throwable fault)
		{
			Log.w(Constant.LOG_TAG, "DefaultRequestListener.onFault fault:" + fault);
		}
	}
}
