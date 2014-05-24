package cn.edu.jlu.ccst.firstaidoflove.functions.hotAddress;

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
public class HotAddress implements Parcelable
{
	private long	hid		= -1;
	/**
	 * 监护对象的id
	 */
	private long	pid		= -1;
	/**
	 * 监护对象名字
	 */
	private String	pname	= null;

	public HotAddress()
	{
		super();
	}

	public HotAddress parse(JSONObject object) throws AidException
	{
		if (object == null)
		{
			return null;
		}
		hid = object.optLong(Constant.KEY_HID);
		pid = object.optLong(Constant.KEY_PID);
		pname = object.optString(Constant.KEY_PNAME);
		return this;
	}

	public HotAddress(long hid, long pid, String pname, double longtitude,
			double latitude, String time)
	{
		super();
		this.hid = hid;
		this.pid = pid;
		this.pname = pname;
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
	 * @return the creator
	 */
	public static Parcelable.Creator<HotAddress> getCreator()
	{
		return HotAddress.CREATOR;
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

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		Bundle bundle = new Bundle();
		if (-1 != hid)
		{
			bundle.putLong(Constant.KEY_HID, hid);
		}
		if (-1 != pid)
		{
			bundle.putLong(Constant.KEY_PID, pid);
		}
		if (null != pname)
		{
			bundle.putString(Constant.KEY_PNAME, pname);
		}
		dest.writeBundle(bundle);
	}

	public static final Parcelable.Creator<HotAddress>	CREATOR	= new Parcelable.Creator<HotAddress>() {
																	@Override
																	public HotAddress createFromParcel(
																			Parcel in)
																	{
																		return new HotAddress(
																				in);
																	}

																	@Override
																	public HotAddress[] newArray(
																			int size)
																	{
																		return new HotAddress[size];
																	}
																};

	public HotAddress(Parcel in)
	{
		Bundle bundle = in.readBundle();
		if (bundle.containsKey(Constant.KEY_HID))
		{
			hid = bundle.getLong(Constant.KEY_HID);
		}
		if (bundle.containsKey(Constant.KEY_PID))
		{
			pid = bundle.getLong(Constant.KEY_PID);
		}
		if (bundle.containsKey(Constant.KEY_PNAME))
		{
			pname = bundle.getString(Constant.KEY_PNAME);
		}
	}
}
