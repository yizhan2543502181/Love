package cn.edu.jlu.ccst.firstaidoflove.functions.beans.medicalHistory;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;

/**
 * 用户实体类
 * 
 * @author wangchangshuai 415939252@qq.com
 * 
 */
public class MedicalRecord implements Parcelable
{
	/**
	 * 监护对象的id
	 */
	private long	pid				= -1;
	/**
	 * 监护对象名字
	 */
	private String	pname			= null;
	private String	sex				= null;
	private int		age				= -1;
	private String	result			= null;
	private String	time			= null;
	private String	bloodPressure	= null;
	private String	bloodSugar		= null;
	private String	heartRate		= null;
	private String	recordOther		= null;

	public MedicalRecord()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public MedicalRecord(long pid, String pname, String sex, int age,
			String result, String time, String bloodPressure,
			String bloodSugar, String heartRate, String recordOther)
	{
		super();
		this.pid = pid;
		this.pname = pname;
		this.sex = sex;
		this.age = age;
		this.result = result;
		this.time = time;
		this.bloodPressure = bloodPressure;
		this.bloodSugar = bloodSugar;
		this.heartRate = heartRate;
		this.recordOther = recordOther;
	}

	public MedicalRecord parse(JSONObject object) throws AidException
	{
		if (object == null)
		{
			return null;
		}
		pid = object.optLong(Constant.KEY_PID);
		pname = object.optString(Constant.KEY_PNAME);
		sex = object.optString(Constant.KEY_SEX);
		age = Integer.parseInt(object.optString(Constant.KEY_AGE));
		result = object.optString(Constant.KEY_RESULT);
		time = object.optString(Constant.KEY_TIME);
		bloodPressure = object.optString(Constant.KEY_BLOOD_PRESSURE);
		bloodSugar = object.optString(Constant.KEY_BLOOD_SUGAR);
		heartRate = object.optString(Constant.KEY_HEART_RATE);
		recordOther = object.optString(Constant.KEY_HEART_RECORD_OTHER);
		return this;
	}

	public long getPid()
	{
		return pid;
	}

	public void setPid(long pid)
	{
		this.pid = pid;
	}

	public String getPname()
	{
		return pname;
	}

	public void setPname(String pname)
	{
		this.pname = pname;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public String getBloodPressure()
	{
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure)
	{
		this.bloodPressure = bloodPressure;
	}

	public String getBloodSugar()
	{
		return bloodSugar;
	}

	public void setBloodSugar(String bloodSugar)
	{
		this.bloodSugar = bloodSugar;
	}

	public String getHeartRate()
	{
		return heartRate;
	}

	public void setHeartRate(String heartRate)
	{
		this.heartRate = heartRate;
	}

	public String getRecordOther()
	{
		return recordOther;
	}

	public void setRecordOther(String recordOther)
	{
		this.recordOther = recordOther;
	}

	/**
	 * @return the creator
	 */
	public static Parcelable.Creator<MedicalRecord> getCreator()
	{
		return MedicalRecord.CREATOR;
	}

	@Override
	public int describeContents()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		Bundle bundle = new Bundle();
		if (-1 != pid)
		{
			bundle.putLong(Constant.KEY_PID, pid);
		}
		if (null != pname)
		{
			bundle.putString(Constant.KEY_PNAME, pname);
		}
		if (null != sex)
		{
			bundle.putString(Constant.KEY_SEX, sex);
		}
		if (-1 != age)
		{
			bundle.putString(Constant.KEY_AGE, String.valueOf(age));
		}
		if (null != result)
		{
			bundle.putString(Constant.KEY_RESULT, result);
		}
		if (null != time)
		{
			bundle.putString(Constant.KEY_TIME, time);
		}
		if (null != bloodPressure)
		{
			bundle.putString(Constant.KEY_BLOOD_PRESSURE, bloodPressure);
		}
		if (null != bloodSugar)
		{
			bundle.putString(Constant.KEY_BLOOD_SUGAR, bloodSugar);
		}
		if (null != heartRate)
		{
			bundle.putString(Constant.KEY_HEART_RATE, heartRate);
		}
		if (null != recordOther)
		{
			bundle.putString(Constant.KEY_HEART_RECORD_OTHER, recordOther);
		}
		dest.writeBundle(bundle);
	}

	public static final Parcelable.Creator<MedicalRecord>	CREATOR	= new Parcelable.Creator<MedicalRecord>() {
																		@Override
																		public MedicalRecord createFromParcel(
																				Parcel in)
																		{
																			try
																			{
																				return new MedicalRecord(
																						in);
																			}
																			catch (AidException e)
																			{
																				e.printStackTrace();
																				return null;
																			}
																		}

																		@Override
																		public MedicalRecord[] newArray(
																				int size)
																		{
																			return new MedicalRecord[size];
																		}
																	};

	public MedicalRecord(Parcel in) throws AidException
	{
		Bundle bundle = in.readBundle();
		if (bundle.containsKey(Constant.KEY_PID))
		{
			pid = bundle.getLong(Constant.KEY_PID);
		}
		if (bundle.containsKey(Constant.KEY_PNAME))
		{
			pname = bundle.getString(Constant.KEY_PNAME);
		}
		if (bundle.containsKey(Constant.KEY_AGE))
		{
			age = Integer.parseInt(bundle.getString(Constant.KEY_AGE));
		}
		if (bundle.containsKey(Constant.KEY_SEX))
		{
			sex = bundle.getString(Constant.KEY_SEX);
		}
		if (bundle.containsKey(Constant.KEY_RESULT))
		{
			result = bundle.getString(Constant.KEY_RESULT);
		}
		if (bundle.containsKey(Constant.KEY_TIME))
		{
			time = bundle.getString(Constant.KEY_TIME);
		}
		if (bundle.containsKey(Constant.KEY_BLOOD_PRESSURE))
		{
			bloodPressure = bundle.getString(Constant.KEY_BLOOD_PRESSURE);
		}
		if (bundle.containsKey(Constant.KEY_BLOOD_SUGAR))
		{
			bloodSugar = bundle.getString(Constant.KEY_BLOOD_SUGAR);
		}
		if (bundle.containsKey(Constant.KEY_HEART_RATE))
		{
			heartRate = bundle.getString(Constant.KEY_HEART_RATE);
		}
		if (bundle.containsKey(Constant.KEY_HEART_RECORD_OTHER))
		{
			recordOther = bundle.getString(Constant.KEY_HEART_RECORD_OTHER);
		}
	}
}
