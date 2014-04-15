package cn.edu.jlu.ccst.firstaidoflove.activity.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.activity.AccidentInfoActivity;
import cn.edu.jlu.ccst.firstaidoflove.functions.AccidentArriveListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidError;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.Accident;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

public class FragmentPageNowAccident extends Fragment
{
	private static FragmentPageNowAccident		instance		= null;
	private View								layout;
	private TextView							noMessageText	= null;
	private ListView							listView;
	private static List<Accident>				accidentList	= new ArrayList<Accident>();
	private static List<Map<String, Object>>	list			= new ArrayList<Map<String, Object>>();
	private static List<Boolean>				listIsImport	= new ArrayList<Boolean>();
	@SuppressLint("UseValueOf")
	private static Integer						lock			= new Integer(0);

	public FragmentPageNowAccident()
	{
		FragmentPageNowAccident.instance = this;
	}

	/**
	 * @return the instance
	 */
	public static FragmentPageNowAccident getInstance()
	{
		return FragmentPageNowAccident.instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		layout = inflater.inflate(R.layout.now_accident_activity_layout, null);
		initView(layout);
		return layout;
	}

	private void initView(View layout)
	{
		noMessageText = (TextView) layout
				.findViewById(R.id.now_accidents_no_message_text);
		listView = (ListView) layout.findViewById(R.id.now_accidents_list);
		listView.setOnItemClickListener(itemClickListener);
		listView.setOnItemLongClickListener(itemLongClickListener);
		updateList();
	}

	/**
	 * 启动线程更新列表
	 * 
	 * @param pathTemp
	 */
	public void updateList()
	{
		for (int i = 0; i < FragmentPageNowAccident.list.size(); i++)
		{
			if (false == FragmentPageNowAccident.listIsImport.get(i))
			{
				Map<String, Object> map = null;
				map = new HashMap<String, Object>();
				map.put("list_item_icon", R.drawable.icon_home_sel);
				map.put("list_item_text", FragmentPageNowAccident.list.get(i)
						.get("list_item_text"));
				synchronized (FragmentPageNowAccident.lock)
				{
					FragmentPageNowAccident.list.set(i, map);
				}
			}
		}
		if (FragmentPageNowAccident.list.size() == 0)
		{
			noMessageText.setVisibility(View.VISIBLE);
		}
		else
		{
			noMessageText.setVisibility(View.GONE);
		}
		final SimpleAdapter adapter = new SimpleAdapter(getActivity(),
				FragmentPageNowAccident.list, R.layout.list_item_layout,
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
	OnItemClickListener						itemClickListener		= new OnItemClickListener() {
																		@Override
																		public void onItemClick(
																				AdapterView<?> arg0,
																				View arg1,
																				int position,
																				long arg3)
																		{
																			Intent intent = new Intent();
																			Bundle bundle = new Bundle();
																			bundle.putParcelable(
																					Constant.ACCIDENT_LABLE,
																					FragmentPageNowAccident.accidentList
																							.get(position));
																			intent.putExtras(bundle);
																			intent.setClass(
																					FragmentPageNowAccident.this
																							.getActivity(),
																					AccidentInfoActivity.class);
																			startActivity(intent);
																			// 变颜色，来改变重要度
																			FragmentPageNowAccident.listIsImport
																					.set(position,
																							false);
																			updateList();
																		}
																	};
	OnItemLongClickListener					itemLongClickListener	= new OnItemLongClickListener() {
																		@Override
																		public boolean onItemLongClick(
																				AdapterView<?> arg0,
																				View arg1,
																				final int position,
																				long arg3)
																		{
																			Util.showOptionWindow(
																					getActivity(),
																					"询问",
																					"是否删除此条信息？",
																					new Util.OnOptionListener() {
																						@Override
																						public void onOK()
																						{
																							FragmentPageNowAccident
																									.removeListItem(position);
																							updateList();
																						}

																						@Override
																						public void onCancel()
																						{}
																					});
																			return false;
																		}
																	};
	public static AccidentArriveListener	accidentArriveListener	= new AccidentArriveListener() {
																		@Override
																		public void onError(
																				AidError error)
																		{}

																		@Override
																		public void onArrive(
																				Accident accident)
																		{
																			FragmentPageNowAccident
																					.addListItem("姓名："
																							+ accident
																									.getPname()
																							+ "\n时间："
																							+ accident
																									.getTime()
																							+ "\n事故地点："
																							+ "经"
																							+ accident
																									.getLongtitude()
																							+ "° "
																							+ "纬"
																							+ accident
																									.getLatitude()
																							+ "° ");
																			FragmentPageNowAccident.accidentList
																					.add(0,
																							accident);
																			if (null != FragmentPageNowAccident
																					.getInstance())
																			{
																				FragmentPageNowAccident
																						.getInstance()
																						.updateList();
																			}
																		}
																	};

	private static void addListItem(String content)
	{
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		map.put("list_item_icon", R.drawable.icon_home_red);
		map.put("list_item_text", content);
		synchronized (FragmentPageNowAccident.lock)
		{
			FragmentPageNowAccident.list.add(0, map);
			FragmentPageNowAccident.listIsImport.add(0, true);
		}
	}

	private static void removeListItem(int position)
	{
		synchronized (FragmentPageNowAccident.lock)
		{
			FragmentPageNowAccident.accidentList.remove(position);
			FragmentPageNowAccident.list.remove(position);
			FragmentPageNowAccident.listIsImport.remove(position);
		}
	}
}
