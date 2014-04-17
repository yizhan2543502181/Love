/**
 * 
 */
package cn.edu.jlu.ccst.firstaidoflove.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.edu.jlu.ccst.firstaidoflove.AbstractAidRequestActivity;
import cn.edu.jlu.ccst.firstaidoflove.AidApplication;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.activity.main.LoginActivity;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractRequestListener;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.Aid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidError;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AsyncAid;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory.TrajectoriesGetRequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory.TrajectoriesGetResponseBean;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.trajectory.Trajectory;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.search.MKRoute;
import com.baidu.platform.comapi.basestruct.GeoPoint;

/**
 * @author Administrator
 * 
 */
public class RecentTrajectoryActivity extends AbstractAidRequestActivity
{
	// 地图相关
	MapView								mMapView		= null;								// 地图View
	private TextView					noMessageText	= null;
	private ProgressDialog				progressDialog	= null;
	private ListView					listView		= null;
	private List<Trajectory>			trajectoryList	= new ArrayList<Trajectory>();
	private List<Map<String, Object>>	list			= new ArrayList<Map<String, Object>>();
	@SuppressLint("UseValueOf")
	private Integer						lock			= new Integer(0);
	private List<GeoPoint>				pointList		= new ArrayList<GeoPoint>();
	private RouteOverlay				routeOverlay	= null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.recent_trajectory_activity_layout);
		baiduMapInit();
		initView();
		updateList();
	}

	private void baiduMapInit()
	{
		/**
		 * 使用地图sdk前需先初始化BMapManager. BMapManager是全局的，可为多个MapView共用，它需要地图模块创建前创建， 并在地图地图模块销毁后销毁，只要还有地图模块在使用，BMapManager就不应该销毁
		 */
		AidApplication app = (AidApplication) getApplication();
		if (app.mBMapManager == null)
		{
			app.mBMapManager = new BMapManager(
					RecentTrajectoryActivity.this.getApplicationContext());
			/**
			 * 如果BMapManager没有初始化则初始化BMapManager
			 */
			app.mBMapManager.init(new AidApplication.MyGeneralListener());
		}
	}

	private void initView()
	{
		noMessageText = (TextView) findViewById(R.id.recent_trajectory_no_message_text);
		listView = (ListView) findViewById(R.id.recent_trajectory_list);
		listView.setOnItemClickListener(itemClickListener);
		mMapView = (MapView) findViewById(R.id.recent_trajectory_baidumap);
		mMapView.getController().enableClick(true);
		mMapView.getController().setZoom(12);
		mMapView.getController().setZoomGesturesEnabled(true);
		mMapView.getController().setRotationGesturesEnabled(false);
		mMapView.getController().setScrollGesturesEnabled(true);
		startGetTrajectories();
	}

	private void genList()
	{
		Random random = new Random();
		list.clear();
		for (int i = 0; i < 6; ++i)
		{
			trajectoryList.add(
					0,
					new Trajectory(currentUser.getPid(),
							currentUser.getPname(), 116.391729 + random
									.nextFloat() / 10.0, 39.944713 + random
									.nextFloat() / 10.0, "2014.04.15"));
			Trajectory trajectory = trajectoryList.get(i);
			addListItem("姓名：" + trajectory.getPname() + "\n时间："
					+ trajectory.getTime() + "\n位置：" + "经"
					+ trajectory.getLongtitude() + "° " + "纬"
					+ trajectory.getLatitude() + "° ");
		}
	}

	private void addListItem(String content)
	{
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		map.put("list_trajectory_item_text", content);
		synchronized (lock)
		{
			list.add(0, map);
		}
	}

	public void updateList()
	{
		genList();
		if (list.size() == 0)
		{
			noMessageText.setVisibility(View.VISIBLE);
		}
		else
		{
			noMessageText.setVisibility(View.GONE);
		}
		final SimpleAdapter adapter = new SimpleAdapter(
				RecentTrajectoryActivity.this, list,
				R.layout.list_trajectory_item_layout,
				new String[] { "list_trajectory_item_text" },
				new int[] { R.id.list_trajectory_item_text });
		listView.setAdapter(adapter);
		route();
		if (pointList.size() > 0)
		{
			moveToSpecialPoint(pointList.get(0));
		}
	}

	private void route()
	{
		pointList.clear();
		for (Trajectory trajectory : trajectoryList)
		{
			pointList.add(new GeoPoint((int) (trajectory.getLatitude() * 1E6),
					(int) (trajectory.getLongtitude() * 1E6)));
		}
		// 起点坐标
		GeoPoint start = pointList.get(0);
		// 终点坐标
		GeoPoint stop = pointList.get(pointList.size() - 1);
		/*
		 * // 第一站，站点坐标为p3,经过p1,p2 GeoPoint[] step1 = new GeoPoint[3]; step1[0] = p1; step1[1] = p2; step1[2] = p3; // 第二站，站点坐标为p5,经过p4 GeoPoint[] step2 = new GeoPoint[2]; step2[0] = p4; step2[1] = p5; // 第三站，站点坐标为p7,经过p6 GeoPoint[] step3 = new GeoPoint[2]; step3[0] = p6; step3[1] = p7;
		 */
		// 站点数据保存在一个二维数据中
		GeoPoint[][] routeData = new GeoPoint[6][];
		for (int i = 0; i < routeData.length; i++)
		{
			routeData[i] = new GeoPoint[1];
			routeData[i][0] = pointList.get(i);
		}
		// 用站点数据构建一个MKRoute
		MKRoute route = new MKRoute();
		route.customizeRoute(start, stop, routeData);
		// 将包含站点信息的MKRoute添加到RouteOverlay中
		routeOverlay = new RouteOverlay(RecentTrajectoryActivity.this, mMapView);
		routeOverlay.setData(route);
		// 清除地图其他图层
		mMapView.getOverlays().clear();
		// 向地图添加构造好的RouteOverlay
		mMapView.getOverlays().add(routeOverlay);
		// 执行刷新使生效
		mMapView.refresh();
	}

	private void moveToSpecialPoint(GeoPoint point)
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
		// 向地图添加构造好的RouteOverlay
		mMapView.getOverlays().add(routeOverlay);
		// 添加一个标注ItemizedOverlay图层
		mMapView.getOverlays().add(itemOverlay);
		// 执行刷新使生效
		mMapView.refresh();
	}

	/**
	 * 列表item点击事件
	 * 
	 * @author Administrator
	 * 
	 */
	OnItemClickListener	itemClickListener	= new OnItemClickListener() {
												@Override
												public void onItemClick(
														AdapterView<?> arg0,
														View arg1,
														int position, long arg3)
												{
													moveToSpecialPoint(pointList
															.get(position));
												}
											};

	private void startGetTrajectories()
	{
		if (null == aid || null == currentUser)
		{
			Util.alert(getApplicationContext(), "用户信息异常，请重新登录！");
			intent.setClass(getApplicationContext(), LoginActivity.class);
			startActivity(intent);
			finish();
		}
		TrajectoriesGetRequestParam param = new TrajectoriesGetRequestParam(
				String.valueOf(aid.getCurrentUid()), String.valueOf(Aid
						.getUserInstance().getPid()));
		param.setUid(String.valueOf(aid.getCurrentUid()));
		try
		{
			progressDialog = new ProgressDialog(RecentTrajectoryActivity.this);
			progressDialog.setMessage("正在获取轨迹，请稍后...");
			progressDialog.show();
			AsyncAid aAid = new AsyncAid(aid);
			// 对结果进行监听
			aAid.getRecentTrajectories(param, new TrajectoriesGetListener());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 监听获取最近运动轨迹
	 * 
	 * @author Administrator
	 * 
	 */
	private class TrajectoriesGetListener extends
			AbstractRequestListener<TrajectoriesGetResponseBean>
	{
		private Handler	handler	= new Handler();

		@Override
		public void onError(AidError AidError)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (RecentTrajectoryActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
					}
					Util.alert(RecentTrajectoryActivity.this, "获取最近轨迹失败");
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
					if (RecentTrajectoryActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						Util.alert(RecentTrajectoryActivity.this, "获取最近轨迹失败");
					}
				}
			});
		}

		@Override
		public void onComplete(final TrajectoriesGetResponseBean bean)
		{
			handler.post(new Runnable() {
				@Override
				public void run()
				{
					if (RecentTrajectoryActivity.this != null)
					{
						if (progressDialog != null
								&& progressDialog.isShowing())
						{
							progressDialog.dismiss();
						}
						if (null != bean.getTrajectories())
						{
							trajectoryList = bean.getTrajectories();
							updateList();
						}
						else
						{
							Util.alert(RecentTrajectoryActivity.this,
									"获取最近轨迹失败");
						}
					}
				}
			});
		}
	}

	@Override
	protected void onPause()
	{
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy()
	{
		mMapView.destroy();
		super.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);
		mMapView.onRestoreInstanceState(savedInstanceState);
	}
}
