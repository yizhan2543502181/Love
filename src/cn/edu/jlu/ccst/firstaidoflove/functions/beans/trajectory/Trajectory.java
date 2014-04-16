package cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.login.Login;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;

/**
 * 用户实体类
 * 
 * @author wangchangshuai 415939252@qq.com
 * 
 */
public class Trajectory implements Parcelable
{
	/**
	 * 监护对象的id
	 */
	private long	pid			= -1;
	/**
	 * 监护对象名字
	 */
	private String	pname		= null;
	/**
	 * 事故地点的经度
	 */
	private double	longtitude	= 0;
	/**
	 * 事故地点的纬度
	 */
	private double	latitude	= 0;
	/**
	 * 事故发生时间
	 */
	private String	time		= null;

	public Trajectory()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Trajectory parse(JSONObject object) throws AidException
	{
		if (object == null)
		{
			return null;
		}
		pid = object.optLong(Constant.KEY_PID);
		pname = object.optString(Constant.KEY_PNAME);
		longtitude = Double.parseDouble(object.optString(Constant.KEY_LON));
		latitude = Double.parseDouble(object.optString(Constant.KEY_LAT));
		time = object.optString(Constant.KEY_TIME);
		return this;
	}

	public Trajectory(long pid, String pname, double longtitude,
			double latitude, String time)
	{
		super();
		this.pid = pid;
		this.pname = pname;
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.time = time;
	}

	public Trajectory(Login login)
	{
		super();
		pid = login.getPid();
		pname = login.getPname();
		longtitude = login.getLon();
		latitude = login.getLat();
		time = null;
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
				+ "]";
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
	public static Parcelable.Creator<Trajectory> getCreator()
	{
		return Trajectory.CREATOR;
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
		if (0 != longtitude)
		{
			bundle.putDouble(Constant.KEY_LON, longtitude);
		}
		if (0 != latitude)
		{
			bundle.putDouble(Constant.KEY_LAT, latitude);
		}
		if (null != time)
		{
			bundle.putString(Constant.KEY_TIME, time);
		}
		dest.writeBundle(bundle);
	}

	public static final Parcelable.Creator<Trajectory>	CREATOR	= new Parcelable.Creator<Trajectory>() {
																	@Override
																	public Trajectory createFromParcel(
																			Parcel in)
																	{
																		return new Trajectory(
																				in);
																	}

																	@Override
																	public Trajectory[] newArray(
																			int size)
																	{
																		return new Trajectory[size];
																	}
																};

	public Trajectory(Parcel in)
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
		if (bundle.containsKey(Constant.KEY_LON))
		{
			longtitude = bundle.getDouble(Constant.KEY_LON);
		}
		if (bundle.containsKey(Constant.KEY_LAT))
		{
			latitude = bundle.getDouble(Constant.KEY_LAT);
		}
		if (bundle.containsKey(Constant.KEY_TIME))
		{
			time = bundle.getString(Constant.KEY_TIME);
		}
	}
}
