package cn.edu.jlu.ccst.firstaidoflove.util;

public class Constant
{
	public static final String	VERSION								= "1.0";
	public static final String	LOG_TAG								= "AID";
	public static final String	AID_LABEL							= "AID";
	public static final String	RESTSERVER_URL						= "http://localhost:7777/service.do";
	public static final String	SHARE_LOGIN_NAME					= "SHARE_LOGIN_NAME";
	public static final String	SHARE_LOGIN_PASSWORD				= "SHARE_LOGIN_PASSWORD";
	public static final String	SHARE_CONFIG						= "SHARE_CONFIG";
	public static final String	RESPONSE_FORMAT_JSON				= "json";
	public static final String	SHARE_SESSION_KEY					= "sessionKey";
	/** 错误码：参数为空 */
	public static final int		ERROR_CODE_NULL_PARAMETER			= -1;
	/**
	 * 错误码：参数超出了限制 <br>
	 * 例如，传入的状态长度超过了140个字符.
	 */
	public static final int		ERROR_CODE_PARAMETER_EXTENDS_LIMIT	= -2;
	/** 错误码：非法参数 */
	public static final int		ERROR_CODE_ILLEGAL_PARAMETER		= -3;
	/** 错误码：Access Token为空或Session Key为空 */
	public static final int		ERROR_CODE_TOKEN_ERROR				= -4;
	/** 错误码：无法解析服务器响应字符串 */
	public static final int		ERROR_CODE_UNABLE_PARSE_RESPONSE	= -5;
	/** 错误码：操作被取消 */
	public static final int		ERROR_CODE_OPERATION_CANCELLED		= -6;
	/** 错误码：身份/权限验证失败 */
	public static final int		ERROR_CODE_AUTH_FAILED				= -7;
	/** 错误码：验证过程被取消 */
	public static final int		ERROR_CODE_AUTH_CANCELLED			= -8;
	/** 错误码：未知错误 */
	public static final int		ERROR_CODE_UNKNOWN_ERROR			= -9;
	/** 错误码：初始化失败 */
	public static final int		ERROR_RENREN_INIT_ERROR				= -10;
	public static String		KEY_RESULT_CODE						= "result_code";
	// "pid":"46465165","pname":"小李","longitude":"23.4587","latitude":"65.2365","time":"2014.04.08 23:56:23"
	public static final String	ACCIDENT_LABLE						= "accident";
	public static final String	KEY_MEDICAL_HISTORY					= "medical_history";
	public static final String	KEY_LON								= "longitude";
	public static final String	KEY_LAT								= "latitude";
	public static final String	LOGIN_NAME							= "login_name";
	public static final String	LOGIN_PASSWORD						= "login_password";
	public static final String	MEDICAL_RECORD_LABLE				= "medical_record";
	public static final String	KEY_TIME							= "time";
	public static final String	KEY_MEDICAL_RECORD					= "medical_record";
	// "pid":"46465165","pname":"小李","longitude":"23.4587","latitude":"65.2365","time":"2014.04.08 23:56:23"
	public static final String	TRAJECTORY_LABLE					= "trajectory";
	public static final String	KEY_UID								= "uid";
	public static final String	KEY_UNAME							= "uname";
	public static final String	KEY_PID								= "pid";
	public static final String	KEY_PNAME							= "pname";
	public static final String	KEY_SEX								= "sex";
	public static final String	KEY_AGE								= "age";
	public static final String	KEY_JOB								= "job";
	public static final String	KEY_RELATIONSHIP					= "relationship";
	public static final String	KEY_HOME_ADDRESS					= "home_address";
	public static final String	KEY_WORK_ADDRESS					= "work_address";
	public static final String	KEY_MOBILE_PHONE_NUM				= "mobile_phone_num";
	public static final String	KEY_HOME_PHONE_NUM					= "home_phone_num";
	public static final String	KEY_WORK_PHONE_NUM					= "work_phone_num";
	public static final String	KEY_RESULT							= "result";
	public static final String	KEY_BLOOD_PRESSURE					= "blood_pressure";
	public static final String	KEY_BLOOD_SUGAR						= "blood_sugar";
	public static final String	KEY_HEART_RATE						= "heart_rate";
	public static final String	KEY_HEART_RECORD_OTHER				= "record_other";
}
