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
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidError;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AsyncAid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient.Patient;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient.PatientGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient.PatientGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

/**
 * @author Administrator
 * 
 */
public class PatientInfoActivity extends BaseActivity
{
	private TextView	patientText			= null;
	private TextView	sexText				= null;
	private TextView	ageText				= null;
	private TextView	jobText				= null;
	private TextView	homeAddressText		= null;
	private TextView	workAddressText		= null;
	private TextView	mobilePhoneNumText	= null;
	private TextView	homePhoneNumText	= null;
	private TextView	workPhoneNumText	= null;
	private Patient		patient				= null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.patient_info_activity_layout);
		initView();
	}

	private void initView()
	{
		patientText = (TextView) findViewById(R.id.patient_name);
		sexText = (TextView) findViewById(R.id.sex);
		ageText = (TextView) findViewById(R.id.age);
		jobText = (TextView) findViewById(R.id.job);
		homeAddressText = (TextView) findViewById(R.id.home_address);
		workAddressText = (TextView) findViewById(R.id.work_address);
		mobilePhoneNumText = (TextView) findViewById(R.id.mobile_phone_num);
		homePhoneNumText = (TextView) findViewById(R.id.home_phone_num);
		workPhoneNumText = (TextView) findViewById(R.id.work_phone_num);
		startGetPatient();
	}

	private void setText()
	{
		patientText.setText(patient.getPname() == null ? "" : patient
				.getPname());
		sexText.setText(patient.getSex() == null ? "" : patient.getSex());
		ageText.setText(String.valueOf(patient.getAge() == -1 ? "" : patient
				.getAge()));
		jobText.setText(patient.getJob() == null ? "" : patient.getJob());
		homeAddressText.setText(patient.getHomeAddress() == null ? "" : patient
				.getHomeAddress());
		workAddressText.setText(patient.getWorkAddress() == null ? "" : patient
				.getWorkAddress());
		mobilePhoneNumText.setText(patient.getMobilePhoneNum() == null ? ""
				: patient.getMobilePhoneNum());
		homePhoneNumText.setText(patient.getHomePhoneNum() == null ? ""
				: patient.getHomePhoneNum());
		workPhoneNumText.setText(patient.getWorkPhoneNum() == null ? ""
				: patient.getWorkPhoneNum());
	}

	private void startGetPatient()
	{
		PatientGetRequestParam param = new PatientGetRequestParam(
				currentUser.getUid(), currentUser.getPid());
		try
		{
			progressDialog = new ProgressDialog(PatientInfoActivity.this);
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
					if (PatientInfoActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						Util.alert(PatientInfoActivity.this, "获取监护对象信息失败");
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
					if (PatientInfoActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						Util.alert(PatientInfoActivity.this, "获取监护对象信息失败");
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
					if (PatientInfoActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						if (null != bean.getPatient())
						{
							patient = bean.getPatient();
							setText();
						}
						else
						{
							Util.alert(PatientInfoActivity.this, "获取监护对象信息失败");
						}
					}
				}
			});
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
