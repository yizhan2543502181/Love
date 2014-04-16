/**
 * 
 */
package cn.edu.jlu.ccst.firstaidoflove.activity;

import android.os.Bundle;
import android.view.Window;
import cn.edu.jlu.ccst.firstaidoflove.AbstractAidRequestActivity;
import cn.edu.jlu.ccst.firstaidoflove.R;

/**
 * @author Administrator
 * 
 */
public class LocaleInfoActivity extends AbstractAidRequestActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.locale_info_activity_layout);
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
