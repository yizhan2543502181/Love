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
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.activity.main.LoginActivity;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractAidRequestActivity;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractRequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.Aid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AsyncAid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.User;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserSetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserSetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.util.AidError;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

/**
 * @author Administrator
 * 
 */
public class PersonalInfoModifyActivity extends AbstractAidRequestActivity
		implements OnClickListener
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
		nameText = (EditText) findViewById(R.id.personal_info_modify_name);
		patientText = (EditText) findViewById(R.id.personal_info_modify_patient_name);
		sexText = (EditText) findViewById(R.id.personal_info_modify_sex);
		ageText = (EditText) findViewById(R.id.personal_info_modify_age);
		jobText = (EditText) findViewById(R.id.personal_info_modify_job);
		relationshipText = (EditText) findViewById(R.id.personal_info_modify_relationship);
		homeAddressText = (EditText) findViewById(R.id.personal_info_modify_home_address);
		workAddressText = (EditText) findViewById(R.id.personal_info_modify_work_address);
		mobilePhoneNumText = (EditText) findViewById(R.id.personal_info_modify_mobile_phone_num);
		homePhoneNumText = (EditText) findViewById(R.id.personal_info_modify_home_phone_num);
		workPhoneNumText = (EditText) findViewById(R.id.personal_info_modify_work_phone_num);
		okBtn = (Button) findViewById(R.id.personal_info_modify_ok_btn);
		okBtn.setOnClickListener(this);
		if (null != Aid.getUserInstance())
		{
			setText();
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

	private void startSetUser()
	{
		if (null == aid)
		{
			Util.alert(getApplicationContext(), "用户信息异常，请重新登登录！");
			intent.setClass(getApplicationContext(), LoginActivity.class);
			startActivity(intent);
			finish();
		}
		User user = new User();
		// =====================================================================================================
		// 此处设置User
		// 当然要考虑异常，比如年龄输入的不是数字等等
		// =====================================================================================================
		user.setAge(Integer.parseInt(ageText.getText().toString()));
		UserSetRequestParam param = new UserSetRequestParam();
		param.setFields(UserSetRequestParam.FIELDS_ALL);
		param.setUser(user);
		try
		{
			progressDialog = new ProgressDialog(PersonalInfoModifyActivity.this);
			progressDialog.setTitle("提示");
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
	 * 监听获取用户的基本信息
	 * 
	 * @author Administrator
	 * 
	 */
	private class UserSetListener extends
			AbstractRequestListener<UserSetResponseBean>
	{
		private Handler	handler;

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
					}
					Util.alert(PersonalInfoModifyActivity.this, "设置失败，请重试！");
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
						Util.alert(PersonalInfoModifyActivity.this, "设置失败，请重试！");
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
									"设置失败，请重试！");
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
		case R.id.personal_info_modify_ok_btn:
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
