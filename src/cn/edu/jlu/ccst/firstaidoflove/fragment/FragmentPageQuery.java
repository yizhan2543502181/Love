package cn.edu.jlu.ccst.firstaidoflove.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import cn.edu.jlu.ccst.firstaidoflove.R;
import cn.edu.jlu.ccst.firstaidoflove.activity.LocalesActivity;
import cn.edu.jlu.ccst.firstaidoflove.activity.MedicalRecordsActivity;
import cn.edu.jlu.ccst.firstaidoflove.activity.PatientInfoActivity;
import cn.edu.jlu.ccst.firstaidoflove.activity.PersonalInfoActivity;
import cn.edu.jlu.ccst.firstaidoflove.activity.RecentAccidentsActivity;
import cn.edu.jlu.ccst.firstaidoflove.activity.RecentTrajectoryActivity;
import cn.edu.jlu.ccst.firstaidoflove.util.Util;

public class FragmentPageQuery extends Fragment
{
	private Intent				intent		= null;
	private static final String	functions[]	= { "个人信息", "病人信息", "最近事故", "最近轨迹",
			"病历查询", "热点查询", "交易记录"			};

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View layout = inflater.inflate(R.layout.query_activity_layout, null);
		init(layout);
		return layout;
	}

	private void init(View layout)
	{
		GridView gridview = (GridView) layout.findViewById(R.id.query_gridview);
		// 生成动态数组，并且转入数据
		ArrayList<HashMap<String, Object>> itemList = new ArrayList<HashMap<String, Object>>();
		for (String function : FragmentPageQuery.functions)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", R.drawable.icon_square_sel);// 添加图像资源的ID
			map.put("text", function);// 按序号做text
			itemList.add(map);
		}
		// 生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
		SimpleAdapter saImageItems = new SimpleAdapter(getActivity(), // 没什么解释
				itemList,// 数据来源
				R.layout.gridview_item_layout,// night_item的XML实现
				// 动态数组与ImageItem对应的子项
				new String[] { "image", "text" },
				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.gridview_item_image, R.id.gridview_item_text });
		// 添加并且显示
		gridview.setAdapter(saImageItems);
		// 添加消息处理
		gridview.setOnItemClickListener(new ItemClickListener());
	}

	// 当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件
	class ItemClickListener implements OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> arg0,// The AdapterView where the click happened
				View arg1,// The view within the AdapterView that was clicked
				int arg2,// The position of the view in the adapter
				long arg3// The row id of the item that was clicked
		)
		{
			intent = new Intent();
			// 在本例中arg2=arg3
			@SuppressWarnings("unchecked")
			HashMap<String, Object> item = (HashMap<String, Object>) arg0
					.getItemAtPosition(arg2);
			switch (arg2)
			{
			case 0:
				intent.setClass(getActivity(), PersonalInfoActivity.class);
				startActivity(intent);
				break;
			case 1:
				intent.setClass(getActivity(), PatientInfoActivity.class);
				startActivity(intent);
				break;
			case 2:
				intent.setClass(getActivity(), RecentAccidentsActivity.class);
				startActivity(intent);
				break;
			case 3:
				intent.setClass(getActivity(), RecentTrajectoryActivity.class);
				startActivity(intent);
				break;
			case 4:
				intent.setClass(getActivity(), MedicalRecordsActivity.class);
				startActivity(intent);
				break;
			case 5:
				intent.setClass(getActivity(), LocalesActivity.class);
				startActivity(intent);
				break;
			case 6:
				Util.showAlert(getActivity(), "提示", "暂未实现");
				break;
			default:
				break;
			}
		}
	}
}
