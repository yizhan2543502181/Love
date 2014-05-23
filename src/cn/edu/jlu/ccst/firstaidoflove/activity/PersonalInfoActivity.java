/**
 * 
 */
package cn.edu.jlu.ccst.firstaidoflove.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.TextView;
import cn.edu.jlu.ccst.firstaidoflove.AbstractAidRequestActivity;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractRequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.Aid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidError;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AsyncAid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

/**
 * @author Administrator
 * 
 */
public class PersonalInfoActivity extends AbstractAidRequestActivity
{
	private TextView	nameText			= null;
	private TextView	patientText			= null;
	private TextView	sexText				= null;
	private TextView	ageText				= null;
	private TextView	jobText				= null;
	private TextView	relationshipText	= null;
	private TextView	homeAddressText		= null;
	private TextView	workAddressText		= null;
	private TextView	mobilePhoneNumText	= null;
	private TextView	homePhoneNumText	= null;
	private TextView	workPhoneNumText	= null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.personal_info_activity_layout);
		initView();
	}

	private void initView()
	{
		nameText = (TextView) findViewById(R.id.personal_info_name);
		patientText = (TextView) findViewById(R.id.personal_info_patient_name);
		sexText = (TextView) findViewById(R.id.personal_info_sex);
		ageText = (TextView) findViewById(R.id.personal_info_age);
		jobText = (TextView) findViewById(R.id.personal_info_job);
		relationshipText = (TextView) findViewById(R.id.personal_info_relationship);
		homeAddressText = (TextView) findViewById(R.id.personal_info_home_address);
		workAddressText = (TextView) findViewById(R.id.personal_info_work_address);
		mobilePhoneNumText = (TextView) findViewById(R.id.personal_info_mobile_phone_num);
		homePhoneNumText = (TextView) findViewById(R.id.personal_info_home_phone_num);
		workPhoneNumText = (TextView) findViewById(R.id.personal_info_work_phone_num);
		if (null != Aid.getUserInstance())
		{
			setText();
		}
		else
		{
			startGetUser();
		}
	}

	private void setText()
	{
		nameText.setText(Aid.getUserInstance().getUname() == null ? "" : Aid
				.getUserInstance().getUname());
		patientText.setText(Aid.getUserInstance().getPname() == null ? "" : Aid
				.getUserInstance().getPname());
		sexText.setText(Aid.getUserInstance().getSex() == null ? "" : Aid
				.getUserInstance().getSex());
		ageText.setText(String
				.valueOf(Aid.getUserInstance().getAge() == -1 ? "" : Aid
						.getUserInstance().getAge()));
		jobText.setText(Aid.getUserInstance().getJob() == null ? "" : Aid
				.getUserInstance().getJob());
		relationshipText
				.setText(Aid.getUserInstance().getRelationship() == null ? ""
						: Aid.getUserInstance().getRelationship());
		homeAddressText
				.setText(Aid.getUserInstance().getHomeAddress() == null ? ""
						: Aid.getUserInstance().getHomeAddress());
		workAddressText
				.setText(Aid.getUserInstance().getWorkAddress() == null ? ""
						: Aid.getUserInstance().getWorkAddress());
		mobilePhoneNumText
				.setText(Aid.getUserInstance().getMobilePhoneNum() == null ? ""
						: Aid.getUserInstance().getMobilePhoneNum());
		homePhoneNumText
				.setText(Aid.getUserInstance().getHomePhoneNum() == null ? ""
						: Aid.getUserInstance().getHomePhoneNum());
		workPhoneNumText
				.setText(Aid.getUserInstance().getWorkPhoneNum() == null ? ""
						: Aid.getUserInstance().getWorkPhoneNum());
	}

	private void startGetUser()
	{
		if (null == aid || null == currentUser)
		{
			Util.alert(getApplicationContext(), "用户信息异常，请重新登录！");
			intent = new Intent();
			intent.setClass(getApplicationContext(), LoginActivity.class);
			startActivity(intent);
			finish();
		}
		UserGetRequestParam param = new UserGetRequestParam(String.valueOf(aid
				.getCurrentUid()));
		param.setFields(UserGetRequestParam.FIELDS_ALL);
		param.setUid(String.valueOf(aid.getCurrentUid()));
		try
		{
			progressDialog = new ProgressDialog(PersonalInfoActivity.this);
			progressDialog.setMessage("正在获取信息，请稍后...");
			progressDialog.show();
			AsyncAid aAid = new AsyncAid(aid);
			// 对结果进行监听
			aAid.getUserInfo(param, new UserGetListener());
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 监听获取用户的基本信息
	 * 
	 * @author Administrator
	 * 
	 */
	private class UserGetListener extends
			AbstractRequestListener<UserGetResponseBean>
	{
		private Handler	handler	= new Handler();

		@Override
		public void onError(AidError AidError)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (PersonalInfoActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
					}
					Util.alert(PersonalInfoActivity.this, "获取用户信息失败");
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
					if (PersonalInfoActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						Util.alert(PersonalInfoActivity.this, "获取用户信息失败");
					}
				}
			});
		}

		@Override
		public void onComplete(final UserGetResponseBean bean)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (PersonalInfoActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						if (null != bean.getUser())
						{
							Aid.setUser(bean.getUser());
							setText();
						}
						else
						{
							Util.alert(PersonalInfoActivity.this, "获取用户信息失败");
						}
					}
				}
			});
		}
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
}
