package cn.edu.jlu.ccst.firstaidoflove.functions.beans.user;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import cn.edu.jlu.ccst.firstaidoflove.util.AidException;

/**
 * 用户实体类
 * 
 * @author wangchangshuai 415939252@qq.com
 * 
 */
public class User implements Parcelable
{
	public static final String	KEY_UID					= "uid";
	public static final String	KEY_UNAME				= "uname";
	public static final String	KEY_PID					= "pid";
	public static final String	KEY_PNAME				= "pname";
	public static final String	KEY_SEX					= "sex";
	public static final String	KEY_AGE					= "age";
	public static final String	KEY_JOB					= "job";
	public static final String	KEY_RELATIONSHIP		= "relationship";
	public static final String	KEY_HOME_ADDRESS		= "home_address";
	public static final String	KEY_WORK_ADDRESS		= "work_address";
	public static final String	KEY_MOBILE_PHONE_NUM	= "mobile_phone_num";
	public static final String	KEY_HOME_PHONE_NUM		= "home_phone_num";
	public static final String	KEY_WORK_PHONE_NUM		= "work_phone_num";
	/**
	 * 用户id
	 */
	private long				uid						= -1;
	/**
	 * 用户名
	 */
	private String				uname					= null;
	/**
	 * 监护对象的id
	 */
	private long				pid						= -1;
	/**
	 * 监护对象名字
	 */
	private String				pname					= null;
	/**
	 * 性别
	 */
	private String				sex						= null;
	/**
	 * 年龄
	 */
	private int					age						= -1;
	/**
	 * 职业
	 */
	private String				job						= null;
	/**
	 * 和监护对象关系
	 */
	private String				relationship			= null;
	/**
	 * 家庭地址
	 */
	private String				homeAddress				= null;
	/**
	 * 单位地址
	 */
	private String				workAddress				= null;
	/**
	 * 手机号
	 */
	private String				mobilePhoneNum			= null;
	/**
	 * 家庭电话
	 */
	private String				homePhoneNum			= null;
	/**
	 * 单位电话
	 */
	private String				workPhoneNum			= null;

	public User()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public User(long uid, String uname, long pid, String pName, String sex,
			int age, String job, String relationship, String homeAddress,
			String workAddress, String mobilePhoneNum, String homePhoneNum,
			String workPhoneNum)
	{
		super();
		this.uid = uid;
		this.uname = uname;
		this.pid = pid;
		pname = pName;
		this.sex = sex;
		this.age = age;
		this.job = job;
		this.relationship = relationship;
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
		this.mobilePhoneNum = mobilePhoneNum;
		this.homePhoneNum = homePhoneNum;
		this.workPhoneNum = workPhoneNum;
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
	 * @return the pName
	 */
	public String getPname()
	{
		return pname;
	}

	/**
	 * @param pname
	 *            the pName to set
	 */
	public void setPname(String pname)
	{
		this.pname = pname;
	}

	/**
	 * @return the sex
	 */
	public String getSex()
	{
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex)
	{
		this.sex = sex;
	}

	/**
	 * @return the age
	 */
	public int getAge()
	{
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age)
	{
		this.age = age;
	}

	/**
	 * @return the job
	 */
	public String getJob()
	{
		return job;
	}

	/**
	 * @param job
	 *            the job to set
	 */
	public void setJob(String job)
	{
		this.job = job;
	}

	/**
	 * @return the relationship
	 */
	public String getRelationship()
	{
		return relationship;
	}

	/**
	 * @param relationship
	 *            the relationship to set
	 */
	public void setRelationship(String relationship)
	{
		this.relationship = relationship;
	}

	/**
	 * @return the homeAddress
	 */
	public String getHomeAddress()
	{
		return homeAddress;
	}

	/**
	 * @param homeAddress
	 *            the homeAddress to set
	 */
	public void setHomeAddress(String homeAddress)
	{
		this.homeAddress = homeAddress;
	}

	/**
	 * @return the workAddress
	 */
	public String getWorkAddress()
	{
		return workAddress;
	}

	/**
	 * @param workAddress
	 *            the workAddress to set
	 */
	public void setWorkAddress(String workAddress)
	{
		this.workAddress = workAddress;
	}

	/**
	 * @return the mobilePhoneNum
	 */
	public String getMobilePhoneNum()
	{
		return mobilePhoneNum;
	}

	/**
	 * @param mobilePhoneNum
	 *            the mobilePhoneNum to set
	 */
	public void setMobilePhoneNum(String mobilePhoneNum)
	{
		this.mobilePhoneNum = mobilePhoneNum;
	}

	/**
	 * @return the homePhoneNum
	 */
	public String getHomePhoneNum()
	{
		return homePhoneNum;
	}

	/**
	 * @param homePhoneNum
	 *            the homePhoneNum to set
	 */
	public void setHomePhoneNum(String homePhoneNum)
	{
		this.homePhoneNum = homePhoneNum;
	}

	/**
	 * @return the workPhoneNum
	 */
	public String getWorkPhoneNum()
	{
		return workPhoneNum;
	}

	/**
	 * @param workPhoneNum
	 *            the workPhoneNum to set
	 */
	public void setWorkPhoneNum(String workPhoneNum)
	{
		this.workPhoneNum = workPhoneNum;
	}

	public User parse(JSONObject object) throws AidException
	{
		if (object == null)
		{
			return null;
		}
		uid = object.optLong(User.KEY_UID);
		uname = object.optString(User.KEY_UNAME);
		pid = object.optLong(User.KEY_PID);
		pname = object.optString(User.KEY_PNAME);
		sex = object.optString(User.KEY_SEX);
		age = object.optInt(User.KEY_AGE);
		job = object.optString(User.KEY_JOB);
		relationship = object.optString(User.KEY_RELATIONSHIP);
		homeAddress = object.optString(User.KEY_HOME_ADDRESS);
		workAddress = object.optString(User.KEY_WORK_ADDRESS);
		mobilePhoneNum = object.optString(User.KEY_MOBILE_PHONE_NUM);
		homePhoneNum = object.optString(User.KEY_HOME_PHONE_NUM);
		workPhoneNum = object.optString(User.KEY_WORK_PHONE_NUM);
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
		return "User [uid=" + uid + ", uname=" + uname + ", pid=" + pid
				+ ", pName=" + pname + ", sex=" + sex + ", age=" + age
				+ ", job=" + job + ", relationship=" + relationship
				+ ", homeAddress=" + homeAddress + ", workAddress="
				+ workAddress + ", mobilePhoneNum=" + mobilePhoneNum
				+ ", homePhoneNum=" + homePhoneNum + ", workPhoneNum="
				+ workPhoneNum + "]";
	}

	public String toString2()
	{
		return uid + "," + uname + "," + pid + "," + pname + "," + sex + ","
				+ age + "," + job + "," + relationship + "," + homeAddress
				+ "," + workAddress + "," + mobilePhoneNum + "," + homePhoneNum
				+ "," + workPhoneNum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + (sex == null ? 0 : sex.hashCode());
		result = prime * result
				+ (homeAddress == null ? 0 : homeAddress.hashCode());
		result = prime * result
				+ (homePhoneNum == null ? 0 : homePhoneNum.hashCode());
		result = prime * result + (job == null ? 0 : job.hashCode());
		result = prime * result
				+ (mobilePhoneNum == null ? 0 : mobilePhoneNum.hashCode());
		result = prime * result + (pname == null ? 0 : pname.hashCode());
		result = prime * result + (int) (pid ^ pid >>> 32);
		result = prime * result
				+ (relationship == null ? 0 : relationship.hashCode());
		result = prime * result + (int) (uid ^ uid >>> 32);
		result = prime * result + (uname == null ? 0 : uname.hashCode());
		result = prime * result
				+ (workAddress == null ? 0 : workAddress.hashCode());
		result = prime * result
				+ (workPhoneNum == null ? 0 : workPhoneNum.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		User other = (User) obj;
		if (age != other.age)
		{
			return false;
		}
		if (sex == null)
		{
			if (other.sex != null)
			{
				return false;
			}
		}
		else if (!sex.equals(other.sex))
		{
			return false;
		}
		if (homeAddress == null)
		{
			if (other.homeAddress != null)
			{
				return false;
			}
		}
		else if (!homeAddress.equals(other.homeAddress))
		{
			return false;
		}
		if (homePhoneNum == null)
		{
			if (other.homePhoneNum != null)
			{
				return false;
			}
		}
		else if (!homePhoneNum.equals(other.homePhoneNum))
		{
			return false;
		}
		if (job == null)
		{
			if (other.job != null)
			{
				return false;
			}
		}
		else if (!job.equals(other.job))
		{
			return false;
		}
		if (mobilePhoneNum == null)
		{
			if (other.mobilePhoneNum != null)
			{
				return false;
			}
		}
		else if (!mobilePhoneNum.equals(other.mobilePhoneNum))
		{
			return false;
		}
		if (pname == null)
		{
			if (other.pname != null)
			{
				return false;
			}
		}
		else if (!pname.equals(other.pname))
		{
			return false;
		}
		if (pid != other.pid)
		{
			return false;
		}
		if (relationship == null)
		{
			if (other.relationship != null)
			{
				return false;
			}
		}
		else if (!relationship.equals(other.relationship))
		{
			return false;
		}
		if (uid != other.uid)
		{
			return false;
		}
		if (uname == null)
		{
			if (other.uname != null)
			{
				return false;
			}
		}
		else if (!uname.equals(other.uname))
		{
			return false;
		}
		if (workAddress == null)
		{
			if (other.workAddress != null)
			{
				return false;
			}
		}
		else if (!workAddress.equals(other.workAddress))
		{
			return false;
		}
		if (workPhoneNum == null)
		{
			if (other.workPhoneNum != null)
			{
				return false;
			}
		}
		else if (!workPhoneNum.equals(other.workPhoneNum))
		{
			return false;
		}
		return true;
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
		if (-1 != uid)
		{
			bundle.putLong(User.KEY_UID, uid);
		}
		if (null != uname)
		{
			bundle.putString(User.KEY_UNAME, uname);
		}
		if (-1 != pid)
		{
			bundle.putLong(User.KEY_PID, pid);
		}
		if (null != pname)
		{
			bundle.putString(User.KEY_PNAME, pname);
		}
		if (null != sex)
		{
			bundle.putString(User.KEY_SEX, sex);
		}
		if (-1 != age)
		{
			bundle.putInt(User.KEY_AGE, age);
		}
		if (null != job)
		{
			bundle.putString(User.KEY_JOB, job);
		}
		if (null != relationship)
		{
			bundle.putString(User.KEY_RELATIONSHIP, relationship);
		}
		if (null != homeAddress)
		{
			bundle.putString(User.KEY_HOME_ADDRESS, homeAddress);
		}
		if (null != workAddress)
		{
			bundle.putString(User.KEY_WORK_ADDRESS, workAddress);
		}
		if (null != mobilePhoneNum)
		{
			bundle.putString(User.KEY_MOBILE_PHONE_NUM, mobilePhoneNum);
		}
		if (null != homePhoneNum)
		{
			bundle.putString(User.KEY_HOME_PHONE_NUM, homePhoneNum);
		}
		if (null != workPhoneNum)
		{
			bundle.putString(User.KEY_WORK_PHONE_NUM, workPhoneNum);
		}
		dest.writeBundle(bundle);
	}

	public static final Parcelable.Creator<User>	CREATOR	= new Parcelable.Creator<User>() {
																@Override
																public User createFromParcel(
																		Parcel in)
																{
																	return new User(
																			in);
																}

																@Override
																public User[] newArray(
																		int size)
																{
																	return new User[size];
																}
															};

	public User(Parcel in)
	{
		Bundle bundle = in.readBundle();
		if (bundle.containsKey(User.KEY_UID))
		{
			uid = bundle.getLong(User.KEY_UID);
		}
		if (bundle.containsKey(User.KEY_UNAME))
		{
			uname = bundle.getString(User.KEY_UNAME);
		}
		if (bundle.containsKey(User.KEY_PID))
		{
			pid = bundle.getLong(User.KEY_PID);
		}
		if (bundle.containsKey(User.KEY_PNAME))
		{
			pname = bundle.getString(User.KEY_PNAME);
		}
		if (bundle.containsKey(User.KEY_SEX))
		{
			sex = bundle.getString(User.KEY_SEX);
		}
		if (bundle.containsKey(User.KEY_AGE))
		{
			age = bundle.getInt(User.KEY_AGE);
		}
		if (bundle.containsKey(User.KEY_JOB))
		{
			job = bundle.getString(User.KEY_JOB);
		}
		if (bundle.containsKey(User.KEY_RELATIONSHIP))
		{
			relationship = bundle.getString(User.KEY_RELATIONSHIP);
		}
		if (bundle.containsKey(User.KEY_HOME_ADDRESS))
		{
			homeAddress = bundle.getString(User.KEY_HOME_ADDRESS);
		}
		if (bundle.containsKey(User.KEY_WORK_ADDRESS))
		{
			workAddress = bundle.getString(User.KEY_WORK_ADDRESS);
		}
		if (bundle.containsKey(User.KEY_MOBILE_PHONE_NUM))
		{
			mobilePhoneNum = bundle.getString(User.KEY_MOBILE_PHONE_NUM);
		}
		if (bundle.containsKey(User.KEY_WORK_PHONE_NUM))
		{
			workPhoneNum = bundle.getString(User.KEY_WORK_PHONE_NUM);
		}
		if (bundle.containsKey(User.KEY_HOME_PHONE_NUM))
		{
			homePhoneNum = bundle.getString(User.KEY_HOME_PHONE_NUM);
		}
	}
}
