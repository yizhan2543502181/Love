package cn.edu.jlu.ccst.firstaidoflove.activity.main;

import java.util.HashSet;
import java.util.Set;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import cn.edu.jlu.ccst.firstaidoflove.AbstractAidRequestActivity;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.activity.FindPasswordActivity;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractRequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.Aid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidError;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AsyncAid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.login.Login;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.login.LoginRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.login.LoginResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory.Trajectory;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.User;
import cn.edu.jlu.ccst.firstaidoflove.util.Constant;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

public class LoginActivity extends AbstractAidRequestActivity implements
		OnClickListener
{
	private static LoginActivity	instance			= null;
	private String					userName			= null;
	private String					password			= null;
	private EditText				userNameEdit		= null;
	private EditText				passwordEdit		= null;
	private CheckBox				rememberMeCheck		= null;
	private Button					loginBtn			= null;
	private TextView				findPasswordText	= null;

	public LoginActivity()
	{
		LoginActivity.instance = this;
	}

	/**
	 * @return the instance
	 */
	public synchronized static LoginActivity getInstance()
	{
		return LoginActivity.instance;
	}

	/**
	 * 
	 * @return the {@link ProgressDialog}
	 */
	public ProgressDialog getProgressDialog()
	{
		return progressDialog;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (null != Aid.getUserInstance())
		{
			intent = new Intent();
			intent.setClass(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		if (null != getSession())
		{
			// 此处就直接跳转到首页
		}
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_activity_layout);
		findViewsById();
		initViewBySharPreferences();
	}

	private String getSession()
	{
		Set<String> set = new HashSet<String>();
		set.add(Constant.SHARE_SESSION_KEY);
		Bundle bundle = Util.getSharePreferences(LoginActivity.this, set);
		return null == bundle.getString(Constant.SHARE_SESSION_KEY)
				|| bundle.getString(Constant.SHARE_SESSION_KEY).equals("") ? null
				: bundle.getString(Constant.SHARE_SESSION_KEY);
	}

	/** 初始化注册View组件 */
	private void findViewsById()
	{
		userNameEdit = (EditText) findViewById(R.id.login_user_name_edit);
		passwordEdit = (EditText) findViewById(R.id.login_password_edit);
		rememberMeCheck = (CheckBox) findViewById(R.id.login_remeber_me_checkbox);
		loginBtn = (Button) findViewById(R.id.login_btn);
		findPasswordText = (TextView) findViewById(R.id.login_forget_password_text);
		loginBtn.setOnClickListener(this);
		findPasswordText.setOnClickListener(this);
	}

	/**
	 * 初始化界面
	 * 
	 * 如果当时点击了RememberMe,并且登陆成功过一次,则saveSharePreferences(true,ture)后,则直接进入
	 * */
	private void initViewBySharPreferences()
	{
		Set<String> set = new HashSet<String>();
		set.add(Constant.SHARE_LOGIN_NAME);
		set.add(Constant.SHARE_LOGIN_PASSWORD);
		Bundle bundle = Util.getSharePreferences(LoginActivity.this, set);
		userName = bundle.getString(Constant.SHARE_LOGIN_NAME);
		password = bundle.getString(Constant.SHARE_LOGIN_PASSWORD);
		Log.d(toString(), "userName=" + userName + " password=" + password);
		if (null != userName && !"".equals(userName.trim()))
		{
			userNameEdit.setText(userName);
		}
		if (null != password && !"".equals(password))
		{
			passwordEdit.setText(password);
			rememberMeCheck.setChecked(true);
		}
	}

	private boolean validate()
	{
		userName = userNameEdit.getText().toString().trim();
		password = passwordEdit.getText().toString();
		if (userName.equals(""))
		{
			Util.alert(LoginActivity.this, "请输入用户名!");
			userNameEdit.requestFocus();
			return false;
		}
		if (password.equals(""))
		{
			Util.alert(LoginActivity.this, "请输入密码!");
			passwordEdit.requestFocus();
			return false;
		}
		return true;
	}

	private void saveAll()
	{
		saveUserName();
		savePassword();
	}

	private void saveUserName()
	{
		Bundle bundle = new Bundle();
		bundle.putString(Constant.SHARE_LOGIN_NAME, userName);
		Util.saveSharePreferences(LoginActivity.this, bundle);
	}

	private void savePassword()
	{
		Bundle bundle = new Bundle();
		bundle.putString(Constant.SHARE_LOGIN_PASSWORD, password);
		Util.saveSharePreferences(LoginActivity.this, bundle);
	}

	/** 清除密码 */
	private void clearSharePassword()
	{
		Set<String> set = new HashSet<String>();
		set.add(Constant.SHARE_LOGIN_PASSWORD);
		Util.delSharePreferences(LoginActivity.this, set);
	}

	/** 记住我的选项是否勾选 */
	private boolean isRememberMe()
	{
		if (rememberMeCheck.isChecked())
		{
			return true;
		}
		return false;
	}

	@Override
	public void onClick(View arg0)
	{
		switch (arg0.getId())
		{
		case R.id.login_btn:
			startLogin();
			break;
		case R.id.login_forget_password_text:
			intent = new Intent();
			intent.setClass(LoginActivity.this, FindPasswordActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	private void startLogin()
	{
		if (!Util.isNetworkConnected(LoginActivity.this))
		{
			Util.alert(LoginActivity.this, "亲，网络不给力呀！");
			return;
		}
		if (validate() != true)
		{
			return;
		}
		if (isRememberMe())
		{
			saveAll();
		}
		else
		{
			saveUserName();
			clearSharePassword();
		}
		// aid = new Aid();
		// aid.setUid((long) 2222222);
		progressDialog = new ProgressDialog(LoginActivity.this);
		progressDialog.setMessage("正在登录，请稍后...");
		progressDialog.show();
		LoginRequestParam param = new LoginRequestParam(userName,
				Util.md5(password));
		AsyncAid aAid = new AsyncAid(aid);
		// 对结果进行监听
		aAid.login(param, new LoginListener(LoginActivity.this));
	}

	/**
	 * 监听获取用户的基本信息
	 * 
	 * @author Administrator
	 * 
	 */
	private class LoginListener extends
			AbstractRequestListener<LoginResponseBean>
	{
		private Context	context;
		private Handler	handler;

		public LoginListener(Context context)
		{
			this.context = context;
			handler = new Handler(context.getMainLooper());
		}

		@Override
		public void onError(AidError aidError)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (LoginActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						Util.alert(context, "登录失败，请重新登录！");
					}
				}
			});
		}

		@Override
		public void onFault(Throwable fault)
		{
			fault.toString();
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (LoginActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();;
						}
						Util.alert(context, "登录失败，请重新登录！");
						// afterLogin();
					}
				}
			});
		}

		@Override
		public void onComplete(final LoginResponseBean bean)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (LoginActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.setMessage("登录成功，正在跳转...");
						}
						Login loginInstance = bean.getLogin();
						if (null != loginInstance)
						{
							intent = new Intent();
							User user = new User();
							user.setUid(loginInstance.getUid());
							user.setUname(loginInstance.getUname());
							user.setPid(loginInstance.getPid());
							user.setPname(loginInstance.getPname());
							Aid.initInstance();
							Aid.setUser(user);
							Aid.getInstance().savePersistSession();
							FragmentPageOverview.trajectory = new Trajectory(
									loginInstance);
							intent.setClass(LoginActivity.this,
									MainActivity.class);
							startActivity(intent);
						}
						else
						{
							Util.alert(context, "登录失败，请重新登录！");
						}
					}
				}
			});
		}
	}

	private void afterLogin()
	{
		intent = new Intent();
		User user = new User();
		user.setUid(2222222);
		user.setUname("王昌帅");
		user.setPid(3333333);
		user.setPname("老刘");
		Aid.setUser(user);
		Login login = new Login(user.getUname(), user.getUid(),
				user.getPname(), user.getPid(), 116.391729, 39.944713);
		FragmentPageOverview.trajectory = new Trajectory(login);
		intent.setClass(LoginActivity.this, MainActivity.class);
		startActivity(intent);
	}
}
