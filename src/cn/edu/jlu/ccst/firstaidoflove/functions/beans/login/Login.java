package cn.edu.jlu.ccst.firstaidoflove.functions.beans.login;

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
public class Login implements Parcelable
{
	private String	uname	= null;
	private long	uid		= -1;
	private String	pname	= null;
	private long	pid		= -1;
	private double	lon		= 0;
	private double	lat		= 0;

	public Login()
	{
		super();
	}

	public Login(String uname, long uid, String pname, long pid, double lon,
			double lat)
	{
		super();
		this.uname = uname;
		this.uid = uid;
		this.pname = pname;
		this.pid = pid;
		this.lon = lon;
		this.lat = lat;
	}

	/**
	 * @return the uid
	 */
	public long getUid()
	{
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(long uid)
	{
		this.uid = uid;
	}

	/**
	 * @return the uname
	 */
	public String getUname()
	{
		return uname;
	}

	/**
	 * @param uname
	 *            the uname to set
	 */
	public void setUname(String uname)
	{
		this.uname = uname;
	}

	/**
	 * @return the pid
	 */
	public long getPid()
	{
		return pid;
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
	 * @return the pname
	 */
	public String getPname()
	{
		return pname;
	}

	/**
	 * @return the lon
	 */
	public double getLon()
	{
		return lon;
	}

	/**
	 * @return the lat
	 */
	public double getLat()
	{
		return lat;
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
	 * @param lon
	 *            the lon to set
	 */
	public void setLon(double lon)
	{
		this.lon = lon;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(double lat)
	{
		this.lat = lat;
	}

	public Login parse(JSONObject jsonObject) throws AidException
	{
		if (jsonObject == null)
		{
			return null;
		}
		if (jsonObject.has(Constant.KEY_UID))
		{
			uid = Long.parseLong(jsonObject.optString(Constant.KEY_UID));
		}
		if (jsonObject.has(Constant.KEY_UNAME))
		{
			uname = jsonObject.optString(Constant.KEY_UNAME);
		}
		if (jsonObject.has(Constant.KEY_PID))
		{
			pid = Long.parseLong(jsonObject.optString(Constant.KEY_PID));
		}
		if (jsonObject.has(Constant.KEY_PNAME))
		{
			pname = jsonObject.optString(Constant.KEY_PNAME);
		}
		if (jsonObject.has(Constant.KEY_LON))
		{
			lon = Double.parseDouble(jsonObject.optString(Constant.KEY_LON));
		}
		if (jsonObject.has(Constant.KEY_LAT))
		{
			lat = Double.parseDouble(jsonObject.optString(Constant.KEY_LAT));
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Login [uname=" + uname + ", uid=" + uid + ", pname=" + pname
				+ ", pid=" + pid + ", lon=" + lon + ", lat=" + lat + "]";
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		Bundle bundle = new Bundle();
		if (-1 != uid)
		{
			bundle.putLong(Constant.KEY_UID, uid);
		}
		if (null != uname)
		{
			bundle.putString(Constant.KEY_UNAME, uname);
		}
		if (-1 != pid)
		{
			bundle.putLong(Constant.KEY_PID, pid);
		}
		if (null != pname)
		{
			bundle.putString(Constant.KEY_PNAME, pname);
		}
		if (-1 != lon)
		{
			bundle.putDouble(Constant.KEY_LON, lon);
		}
		if (-1 != lat)
		{
			bundle.putDouble(Constant.KEY_LAT, lat);
		}
		dest.writeBundle(bundle);
	}

	public static final Parcelable.Creator<Login>	CREATOR	= new Parcelable.Creator<Login>() {
																@Override
																public Login createFromParcel(
																		Parcel in)
																{
																	return new Login(
																			in);
																}

																@Override
																public Login[] newArray(
																		int size)
																{
																	return new Login[size];
																}
															};

	public Login(Parcel in)
	{
		Bundle bundle = in.readBundle();
		if (bundle.containsKey(Constant.KEY_UID))
		{
			uid = bundle.getLong(Constant.KEY_UID);
		}
		if (bundle.containsKey(Constant.KEY_UNAME))
		{
			uname = bundle.getString(Constant.KEY_UNAME);
		}
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
			lon = bundle.getDouble(Constant.KEY_LON);
		}
		if (bundle.containsKey(Constant.KEY_LAT))
		{
			lat = bundle.getDouble(Constant.KEY_LAT);
		}
	}
}
