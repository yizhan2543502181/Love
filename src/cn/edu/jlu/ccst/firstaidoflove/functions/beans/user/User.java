package cn.edu.jlu.ccst.firstaidoflove.functions.beans.user;

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
public class User implements Parcelable
{
	/**
	 * 用户id
	 */
	private long	uid				= -1;
	/**
	 * 用户名
	 */
	private String	uname			= null;
	/**
	 * 监护对象的id
	 */
	private long	pid				= -1;
	/**
	 * 监护对象名字
	 */
	private String	pname			= null;
	/**
	 * 性别
	 */
	private String	sex				= null;
	/**
	 * 年龄
	 */
	private int		age				= -1;
	/**
	 * 职业
	 */
	private String	job				= null;
	/**
	 * 和监护对象关系
	 */
	private String	relationship	= null;
	/**
	 * 家庭地址
	 */
	private String	homeAddress		= null;
	/**
	 * 单位地址
	 */
	private String	workAddress		= null;
	/**
	 * 手机号
	 */
	private String	mobilePhoneNum	= null;
	/**
	 * 家庭电话
	 */
	private String	homePhoneNum	= null;
	/**
	 * 单位电话
	 */
	private String	workPhoneNum	= null;

	public User()
	{
		super();
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
		uid = object.optLong(Constant.KEY_UID);
		uname = object.optString(Constant.KEY_UNAME);
		pid = object.optLong(Constant.KEY_PID);
		pname = object.optString(Constant.KEY_PNAME);
		sex = object.optString(Constant.KEY_SEX);
		age = object.optInt(Constant.KEY_AGE);
		job = object.optString(Constant.KEY_JOB);
		relationship = object.optString(Constant.KEY_RELATIONSHIP);
		homeAddress = object.optString(Constant.KEY_HOME_ADDRESS);
		workAddress = object.optString(Constant.KEY_WORK_ADDRESS);
		mobilePhoneNum = object.optString(Constant.KEY_MOBILE_PHONE_NUM);
		homePhoneNum = object.optString(Constant.KEY_HOME_PHONE_NUM);
		workPhoneNum = object.optString(Constant.KEY_WORK_PHONE_NUM);
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
		result = (prime * result) + age;
		result = (prime * result) + (sex == null ? 0 : sex.hashCode());
		result = (prime * result)
				+ (homeAddress == null ? 0 : homeAddress.hashCode());
		result = (prime * result)
				+ (homePhoneNum == null ? 0 : homePhoneNum.hashCode());
		result = (prime * result) + (job == null ? 0 : job.hashCode());
		result = (prime * result)
				+ (mobilePhoneNum == null ? 0 : mobilePhoneNum.hashCode());
		result = (prime * result) + (pname == null ? 0 : pname.hashCode());
		result = (prime * result) + (int) (pid ^ (pid >>> 32));
		result = (prime * result)
				+ (relationship == null ? 0 : relationship.hashCode());
		result = (prime * result) + (int) (uid ^ (uid >>> 32));
		result = (prime * result) + (uname == null ? 0 : uname.hashCode());
		result = (prime * result)
				+ (workAddress == null ? 0 : workAddress.hashCode());
		result = (prime * result)
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
		if (null != sex)
		{
			bundle.putString(Constant.KEY_SEX, sex);
		}
		if (-1 != age)
		{
			bundle.putInt(Constant.KEY_AGE, age);
		}
		if (null != job)
		{
			bundle.putString(Constant.KEY_JOB, job);
		}
		if (null != relationship)
		{
			bundle.putString(Constant.KEY_RELATIONSHIP, relationship);
		}
		if (null != homeAddress)
		{
			bundle.putString(Constant.KEY_HOME_ADDRESS, homeAddress);
		}
		if (null != workAddress)
		{
			bundle.putString(Constant.KEY_WORK_ADDRESS, workAddress);
		}
		if (null != mobilePhoneNum)
		{
			bundle.putString(Constant.KEY_MOBILE_PHONE_NUM, mobilePhoneNum);
		}
		if (null != homePhoneNum)
		{
			bundle.putString(Constant.KEY_HOME_PHONE_NUM, homePhoneNum);
		}
		if (null != workPhoneNum)
		{
			bundle.putString(Constant.KEY_WORK_PHONE_NUM, workPhoneNum);
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
		if (bundle.containsKey(Constant.KEY_SEX))
		{
			sex = bundle.getString(Constant.KEY_SEX);
		}
		if (bundle.containsKey(Constant.KEY_AGE))
		{
			age = bundle.getInt(Constant.KEY_AGE);
		}
		if (bundle.containsKey(Constant.KEY_JOB))
		{
			job = bundle.getString(Constant.KEY_JOB);
		}
		if (bundle.containsKey(Constant.KEY_RELATIONSHIP))
		{
			relationship = bundle.getString(Constant.KEY_RELATIONSHIP);
		}
		if (bundle.containsKey(Constant.KEY_HOME_ADDRESS))
		{
			homeAddress = bundle.getString(Constant.KEY_HOME_ADDRESS);
		}
		if (bundle.containsKey(Constant.KEY_WORK_ADDRESS))
		{
			workAddress = bundle.getString(Constant.KEY_WORK_ADDRESS);
		}
		if (bundle.containsKey(Constant.KEY_MOBILE_PHONE_NUM))
		{
			mobilePhoneNum = bundle.getString(Constant.KEY_MOBILE_PHONE_NUM);
		}
		if (bundle.containsKey(Constant.KEY_WORK_PHONE_NUM))
		{
			workPhoneNum = bundle.getString(Constant.KEY_WORK_PHONE_NUM);
		}
		if (bundle.containsKey(Constant.KEY_HOME_PHONE_NUM))
		{
			homePhoneNum = bundle.getString(Constant.KEY_HOME_PHONE_NUM);
		}
	}

	public Bundle getParams()
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
		if (null != sex)
		{
			bundle.putString(Constant.KEY_SEX, sex);
		}
		if (-1 != age)
		{
			bundle.putInt(Constant.KEY_AGE, age);
		}
		if (null != job)
		{
			bundle.putString(Constant.KEY_JOB, job);
		}
		if (null != relationship)
		{
			bundle.putString(Constant.KEY_RELATIONSHIP, relationship);
		}
		if (null != homeAddress)
		{
			bundle.putString(Constant.KEY_HOME_ADDRESS, homeAddress);
		}
		if (null != workAddress)
		{
			bundle.putString(Constant.KEY_WORK_ADDRESS, workAddress);
		}
		if (null != mobilePhoneNum)
		{
			bundle.putString(Constant.KEY_MOBILE_PHONE_NUM, mobilePhoneNum);
		}
		if (null != homePhoneNum)
		{
			bundle.putString(Constant.KEY_HOME_PHONE_NUM, homePhoneNum);
		}
		if (null != workPhoneNum)
		{
			bundle.putString(Constant.KEY_WORK_PHONE_NUM, workPhoneNum);
		}
		return bundle;
	}
}
