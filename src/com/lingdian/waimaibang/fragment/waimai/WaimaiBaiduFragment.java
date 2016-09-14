package com.lingdian.waimaibang.fragment.waimai;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lingdian.waimaibang.R;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.lingdian.waimaibang.activity.MainActivity;
import com.lingdian.waimaibang.activity.wo.NoticeDetailActivity;
import com.lingdian.waimaibang.adapter.XiaoxiAdapter;
import com.lingdian.waimaibang.fragment.BaseFragment;
import com.lingdian.waimaibang.fragment.waimai.adapter.BaiduAdapter;
import com.lingdian.waimaibang.model.GsonUtil;
import com.lingdian.waimaibang.model.Register;
import com.lingdian.waimaibang.model.XiaoxiModel;
import com.lingdian.waimaibang.utils.AsyncHttpUtil;
import com.lingdian.waimaibang.utils.BaseUrlAndKey;
import com.lingdian.waimaibang.utils.SharedpreferncesUtil;
import com.lingdian.waimaibang.view.ChooseRemindDialog;
import com.lingdian.waimaibang.view.swipelistview.BaseSwipeListViewListener;
import com.lingdian.waimaibang.view.swipelistview.SwipeListView;
import com.lingdian.waimaibang.waimaimodel.BaiduContent;
import com.lingdian.waimaibang.waimaimodel.BaiduPosition;

public class WaimaiBaiduFragment extends BaseFragment implements OnClickListener {

	private Context context;
	private ListView baidu_position_list;
	private List<BaiduContent> xiaoxiModels;
	private BaiduAdapter baiduAdapter;
    private String dingweiName;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_wo_xiaoxi, container,
				false);
		context = getActivity();
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		init();
		findViewById();
		setListener();
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		baidu_position_list = (ListView) this.getView().findViewById(
				R.id.baidu_position_list);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
	
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		dingweiName = SharedpreferncesUtil.getDingweiName(context);
		if(!TextUtils.isEmpty(dingweiName)){
			showLoadingDlg("正在获取附近位置...", true);
			http_get_baidu_position();
		}
		
	}
	private void http_get_baidu_position() {

		String url = "http://waimai.baidu.com/waimai?qt=poisearch&ie=utf-8&sug=0&tn=B_NORMAL_MAP&oue=1&res=1&display=json&wd="+dingweiName+"&c=320&lat=&lng=&callback=jsonp2";
		
		AsyncHttpUtil.get(url, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
				hideTextLoadingDlg();
				arg2 = arg2.substring(arg2.indexOf("[") + 1, arg2.indexOf("]"));
				arg2 = "["+arg2+"]";
				
				
				xiaoxiModels = GsonUtil.getBaiduPosition(arg2);
				if(xiaoxiModels != null){
					baiduAdapter = new BaiduAdapter(context, xiaoxiModels);
					baidu_position_list.setAdapter(baiduAdapter);
				}
				
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				hideTextLoadingDlg();
			}
		
		});

	}

	
	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		}
	}


	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		if (!hidden) {
		
		}
	}

}
