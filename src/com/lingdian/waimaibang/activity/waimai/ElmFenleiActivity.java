package com.lingdian.waimaibang.activity.waimai;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

import com.handmark.pulltorefresh.library.PullToRefreshAdapterViewBase.OnPreRefreshingAnimListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.BaseActivity;
import com.lingdian.waimaibang.api.DataTask;
import com.lingdian.waimaibang.fragment.waimai.adapter.ElmAdapter;
import com.lingdian.waimaibang.model.GsonUtil;
import com.lingdian.waimaibang.utils.AsyncHttpUtil;
import com.lingdian.waimaibang.waimaimodel.ShangJiaMainBean;
import com.loopj.android.http.TextHttpResponseHandler;

public class ElmFenleiActivity extends BaseActivity implements OnClickListener {

	public RelativeLayout title_bar_left_layout;
	public TextView title_bar_text;

	private PullToRefreshListView fragment_fenlei_list;
	private List<ShangJiaMainBean> lists = new ArrayList<ShangJiaMainBean>();
	private ElmAdapter mAdapter;
	private boolean isPage = true; // 是否还有下一页
	private int offset = 0;
	private String geocode;
	private String type;
	private String titleName;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_waimaibang_elm_fenlei);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		title_bar_left_layout = (RelativeLayout) findViewById(R.id.title_bar_left_layout);
		title_bar_text = (TextView) findViewById(R.id.title_bar_text);
		fragment_fenlei_list = (PullToRefreshListView) findViewById(R.id.fragment_fenlei_list);
		mAdapter = new ElmAdapter(ElmFenleiActivity.this, lists);
		fragment_fenlei_list.setAdapter(mAdapter);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		title_bar_left_layout.setOnClickListener(this);
		fragment_fenlei_list
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

		fragment_fenlei_list
				.setOnPreRefreshingAnimListener(new OnPreRefreshingAnimListener() {
					@Override
					public void onPreRefreshingAnim() {
						fragment_fenlei_list
								.setFooterLoadingViewHeaderText("加载更多信息");
					}
				});
		fragment_fenlei_list
				.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
					@Override
					public void onLastItemVisible() {
						if (isPage) {
							refreshData(false);
						} else {
							fragment_fenlei_list.onRefreshComplete();
						}

					}
				});
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		geocode = getIntent().getStringExtra("geocode");
		type = getIntent().getStringExtra("type");
		titleName = getIntent().getStringExtra("titleName");
		title_bar_text.setText(titleName);
		showLoadingDlg("正在加载数据中...", false);
		http_get_elm();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_bar_left_layout:
			finish();
			break;
		}

	}
	
	
	
	
	public void http_get_elm() {
		String url ="";
		
		if(type.equals("000")){
			 url = "http://restapi.ele.me/v4/restaurants?type=geohash&extras[]=food_activity&extras[]=restaurant_activity&extras[]=identification&geohash="
						+ geocode + "&offset=" + offset + "&limit=20&is_premium=1";
		}else if(type.equals("244")){
			 url = "http://restapi.ele.me/v4/restaurants?type=geohash&extras[]=food_activity&extras[]=restaurant_activity&extras[]=identification&geohash="
						+ geocode + "&offset=" + offset + "&limit=20&restaurant_category_id="+type;
		}else if(type.equals("0001")){
			 url = "http://restapi.ele.me/v4/restaurants?type=geohash&extras[]=food_activity&extras[]=restaurant_activity&extras[]=identification&geohash="
						+ geocode + "&offset=" + offset + "&limit=20&activity_types[]=3&support_ids[]=5";
		}else if(type.equals("239")){
			 url = "http://restapi.ele.me/v4/restaurants?type=geohash&extras[]=food_activity&extras[]=restaurant_activity&extras[]=identification&geohash="
						+ geocode + "&offset=" + offset + "&limit=20&restaurant_category_id="+type;
		}else if(type.equals("248")){
			 url = "http://restapi.ele.me/v4/restaurants?type=geohash&extras[]=food_activity&extras[]=restaurant_activity&extras[]=identification&geohash="
						+ geocode + "&offset=" + offset + "&limit=20&restaurant_category_id="+type;
		}else if(type.equals("252")){
			 url = "http://restapi.ele.me/v4/restaurants?type=geohash&extras[]=food_activity&extras[]=restaurant_activity&extras[]=identification&geohash="
						+ geocode + "&offset=" + offset + "&limit=20&restaurant_category_id="+type;
		}
		
		AsyncHttpUtil.get(url, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
				hideTextLoadingDlg();
				List<ShangJiaMainBean> shangJiaMainBeans = GsonUtil
						.getShangJiaMainBeans(arg2);
				fragment_fenlei_list.onRefreshComplete();
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

}
