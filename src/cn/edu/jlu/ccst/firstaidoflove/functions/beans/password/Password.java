package cn.edu.jlu.ccst.firstaidoflove.functions.beans.password;

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
public class Password implements Parcelable
{
	/**
	 * 用户的id
	 */
	private long	uid			= -1;
	private String	oldPassword	= null;
	private String	newPassword	= null;

	public Password()
	{
		super();
	}

	public long getUid()
	{
		return uid;
	}

	public void setUid(long uid)
	{
		this.uid = uid;
	}

	public String getOldPassword()
	{
		return oldPassword;
	}

	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
	}

	public String getNewPassword()
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	public Password parse(JSONObject object) throws AidException
	{
		if (object == null)
		{
			return null;
		}
		uid = object.optLong(Constant.KEY_UID);
		oldPassword = object.optString(Constant.KEY_OLD_PASSWORD);
		newPassword = object.optString(Constant.KEY_NEW_PASSWORD);
		return this;
	}

	public Password(long uid, String oldPassword, String newPassword)
	{
		super();
		this.uid = uid;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	@Override
	public String toString()
	{
		return "Password [uid=" + uid + ", oldPassword=" + oldPassword
				+ ", newPassword=" + newPassword + "]";
	}

	public String toString2()
	{
		return uid + "," + oldPassword + "," + newPassword;
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
		if (null != oldPassword)
		{
			bundle.putString(Constant.KEY_OLD_PASSWORD, oldPassword);
		}
		if (null != newPassword)
		{
			bundle.putString(Constant.KEY_NEW_PASSWORD, newPassword);
		}
		dest.writeBundle(bundle);
	}

	public static final Parcelable.Creator<Password>	CREATOR	= new Parcelable.Creator<Password>() {
																	@Override
																	public Password createFromParcel(
																			Parcel in)
																	{
																		return new Password(
																				in);
																	}

																	@Override
																	public Password[] newArray(
																			int size)
																	{
																		return new Password[size];
																	}
																};

	public Password(Parcel in)
	{
		Bundle bundle = in.readBundle();
		if (bundle.containsKey(Constant.KEY_UID))
		{
			uid = bundle.getLong(Constant.KEY_UID);
		}
		if (bundle.containsKey(Constant.KEY_OLD_PASSWORD))
		{
			oldPassword = bundle.getString(Constant.KEY_OLD_PASSWORD);
		}
		if (bundle.containsKey(Constant.KEY_NEW_PASSWORD))
		{
			newPassword = bundle.getString(Constant.KEY_NEW_PASSWORD);
		}
	}

	public Bundle getParams()
	{
		Bundle bundle = new Bundle();
		if (-1 != uid)
		{
			bundle.putLong(Constant.KEY_UID, uid);
		}
		if (null != oldPassword)
		{
			bundle.putString(Constant.KEY_OLD_PASSWORD, oldPassword);
		}
		if (null != newPassword)
		{
			bundle.putString(Constant.KEY_NEW_PASSWORD, newPassword);
		}
		return bundle;
	}
}
