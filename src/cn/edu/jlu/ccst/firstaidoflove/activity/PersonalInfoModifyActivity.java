/**
 * 
 */
package cn.edu.jlu.ccst.firstaidoflove.activity;

import android.os.Bundle;
import android.view.Window;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractAidRequestActivity;

/**
 * @author Administrator
 * 
 */
public class PersonalInfoModifyActivity extends AbstractAidRequestActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.personal_info_modify_activity_layout);
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onRestart()
	{
		// TODO Auto-generated method stub
		super.onRestart();
	}
}
