package com.lingdian.waimaibang.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.model.CityModel;
import com.lingdian.waimaibang.utils.AppUtil;
import com.lingdian.waimaibang.utils.LocationBase;
import com.lingdian.waimaibang.utils.SharedpreferncesUtil;
import com.lingdian.waimaibang.utils.LocationBase.LocationContent;
import com.lingdian.waimaibang.view.MyLetterListView;
import com.lingdian.waimaibang.view.MyLetterListView.OnTouchingLetterChangedListener;
import com.umeng.analytics.MobclickAgent;

public class ActivitySelectCity extends Activity {
	private ListAdapter adapter;
	private ListView personList;
	private ImageView title_bar_left;
	private TextView overlay; // 对话框首字母textview
	private MyLetterListView letterListView; // A-Z listview
	private HashMap<String, Integer> alphaIndexer;// 存放存在的汉语拼音首字母和与之对应的列表位置
	private String[] sections;// 存放存在的汉语拼音首字母
	private Handler handler;
	private OverlayThread overlayThread; // 显示首字母对话框
	private ArrayList<CityModel> allCity_lists; // 所有城市列表
	private ArrayList<CityModel> ShowCity_lists; // 需要显示的城市列表-随搜索而改变
	private ArrayList<CityModel> city_lists;// 城市列表
	private String lngCityName = "";// 存放返回的城市名
	private JSONArray chineseCities;
	private EditText sh;
	private static final int DISMISSDIALOG = 3;

	private TextView now_city;
	private LinearLayout city_title_layout;
	private LinearLayout gps_dingwei_layout;
	private TextView gps_city;
	private LocationBase mLocationBase = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_selectcity);
		personList = (ListView) findViewById(R.id.list_view);
		title_bar_left = (ImageView) findViewById(R.id.title_bar_left);
		allCity_lists = new ArrayList<CityModel>();
		letterListView = (MyLetterListView) findViewById(R.id.MyLetterListView01);
		sh = (EditText) findViewById(R.id.sh);

		LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout header1 = (LinearLayout) inflator.inflate(
				R.layout.listitem_city_listtop_view, null);
		city_title_layout = (LinearLayout) header1
				.findViewById(R.id.city_title_layout);
		now_city = (TextView) header1.findViewById(R.id.now_city);

		gps_dingwei_layout = (LinearLayout) header1.findViewById(R.id.gps_dingwei_layout);
		gps_city = (TextView) header1.findViewById(R.id.gps_city);
		
		lngCityName = SharedpreferncesUtil.getCityInfo(ActivitySelectCity.this);
		if (!TextUtils.isEmpty(lngCityName)) {
			now_city.setText(lngCityName);
		}
		city_title_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("lngCityName", lngCityName);
				setResult(99, intent);
				finish();
			}
		});
		personList.addHeaderView(header1);

		letterListView
				.setOnTouchingLetterChangedListener(new LetterListViewListener());
		alphaIndexer = new HashMap<String, Integer>();
		handler = new Handler();
		overlayThread = new OverlayThread();
		personList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				intent.putExtra("lngCityName",
						ShowCity_lists.get(arg2 - 1).name);
				setResult(99, intent);
				finish();
			}
		});
		title_bar_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		initOverlay();
		Thread thread = new Thread() {
			@Override
			public void run() {
				hotCityInit();
				handler2.sendEmptyMessage(DISMISSDIALOG);
				super.run();
			}
		};
		thread.start();
		
		getLocation1();
	}

	/**
	 * 热门城市
	 */
	public void hotCityInit() {
		CityModel city;
		city = new CityModel("杭州", "");
		allCity_lists.add(city);
		city = new CityModel("上海", "");
		allCity_lists.add(city);
		city = new CityModel("南京", "");
		allCity_lists.add(city);
		city = new CityModel("苏州", "");
		allCity_lists.add(city);
		city = new CityModel("宁波", "");
		allCity_lists.add(city);
		city = new CityModel("无锡", "");
		allCity_lists.add(city);
		city_lists = getCityList();
		allCity_lists.addAll(city_lists);
		ShowCity_lists = allCity_lists;
	}

	private ArrayList<CityModel> getCityList() {
		ArrayList<CityModel> list = new ArrayList<CityModel>();
		try {
			chineseCities = new JSONArray(getResources().getString(
					R.string.citys));
			for (int i = 0; i < chineseCities.length(); i++) {
				JSONObject jsonObject = chineseCities.getJSONObject(i);
				CityModel city = new CityModel(jsonObject.getString("name"),
						jsonObject.getString("pinyin"));
				list.add(city);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(list, comparator);
		return list;
	}

	/**
	 * a-z排序
	 */
	Comparator comparator = new Comparator<CityModel>() {
		@Override
		public int compare(CityModel lhs, CityModel rhs) {
			String a = lhs.getPinyi().substring(0, 1);
			String b = rhs.getPinyi().substring(0, 1);
			int flag = a.compareTo(b);
			if (flag == 0) {
				return a.compareTo(b);
			} else {
				return flag;
			}

		}
	};

	// private void setAdapter(List<City> list) {
	// adapter = new ListAdapter(this, list);
	// personList.setAdapter(adapter);
	// }

	public class ListAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		final int VIEW_TYPE = 3;

		public ListAdapter(Context context) {
			this.inflater = LayoutInflater.from(context);
			alphaIndexer = new HashMap<String, Integer>();
			sections = new String[ShowCity_lists.size()];
			for (int i = 0; i < ShowCity_lists.size(); i++) {
				// 当前汉语拼音首字母
				String currentStr = getAlpha(ShowCity_lists.get(i).getPinyi());
				// 上一个汉语拼音首字母，如果不存在为“ ”
				String previewStr = (i - 1) >= 0 ? getAlpha(ShowCity_lists.get(
						i - 1).getPinyi()) : " ";
				if (!previewStr.equals(currentStr)) {
					String name = getAlpha(ShowCity_lists.get(i).getPinyi());
					alphaIndexer.put(name, i);
					sections[i] = name;
				}
			}
		}

		@Override
		public int getCount() {
			return ShowCity_lists.size();
		}

		@Override
		public Object getItem(int position) {
			return ShowCity_lists.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public int getItemViewType(int position) {
			// TODO Auto-generated method stub
			int type = 2;

			if (position == 0 && sh.getText().length() == 0) {// 不是在搜索状态下
				type = 0;
			}
			return type;
		}

		@Override
		public int getViewTypeCount() {// 这里需要返回需要集中布局类型，总大小为类型的种数的下标
			return VIEW_TYPE;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			int viewType = getItemViewType(position);
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.city_list_item, null);
				holder = new ViewHolder();
				holder.alpha = (TextView) convertView.findViewById(R.id.alpha);
				holder.name = (TextView) convertView.findViewById(R.id.name);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			// if (sh.getText().length()==0) {//搜所状态
			// holder.name.setText(list.get(position).getName());
			// holder.alpha.setVisibility(View.GONE);
			// }else if(position>0){
			// 显示拼音和热门城市，一次检查本次拼音和上一个字的拼音，如果一样则不显示，如果不一样则显示

			holder.name.setText(ShowCity_lists.get(position).getName());
			String currentStr = getAlpha(ShowCity_lists.get(position)
					.getPinyi());// 本次拼音
			String previewStr = (position - 1) >= 0 ? getAlpha(ShowCity_lists
					.get(position - 1).getPinyi()) : " ";// 上一个拼音
			if (!previewStr.equals(currentStr)) {// 不一样则显示
				holder.alpha.setVisibility(View.VISIBLE);
				if (currentStr.equals("#")) {
					currentStr = "热门城市";
				}
				holder.alpha.setText(currentStr);
			} else {
				holder.alpha.setVisibility(View.GONE);
			}
			// }
			return convertView;
		}

		private class ViewHolder {
			TextView alpha; // 首字母标题
			TextView name; // 城市名字
		}
	}

	// 初始化汉语拼音首字母弹出提示框
	private void initOverlay() {
		LayoutInflater inflater = LayoutInflater.from(this);
		overlay = (TextView) inflater.inflate(R.layout.overlay, null);
		overlay.setVisibility(View.INVISIBLE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
						| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				PixelFormat.TRANSLUCENT);
		WindowManager windowManager = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.addView(overlay, lp);
	}

	private class LetterListViewListener implements
			OnTouchingLetterChangedListener {

		@Override
		public void onTouchingLetterChanged(final String s) {
			if (alphaIndexer.get(s) != null) {
				int position = alphaIndexer.get(s);
				personList.setSelection(position);
				overlay.setText(sections[position]);
				overlay.setVisibility(View.VISIBLE);
				handler.removeCallbacks(overlayThread);
				// 延迟一秒后执行，让overlay为不可见
				handler.postDelayed(overlayThread, 0);
			}
		}

	}

	// 设置overlay不可见
	private class OverlayThread implements Runnable {
		@Override
		public void run() {
			overlay.setVisibility(View.GONE);
		}

	}

	// 获得汉语拼音首字母
	private String getAlpha(String str) {

		if (str.equals("-")) {
			return "&";
		}
		if (str == null) {
			return "#";
		}
		if (str.trim().length() == 0) {
			return "#";
		}
		char c = str.trim().substring(0, 1).charAt(0);
		// 正则表达式，判断首字母是否是英文字母
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c + "").matches()) {
			return (c + "").toUpperCase();
		} else {
			return "#";
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	Handler handler2 = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case DISMISSDIALOG:
				adapter = new ListAdapter(ActivitySelectCity.this);
				personList.setAdapter(adapter);

				sh.addTextChangedListener(new TextWatcher() {
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
					}

					@Override
					public void afterTextChanged(Editable s) {
						// 搜索符合用户输入的城市名
						if (s.length() > 0) {
							ArrayList<CityModel> changecity = new ArrayList<CityModel>();
							for (int i = 0; i < city_lists.size(); i++) {
								if (city_lists.get(i).name.indexOf(sh.getText()
										.toString()) != -1) {
									changecity.add(city_lists.get(i));
								}
							}
							ShowCity_lists = changecity;
						} else {
							ShowCity_lists = allCity_lists;
						}
						adapter.notifyDataSetChanged();
					}
				});
				break;
			default:
				break;
			}
		};
	};
	
	
	/**
	 * 开启定位
	 * 
	 */
	private void getLocation1() {
		this.mLocationBase = new LocationBase(this, true,
				new LocationBase.LocationListener() {

					public void onGetGaodeLocation(
							LocationContent paramLocationContent,
							boolean paramBoolean) {
						// TODO Auto-generated method stub
						String dingwei_name = paramLocationContent.getCity();
						
						if (TextUtils.isEmpty(dingwei_name)) {
							gps_dingwei_layout.setVisibility(View.GONE);
						}else{
							gps_dingwei_layout.setVisibility(View.VISIBLE);
							dingwei_name = dingwei_name.replace("市", "");
							gps_city.setText(dingwei_name);
						}
					}
				});
	}
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
