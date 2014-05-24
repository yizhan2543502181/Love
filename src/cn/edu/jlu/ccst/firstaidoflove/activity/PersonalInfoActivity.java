/**
 * 
 */
package cn.edu.jlu.ccst.firstaidoflove.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.TextView;
import cn.edu.jlu.ccst.firstaidoflove.BaseActivity;
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
public class PersonalInfoActivity extends BaseActivity
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
		nameText = (TextView) findViewById(R.id.name);
		patientText = (TextView) findViewById(R.id.patient_name);
		sexText = (TextView) findViewById(R.id.sex);
		ageText = (TextView) findViewById(R.id.age);
		jobText = (TextView) findViewById(R.id.job);
		relationshipText = (TextView) findViewById(R.id.relationship);
		homeAddressText = (TextView) findViewById(R.id.home_address);
		workAddressText = (TextView) findViewById(R.id.work_address);
		mobilePhoneNumText = (TextView) findViewById(R.id.mobile_phone_num);
		homePhoneNumText = (TextView) findViewById(R.id.home_phone_num);
		workPhoneNumText = (TextView) findViewById(R.id.work_phone_num);
		startGetUser();
	}

	private void setText()
	{
		nameText.setText(currentUser.getUname() == null ? "" : currentUser
				.getUname());
		patientText.setText(currentUser.getPname() == null ? "" : currentUser
				.getPname());
		sexText.setText(currentUser.getSex() == null ? "" : currentUser
				.getSex());
		ageText.setText(String.valueOf(currentUser.getAge() == -1 ? ""
				: currentUser.getAge()));
		jobText.setText(currentUser.getJob() == null ? "" : currentUser
				.getJob());
		relationshipText.setText(currentUser.getRelationship() == null ? ""
				: currentUser.getRelationship());
		homeAddressText.setText(currentUser.getHomeAddress() == null ? ""
				: currentUser.getHomeAddress());
		workAddressText.setText(currentUser.getWorkAddress() == null ? ""
				: currentUser.getWorkAddress());
		mobilePhoneNumText.setText(currentUser.getMobilePhoneNum() == null ? ""
				: currentUser.getMobilePhoneNum());
		homePhoneNumText.setText(currentUser.getHomePhoneNum() == null ? ""
				: currentUser.getHomePhoneNum());
		workPhoneNumText.setText(currentUser.getWorkPhoneNum() == null ? ""
				: currentUser.getWorkPhoneNum());
	}

	private void startGetUser()
	{
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
						Util.alert(PersonalInfoActivity.this, "获取用户信息失败");
					}
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
							Aid.saveUser(bean.getUser());
							currentUser = Aid
									.getUserInstance(PersonalInfoActivity.this);
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
