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
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidError;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AsyncAid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient.Patient;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient.PatientGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient.PatientGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient.PatientSetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient.PatientSetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

/**
 * @author Administrator
 * 
 */
public class PatientInfoModifyActivity extends BaseActivity implements
		OnClickListener
{
	private EditText		patientText			= null;
	private EditText		sexText				= null;
	private EditText		ageText				= null;
	private EditText		jobText				= null;
	private EditText		homeAddressText		= null;
	private EditText		workAddressText		= null;
	private EditText		mobilePhoneNumText	= null;
	private EditText		homePhoneNumText	= null;
	private EditText		workPhoneNumText	= null;
	private static Patient	patient				= null;
	private Button			okBtn				= null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.patient_info_modify_activity_layout);
		initView();
	}

	private void initView()
	{
		patientText = (EditText) findViewById(R.id.patient_name);
		sexText = (EditText) findViewById(R.id.sex);
		ageText = (EditText) findViewById(R.id.age);
		jobText = (EditText) findViewById(R.id.job);
		homeAddressText = (EditText) findViewById(R.id.home_address);
		workAddressText = (EditText) findViewById(R.id.work_address);
		mobilePhoneNumText = (EditText) findViewById(R.id.mobile_phone_num);
		homePhoneNumText = (EditText) findViewById(R.id.home_phone_num);
		workPhoneNumText = (EditText) findViewById(R.id.work_phone_num);
		okBtn = (Button) findViewById(R.id.ok_btn);
		okBtn.setOnClickListener(this);
		if (null != PatientInfoModifyActivity.patient)
		{
			setText();
		}
		else
		{
			startGetPatient();
		}
	}

	private void setText()
	{
		patientText
				.setText(PatientInfoModifyActivity.patient.getPname() == null ? ""
						: PatientInfoModifyActivity.patient.getPname());
		sexText.setText(PatientInfoModifyActivity.patient.getSex() == null ? ""
				: PatientInfoModifyActivity.patient.getSex());
		ageText.setText(String.valueOf(PatientInfoModifyActivity.patient
				.getAge() == -1 ? "" : PatientInfoModifyActivity.patient
				.getAge()));
		jobText.setText(PatientInfoModifyActivity.patient.getJob() == null ? ""
				: PatientInfoModifyActivity.patient.getJob());
		homeAddressText.setText(PatientInfoModifyActivity.patient
				.getHomeAddress() == null ? ""
				: PatientInfoModifyActivity.patient.getHomeAddress());
		workAddressText.setText(PatientInfoModifyActivity.patient
				.getWorkAddress() == null ? ""
				: PatientInfoModifyActivity.patient.getWorkAddress());
		mobilePhoneNumText.setText(PatientInfoModifyActivity.patient
				.getMobilePhoneNum() == null ? ""
				: PatientInfoModifyActivity.patient.getMobilePhoneNum());
		homePhoneNumText.setText(PatientInfoModifyActivity.patient
				.getHomePhoneNum() == null ? ""
				: PatientInfoModifyActivity.patient.getHomePhoneNum());
		workPhoneNumText.setText(PatientInfoModifyActivity.patient
				.getWorkPhoneNum() == null ? ""
				: PatientInfoModifyActivity.patient.getWorkPhoneNum());
	}

	private void startGetPatient()
	{
		PatientGetRequestParam param = new PatientGetRequestParam(
				currentUser.getUid(), currentUser.getPid());
		try
		{
			progressDialog = new ProgressDialog(PatientInfoModifyActivity.this);
			progressDialog.setMessage("正在获取信息，请稍后...");
			progressDialog.show();
			AsyncAid aAid = new AsyncAid(aid);
			// 对结果进行监听
			aAid.getPatientInfo(param, new PatientGetListener());
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
	private class PatientGetListener extends
			AbstractRequestListener<PatientGetResponseBean>
	{
		private Handler	handler	= new Handler();

		@Override
		public void onError(AidError AidError)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (PatientInfoModifyActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						Util.alert(PatientInfoModifyActivity.this, "获取监护对象信息失败");
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
					if (PatientInfoModifyActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						Util.alert(PatientInfoModifyActivity.this, "获取监护对象信息失败");
					}
				}
			});
		}

		@Override
		public void onComplete(final PatientGetResponseBean bean)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (PatientInfoModifyActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						if (null != bean.getPatient())
						{
							PatientInfoModifyActivity.patient = bean
									.getPatient();
							setText();
						}
						else
						{
							Util.alert(PatientInfoModifyActivity.this,
									"获取监护对象信息失败");
						}
					}
				}
			});
		}
	}

	private void startSetPatient()
	{
		Patient patient = new Patient();
		// =====================================================================================================
		// 此处设置Patient
		// 当然要考虑异常，比如年龄输入的不是数字等等
		int age = -1;
		try
		{
			age = Integer.parseInt(ageText.getText().toString());
		}
		catch (Exception e)
		{}
		patient.setAge(age);
		patient.setHomeAddress(homeAddressText.getText().toString());
		patient.setHomePhoneNum(homePhoneNumText.getText().toString());
		patient.setJob(jobText.getText().toString());
		patient.setMobilePhoneNum(mobilePhoneNumText.getText().toString());
		patient.setPid(currentUser.getPid());
		patient.setPname(patientText.getText().toString());
		patient.setSex(sexText.getText().toString());
		patient.setWorkAddress(workAddressText.getText().toString());
		patient.setWorkPhoneNum(workPhoneNumText.getText().toString());
		// =====================================================================================================
		PatientSetRequestParam param = new PatientSetRequestParam();
		param.setFields(PatientSetRequestParam.FIELDS_ALL);
		param.setPatient(patient);
		try
		{
			progressDialog = new ProgressDialog(PatientInfoModifyActivity.this);
			progressDialog.setMessage("正在更新，请稍后...");
			progressDialog.show();
			AsyncAid aAid = new AsyncAid(aid);
			// 对结果进行监听
			aAid.setPatientInfo(param, new PatientSetListener());
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
	private class PatientSetListener extends
			AbstractRequestListener<PatientSetResponseBean>
	{
		private Handler	handler	= new Handler();

		@Override
		public void onError(AidError AidError)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (PatientInfoModifyActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						Util.alert(PatientInfoModifyActivity.this,
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
					if (PatientInfoModifyActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						Util.alert(PatientInfoModifyActivity.this,
								"更新信息失败，请重试！");
					}
				}
			});
		}

		@Override
		public void onComplete(final PatientSetResponseBean bean)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (PatientInfoModifyActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						if (null != bean.getResultCode())
						{
							if (bean.getResultCode().getResultCode() == PatientSetResponseBean.SUCCESS)
							{
								Util.alert(PatientInfoModifyActivity.this,
										"设置成功！");
								finish();
							}
						}
						else
						{
							Util.alert(PatientInfoModifyActivity.this,
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
			startSetPatient();
			break;
		default:
			break;
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();
	}

	@Override
	protected void onRestart()
	{
		super.onRestart();
	}
}
