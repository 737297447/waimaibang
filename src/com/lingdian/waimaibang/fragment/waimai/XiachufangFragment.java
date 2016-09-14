package com.lingdian.waimaibang.fragment.waimai;

import java.util.List;

import org.apache.http.Header;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.waimai.XcfSearchWebActivity;
import com.lingdian.waimaibang.activity.waimai.XcfWebActivity;
import com.lingdian.waimaibang.activity.waimai.XcfWebActivity1;
import com.lingdian.waimaibang.fragment.BaseFragment;
import com.lingdian.waimaibang.fragment.waimai.adapter.XiaChufangAdapter;
import com.lingdian.waimaibang.model.GsonUtil;
import com.lingdian.waimaibang.utils.AsyncHttpUtil;
import com.lingdian.waimaibang.view.ClearEditText;
import com.lingdian.waimaibang.waimaimodel.XItems;
import com.lingdian.waimaibang.waimaimodel.XiachufangModel;
import com.loopj.android.http.TextHttpResponseHandler;

public class XiachufangFragment extends BaseFragment implements OnClickListener {

	public Context context;
	public XiaChufangAdapter xAdapter;
	public ListView fragment_xiachufang_list;
    public ClearEditText filter_edit;
    public TextView btn_search_cancel;
	
    public RelativeLayout top_layout_1,top_layout_2,top_layout_3;
    public ScrollView scrollview;
    
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_xiachufang_main,
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

		filter_edit = (ClearEditText) this.getView().findViewById(R.id.filter_edit);
		btn_search_cancel = (TextView) this.getView().findViewById(R.id.btn_search_cancel);
		scrollview = (ScrollView) this.getView().findViewById(R.id.scrollview);
		top_layout_1 = (RelativeLayout) this.getView().findViewById(R.id.top_layout_1);
		top_layout_2 = (RelativeLayout) this.getView().findViewById(R.id.top_layout_2);
		top_layout_3 = (RelativeLayout) this.getView().findViewById(R.id.top_layout_3);
		fragment_xiachufang_list = (ListView) this.getView()
				.findViewById(R.id.fragment_xiachufang_list);

		
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		btn_search_cancel.setOnClickListener(this);
		top_layout_1.setOnClickListener(this);
		top_layout_2.setOnClickListener(this);
		top_layout_3.setOnClickListener(this);
		scrollview.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				
					hideSoftInput(context, filter_edit);
				return false;
			}
		});
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		showLoadingDlg("正在加载中...", true);
		http_get_top();
		http_get_xiachufang();
	}
	
	
	

	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		if (!hidden) {

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btn_search_cancel:
			
			hideSoftInput(context, filter_edit);
			if(TextUtils.isEmpty(filter_edit.getText().toString())){
				showToast("请输入您要搜索的菜谱、食材");
				return;
			}
			
			Intent i = new Intent(context,XcfSearchWebActivity.class);
			i.putExtra("name", filter_edit.getText().toString());
			startActivity(i);
			
			break;
		case R.id.top_layout_1:
			Intent i1 = new Intent(context, XcfWebActivity.class);
			i1.putExtra("name", "本周推荐");
			i1.putExtra("webUrl", "http://www.xiachufang.com/explore/");
			startActivity(i1);
			
			break;
		case R.id.top_layout_2:
			Intent i2 = new Intent(context, XcfWebActivity.class);
			i2.putExtra("name", "新秀菜谱");
			i2.putExtra("webUrl", "http://www.xiachufang.com/explore/rising/");
			startActivity(i2);
			break;
		case R.id.top_layout_3:
			Intent i3 = new Intent(context, XcfWebActivity1.class);
			i3.putExtra("name", "菜谱分类");
			i3.putExtra("webUrl", "https://www.xiachufang.com/page/app-category/");
			startActivity(i3);
			break;
		}
	}

	
	
	public void http_get_top() {

		String url = "http://api.huofar.com/scene/home";

		AsyncHttpUtil.get(url, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
				hideTextLoadingDlg();
				System.out.println("********************" + arg2);
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
	
	
	
	public void http_get_xiachufang() {

		String url = "http://api.xiachufang.com/v2/issues/list.json?origin=android&api_key=07e72bef932537c71f9cafbe4c94df1c&size=1&api_sign=e3b769b83dcf5e5281bf117d0febbb34&timezone=Asia%2FShanghai&version=133&";

		AsyncHttpUtil.get(url, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
				hideTextLoadingDlg();
				XiachufangModel xModel = GsonUtil.getXiachufangModel(arg2);
				if (xModel.getContent().getIssues() != null) {
					List<XItems> xItems = xModel.getContent().getIssues()
							.get(0).getItems();
					xAdapter = new XiaChufangAdapter(context, xItems,xModel.getContent()
							.getIssues().get(0).getTitle());
					fragment_xiachufang_list.setAdapter(xAdapter);

				}

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

}
