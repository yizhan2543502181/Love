/**
 * 
 */
package cn.edu.jlu.ccst.firstaidoflove.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.edu.jlu.ccst.firstaidoflove.AidApplication;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractAidRequestActivity;
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
	private ListView					listView;
	private List<Map<String, Object>>	list			= new ArrayList<Map<String, Object>>();
	private List<Boolean>				listIsImport	= new ArrayList<Boolean>();
	@SuppressLint("UseValueOf")
	private Integer						lock			= new Integer(0);
	private List<GeoPoint>				pointList		= new ArrayList<GeoPoint>();
	private RouteOverlay				routeOverlay	= null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/**
		 * 使用地图sdk前需先初始化BMapManager. BMapManager是全局的，可为多个MapView共用，它需要地图模块创建前创建， 并在地图地图模块销毁后销毁，只要还有地图模块在使用，BMapManager就不应该销毁
		 */
		AidApplication app = (AidApplication) getApplication();
		if (app.mBMapManager == null)
		{
			app.mBMapManager = new BMapManager(getApplicationContext());
			/**
			 * 如果BMapManager没有初始化则初始化BMapManager
			 */
			app.mBMapManager.init(new AidApplication.MyGeneralListener());
		}
		setContentView(R.layout.recent_trajectory_activity_layout);
		genList();
		initView();
		CharSequence titleLable = "路线规划功能——自设路线示例";
		setTitle(titleLable);
		// 初始化地图
		mMapView = (MapView) findViewById(R.id.recent_trajectory_baidumap);
		mMapView.getController().enableClick(true);
		mMapView.getController().setZoom(13);
		/**
		 * 演示自定义路线使用方法 在北京地图上画一个北斗七星 想知道某个点的百度经纬度坐标请点击：http://api.map.baidu.com/lbsapi/getpoint/index.html
		 */
		route();
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

	private void route()
	{
		pointList.clear();
		pointList.add(new GeoPoint((int) (39.9411 * 1E6),
				(int) (116.3714 * 1E6)));
		pointList.add(new GeoPoint((int) (39.9498 * 1E6),
				(int) (116.3785 * 1E6)));
		pointList.add(new GeoPoint((int) (39.9436 * 1E6),
				(int) (116.4029 * 1E6)));
		pointList.add(new GeoPoint((int) (39.9329 * 1E6),
				(int) (116.4035 * 1E6)));
		pointList.add(new GeoPoint((int) (39.9218 * 1E6),
				(int) (116.4115 * 1E6)));
		pointList.add(new GeoPoint((int) (39.9144 * 1E6),
				(int) (116.4230 * 1E6)));
		pointList.add(new GeoPoint((int) (39.9126 * 1E6),
				(int) (116.4387 * 1E6)));
		// 起点坐标
		GeoPoint start = pointList.get(0);
		// 终点坐标
		GeoPoint stop = pointList.get(pointList.size() - 1);
		/*
		 * // 第一站，站点坐标为p3,经过p1,p2 GeoPoint[] step1 = new GeoPoint[3]; step1[0] = p1; step1[1] = p2; step1[2] = p3; // 第二站，站点坐标为p5,经过p4 GeoPoint[] step2 = new GeoPoint[2]; step2[0] = p4; step2[1] = p5; // 第三站，站点坐标为p7,经过p6 GeoPoint[] step3 = new GeoPoint[2]; step3[0] = p6; step3[1] = p7;
		 */
		// 站点数据保存在一个二维数据中
		GeoPoint[][] routeData = new GeoPoint[7][];
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

	private void initView()
	{
		noMessageText = (TextView) findViewById(R.id.recent_trajectory_no_message_text);
		listView = (ListView) findViewById(R.id.recent_trajectory_list);
		listView.setOnItemClickListener(itemClickListener);
		listView.setOnItemLongClickListener(itemLongClickListener);
		updateList();
	}

	/**
	 * 启动线程更新列表
	 * 
	 * @param pathTemp
	 */
	private void updateList()
	{
		(new ListViewUpdateThread()).start();
	}

	/**
	 * 列表item点击事件
	 * 
	 * @author Administrator
	 * 
	 */
	OnItemClickListener		itemClickListener		= new OnItemClickListener() {
														@Override
														public void onItemClick(
																AdapterView<?> arg0,
																View arg1,
																int position,
																long arg3)
														{
															moveToSpecialPoint(pointList
																	.get(position));
														}
													};
	OnItemLongClickListener	itemLongClickListener	= new OnItemLongClickListener() {
														@Override
														public boolean onItemLongClick(
																AdapterView<?> arg0,
																View arg1,
																final int position,
																long arg3)
														{
															Util.showOptionWindow(
																	RecentTrajectoryActivity.this,
																	"询问",
																	"是否删除此条信息？",
																	new Util.OnOptionListener() {
																		@Override
																		public void onOK()
																		{
																			synchronized (lock)
																			{
																				list.remove(position);
																				listIsImport
																						.remove(position);
																			}
																			updateList();
																		}

																		@Override
																		public void onCancel()
																		{}
																	});
															return false;
														}
													};

	/**
	 * 产生新列表的方法
	 * 
	 * @param path
	 * @return
	 */
	private void genList()
	{
		for (int i = 0; i < 6; ++i)
		{
			addListItem("\t姓名：李四" + "\n\t位置：和平广场" + i + "号"
					+ "\n\t时间：2014.4.3 18:4" + i);
		}
	}

	private void addListItem(String content)
	{
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		map.put("trajectory_item_text", content);
		synchronized (lock)
		{
			list.add(0, map);
			listIsImport.add(true);
		}
	}

	/**
	 * 用于更新列表的线程类
	 * 
	 * @param pathTemp
	 */
	public class ListViewUpdateThread extends Thread
	{
		@Override
		public void run()
		{
			runOnUiThread(new Runnable() {
				@Override
				public void run()
				{
					progressDialog = new ProgressDialog(
							RecentTrajectoryActivity.this);
					progressDialog.setMessage("正在加载...");
					progressDialog.setTitle("请稍后...");
					progressDialog.show();
					if (list.size() == 0)
					{
						noMessageText.setVisibility(View.VISIBLE);
					}
				}
			});
			final SimpleAdapter adapter = new SimpleAdapter(
					RecentTrajectoryActivity.this, list,
					R.layout.list_trajectory_item_layout,
					new String[] { "trajectory_item_text" },
					new int[] { R.id.trajectory_item_text });
			runOnUiThread(new Runnable() {
				@Override
				public void run()
				{
					listView.setAdapter(adapter);
					progressDialog.cancel();
				}
			});
		}
	}
}
