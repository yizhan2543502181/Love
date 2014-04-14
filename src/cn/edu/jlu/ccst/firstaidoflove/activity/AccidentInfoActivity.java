/**
 * 
 */
package cn.edu.jlu.ccst.firstaidoflove.activity;

import java.util.concurrent.Executors;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.jlu.ccst.firstaidoflove.AidApplication;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.functions.AbstractAidRequestActivity;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.accident.Accident;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

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

/**
 * @author Administrator
 * 
 */
public class AccidentInfoActivity extends AbstractAidRequestActivity implements
		OnClickListener
{
	private TextView	locationText			= null;
	private TextView	patientNameText			= null;
	private TextView	patientMedicalHistory	= null;
	private MapView		mMapView				= null;
	// 搜索相关
	private MKSearch	mSearch					= null; // 搜索模块，也可去掉地图模块独立使用
	private GeoPoint	point					= null;
	private String		location				= null;
	private Accident	accident				= null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Bundle bundle = intent.getExtras();
		if (bundle != null && bundle.containsKey(Accident.ACCIDENT_LABLE))
		{
			accident = bundle.getParcelable(Accident.ACCIDENT_LABLE);
		}
		setContentView(R.layout.accident_info_activity_layout);
		baiduMapInit();
		initView();
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
					AccidentInfoActivity.this.getApplicationContext());
			/**
			 * 如果BMapManager没有初始化则初始化BMapManager
			 */
			app.mBMapManager.init(new AidApplication.MyGeneralListener());
		}
		// 初始化搜索模块，注册事件监听
		mSearch = new MKSearch();
		mSearch.init(app.mBMapManager, new BaiduSearchListener());
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
			locationText.setText(location + "附近");
		}
	}

	private void initView()
	{
		locationText = (TextView) findViewById(R.id.accident_info_location_text);
		locationText.setOnClickListener(this);
		patientNameText = (TextView) findViewById(R.id.accident_info_patient_name_text);
		patientMedicalHistory = (TextView) findViewById(R.id.accident_info_medical_history);
		if (null != accident)
		{
			patientNameText.setText(accident.getPname());
			patientMedicalHistory.setText(accident.getMedicalHistory());
		}
		mMapView = (MapView) findViewById(R.id.accident_info_baidumap);
		mMapView.getController().enableClick(true);
		mMapView.getController().setZoom(12);
		mMapView.getController().setZoomGesturesEnabled(true);
		mMapView.getController().setRotationGesturesEnabled(false);
		mMapView.getController().setScrollGesturesEnabled(true);
		if (null != point)
		{
			moveToSpecialPoint(point, location);
		}
		else
		{
			startLocate();
		}
	}

	private void startLocate()
	{
		Executors.newFixedThreadPool(2).execute(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					if (null != accident)
					{
						if (null != AccidentInfoActivity.this)
						{
							AccidentInfoActivity.this
									.runOnUiThread(new Runnable() {
										@Override
										public void run()
										{
											progressDialog = new ProgressDialog(
													AccidentInfoActivity.this);
											progressDialog
													.setMessage("正在获取事故位置...");
											progressDialog.show();
										}
									});
						}
						point = new GeoPoint(
								(int) (accident.getLatitude() * 1e6),
								(int) (accident.getLongtitude() * 1e6));
						// 反Geo搜索
						mSearch.reverseGeocode(point);
					}
					else
					{
						if (null != AccidentInfoActivity.this)
						{
							AccidentInfoActivity.this
									.runOnUiThread(new Runnable() {
										@Override
										public void run()
										{
											Util.alert(
													AccidentInfoActivity.this,
													"获取位置失败，请返回重新打开此页面！");
											locationText
													.setText("位置获取失败,点此重新获取");
										}
									});
						}
					}
				}
				catch (Exception e)
				{
					Util.logger("aid exception " + e.getMessage());
				}
			}
		});
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.accident_info_location_text:
			startLocate();
			break;
		default:
			break;
		}
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
				location = "位置获取失败,点此重新获取";
				locationText.setText(location);
				String str = String.format("错误号：%d", error);
				Toast.makeText(AccidentInfoActivity.this, str,
						Toast.LENGTH_LONG).show();
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
				Toast.makeText(AccidentInfoActivity.this, strInfo,
						Toast.LENGTH_LONG).show();
			}
			if (res.type == MKAddrInfo.MK_REVERSEGEOCODE)
			{
				// 反地理编码：通过坐标点检索详细地址及周边poi
				String strInfo = res.strAddr;
				location = strInfo;
				location = location == null ? "位置获取失败,点此重新获取" : location;
				locationText.setText(location + "附近(点击重新获取)");
			}
			moveToSpecialPoint(res.geoPt, location);
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
}
