/**
 * 
 */
package cn.edu.jlu.ccst.firstaidoflove.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import cn.edu.jlu.ccst.firstaidoflove.BaseActivity;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractRequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.Aid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidError;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AsyncAid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.User;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserSetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserSetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

/**
 * @author Administrator
 * 
 */
public class PersonalInfoModifyActivity extends BaseActivity implements
		OnClickListener
{
	private EditText	nameText			= null;
	private EditText	patientText			= null;
	private EditText	sexText				= null;
	private EditText	ageText				= null;
	private EditText	jobText				= null;
	private EditText	relationshipText	= null;
	private EditText	homeAddressText		= null;
	private EditText	workAddressText		= null;
	private EditText	mobilePhoneNumText	= null;
	private EditText	homePhoneNumText	= null;
	private EditText	workPhoneNumText	= null;
	private Button		okBtn				= null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.personal_info_modify_activity_layout);
		initView();
	}

	private void initView()
	{
		nameText = (EditText) findViewById(R.id.name);
		patientText = (EditText) findViewById(R.id.patient_name);
		sexText = (EditText) findViewById(R.id.sex);
		ageText = (EditText) findViewById(R.id.age);
		jobText = (EditText) findViewById(R.id.job);
		relationshipText = (EditText) findViewById(R.id.relationship);
		homeAddressText = (EditText) findViewById(R.id.home_address);
		workAddressText = (EditText) findViewById(R.id.work_address);
		mobilePhoneNumText = (EditText) findViewById(R.id.mobile_phone_num);
		homePhoneNumText = (EditText) findViewById(R.id.home_phone_num);
		workPhoneNumText = (EditText) findViewById(R.id.work_phone_num);
		okBtn = (Button) findViewById(R.id.ok_btn);
		okBtn.setOnClickListener(this);
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
			progressDialog = new ProgressDialog(PersonalInfoModifyActivity.this);
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
					if (PersonalInfoModifyActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						Util.alert(PersonalInfoModifyActivity.this, "获取用户信息失败");
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
					if (PersonalInfoModifyActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						Util.alert(PersonalInfoModifyActivity.this, "获取用户信息失败");
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
					if (PersonalInfoModifyActivity.this != null)
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
									.getUserInstance(PersonalInfoModifyActivity.this);
							setText();
						}
						else
						{
							Util.alert(PersonalInfoModifyActivity.this,
									"获取用户信息失败");
						}
					}
				}
			});
		}
	}

	private void startSetUser()
	{
		User user = new User();
		// =====================================================================================================
		// 此处设置User
		// 当然要考虑异常，比如年龄输入的不是数字等等
		int age = -1;
		try
		{
			age = Integer.parseInt(ageText.getText().toString());
		}
		catch (Exception e)
		{}
		user.setAge(age);
		user.setHomeAddress(homeAddressText.getText().toString());
		user.setHomePhoneNum(homePhoneNumText.getText().toString());
		user.setJob(jobText.getText().toString());
		user.setMobilePhoneNum(mobilePhoneNumText.getText().toString());
		user.setPid(currentUser.getPid());
		user.setPname(patientText.getText().toString());
		user.setRelationship(relationshipText.getText().toString());
		user.setSex(sexText.getText().toString());
		user.setUid(currentUser.getUid());
		user.setUname(currentUser.getUname());
		user.setWorkAddress(workAddressText.getText().toString());
		user.setWorkPhoneNum(workPhoneNumText.getText().toString());
		// =====================================================================================================
		UserSetRequestParam param = new UserSetRequestParam();
		param.setFields(UserSetRequestParam.FIELDS_ALL);
		param.setUser(user);
		try
		{
			progressDialog = new ProgressDialog(PersonalInfoModifyActivity.this);
			progressDialog.setMessage("正在更新，请稍后...");
			progressDialog.show();
			AsyncAid aAid = new AsyncAid(aid);
			// 对结果进行监听
			aAid.setUserInfo(param, new UserSetListener());
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 监听设置用户的基本信息
	 * 
	 * @author Administrator
	 * 
	 */
	private class UserSetListener extends
			AbstractRequestListener<UserSetResponseBean>
	{
		private Handler	handler	= new Handler();

		@Override
		public void onError(AidError AidError)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (PersonalInfoModifyActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						Util.alert(PersonalInfoModifyActivity.this,
								"更新信息失败，请重试！");
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
					if (PersonalInfoModifyActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						Util.alert(PersonalInfoModifyActivity.this,
								"更新信息失败，请重试！");
					}
				}
			});
		}

		@Override
		public void onComplete(final UserSetResponseBean bean)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (PersonalInfoModifyActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						if (null != bean.getResultCode())
						{
							if (bean.getResultCode().getResultCode() == UserSetResponseBean.SUCCESS)
							{
								Util.alert(PersonalInfoModifyActivity.this,
										"设置成功！");
								finish();
							}
						}
						else
						{
							Util.alert(PersonalInfoModifyActivity.this,
									"更新信息失败，请重试！");
						}
					}
				}
			});
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.ok_btn:
			startSetUser();
			break;
		default:
			break;
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
