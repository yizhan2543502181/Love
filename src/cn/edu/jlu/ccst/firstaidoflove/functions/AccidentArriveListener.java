/**
 * 
 */
package cn.edu.jlu.ccst.firstaidoflove.functions;

import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidError;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.Accident;

/**
 * @author Administrator
 * 
 */
public interface AccidentArriveListener
{
	abstract void onArrive(Accident accident);

	abstract void onError(AidError error);
}
