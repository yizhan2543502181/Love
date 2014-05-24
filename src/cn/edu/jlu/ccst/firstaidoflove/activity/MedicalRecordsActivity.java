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
import cn.edu.jlu.ccst.firstaidoflove.BaseActivity;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractRequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidError;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AsyncAid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.medicalRecord.MedicalRecord;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.medicalRecord.MedicalRecordsGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.medicalRecord.MedicalRecordsGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

/**
 * @author Administrator
 * 
 */
public class MedicalRecordsActivity extends BaseActivity
{
	private TextView					noMessageText		= null;
	private ProgressDialog				progressDialog		= null;
	private ListView					listView;
	private List<MedicalRecord>			medicalRecordList	= new ArrayList<MedicalRecord>();
	private List<Map<String, Object>>	list				= new ArrayList<Map<String, Object>>();
	@SuppressLint("UseValueOf")
	private Integer						lock				= new Integer(0);

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.medical_records_activity_layout);
		initView();
		updateList();
	}

	private void initView()
	{
		noMessageText = (TextView) findViewById(R.id.medical_records_no_message_text);
		listView = (ListView) findViewById(R.id.medical_records_list);
		listView.setOnItemClickListener(itemClickListener);
		startGetMedicalRecords();
	}

	private void genList()
	{
		list.clear();
		for (int i = 0; i < 6; ++i)
		{
			medicalRecordList.add(0, new MedicalRecord(i, currentUser.getPid(),
					currentUser.getPname(), "男", 58, "高血压", "2014.04.15",
					"100-170", "24ug", "89", "无"));
			MedicalRecord medicalRecord = medicalRecordList.get(i);
			addListItem("姓名：" + medicalRecord.getPname() + "\n鉴定疾病："
					+ medicalRecord.getResult() + "\n鉴定时间："
					+ medicalRecord.getTime());
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
				MedicalRecordsActivity.this, list, R.layout.list_item_layout,
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
															Constant.MEDICAL_RECORD_LABEL,
															medicalRecordList
																	.get(position));
													intent.putExtras(bundle);
													intent.setClass(
															MedicalRecordsActivity.this,
															MedicalRecordInfoActivity.class);
													startActivity(intent);
												}
											};

	private void startGetMedicalRecords()
	{
		MedicalRecordsGetRequestParam param = new MedicalRecordsGetRequestParam(
				currentUser.getUid(), currentUser.getPid());
		try
		{
			progressDialog = new ProgressDialog(MedicalRecordsActivity.this);
			progressDialog.setMessage("正在获取信息，请稍后...");
			progressDialog.show();
			AsyncAid aAid = new AsyncAid(aid);
			// 对结果进行监听
			aAid.getRecentMedicalRecords(param, new MedicalRecordsGetListener());
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
	private class MedicalRecordsGetListener extends
			AbstractRequestListener<MedicalRecordsGetResponseBean>
	{
		private Handler	handler	= new Handler();

		@Override
		public void onError(AidError AidError)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (MedicalRecordsActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
					}
					Util.alert(MedicalRecordsActivity.this, "获取最近病历失败");
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
					if (MedicalRecordsActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						Util.alert(MedicalRecordsActivity.this, "获取最近病历失败");
					}
				}
			});
		}

		@Override
		public void onComplete(final MedicalRecordsGetResponseBean bean)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (MedicalRecordsActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						if (null != bean.getMedicalRecords())
						{
							medicalRecordList = bean.getMedicalRecords();
							updateList();
						}
						else
						{
							Util.alert(MedicalRecordsActivity.this, "获取最近病历失败");
						}
					}
				}
			});
		}
	}
}
