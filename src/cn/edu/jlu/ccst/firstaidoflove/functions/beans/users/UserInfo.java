/**
 * $id$ Copyright 2011-2012 Renren Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.users;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.edu.jlu.ccst.firstaidoflove.util.AidException;

/**
 * user数据的封装
 * 
 * @author hecao (he.cao@renren-inc.com)
 * 
 */
public class UserInfo implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID		= 1L;
	public static final String	KEY_UID					= "uid";
	public static final String	KEY_NAME				= "name";
	public static final String	KEY_SEX					= "sex";
	public static final String	KEY_STAR				= "star";
	public static final String	KEY_ZIDOU				= "zidou";
	public static final String	KEY_VIP					= "vip";
	public static final String	KEY_BIRTHDAY			= "birthday";
	public static final String	KEY_EMAIL_HASH			= "email_hash";
	public static final String	KEY_TINYURL				= "tinyurl";
	public static final String	KEY_HEADURL				= "headurl";
	public static final String	KEY_MAINURL				= "mainurl";
	public static final String	KEY_HOMETOWN_LOCATION	= "hometown_location";
	public static final String	KEY_WORK_INFO			= "work_history";
	public static final String	KEY_UNIVERSITY_INFO		= "university_history";
	public static final String	KEY_HS_INFO				= "hs_history";

	/**
	 * 家乡信息
	 * 
	 * @author hecao
	 * 
	 */
	public static class HomeTownLocation implements Serializable
	{
		/**
		 * 
		 */
		private static final long	serialVersionUID	= 1L;
		public static final String	KEY_COUNTRY			= "country";
		public static final String	KEY_PROVINCE		= "province";
		public static final String	KEY_CITY			= "city";
		/**
		 * 所在国家
		 */
		private String				country				= "";
		/**
		 * 所在省份
		 */
		private String				province			= "";
		/**
		 * 所在城市
		 */
		private String				city				= "";

		public static ArrayList<HomeTownLocation> parse(JSONArray array)
		{
			if (array == null)
			{
				return null;
			}
			ArrayList<HomeTownLocation> homeTownLocation = new ArrayList<UserInfo.HomeTownLocation>();
			int size = array.length();
			for (int i = 0; i < size; i++)
			{
				HomeTownLocation temp = HomeTownLocation.parse(array
						.optJSONObject(i));
				if (temp != null)
				{
					homeTownLocation.add(temp);
				}
			}
			return homeTownLocation;
		}

		public static HomeTownLocation parse(JSONObject object)
		{
			if (object == null)
			{
				return null;
			}
			HomeTownLocation homeTomeLocation = new HomeTownLocation();
			homeTomeLocation.country = object
					.optString(HomeTownLocation.KEY_COUNTRY);
			homeTomeLocation.province = object
					.optString(HomeTownLocation.KEY_PROVINCE);
			homeTomeLocation.city = object.optString(HomeTownLocation.KEY_CITY);
			return homeTomeLocation;
		}

		public void setCountry(String country)
		{
			this.country = country;
		}

		public String getCountry()
		{
			return country;
		}

		public void setProvince(String province)
		{
			this.province = province;
		}

		public String getProvince()
		{
			return province;
		}

		public void setCity(String city)
		{
			this.city = city;
		}

		public String getCity()
		{
			return city;
		}

		@Override
		public String toString()
		{
			StringBuffer sb = new StringBuffer();
			sb.append("\t").append(HomeTownLocation.KEY_COUNTRY).append(" = ")
					.append(country).append("\r\n");
			sb.append("\t").append(HomeTownLocation.KEY_PROVINCE).append(" = ")
					.append(province).append("\r\n");
			sb.append("\t").append(HomeTownLocation.KEY_CITY).append(" = ")
					.append(city).append("\r\n");
			return sb.toString();
		}
	}

	/**
	 * 工作信息
	 * 
	 * @author hecao
	 * 
	 */
	public static class WorkInfo implements Serializable
	{
		/**
		 * 
		 */
		private static final long	serialVersionUID	= 1L;
		public static final String	KEY_COMPANY_NAME	= "company_name";
		public static final String	KEY_DESCRIPTION		= "description";
		public static final String	KEY_START_DATE		= "start_date";
		public static final String	KEY_END_DATE		= "end_date";
		/**
		 * 公司名称
		 */
		private String				companyName			= "";
		/**
		 * 工作描述
		 */
		private String				description			= "";
		/**
		 * 入职时间
		 */
		private String				startDate			= "";
		/**
		 * 离职时间
		 */
		private String				endDate				= "";

		public static ArrayList<WorkInfo> parse(JSONArray array)
		{
			if (array == null)
			{
				return null;
			}
			ArrayList<WorkInfo> workInfo = new ArrayList<UserInfo.WorkInfo>();
			int size = array.length();
			for (int i = 0; i < size; i++)
			{
				WorkInfo temp = WorkInfo.parse(array.optJSONObject(i));
				if (temp != null)
				{
					workInfo.add(temp);
				}
			}
			return workInfo;
		}

		public static WorkInfo parse(JSONObject object)
		{
			if (object == null)
			{
				return null;
			}
			WorkInfo workInfo = new WorkInfo();
			workInfo.companyName = object.optString(WorkInfo.KEY_COMPANY_NAME);
			workInfo.description = object.optString(WorkInfo.KEY_DESCRIPTION);
			workInfo.startDate = object.optString(WorkInfo.KEY_START_DATE);
			workInfo.endDate = object.optString(WorkInfo.KEY_END_DATE);
			return workInfo;
		}

		public void setCompanyName(String companyName)
		{
			this.companyName = companyName;
		}

		public String getCompanyName()
		{
			return companyName;
		}

		public void setDescription(String description)
		{
			this.description = description;
		}

		public String getDescription()
		{
			return description;
		}

		public void setStartDate(String startDate)
		{
			this.startDate = startDate;
		}

		public String getStartDate()
		{
			return startDate;
		}

		public void setEndDate(String endDate)
		{
			this.endDate = endDate;
		}

		public String getEndDate()
		{
			return endDate;
		}

		@Override
		public String toString()
		{
			StringBuffer sb = new StringBuffer();
			sb.append("\t").append(WorkInfo.KEY_COMPANY_NAME).append(" = ")
					.append(companyName).append("\r\n");
			sb.append("\t").append(WorkInfo.KEY_DESCRIPTION).append(" = ")
					.append(description).append("\r\n");
			sb.append("\t").append(WorkInfo.KEY_START_DATE).append(" = ")
					.append(startDate).append("\r\n");
			sb.append("\t").append(WorkInfo.KEY_END_DATE).append(" = ")
					.append(endDate).append("\r\n");
			return sb.toString();
		}
	}

	/**
	 * 就读大学信息
	 * 
	 * @author hecao
	 * 
	 */
	public static class UniversityInfo implements Serializable
	{
		/**
		 * 
		 */
		private static final long	serialVersionUID	= 1L;
		public static final String	KEY_NAME			= "name";
		public static final String	KEY_YEAR			= "year";
		public static final String	KEY_DEPARTMENT		= "department";
		/**
		 * 大学名称
		 */
		private String				name				= "";
		/**
		 * 入学年份
		 */
		private long				year;
		/**
		 * 学院名称
		 */
		private String				department			= "";

		public static ArrayList<UniversityInfo> parse(JSONArray array)
		{
			if (array == null)
			{
				return null;
			}
			ArrayList<UniversityInfo> universityInfo = new ArrayList<UserInfo.UniversityInfo>();
			int size = array.length();
			for (int i = 0; i < size; i++)
			{
				UniversityInfo temp = UniversityInfo.parse(array
						.optJSONObject(i));
				if (temp != null)
				{
					universityInfo.add(temp);
				}
			}
			return universityInfo;
		}

		public static UniversityInfo parse(JSONObject object)
		{
			if (object == null)
			{
				return null;
			}
			UniversityInfo universityInfo = new UniversityInfo();
			universityInfo.name = object.optString(UniversityInfo.KEY_NAME);
			universityInfo.year = object.optLong(UniversityInfo.KEY_YEAR);
			universityInfo.department = object
					.optString(UniversityInfo.KEY_DEPARTMENT);
			return universityInfo;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public String getName()
		{
			return name;
		}

		public void setYear(long year)
		{
			this.year = year;
		}

		public long getYear()
		{
			return year;
		}

		public void setDepartment(String department)
		{
			this.department = department;
		}

		public String getDepartment()
		{
			return department;
		}

		@Override
		public String toString()
		{
			StringBuffer sb = new StringBuffer();
			sb.append("\t").append(UniversityInfo.KEY_NAME).append(" = ")
					.append(name).append("\r\n");
			sb.append("\t").append(UniversityInfo.KEY_YEAR).append(" = ")
					.append(year).append("\r\n");
			sb.append("\t").append(UniversityInfo.KEY_DEPARTMENT).append(" = ")
					.append(department).append("\r\n");
			return sb.toString();
		}
	}

	/**
	 * 就读高中学校信息
	 * 
	 * @author hecao
	 * 
	 */
	public static class HSInfo implements Serializable
	{
		/**
		 * 
		 */
		private static final long	serialVersionUID	= 1L;
		public static final String	KEY_NAME			= "name";
		public static final String	KEY_GRAD_YEAR		= "grad_year";
		/**
		 * 学校名称
		 */
		private String				name				= "";
		/**
		 * 入学年份
		 */
		private long				gradYear;

		public static ArrayList<HSInfo> parse(JSONArray array)
		{
			if (array == null)
			{
				return null;
			}
			ArrayList<HSInfo> hsInfo = new ArrayList<UserInfo.HSInfo>();
			int size = array.length();
			for (int i = 0; i < size; i++)
			{
				HSInfo temp = HSInfo.parse(array.optJSONObject(i));
				if (temp != null)
				{
					hsInfo.add(temp);
				}
			}
			return hsInfo;
		}

		public static HSInfo parse(JSONObject object)
		{
			if (object == null)
			{
				return null;
			}
			HSInfo hsInfo = new HSInfo();
			hsInfo.name = object.optString(HSInfo.KEY_NAME);
			hsInfo.gradYear = object.optLong(HSInfo.KEY_GRAD_YEAR);
			return hsInfo;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public String getName()
		{
			return name;
		}

		public void setGradYear(long gradYear)
		{
			this.gradYear = gradYear;
		}

		public long getGradYear()
		{
			return gradYear;
		}

		@Override
		public String toString()
		{
			StringBuffer sb = new StringBuffer();
			sb.append("\t").append(HSInfo.KEY_NAME).append(" = ").append(name)
					.append("\r\n");
			sb.append("\t").append(HSInfo.KEY_GRAD_YEAR).append(" = ")
					.append(gradYear).append("\r\n");
			return sb.toString();
		}
	}

	/**
	 * 用户uid
	 */
	private long						uid			= -1;
	/**
	 * 用户姓名
	 */
	private String						name		= "";
	/**
	 * 性别，1表示男性，0表示女性 。。。。
	 */
	private int							sex;
	/**
	 * 是否为星级用户，校内中值1表示是；值0表示不是,开心中0表示非真实姓名和头像，1表示非真实姓名，2表示非真实头像，3表示真实用户
	 */
	private int							star;
	/**
	 * 是否为vip用户，值1表示是；值0表示不是
	 */
	private int							zidou;
	/**
	 * 是否为vip用户等级，前提是zidou节点必须为1
	 */
	private int							vip;
	/**
	 * 出生时间，格式为：yyyy-mm-dd，需要自行格式化日期显示格式。注：年份60后，实际返回1760-mm-dd；70后，返回1770- mm-dd；80后，返回1780-mm-dd；90后，返回1790-mm-dd
	 */
	private String						birthday	= "0000-00-00";
	/**
	 * 用户经过验证的email的信息字符串：email通过了connect.registerUsers接口。 字符串包含的email经过了crc32和md5的编码
	 */
	private String						emailHash	= "";
	/**
	 * 头像链接 50*50大小
	 */
	private String						tinyurl;
	/**
	 * 头像链接 100*100大小
	 */
	private String						headurl;
	/**
	 * 头像链接 200*200大小
	 */
	private String						mainurl;
	/**
	 * 家乡信息
	 */
	private HomeTownLocation			homeTownLocation;
	/**
	 * 工作信息
	 */
	private ArrayList<WorkInfo>			workInfo;
	/**
	 * 就读大学信息
	 */
	private ArrayList<UniversityInfo>	universityInfo;
	/**
	 * 就读高中学校信息
	 */
	private ArrayList<HSInfo>			hsInfo;

	public UserInfo parse(JSONObject object) throws AidException
	{
		if (object == null)
		{
			return null;
		}
		uid = object.optLong(UserInfo.KEY_UID);
		name = object.optString(UserInfo.KEY_NAME);
		sex = object.optInt(UserInfo.KEY_SEX);
		star = object.optInt(UserInfo.KEY_STAR);
		zidou = object.optInt(UserInfo.KEY_ZIDOU);
		vip = object.optInt(UserInfo.KEY_VIP);
		birthday = object.optString(UserInfo.KEY_BIRTHDAY);
		emailHash = object.optString(UserInfo.KEY_EMAIL_HASH);
		tinyurl = object.optString(UserInfo.KEY_TINYURL);
		headurl = object.optString(UserInfo.KEY_HEADURL);
		mainurl = object.optString(UserInfo.KEY_MAINURL);
		homeTownLocation = HomeTownLocation.parse(object
				.optJSONObject(UserInfo.KEY_HOMETOWN_LOCATION));
		workInfo = WorkInfo.parse(object.optJSONArray(UserInfo.KEY_WORK_INFO));
		universityInfo = UniversityInfo.parse(object
				.optJSONArray(UserInfo.KEY_UNIVERSITY_INFO));
		hsInfo = HSInfo.parse(object.optJSONArray(UserInfo.KEY_HS_INFO));
		return this;
	}

	public void setUid(long uid)
	{
		this.uid = uid;
	}

	public long getUid()
	{
		return uid;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setSex(int sex)
	{
		this.sex = sex;
	}

	public int getSex()
	{
		return sex;
	}

	public void setStar(int star)
	{
		this.star = star;
	}

	public int getStar()
	{
		return star;
	}

	public void setZidou(int zidou)
	{
		this.zidou = zidou;
	}

	public int getZidou()
	{
		return zidou;
	}

	public void setVip(int vip)
	{
		this.vip = vip;
	}

	public int getVip()
	{
		return vip;
	}

	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}

	public String getBirthday()
	{
		return birthday;
	}

	public void setEmailHash(String emailHash)
	{
		this.emailHash = emailHash;
	}

	public String getEmailHash()
	{
		return emailHash;
	}

	public void setTinyurl(String tinyurl)
	{
		this.tinyurl = tinyurl;
	}

	public String getTinyurl()
	{
		return tinyurl;
	}

	public void setHeadurl(String headurl)
	{
		this.headurl = headurl;
	}

	public String getHeadurl()
	{
		return headurl;
	}

	public void setMainurl(String mainurl)
	{
		this.mainurl = mainurl;
	}

	public String getMainurl()
	{
		return mainurl;
	}

	public void setHomeTownLocation(HomeTownLocation homeTownLocation)
	{
		this.homeTownLocation = homeTownLocation;
	}

	public HomeTownLocation getHomeTownLocation()
	{
		return homeTownLocation;
	}

	public void setWorkInfo(ArrayList<WorkInfo> workInfo)
	{
		this.workInfo = workInfo;
	}

	public ArrayList<WorkInfo> getWorkInfo()
	{
		return workInfo;
	}

	public void setUniversityInfo(ArrayList<UniversityInfo> universityInfo)
	{
		this.universityInfo = universityInfo;
	}

	public ArrayList<UniversityInfo> getUniversityInfo()
	{
		return universityInfo;
	}

	public void setHsInfo(ArrayList<HSInfo> hsInfo)
	{
		this.hsInfo = hsInfo;
	}

	public ArrayList<HSInfo> getHsInfo()
	{
		return hsInfo;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(UserInfo.KEY_UID).append(" = ").append(uid).append("\r\n");
		sb.append(UserInfo.KEY_NAME).append(" = ").append(name).append("\r\n");
		sb.append(UserInfo.KEY_SEX).append(" = ").append(sex).append("\r\n");
		sb.append(UserInfo.KEY_STAR).append(" = ").append(star).append("\r\n");
		sb.append(UserInfo.KEY_ZIDOU).append(" = ").append(zidou)
				.append("\r\n");
		sb.append(UserInfo.KEY_VIP).append(" = ").append(vip).append("\r\n");
		sb.append(UserInfo.KEY_BIRTHDAY).append(" = ").append(birthday)
				.append("\r\n");
		sb.append(UserInfo.KEY_EMAIL_HASH).append(" = ").append(emailHash)
				.append("\r\n");
		sb.append(UserInfo.KEY_TINYURL).append(" = ").append(tinyurl)
				.append("\r\n");
		sb.append(UserInfo.KEY_HEADURL).append(" = ").append(headurl)
				.append("\r\n");
		sb.append(UserInfo.KEY_MAINURL).append(" = ").append(mainurl)
				.append("\r\n");
		if (homeTownLocation != null)
		{
			sb.append(UserInfo.KEY_HOMETOWN_LOCATION).append(" = ")
					.append("\r\n");
			sb.append(homeTownLocation.toString()).append("\r\n");
		}
		if (workInfo != null)
		{
			sb.append(UserInfo.KEY_WORK_INFO).append(" = ").append("\r\n");
			for (WorkInfo w : workInfo)
			{
				sb.append(w.toString()).append("\r\n");
			}
		}
		if (universityInfo != null)
		{
			sb.append(UserInfo.KEY_UNIVERSITY_INFO).append(" = ")
					.append("\r\n");
			for (UniversityInfo u : universityInfo)
			{
				sb.append(u.toString()).append("\r\n");
			}
		}
		if (hsInfo != null)
		{
			sb.append(UserInfo.KEY_HS_INFO).append(" = ").append("\r\n");
			for (HSInfo h : hsInfo)
			{
				sb.append(h.toString()).append("\r\n");
			}
		}
		return sb.toString();
	}
}
