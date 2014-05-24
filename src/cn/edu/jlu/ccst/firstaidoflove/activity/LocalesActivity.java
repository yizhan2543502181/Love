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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.edu.jlu.ccst.firstaidoflove.BaseActivity;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

/**
 * @author Administrator
 * 
 */
public class LocalesActivity extends BaseActivity implements
		OnClickListener
{
	private Button						addNewBtn		= null;
	private TextView					noMessageText	= null;
	private ProgressDialog				progressDialog	= null;
	private ListView					listView;
	private List<Map<String, Object>>	list			= new ArrayList<Map<String, Object>>();
	private List<Boolean>				listIsImport	= new ArrayList<Boolean>();
	@SuppressLint("UseValueOf")
	private Integer						lock			= new Integer(0);

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.locales_activity_layout);
		genList();
		initView();
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onRestart()
	{
		// TODO Auto-generated method stub
		super.onRestart();
	}

	private void initView()
	{
		addNewBtn = (Button) findViewById(R.id.locale_add_btn);
		addNewBtn.setOnClickListener(this);
		noMessageText = (TextView) findViewById(R.id.locales_no_message_text);
		listView = (ListView) findViewById(R.id.locales_list);
		listView.setOnItemClickListener(itemClickListener);
		listView.setOnItemLongClickListener(itemLongClickListener);
		updateList();
	}

	/**
	 * 启动线程更新列表
	 * 
	 * @param pathTemp
	 */
	private void updateList()
	{
		(new ListViewUpdateThread()).start();
	}

	/**
	 * 列表item点击事件
	 * 
	 * @author Administrator
	 * 
	 */
	OnItemClickListener		itemClickListener		= new OnItemClickListener() {
														@Override
														public void onItemClick(
																AdapterView<?> arg0,
																View arg1,
																int position,
																long arg3)
														{
															// String name = ((HashMap<String, String>) arg0
															// .getItemAtPosition(position)).get("list_item_text");
															Intent intent = new Intent();
															intent.setClass(
																	LocalesActivity.this,
																	LocaleInfoActivity.class);
															startActivity(intent);
														}
													};
	OnItemLongClickListener	itemLongClickListener	= new OnItemLongClickListener() {
														@Override
														public boolean onItemLongClick(
																AdapterView<?> arg0,
																View arg1,
																final int position,
																long arg3)
														{
															Util.showOptionWindow(
																	LocalesActivity.this,
																	"询问",
																	"是否删除此条信息？",
																	new Util.OnOptionListener() {
																		@Override
																		public void onOK()
																		{
																			synchronized (lock)
																			{
																				list.remove(position);
																				listIsImport
																						.remove(position);
																			}
																			updateList();
																		}

																		@Override
																		public void onCancel()
																		{}
																	});
															return false;
														}
													};

	/**
	 * 产生新列表的方法
	 * 
	 * @param path
	 * @return
	 */
	private void genList()
	{
		for (int i = 0; i < 6; ++i)
		{
			addListItem("\t姓名：李四\n\t地点：马兰街道" + i + "号");
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
			listIsImport.add(true);
		}
	}

	/**
	 * 用于更新列表的线程类
	 * 
	 * @param pathTemp
	 */
	public class ListViewUpdateThread extends Thread
	{
		@Override
		public void run()
		{
			runOnUiThread(new Runnable() {
				@Override
				public void run()
				{
					progressDialog = new ProgressDialog(LocalesActivity.this);
					progressDialog.setMessage("正在加载...");
					progressDialog.setTitle("请稍后...");
					progressDialog.show();
					if (list.size() == 0)
					{
						noMessageText.setVisibility(View.VISIBLE);
					}
				}
			});
			final SimpleAdapter adapter = new SimpleAdapter(
					LocalesActivity.this, list, R.layout.list_item_layout,
					new String[] { "list_item_icon", "list_item_text" },
					new int[] { R.id.list_item_icon, R.id.list_item_text });
			runOnUiThread(new Runnable() {
				@Override
				public void run()
				{
					listView.setAdapter(adapter);
					progressDialog.cancel();
				}
			});
		}
	}

	@Override
	public void onClick(View v)
	{
		Intent intent = new Intent();
		switch (v.getId())
		{
		case R.id.locale_add_btn:
			intent.setClass(LocalesActivity.this, LocaleInfoAddActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
