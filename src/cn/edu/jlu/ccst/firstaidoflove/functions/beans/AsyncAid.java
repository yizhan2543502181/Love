package cn.edu.jlu.ccst.firstaidoflove.functions.beans;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.os.Bundle;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractRequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.RequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.AccidentsGetHelper;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.AccidentsGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.AccidentsGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.login.LoginHelper;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.login.LoginRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.login.LoginResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.medicalRecord.MedicalRecordsGetHelper;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.medicalRecord.MedicalRecordsGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.medicalRecord.MedicalRecordsGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.password.PasswordSetHelper;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.password.PasswordSetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.password.PasswordSetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient.PatientGetHelper;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient.PatientGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient.PatientGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient.PatientSetHelper;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient.PatientSetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.patient.PatientSetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory.TrajectoriesGetHelper;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory.TrajectoriesGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory.TrajectoriesGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory.TrajectoryGetHelper;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory.TrajectoryGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory.TrajectoryGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserGetHelper;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserSetHelper;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserSetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.UserSetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

public class AsyncAid
{
	private Aid			aid;
	private Executor	pool;

	public AsyncAid(Aid aid)
	{
		this.aid = aid;
		pool = Executors.newFixedThreadPool(10);
	}

	/**
	 * 调用 爱的监护者 APIs，服务器的响应是JSON串。
	 * 
	 * 爱的监护者 APIs 详细信息见 http://wiki.dev.aid.com/wiki/API
	 * 
	 * @param parameters
	 *            注意监听器中不在主线程中执行，所以不能在监听器中直接更新UI代码。
	 * @param listener
	 */
	public void requestJSON(Bundle parameters, RequestListener listener)
	{
		request(parameters, listener, Constant.RESPONSE_FORMAT_JSON);
	}

	/**
	 * 调用 爱的监护者 APIs。
	 * 
	 * 爱的监护者 APIs 详细信息见 http://wiki.dev.aid.com/wiki/API
	 * 
	 * @param parameters
	 * @param listener
	 *            注意监听器中不在主线程中执行，所以不能在监听器中直接更新UI代码。
	 * @param format
	 *            return data format (json or xml)
	 */
	private void request(final Bundle parameters,
			final RequestListener listener, final String format)
	{
		pool.execute(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					String resp = "";
					if ("json".equalsIgnoreCase(format))
					{
						resp = aid.requestJSON(parameters);
					}
					AidError aidError = Util.parseAidError(resp, format);
					if (aidError != null)
					{
						listener.onAidError(aidError);
					}
					else
					{
						listener.onComplete(resp);
					}
				}
				catch (Throwable e)
				{
					listener.onFault(e);
				}
			}
		});
	}

	/**
	 * 登录
	 * 
	 * @param param
	 * @param listener
	 */
	public void login(LoginRequestParam param,
			AbstractRequestListener<LoginResponseBean> listener)
	{
		new LoginHelper(aid).asyncLogin(pool, param, listener);
	}

	/**
	 * 获取用户的个人信息
	 * 
	 * @param param
	 * @param listener
	 */
	public void getUserInfo(UserGetRequestParam param,
			AbstractRequestListener<UserGetResponseBean> listener)
	{
		new UserGetHelper(aid).asyncGetUserInfo(pool, param, listener);
	}

	/**
	 * 设置用户的个人信息
	 * 
	 * @param param
	 * @param listener
	 */
	public void setUserInfo(UserSetRequestParam param,
			AbstractRequestListener<UserSetResponseBean> listener)
	{
		new UserSetHelper(aid).asyncsetUserInfo(pool, param, listener);
	}

	/**
	 * 获取监护对象的个人信息
	 * 
	 * @param param
	 * @param listener
	 */
	public void getPatientInfo(PatientGetRequestParam param,
			AbstractRequestListener<PatientGetResponseBean> listener)
	{
		new PatientGetHelper(aid).asyncGetPatientInfo(pool, param, listener);
	}

	/**
	 * 设置监护对象的个人信息
	 * 
	 * @param param
	 * @param listener
	 */
	public void setPatientInfo(PatientSetRequestParam param,
			AbstractRequestListener<PatientSetResponseBean> listener)
	{
		new PatientSetHelper(aid).asyncsetPatientInfo(pool, param, listener);
	}

	/**
	 * 获取最近的事故
	 * 
	 * @param param
	 * @param listener
	 */
	public void getRecentAccidents(AccidentsGetRequestParam param,
			AbstractRequestListener<AccidentsGetResponseBean> listener)
	{
		new AccidentsGetHelper(aid).asyncGetAccidents(pool, param, listener);
	}

	/**
	 * 获取最近的运动轨迹
	 * 
	 * @param param
	 * @param listener
	 */
	public void getRecentTrajectories(TrajectoriesGetRequestParam param,
			AbstractRequestListener<TrajectoriesGetResponseBean> listener)
	{
		new TrajectoriesGetHelper(aid).asyncGetTrajectories(pool, param,
				listener);
	}

	/**
	 * 获取最近的病历
	 * 
	 * @param param
	 * @param listener
	 */
	public void getRecentMedicalRecords(MedicalRecordsGetRequestParam param,
			AbstractRequestListener<MedicalRecordsGetResponseBean> listener)
	{
		new MedicalRecordsGetHelper(aid).asyncGetMedicalRecords(pool, param,
				listener);
	}

	/**
	 * 获取最近一次位置
	 * 
	 * @param param
	 * @param listener
	 */
	public void getRecentTrajectory(TrajectoryGetRequestParam param,
			AbstractRequestListener<TrajectoryGetResponseBean> listener)
	{
		new TrajectoryGetHelper(aid).asyncGetTrajectory(pool, param, listener);
	}

	/**
	 * 获取最近一次位置
	 * 
	 * @param param
	 * @param listener
	 */
	public void setPassword(PasswordSetRequestParam param,
			AbstractRequestListener<PasswordSetResponseBean> listener)
	{
		new PasswordSetHelper(aid).asyncSetPassword(pool, param, listener);
	}
}
