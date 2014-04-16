/**
 * $id$ Copyright 2011-2012 Aid Inc. All rights reserved.
 */
package cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory;

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
 * @author hecao (he.cao@aid-inc.com) trajectory.getInfo接口 助手类
 * 
 */
public class TrajectoryGetHelper {
	/**
	 * aid对象
	 */
	private Aid aid;

	public TrajectoryGetHelper(Aid aid) {
		this.aid = aid;
	}

	/**
	 * 同步调用trajectory.getInfo接口<br>
	 * 
	 * @param param
	 *            请求对象
	 * @return 返回{@link LoginResponseBean}对象
	 * @throws AidException
	 */
	public TrajectoryGetResponseBean getTrajectory(
			TrajectoryGetRequestParam param) throws AidException, Throwable {
		Bundle parameters = param.getParams();
		TrajectoryGetResponseBean trajectoryBean = null;
		try {
			String response = aid.requestJSON(parameters);
			if (response != null) {
				Util.checkResponse(response, Constant.RESPONSE_FORMAT_JSON);
			} else {
				Util.logger("null response");
				throw new AidException(Constant.ERROR_CODE_UNKNOWN_ERROR,
						"null response", "null response");
			}
			trajectoryBean = new TrajectoryGetResponseBean(response);
		} catch (RuntimeException re) {
			Util.logger("runtime exception " + re.getMessage());
			throw new Throwable(re);
		}
		return trajectoryBean;
	}

	/**
	 * 异步方法调用trajectory.getInfo接口<br>
	 * 
	 * @param pool
	 *            线程池
	 * @param param
	 *            请求对象
	 * @param listener
	 *            回调
	 */
	public void asyncGetTrajectory(Executor pool,
			final TrajectoryGetRequestParam param,
			final AbstractRequestListener<TrajectoryGetResponseBean> listener) {
		pool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					TrajectoryGetResponseBean trajectoryBean = getTrajectory(param);
					if (listener != null) {
						listener.onComplete(trajectoryBean);
					}
				} catch (AidException e) {
					Util.logger("aid exception " + e.getMessage());
					if (listener != null) {
						listener.onError(new AidError(e.getMessage()));
						e.printStackTrace();
					}
				} catch (Throwable e) {
					Util.logger("on fault " + e.getMessage());
					if (listener != null) {
						listener.onFault(e);
					}
				}
			}
		});
	}
}
