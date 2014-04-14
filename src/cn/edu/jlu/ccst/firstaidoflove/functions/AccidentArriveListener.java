/**
 * 
 */
package cn.edu.jlu.ccst.firstaidoflove.functions;

import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.Accident;
import cn.edu.jlu.ccst.firstaidoflove.util.AidError;

/**
 * @author Administrator
 * 
 */
public interface AccidentArriveListener
{
	abstract void onArrive(Accident accident);

	abstract void onError(AidError error);
}
