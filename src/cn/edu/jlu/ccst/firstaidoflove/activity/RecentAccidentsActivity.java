/**
 * 
 */
package cn.edu.jlu.ccst.firstaidoflove.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.edu.jlu.ccst.firstaidoflove.AbstractAidRequestActivity;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.activity.main.LoginActivity;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractRequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.Aid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidError;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AsyncAid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.Accident;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.AccidentsGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.AccidentsGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

/**
 * @author Administrator
 * 
 */
public class RecentAccidentsActivity extends AbstractAidRequestActivity
{
	private TextView					noMessageText	= null;
	private ProgressDialog				progressDialog	= null;
	private ListView					listView;
	private List<Accident>				accidentList	= new ArrayList<Accident>();
	private List<Map<String, Object>>	list			= new ArrayList<Map<String, Object>>();
	@SuppressLint("UseValueOf")
	private Integer						lock			= new Integer(0);

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.recent_accidents_activity_layout);
		initView();
		updateList();
	}

	private void initView()
	{
		noMessageText = (TextView) findViewById(R.id.recent_accidents_no_message_text);
		listView = (ListView) findViewById(R.id.recent_accidents_list);
		listView.setOnItemClickListener(itemClickListener);
		startGetAccidents();
	}

	private void genList()
	{
		list.clear();
		for (int i = 0; i < 6; ++i)
		{
			accidentList.add(0,
					new Accident(currentUser.getPid(), currentUser.getPname(),
							110.235, 35.3254, "2014.04.15", "糖尿病，高血压，骨折"));
			Accident accident = accidentList.get(i);
			addListItem("姓名：" + accident.getPname() + "\n时间："
					+ accident.getTime() + "\n事故地点：" + "经"
					+ accident.getLongtitude() + "° " + "纬"
					+ accident.getLatitude() + "° ");
		}
	}

	private void addListItem(String content)
	{
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		map.put("list_item_icon", R.drawable.icon_home_sel);
		map.put("list_item_text", content);
		synchronized (lock)
		{
			list.add(0, map);
		}
	}

	public void updateList()
	{
		genList();
		if (list.size() == 0)
		{
			noMessageText.setVisibility(View.VISIBLE);
		}
		else
		{
			noMessageText.setVisibility(View.GONE);
		}
		final SimpleAdapter adapter = new SimpleAdapter(
				RecentAccidentsActivity.this, list, R.layout.list_item_layout,
				new String[] { "list_item_icon", "list_item_text" }, new int[] {
						R.id.list_item_icon, R.id.list_item_text });
		listView.setAdapter(adapter);
	}

	/**
	 * 列表item点击事件
	 * 
	 * @author Administrator
	 * 
	 */
	OnItemClickListener	itemClickListener	= new OnItemClickListener() {
												@Override
												public void onItemClick(
														AdapterView<?> arg0,
														View arg1,
														int position, long arg3)
												{
													intent = new Intent();
													Bundle bundle = new Bundle();
													bundle.putParcelable(
															Constant.ACCIDENT_LABEL,
															accidentList
																	.get(position));
													intent.putExtras(bundle);
													intent.setClass(
															RecentAccidentsActivity.this,
															AccidentInfoActivity.class);
													startActivity(intent);
												}
											};

	private void startGetAccidents()
	{
		if (null == aid || null == currentUser)
		{
			Util.alert(getApplicationContext(), "用户信息异常，请重新登录！");
			intent = new Intent();
			intent.setClass(getApplicationContext(), LoginActivity.class);
			startActivity(intent);
			finish();
		}
		AccidentsGetRequestParam param = new AccidentsGetRequestParam(
				String.valueOf(aid.getCurrentUid()), String.valueOf(Aid
						.getUserInstance().getPid()));
		param.setUid(String.valueOf(aid.getCurrentUid()));
		try
		{
			progressDialog = new ProgressDialog(RecentAccidentsActivity.this);
			progressDialog.setMessage("正在获取信息，请稍后...");
			progressDialog.show();
			AsyncAid aAid = new AsyncAid(aid);
			// 对结果进行监听
			aAid.getRecentAccidents(param, new AccidentsGetListener());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 监听获取用户的基本信息
	 * 
	 * @author Administrator
	 * 
	 */
	private class AccidentsGetListener extends
			AbstractRequestListener<AccidentsGetResponseBean>
	{
		private Handler	handler	= new Handler();

		@Override
		public void onError(AidError AidError)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (RecentAccidentsActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
					}
					Util.alert(RecentAccidentsActivity.this, "获取最近事故失败");
				}
			});
		}

		@Override
		public void onFault(Throwable fault)
		{
			fault.toString();
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (RecentAccidentsActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						Util.alert(RecentAccidentsActivity.this, "获取最近事故失败");
					}
				}
			});
		}

		@Override
		public void onComplete(final AccidentsGetResponseBean bean)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (RecentAccidentsActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						if (null != bean.getAccidents())
						{
							accidentList = bean.getAccidents();
							updateList();
						}
						else
						{
							Util.alert(RecentAccidentsActivity.this, "获取最近事故失败");
						}
					}
				}
			});
		}
	}
}
