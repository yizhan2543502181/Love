package cn.edu.jlu.ccst.firstaidoflove.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import cn.edu.jlu.ccst.firstaidoflove.BaseActivity;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

public class FindPasswordActivity extends BaseActivity implements
		OnClickListener
{
	private Button	findPasswordBtn	= null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.find_password_activity_layout);
		initView();
	}

	private void initView()
	{
		findPasswordBtn = (Button) findViewById(R.id.find_password_btn);
		findPasswordBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0)
	{
		switch (arg0.getId())
		{
		case R.id.find_password_btn:
			Util.showAlert(this, "提示", "找回密码");
			// Intent intent = new Intent();
			// intent.setClass(FindPasswordActivity.this, LoginActivity.class);
			// startActivity(intent);
			break;
		default:
			break;
		}
	}
}
