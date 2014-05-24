package cn.edu.jlu.ccst.firstaidoflove.util;

public class Constant
{
	public static final int		FAILED						= 1;
	public static final int		SUCCESS						= 0;
	public static final String	VERSION						= "1.0.0";
	public static final String	LOG_TAG						= "AID";
	public static final String	AID_LABEL					= "aid_label";
	public static final String	RESTSERVER_URL				= "http://49.140.90.202:8088/medical/index.php";
	public static final String	SHARE_LOGIN_NAME			= "SHARE_LOGIN_NAME";
	public static final String	SHARE_LOGIN_PASSWORD		= "SHARE_LOGIN_PASSWORD";
	public static final String	SHARE_CONFIG				= "SHARE_CONFIG";
	public static final String	RESPONSE_FORMAT_JSON		= "json";
	public static final String	SHARE_SESSION_KEY			= "sessionKey";
	// 错误码
	public static final int		ERROR_CODE_NULL_PARAMETER	= -1;												// 空参数
	public static final int		ERROR_CODE_UNKNOWN_ERROR	= -4;												// 空response
	// 用Intent传输时附带的参数
	public static final String	KEY_RESULT_CODE				= "result_code";
	public static final String	KEY_FIELDS					= "fields";
	public static final String	KEY_VALUES					= "values";
	public static final String	KEY_METHOD					= "method";
	//
	public static final String	ACCIDENT_LABEL				= "accident_label";
	public static final String	LOGIN_LABEL					= "login_label";
	public static final String	MEDICAL_RECORD_LABEL		= "medical_record_label";
	public static final String	TRAJECTORY_LABEL			= "trajectory_label";
	// 登录请求参数
	public static final String	KEY_LOGIN_NAME				= "login_name";
	public static final String	KEY_LOGIN_PASSWORD			= "login_password";
	//
	// 登录返回信息
	public static final String	KEY_UID						= "uid";
	public static final String	KEY_UNAME					= "uname";
	public static final String	KEY_PID						= "pid";
	public static final String	KEY_PNAME					= "pname";
	public static final String	KEY_LON						= "longitude";
	public static final String	KEY_LAT						= "latitude";
	//
	// 用户的信息
	// public static final String KEY_UID = "uid";
	// public static final String KEY_UNAME = "uname";
	// public static final String KEY_PID = "pid";
	// public static final String KEY_PNAME = "pname";
	public static final String	KEY_SEX						= "sex";
	public static final String	KEY_AGE						= "age";
	public static final String	KEY_JOB						= "job";
	public static final String	KEY_RELATIONSHIP			= "relationship";
	public static final String	KEY_HOME_ADDRESS			= "home_address";
	public static final String	KEY_WORK_ADDRESS			= "work_address";
	public static final String	KEY_MOBILE_PHONE_NUM		= "mobile_phone_num";
	public static final String	KEY_HOME_PHONE_NUM			= "home_phone_num";
	public static final String	KEY_WORK_PHONE_NUM			= "work_phone_num";
	//
	// 病人的信息
	// public static final String KEY_PID = "pid";
	// public static final String KEY_PNAME = "pname";
	// public static final String KEY_SEX = "sex";
	// public static final String KEY_AGE = "age";
	// public static final String KEY_JOB = "job";
	// public static final String KEY_HOME_ADDRESS = "home_address";
	// public static final String KEY_WORK_ADDRESS = "work_address";
	// public static final String KEY_MOBILE_PHONE_NUM = "mobile_phone_num";
	// public static final String KEY_HOME_PHONE_NUM = "home_phone_num";
	// public static final String KEY_WORK_PHONE_NUM = "work_phone_num";
	//
	// 事故信息
	public static final String	KEY_AID						= "aid";
	// public static final String KEY_PID = "pid";
	// public static final String KEY_PNAME = "pname";
	// public static final String KEY_LON = "longitude";
	// public static final String KEY_LAT = "latitude";
	public static final String	KEY_TIME					= "time";
	public static final String	KEY_MEDICAL_HISTORY			= "medical_history";
	//
	// 病历
	public static final String	KEY_MID						= "mid";
	// public static final String KEY_PID = "pid";
	// public static final String KEY_PNAME = "pname";
	// public static final String KEY_SEX = "sex";
	// public static final String KEY_AGE = "age";
	public static final String	KEY_RESULT					= "result";
	// public static final String KEY_TIME = "time";
	public static final String	KEY_BLOOD_PRESSURE			= "blood_pressure";
	public static final String	KEY_BLOOD_SUGAR				= "blood_sugar";
	public static final String	KEY_HEART_RATE				= "heart_rate";
	public static final String	KEY_RECORD_OTHER			= "record_other";
	//
	// 轨迹的信息
	public static final String	KEY_TID						= "tid";
	// public static final String KEY_PID = "pid";
	// public static final String KEY_PNAME = "pname";
	// public static final String KEY_LON = "longitude";
	// public static final String KEY_LAT = "latitude";
	// public static final String KEY_TIME = "time";
	//
	// 热点的信息
	public static final String	KEY_HID						= "hid";
	// public static final String KEY_PID = "pid";
	// public static final String KEY_PNAME = "pname";
	public static final String	KEY_ADDRESS_NAME			= "address_name";
	public static final String	KEY_ADDRESS					= "address";
	public static final String	KEY_CONTACT_PERSON			= "contact_person";
	public static final String	KEY_CONTACT_PHONE_NUM		= "contact_phone_num";
	public static final String	KEY_DESCRIPTION				= "description";
	//
	// 设置密码
	public static final String	KEY_OLD_PASSWORD			= "old_password";
	public static final String	KEY_NEW_PASSWORD			= "new_password";
}
