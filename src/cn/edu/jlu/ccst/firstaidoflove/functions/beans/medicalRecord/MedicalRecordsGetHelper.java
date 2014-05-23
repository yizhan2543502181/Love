/**
 * $id$ Copyright 2014 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.medicalRecord;

import java.util.concurrent.Executor;

import android.os.Bundle;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractRequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.Aid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidError;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.login.LoginResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

/**
 * 
 * @author hecao (he.cao@aid-inc.com) medicalRecords.getInfo接口 助手类
 * 
 */
public class MedicalRecordsGetHelper
{
	/**
	 * aid对象
	 */
	private Aid	aid;

	public MedicalRecordsGetHelper(Aid aid)
	{
		this.aid = aid;
	}

	/**
	 * 同步调用medicalRecords.getInfo接口<br>
	 * 
	 * @param param
	 *            请求对象
	 * @return 返回{@link LoginResponseBean}对象
	 * @throws AidException
	 */
	public MedicalRecordsGetResponseBean getMedicalRecords(
			MedicalRecordsGetRequestParam param) throws AidException, Throwable
	{
		Bundle parameters = param.getParams();
		MedicalRecordsGetResponseBean medicalRecordsBean = null;
		try
		{
			String response = aid.requestJSON(parameters);
			if (response != null)
			{
				Util.checkResponse(response, Constant.RESPONSE_FORMAT_JSON);
			}
			else
			{
				Util.logger("null response");
				throw new AidException(Constant.ERROR_CODE_UNKNOWN_ERROR,
						"null response", "null response");
			}
			medicalRecordsBean = new MedicalRecordsGetResponseBean(response);
		}
		catch (RuntimeException re)
		{
			Util.logger("runtime exception " + re.getMessage());
			throw new Throwable(re);
		}
		return medicalRecordsBean;
	}

	/**
	 * 异步方法调用medicalRecords.getInfo接口<br>
	 * 
	 * @param pool
	 *            线程池
	 * @param param
	 *            请求对象
	 * @param listener
	 *            回调
	 */
	public void asyncGetMedicalRecords(
			Executor pool,
			final MedicalRecordsGetRequestParam param,
			final AbstractRequestListener<MedicalRecordsGetResponseBean> listener)
	{
		pool.execute(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					MedicalRecordsGetResponseBean medicalRecordsBean = getMedicalRecords(param);
					if (listener != null)
					{
						listener.onComplete(medicalRecordsBean);
					}
				}
				catch (AidException e)
				{
					Util.logger("aid exception " + e.getMessage());
					if (listener != null)
					{
						listener.onError(new AidError(e.getMessage()));
						e.printStackTrace();
					}
				}
				catch (Throwable e)
				{
					Util.logger("on fault " + e.getMessage());
					if (listener != null)
					{
						listener.onFault(e);
					}
				}
			}
		});
	}
}
