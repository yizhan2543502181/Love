package cn.edu.jlu.ccst.firstaidoflove.functions.beans.friends;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import cn.edu.jlu.ccst.firstaidoflove.functions.ResponseBean;

/**
 * 
 * @author hecao (he.cao@renren-inc.com)
 * 
 *         单个好友的封装
 * 
 */
public class Friend extends ResponseBean implements Parcelable
{

	private static final String	KEY_UID					= "id";
	private static final String	KEY_NAME				= "name";
	private static final String	KEY_HEADURL				= "headurl";
	private static final String	KEY_HEADURL_WITH_LOGO	= "headurl_with_logo";
	private static final String	KEY_TINYURL_WITH_LOGO	= "tinyurl_with_logo";

	/**
	 * uid
	 */
	private long				uid						= 0;

	/**
	 * 姓名
	 */
	private String				name					= null;

	/**
	 * 头像
	 */
	private String				headurl					= null;

	/**
	 * 带有人人logo的头像
	 */
	private String				headurl_with_logo		= null;

	/**
	 * 带有人人logo的小头像
	 */
	private String				tinyurl_with_logo		= null;

	/**
	 * 注以下变量在寻找好友的时候用到
	 */

	private static final String	KEY_TINYURL				= "tinyurl";
	private static final String	KEY_INFO				= "info";
	private static final String	KEY_IS_FRIEND			= "isFriend";

	/**
	 * 用户的头像(50*50)
	 */
	private String				tinyrul					= null;
	/**
	 * 用户的地域信息，以逗号(,)分隔
	 */
	private String				info					= null;
	/**
	 * 是否为好友，“1”表示是，“0”表示否
	 */
	private int					isFriend;

	public Friend()
	{
		super(null);
	}

	public Friend(String response)
	{
		super(response);
	}

	public Friend(JSONObject object)
	{
		super(null);
		if (object != null)
		{
			uid = object.optLong(KEY_UID);
			name = object.optString(KEY_NAME);
			headurl = object.optString(KEY_HEADURL);
			headurl_with_logo = object.optString(KEY_HEADURL_WITH_LOGO);
			tinyurl_with_logo = object.optString(KEY_TINYURL_WITH_LOGO);

			// 注以下变量在寻找好友的时候用到

			tinyrul = object.optString(KEY_TINYURL);
			info = object.optString(KEY_INFO);
			isFriend = object.optInt(KEY_IS_FRIEND);
		}
	}

	public long getUid()
	{
		return uid;
	}

	public void setUid(long uid)
	{
		this.uid = uid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getHeadurl()
	{
		return headurl;
	}

	public void setHeadurl(String headurl)
	{
		this.headurl = headurl;
	}

	public String getHeadurl_with_logo()
	{
		return headurl_with_logo;
	}

	public void setHeadurl_with_logo(String headurl_with_logo)
	{
		this.headurl_with_logo = headurl_with_logo;
	}

	public String getTinyurl_with_logo()
	{
		return tinyurl_with_logo;
	}

	public void setTinyurl_with_logo(String tinyurl_with_logo)
	{
		this.tinyurl_with_logo = tinyurl_with_logo;
	}

	public String getTinyrul()
	{
		return tinyrul;
	}

	public void setTinyrul(String tinyrul)
	{
		this.tinyrul = tinyrul;
	}

	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}

	public int getIsFriend()
	{
		return isFriend;
	}

	public void setIsFriend(int isFriend)
	{
		this.isFriend = isFriend;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(KEY_UID).append(" = ").append(uid).append("\r\n");
		sb.append(KEY_NAME).append(" = ").append(name).append("\r\n");
		sb.append(KEY_HEADURL).append(" = ").append(headurl).append("\r\n");
		sb.append(KEY_HEADURL_WITH_LOGO).append(" = ").append(headurl_with_logo).append("\r\n");
		sb.append(KEY_TINYURL_WITH_LOGO).append(" = ").append(tinyurl_with_logo).append("\r\n");

		sb.append(KEY_TINYURL).append(" = ").append(tinyrul).append("\r\n");
		sb.append(KEY_INFO).append(" = ").append(info).append("\r\n");
		sb.append(KEY_IS_FRIEND).append(" = ").append(isFriend + "").append("\r\n");
		return sb.toString();
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
		if (uid != 0)
		{
			bundle.putLong(KEY_UID, uid);
		}
		if (name != null)
		{
			bundle.putString(KEY_NAME, name);
		}
		if (headurl != null)
		{
			bundle.putString(KEY_HEADURL, headurl);
		}
		if (headurl_with_logo != null)
		{
			bundle.putString(KEY_HEADURL_WITH_LOGO, headurl_with_logo);
		}
		if (tinyurl_with_logo != null)
		{
			bundle.putString(KEY_TINYURL_WITH_LOGO, tinyurl_with_logo);
		}
		// 以下在寻找好友时用到
		if (tinyrul != null)
		{
			bundle.putString(KEY_TINYURL, tinyrul);
		}
		if (info != null)
		{
			bundle.putString(KEY_INFO, info);
		}
		bundle.putInt(KEY_IS_FRIEND, isFriend);

		dest.writeBundle(bundle);
	}

	public static final Parcelable.Creator<Friend>	CREATOR	= new Parcelable.Creator<Friend>() {
																@Override
																public Friend createFromParcel(
																		Parcel in)
																{
																	return new Friend(in);
																}

																@Override
																public Friend[] newArray(int size)
																{
																	return new Friend[size];
																}
															};

	/**
	 * 序列化构造函数
	 * 
	 * @param in
	 */
	public Friend(Parcel in)
	{
		super(null);
		Bundle bundle = in.readBundle();

		if (bundle.containsKey(KEY_UID))
		{
			uid = bundle.getLong(KEY_UID);
		}
		if (bundle.containsKey(KEY_NAME))
		{
			name = bundle.getString(KEY_NAME);
		}
		if (bundle.containsKey(KEY_HEADURL))
		{
			headurl = bundle.getString(KEY_HEADURL);
		}
		if (bundle.containsKey(KEY_HEADURL_WITH_LOGO))
		{
			headurl_with_logo = bundle.getString(KEY_HEADURL_WITH_LOGO);
		}
		if (bundle.containsKey(KEY_TINYURL_WITH_LOGO))
		{
			tinyurl_with_logo = bundle.getString(KEY_TINYURL_WITH_LOGO);
		}
		if (bundle.containsKey(KEY_TINYURL))
		{
			tinyrul = bundle.getString(KEY_TINYURL);
		}
		if (bundle.containsKey(KEY_INFO))
		{
			info = bundle.getString(KEY_INFO);
		}
		if (bundle.containsKey(KEY_IS_FRIEND))
		{
			isFriend = bundle.getInt(KEY_IS_FRIEND);
		}
	}
}
