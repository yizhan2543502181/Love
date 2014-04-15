/**
 * 
 */
package cn.edu.jlu.ccst.firstaidoflove.activity;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractAidRequestActivity;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.medicalHistory.MedicalRecord;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;

/**
 * @author Administrator
 * 
 */
public class MedicalRecordInfoActivity extends AbstractAidRequestActivity
{
	private MedicalRecord	medicalRecord		= null;
	private TextView		pnameText			= null;
	private TextView		sexText				= null;
	private TextView		ageText				= null;
	private TextView		resultText			= null;
	private TextView		timeText			= null;
	private TextView		bloodPressureText	= null;
	private TextView		bloodSugarText		= null;
	private TextView		heartRateText		= null;
	private TextView		recordOtherText		= null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Bundle bundle = intent.getExtras();
		if (bundle != null && bundle.containsKey(Constant.MEDICAL_RECORD_LABLE))
		{
			medicalRecord = bundle.getParcelable(Constant.MEDICAL_RECORD_LABLE);
		}
		setContentView(R.layout.medical_record_info_activity_layout);
		initView();
	}

	private void initView()
	{
		pnameText = (TextView) findViewById(R.id.medical_record_info_name);
		sexText = (TextView) findViewById(R.id.medical_record_info_sex);
		ageText = (TextView) findViewById(R.id.medical_record_info_age);
		resultText = (TextView) findViewById(R.id.medical_record_info_result);
		timeText = (TextView) findViewById(R.id.medical_record_info_time);
		bloodPressureText = (TextView) findViewById(R.id.medical_record_info_blood_pressure);
		bloodSugarText = (TextView) findViewById(R.id.medical_record_info_blood_sugar);
		heartRateText = (TextView) findViewById(R.id.medical_record_info_heart_rate);
		recordOtherText = (TextView) findViewById(R.id.medical_record_info_other);
		if (null != medicalRecord)
		{
			pnameText.setText(medicalRecord.getPname());
			sexText.setText(medicalRecord.getSex());
			ageText.setText(String.valueOf(medicalRecord.getAge()));
			resultText.setText(medicalRecord.getResult());
			timeText.setText(medicalRecord.getTime());
			bloodPressureText.setText(medicalRecord.getBloodPressure());
			bloodSugarText.setText(medicalRecord.getBloodSugar());
			heartRateText.setText(medicalRecord.getHeartRate());
			recordOtherText.setText(medicalRecord.getRecordOther());
		}
	}
}
