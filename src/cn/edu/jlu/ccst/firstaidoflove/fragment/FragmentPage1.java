package cn.edu.jlu.ccst.firstaidoflove.fragment;

import java.util.concurrent.Executors;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.jlu.ccst.firstaidoflove.AidApplication;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.activity.LoginActivity;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractRequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.RequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.Aid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidError;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AsyncAid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.login.Login;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory.Trajectory;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory.TrajectoryGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory.TrajectoryGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.user.User;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;
import cn.edu.jlu.ccst.firstaidoflove.util.Util.OnOptionListener;
import cn.jpush.android.api.JPushInterface;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKShareUrlResult;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class FragmentPage1 extends Fragment implements OnClickListener
{
	private TextView			userNameText	= null;
	private TextView			patientNameText	= null;
	private TextView			locationText	= null;
	private MapView				mMapView		= null;
	private Button				logout_btn		= null;
	// 搜索相关
	private MKSearch			mSearch			= null;			// 搜索模块，也可去掉地图模块独立使用
	public static Trajectory	trajectory		= null;
	private static GeoPoint		point			= null;
	private static String		location		= null;
	private ProgressDialog		progressDialog	= null;
	private Login				login			= null;
	private final String		errorMessage	= "获取位置失败,点此重新获取";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		baiduMapInit();
	}

	private void baiduMapInit()
	{
		/**
		 * 使用地图sdk前需先初始化BMapManager. BMapManager是全局的，可为多个MapView共用，它需要地图模块创建前创建， 并在地图地图模块销毁后销毁，只要还有地图模块在使用，BMapManager就不应该销毁
		 */
		AidApplication app = (AidApplication) getActivity().getApplication();
		if (app.mBMapManager == null)
		{
			app.mBMapManager = new BMapManager(getActivity()
					.getApplicationContext());
			/**
			 * 如果BMapManager没有初始化则初始化BMapManager
			 */
			app.mBMapManager.init(new AidApplication.MyGeneralListener());
		}
		// 初始化搜索模块，注册事件监听
		mSearch = new MKSearch();
		mSearch.init(app.mBMapManager, new BaiduSearchListener());
	}

	private void initView()
	{
		logout_btn = (Button) getActivity().findViewById(R.id.logout_btn);
		logout_btn.setOnClickListener(this);
		userNameText = (TextView) getActivity().findViewById(
				R.id.overview_activity_user_name_text);
		patientNameText = (TextView) getActivity().findViewById(
				R.id.overview_activity_patient_name_text);
		if (null != Aid.getUserInstance())
		{
			userNameText.setText(Aid.getUserInstance().getUname());
			patientNameText.setText(Aid.getUserInstance().getPname());
		}
		locationText = (TextView) getActivity().findViewById(
				R.id.overview_location_text);
		locationText.setOnClickListener(this);
		mMapView = (MapView) getActivity().findViewById(R.id.overview_baidumap);
		mMapView.getController().enableClick(true);
		mMapView.getController().setZoom(12);
		mMapView.getController().setZoomGesturesEnabled(true);
		mMapView.getController().setRotationGesturesEnabled(false);
		mMapView.getController().setScrollGesturesEnabled(true);
		if (null != FragmentPage1.point
				&& null != FragmentPage1.location
				&& !FragmentPage1.location.equals(errorMessage))
		{
			moveToSpecialPoint(FragmentPage1.point,
					FragmentPage1.location);
		}
		else if (null != FragmentPage1.trajectory)
		{
			FragmentPage1.point = new GeoPoint(
					(int) (FragmentPage1.trajectory.getLatitude() * 1e6),
					(int) (FragmentPage1.trajectory.getLongtitude() * 1e6));
			moveToSpecialPoint(FragmentPage1.point, null);
			startParseLocation();
		}
		else
		{
			startLocate();
		}
	}

	private void moveToSpecialPoint(GeoPoint point, String location)
	{// 地图移动到该点
		mMapView.getController().animateTo(point);
		// 生成ItemizedOverlay图层用来标注结果点
		ItemizedOverlay<OverlayItem> itemOverlay = new ItemizedOverlay<OverlayItem>(
				null, mMapView);
		// 生成Item
		OverlayItem item = new OverlayItem(point, "", null);
		// 得到需要标在地图上的资源
		Drawable marker = getResources().getDrawable(R.drawable.icon_gcoding);
		// 为maker定义位置和边界
		marker.setBounds(0, 0, marker.getIntrinsicWidth(),
				marker.getIntrinsicHeight());
		// 给item设置marker
		item.setMarker(marker);
		// 在图层上添加item
		itemOverlay.addItem(item);
		// 清除地图其他图层
		mMapView.getOverlays().clear();
		// 添加一个标注ItemizedOverlay图层
		mMapView.getOverlays().add(itemOverlay);
		// 执行刷新使生效
		mMapView.refresh();
		if (null != location)
		{
			locationText.setText(location + "附近(点击重新获取)");
		}
	}

	private void startLocate()
	{
		Aid aid = Aid.getInstance();
		User currentUser = Aid.getUserInstance();
		Intent intent = new Intent();
		if (null == aid || null == currentUser)
		{
			Util.alert(getActivity().getApplicationContext(), "用户信息异常，请重新登录！");
			intent = new Intent();
			intent.setClass(getActivity().getApplicationContext(),
					LoginActivity.class);
			startActivity(intent);
			getActivity().finish();
		}
		TrajectoryGetRequestParam param = new TrajectoryGetRequestParam(
				String.valueOf(aid.getCurrentUid()), String.valueOf(Aid
						.getUserInstance().getPid()));
		param.setUid(String.valueOf(aid.getCurrentUid()));
		try
		{
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setMessage("正在获取最后一次位置，请稍后...");
			progressDialog.show();
			AsyncAid aAid = new AsyncAid(aid);
			// 对结果进行监听
			aAid.getRecentTrajectory(param, new TrajectoryGetListener());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 监听获取最近一次位置
	 * 
	 * @author Administrator
	 * 
	 */
	private class TrajectoryGetListener extends
			AbstractRequestListener<TrajectoryGetResponseBean>
	{
		private Handler	handler	= new Handler();

		@Override
		public void onError(AidError AidError)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (getActivity() != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						FragmentPage1.location = errorMessage;
						locationText.setText(FragmentPage1.location);
					}
					Util.alert(getActivity(), "获取最近一次位置失败！");
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
					if (getActivity() != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						FragmentPage1.location = errorMessage;
						locationText.setText(FragmentPage1.location);
						Util.alert(getActivity(), "获取最近一次位置失败！");
					}
				}
			});
		}

		@Override
		public void onComplete(final TrajectoryGetResponseBean bean)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (getActivity() != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						if (null != bean.getTrajectory())
						{
							FragmentPage1.trajectory = bean
									.getTrajectory();
							FragmentPage1.point = new GeoPoint(
									(int) (FragmentPage1.trajectory
											.getLatitude() * 1e6),
									(int) (FragmentPage1.trajectory
											.getLongtitude() * 1e6));
							moveToSpecialPoint(FragmentPage1.point, null);
							startParseLocation();
						}
						else
						{
							FragmentPage1.location = errorMessage;
							locationText.setText(FragmentPage1.location);
							Util.alert(getActivity(), "获取最近一次位置失败！");
						}
					}
				}
			});
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.logout_btn:
			Util.showOptionWindow(getActivity(), "提示", "注销后需重新登录，确定注销?",
					new OnOptionListener() {
						@Override
						public void onOK()
						{
							AsyncAid aAid = new AsyncAid(Aid.getInstance());
							aAid.logout(new RequestListener() {
								@Override
								public void onFault(Throwable fault)
								{
									getActivity().finish();
								}

								@Override
								public void onComplete(String response)
								{
									getActivity().finish();
								}

								@Override
								public void onAidError(AidError AidError)
								{
									getActivity().finish();
								}
							});
							JPushInterface.stopPush(getActivity()
									.getApplicationContext());
						}

						@Override
						public void onCancel()
						{}
					});
			break;
		case R.id.overview_location_text:
			startParseLocation();
			break;
		default:
			break;
		}
	}

	private void startParseLocation()
	{
		Executors.newFixedThreadPool(2).execute(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					if (null != getActivity())
					{
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run()
							{
								progressDialog = new ProgressDialog(
										getActivity());
								progressDialog.setMessage("正在解析位置...");
								progressDialog.show();
							}
						});
					}
					// 反Geo搜索
					mSearch.reverseGeocode(FragmentPage1.point);
				}
				catch (Exception e)
				{
					Util.logger("aid exception " + e.getMessage());
				}
			}
		});
	}

	class BaiduSearchListener implements MKSearchListener
	{
		@Override
		public void onGetPoiDetailSearchResult(int type, int error)
		{
			if (null != progressDialog && progressDialog.isShowing())
			{
				progressDialog.dismiss();
			}
		}

		@Override
		public void onGetAddrResult(MKAddrInfo res, int error)
		{
			if (null != progressDialog && progressDialog.isShowing())
			{
				progressDialog.dismiss();
			}
			if (error != 0)
			{
				FragmentPage1.location = "解析位置失败,点此重新解析";
				locationText.setText(FragmentPage1.location);
				String str = String.format("错误号：%d", error);
				Toast.makeText(getActivity(), str, Toast.LENGTH_LONG).show();
				return;
			}
			// 地图移动到该点
			mMapView.getController().animateTo(res.geoPt);
			if (res.type == MKAddrInfo.MK_GEOCODE)
			{
				// 地理编码：通过地址检索坐标点
				String strInfo = String.format("纬度：%f 经度：%f",
						res.geoPt.getLatitudeE6() / 1e6,
						res.geoPt.getLongitudeE6() / 1e6);
				Toast.makeText(getActivity(), strInfo, Toast.LENGTH_LONG)
						.show();
			}
			if (res.type == MKAddrInfo.MK_REVERSEGEOCODE)
			{
				// 反地理编码：通过坐标点检索详细地址及周边poi
				String strInfo = res.strAddr;
				FragmentPage1.location = strInfo;
				FragmentPage1.location = FragmentPage1.location == null ? "位置获取失败,点此重新获取"
						: FragmentPage1.location;
				locationText.setText(FragmentPage1.location
						+ "附近(点击重新获取)");
			}
			moveToSpecialPoint(res.geoPt, FragmentPage1.location);
		}

		@Override
		public void onGetPoiResult(MKPoiResult res, int type, int error)
		{
			if (null != progressDialog && progressDialog.isShowing())
			{
				progressDialog.dismiss();
			}
		}

		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult res, int error)
		{
			if (null != progressDialog && progressDialog.isShowing())
			{
				progressDialog.dismiss();
			}
		}

		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult res, int error)
		{
			if (null != progressDialog && progressDialog.isShowing())
			{
				progressDialog.dismiss();
			}
		}

		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult res, int error)
		{
			if (null != progressDialog && progressDialog.isShowing())
			{
				progressDialog.dismiss();
			}
		}

		@Override
		public void onGetBusDetailResult(MKBusLineResult result, int iError)
		{
			if (null != progressDialog && progressDialog.isShowing())
			{
				progressDialog.dismiss();
			}
		}

		@Override
		public void onGetSuggestionResult(MKSuggestionResult res, int arg1)
		{
			if (null != progressDialog && progressDialog.isShowing())
			{
				progressDialog.dismiss();
			}
		}

		@Override
		public void onGetShareUrlResult(MKShareUrlResult result, int type,
				int error)
		{
			if (null != progressDialog && progressDialog.isShowing())
			{
				progressDialog.dismiss();
			}
		}
	}

	@Override
	public void onStart()
	{
		super.onStart();
		if (null != LoginActivity.getInstance()
				&& null != LoginActivity.getInstance().getProgressDialog()
				&& LoginActivity.getInstance().getProgressDialog().isShowing())
		{
			LoginActivity.getInstance().getProgressDialog().dismiss();
			LoginActivity.getInstance().finish();
		}
		initView();
	}

	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.overview_activity_layout, null);
	}

	@Override
	public void onPause()
	{
		mMapView.onPause();
		super.onPause();
	}

	@Override
	public void onResume()
	{
		mMapView.onResume();
		super.onResume();
	}

	@Override
	public void onDestroy()
	{
		mMapView.destroy();
		mSearch.destory();
		super.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);
	}

	public static GeoPoint getPoint()
	{
		return FragmentPage1.point;
	}

	public static void setPoint(GeoPoint point)
	{
		FragmentPage1.point = point;
	}

	public static String getLocation()
	{
		return FragmentPage1.location;
	}

	public static void setLocation(String location)
	{
		FragmentPage1.location = location;
	}
}
