package cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient;

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
public class Patient implements Parcelable
{
	/**
	 * 监护对象的id
	 */
	private long	pid	           = -1;
	/**
	 * 监护对象名字
	 */
	private String	pname	       = null;
	/**
	 * 性别
	 */
	private String	sex	           = null;
	/**
	 * 年龄
	 */
	private int	   age	           = -1;
	/**
	 * 职业
	 */
	private String	job	           = null;
	/**
	 * 家庭地址
	 */
	private String	homeAddress	   = null;
	/**
	 * 单位地址
	 */
	private String	workAddress	   = null;
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

	public Patient()
	{
		super();
	}

	public Patient(long pid, String pName, String sex, int age, String job,
	        String homeAddress, String workAddress, String mobilePhoneNum,
	        String homePhoneNum, String workPhoneNum)
	{
		super();
		this.pid = pid;
		pname = pName;
		this.sex = sex;
		this.age = age;
		this.job = job;
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
		this.mobilePhoneNum = mobilePhoneNum;
		this.homePhoneNum = homePhoneNum;
		this.workPhoneNum = workPhoneNum;
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

	public Patient parse(JSONObject object) throws AidException
	{
		if (object == null)
		{
			return null;
		}
		pid = object.optLong(Constant.KEY_PID);
		pname = object.optString(Constant.KEY_PNAME);
		sex = object.optString(Constant.KEY_SEX);
		age = object.optInt(Constant.KEY_AGE);
		job = object.optString(Constant.KEY_JOB);
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
		return "User [pid=" + pid + ", pName=" + pname + ", sex=" + sex
		        + ", age=" + age + ", job=" + job + ", homeAddress="
		        + homeAddress + ", workAddress=" + workAddress
		        + ", mobilePhoneNum=" + mobilePhoneNum + ", homePhoneNum="
		        + homePhoneNum + ", workPhoneNum=" + workPhoneNum + "]";
	}

	public String toString2()
	{
		return pid + "," + pname + "," + sex + "," + age + "," + job + ","
		        + homeAddress + "," + workAddress + "," + mobilePhoneNum + ","
		        + homePhoneNum + "," + workPhoneNum;
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

	public static final Parcelable.Creator<Patient>	CREATOR	= new Parcelable.Creator<Patient>() {
		                                                        @Override
		                                                        public Patient createFromParcel(
		                                                                Parcel in)
		                                                        {
			                                                        return new Patient(
			                                                                in);
		                                                        }

		                                                        @Override
		                                                        public Patient[] newArray(
		                                                                int size)
		                                                        {
			                                                        return new Patient[size];
		                                                        }
	                                                        };

	public Patient(Parcel in)
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
}
