package com.lingdian.waimaibang.fragment.waimai;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

import com.handmark.pulltorefresh.library.PullToRefreshAdapterViewBase.OnPreRefreshingAnimListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.waimai.ElmFenleiActivity;
import com.lingdian.waimaibang.api.DataTask;
import com.lingdian.waimaibang.fragment.BaseFragment;
import com.lingdian.waimaibang.fragment.waimai.adapter.ElmAdapter;
import com.lingdian.waimaibang.model.GsonUtil;
import com.lingdian.waimaibang.tools.Geohash;
import com.lingdian.waimaibang.utils.AsyncHttpUtil;
import com.lingdian.waimaibang.utils.LocationBase;
import com.lingdian.waimaibang.utils.LocationBase.LocationContent;
import com.lingdian.waimaibang.utils.SharedpreferncesUtil;
import com.lingdian.waimaibang.waimaimodel.ShangJiaMainBean;
import com.loopj.android.http.TextHttpResponseHandler;

public class WimaiElmFragment extends BaseFragment implements OnClickListener {

	private Context context;
	private PullToRefreshListView fragment_quanbu_list;
	private List<ShangJiaMainBean> lists = new ArrayList<ShangJiaMainBean>();
	private ElmAdapter mAdapter;

	private boolean isPage = true; // 是否还有下一页
	private int offset = 0;
	private Double latitude;
	private Double longitude;
	public String geocode;

	private LinearLayout elm_layout_1, elm_layout_2, elm_layout_3,
			elm_layout_4, elm_layout_5, elm_layout_6, elm_layout_7;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_waimaibang_elm,
				container, false);
		context = getActivity();
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub

		fragment_quanbu_list = (PullToRefreshListView) this.getView()
				.findViewById(R.id.fragment_quanbu_list);
		LayoutInflater inflator = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout header1 = (LinearLayout) inflator.inflate(
				R.layout.waimai_elm_top_layout, null);

		elm_layout_1 = (LinearLayout) header1.findViewById(R.id.elm_layout_1);
		elm_layout_2 = (LinearLayout) header1.findViewById(R.id.elm_layout_2);
		elm_layout_3 = (LinearLayout) header1.findViewById(R.id.elm_layout_3);
		elm_layout_4 = (LinearLayout) header1.findViewById(R.id.elm_layout_4);
		elm_layout_5 = (LinearLayout) header1.findViewById(R.id.elm_layout_5);
		elm_layout_6 = (LinearLayout) header1.findViewById(R.id.elm_layout_6);
		elm_layout_7 = (LinearLayout) header1.findViewById(R.id.elm_layout_7);

		fragment_quanbu_list.getRefreshableView().addHeaderView(header1);

		mAdapter = new ElmAdapter(context, lists);
		fragment_quanbu_list.setAdapter(mAdapter);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

		elm_layout_1.setOnClickListener(this);
		elm_layout_2.setOnClickListener(this);
		elm_layout_3.setOnClickListener(this);
		elm_layout_4.setOnClickListener(this);
		elm_layout_5.setOnClickListener(this);
		elm_layout_6.setOnClickListener(this);
		elm_layout_7.setOnClickListener(this);

		fragment_quanbu_list
				.setOnRefreshListener(new OnRefreshListener<ListView>() {
					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {
						if (refreshView.isHeaderShown()) {
							offset = 0;
							refreshData(true);
						}
					}
				});

		fragment_quanbu_list
				.setOnPreRefreshingAnimListener(new OnPreRefreshingAnimListener() {
					@Override
					public void onPreRefreshingAnim() {
						fragment_quanbu_list
								.setFooterLoadingViewHeaderText("加载更多信息");
					}
				});
		fragment_quanbu_list
				.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
					@Override
					public void onLastItemVisible() {
						if (isPage) {
							refreshData(false);
						} else {
							fragment_quanbu_list.onRefreshComplete();
						}

					}
				});

	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		showLoadingDlg("正在加载数据中...", true);
		getLocation1();
	}
	
	/**
	 * 开启定位
	 * 
	 */
	private void getLocation1() {
		this.mLocationBase = new LocationBase(getActivity(), true,
				new LocationBase.LocationListener() {

					public void onGetGaodeLocation(
							LocationContent paramLocationContent,
							boolean paramBoolean) {
						// TODO Auto-generated method stub
						latitude = paramLocationContent.getLatitude();
						longitude = paramLocationContent.getLongitude();
						dingwei_name = paramLocationContent.getCity();
						dingwei_name = dingwei_name.replace("市", "");
						SharedpreferncesUtil.saveDingweiName(context, paramLocationContent.getAddress());
						
						Geohash geohash = new Geohash();
						geocode = geohash.encode(latitude, longitude);
						
						
						
						SharedpreferncesUtil.saveGeoceode(context, geocode);
						
						SharedpreferncesUtil.saveLAT(context, latitude+"");
						SharedpreferncesUtil.saveLONG(context, longitude+"");
						
						
						
						LocalBroadcastManager.getInstance(
								context).sendBroadcast(
								new Intent(WimaiMainFragment.REFRESH));

						
						http_get_elm();

					}
				});
	}

	public void http_get_elm() {

		String url = "http://restapi.ele.me/v4/restaurants?type=geohash&extras[]=food_activity&extras[]=restaurant_activity&extras[]=identification&geohash="
				+ geocode + "&offset=" + offset + "&limit=20";
		AsyncHttpUtil.get(url, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
				hideTextLoadingDlg();
				List<ShangJiaMainBean> shangJiaMainBeans = GsonUtil
						.getShangJiaMainBeans(arg2);
				
				fragment_quanbu_list.onRefreshComplete();
				if (offset == 0) {
					mAdapter.notifyDataSetChanged(shangJiaMainBeans);
				} else {
					mAdapter.notifyDataSetChangedAppend(shangJiaMainBeans);
				}

				if (shangJiaMainBeans == null || shangJiaMainBeans.size() < 20) {
					isPage = false;
				}
				offset += 20;
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				System.out.println("**********error**********" + arg2);
				hideTextLoadingDlg();
			}

		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.elm_layout_1:
			Intent i = new Intent(context,ElmFenleiActivity.class);
			i.putExtra("geocode", geocode);
			i.putExtra("titleName", "品牌馆");
			i.putExtra("type", "000");
			context.startActivity(i);
			
			break;
		case R.id.elm_layout_2:
			showToast("更多商户接入中，敬请期待！");
			break;
		case R.id.elm_layout_3:
			Intent i3 = new Intent(context,ElmFenleiActivity.class);
			i3.putExtra("geocode", geocode);
			i3.putExtra("type", "0001");
			i3.putExtra("titleName", "新品特惠");
			context.startActivity(i3);
			break;
		case R.id.elm_layout_4:
			Intent i4 = new Intent(context,ElmFenleiActivity.class);
			i4.putExtra("geocode", geocode);
			i4.putExtra("type", "239");
			i4.putExtra("titleName", "下午茶");
			context.startActivity(i4);
			break;
		case R.id.elm_layout_5:
			Intent i5 = new Intent(context,ElmFenleiActivity.class);
			i5.putExtra("geocode", geocode);
			i5.putExtra("type", "244");
			i5.putExtra("titleName", "水果");
			context.startActivity(i5);
			break;
		case R.id.elm_layout_6:
			Intent i6 = new Intent(context,ElmFenleiActivity.class);
			i6.putExtra("geocode", geocode);
			i6.putExtra("type", "248");
			i6.putExtra("titleName", "鲜花蛋糕");
			context.startActivity(i6);
			break;
		case R.id.elm_layout_7:
			Intent i7 = new Intent(context,ElmFenleiActivity.class);
			i7.putExtra("geocode", geocode);
			i7.putExtra("type", "252");
			i7.putExtra("titleName", "便利店");
			context.startActivity(i7);
			break;
		}
	}

	private void refreshData(boolean clean) {
		dataTask = new RefreshDataTask(dataTask.getID() + 1, clean);
		dataTask.run();
	}

	class RefreshDataTask extends DataTask {

		boolean clean = false; // 是否清空原数据

		public RefreshDataTask(int id, boolean flag) {
			super(id);
			this.clean = flag;
		}

		@Override
		public void run() {
			// 判断是不是用户的最后操作,最后任务的ID如果比此任务的ID大则丢弃请求结果
			if (getID() < dataTask.getID()) {
				return;
			} else {
				http_get_elm();
			}

		}

	}

	/**
	 * 左右切换fragment的时候执行的生命周期
	 * 
	 * @param hidden
	 *            当为false的时候，说明当前的界面在前台战士
	 */
	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		if (!hidden) {

		}
	}

}
