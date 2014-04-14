package cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.login.Login;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.User;
import cn.edu.jlu.ccst.firstaidoflove.util.AidException;

/**
 * 用户实体类
 * 
 * @author wangchangshuai 415939252@qq.com
 * 
 */
public class Accident implements Parcelable
{
	// "pid":"46465165","pname":"小李","longitude":"23.4587","latitude":"65.2365","time":"2014.04.08 23:56:23"
	public static final String	ACCIDENT_LABLE		= "accident";
	public static final String	KEY_TIME			= "time";
	public static final String	KEY_MEDICAL_HISTORY	= "medical_history";
	/**
	 * 监护对象的id
	 */
	private long				pid					= -1;
	/**
	 * 监护对象名字
	 */
	private String				pname				= null;
	/**
	 * 事故地点的经度
	 */
	private double				longtitude			= 0;
	/**
	 * 事故地点的纬度
	 */
	private double				latitude			= 0;
	/**
	 * 事故发生时间
	 */
	private String				time				= null;
	/**
	 * 最近病史
	 */
	private String				medicalHistory		= null;

	public Accident()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Accident parse(JSONObject object) throws AidException
	{
		if (object == null)
		{
			return null;
		}
		pid = object.optLong(User.KEY_PID);
		pname = object.optString(User.KEY_PNAME);
		longtitude = Double.parseDouble(object.optString(Login.KEY_LON));
		latitude = Double.parseDouble(object.optString(Login.KEY_LAT));
		time = object.optString(Accident.KEY_TIME);
		medicalHistory = object.optString(Accident.KEY_MEDICAL_HISTORY);
		return this;
	}

	public Accident(long pid, String pname, double longtitude, double latitude,
			String time, String medicalHistory)
	{
		super();
		this.pid = pid;
		this.pname = pname;
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.time = time;
		this.medicalHistory = medicalHistory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Accident [pid=" + pid + ", pname=" + pname + ", longtitude="
				+ longtitude + ", latitude=" + latitude + ", time=" + time
				+ ", medicalHistory=" + medicalHistory + "]";
	}

	/**
	 * @return the pid
	 */
	public long getPid()
	{
		return pid;
	}

	/**
	 * @return the pname
	 */
	public String getPname()
	{
		return pname;
	}

	/**
	 * @return the longtitude
	 */
	public double getLongtitude()
	{
		return longtitude;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude()
	{
		return latitude;
	}

	/**
	 * @return the time
	 */
	public String getTime()
	{
		return time;
	}

	/**
	 * @return the creator
	 */
	public static Parcelable.Creator<Accident> getCreator()
	{
		return Accident.CREATOR;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(long pid)
	{
		this.pid = pid;
	}

	/**
	 * @param pname
	 *            the pname to set
	 */
	public void setPname(String pname)
	{
		this.pname = pname;
	}

	/**
	 * @param longtitude
	 *            the longtitude to set
	 */
	public void setLongtitude(double longtitude)
	{
		this.longtitude = longtitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time)
	{
		this.time = time;
	}

	/**
	 * @return the medicalHistory
	 */
	public String getMedicalHistory()
	{
		return medicalHistory;
	}

	/**
	 * @param medicalHistory
	 *            the medicalHistory to set
	 */
	public void setMedicalHistory(String medicalHistory)
	{
		this.medicalHistory = medicalHistory;
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
			bundle.putLong(User.KEY_PID, pid);
		}
		if (null != pname)
		{
			bundle.putString(User.KEY_PNAME, pname);
		}
		if (0 != longtitude)
		{
			bundle.putDouble(Login.KEY_LON, longtitude);
		}
		if (0 != latitude)
		{
			bundle.putDouble(Login.KEY_LAT, latitude);
		}
		if (null != time)
		{
			bundle.putString(Accident.KEY_TIME, time);
		}
		if (null != medicalHistory)
		{
			bundle.putString(Accident.KEY_MEDICAL_HISTORY, medicalHistory);
		}
		dest.writeBundle(bundle);
	}

	public static final Parcelable.Creator<Accident>	CREATOR	= new Parcelable.Creator<Accident>() {
																	@Override
																	public Accident createFromParcel(
																			Parcel in)
																	{
																		return new Accident(
																				in);
																	}

																	@Override
																	public Accident[] newArray(
																			int size)
																	{
																		return new Accident[size];
																	}
																};

	public Accident(Parcel in)
	{
		Bundle bundle = in.readBundle();
		if (bundle.containsKey(User.KEY_PID))
		{
			pid = bundle.getLong(User.KEY_PID);
		}
		if (bundle.containsKey(User.KEY_PNAME))
		{
			pname = bundle.getString(User.KEY_PNAME);
		}
		if (bundle.containsKey(Login.KEY_LON))
		{
			longtitude = bundle.getDouble(Login.KEY_LON);
		}
		if (bundle.containsKey(Login.KEY_LAT))
		{
			latitude = bundle.getDouble(Login.KEY_LAT);
		}
		if (bundle.containsKey(Accident.KEY_TIME))
		{
			time = bundle.getString(Accident.KEY_TIME);
		}
		if (bundle.containsKey(Accident.KEY_MEDICAL_HISTORY))
		{
			medicalHistory = bundle.getString(Accident.KEY_MEDICAL_HISTORY);
		}
	}
}
