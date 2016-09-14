package com.lingdian.waimaibang.activity;

import java.util.List;
import java.util.Map;

import org.apache.http.Header;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.lingdian.waimaibang.adapter.AdminGridAdapter;
import com.lingdian.waimaibang.model.GsonUtil;
import com.lingdian.waimaibang.model.Register;
import com.lingdian.waimaibang.model.common.Photos;
import com.lingdian.waimaibang.utils.AsyncHttpUtil;

public class AdminImgActivity extends BaseActivity {

	private RelativeLayout camerasdk_title_rlyt_left;
	private TextView tab_sticker_library,camerasdk_actionbar_title;
	private AdminGridAdapter adapter;
	public String access_token;
	private Register register;
	private GridView admin_gridview;
	private List<Photos> pList;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_setimg);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		camerasdk_title_rlyt_left = (RelativeLayout) findViewById(R.id.camerasdk_title_rlyt_left);
		tab_sticker_library = (TextView) findViewById(R.id.camerasdk_title_txv_right_text);
		tab_sticker_library.setText("完成");
		admin_gridview = (GridView) findViewById(R.id.admin_gridview);
		camerasdk_actionbar_title = (TextView) findViewById(R.id.camerasdk_actionbar_title);
		camerasdk_actionbar_title.setText("封面设置");
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		camerasdk_title_rlyt_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		tab_sticker_library.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(adapter != null){
					int selectPosition = adapter.getMap();
					if(pList !=null){
						http_set(pList.get(selectPosition).getId()+"");
					}
				}
			}
		});
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		if (register != null) {
			if (register.getAccess_token() != null) {
				access_token = register.getAccess_token().getToken();
			} else {
				access_token = "21dee75ff8a82ad376e93e027c6ed1281579b845fe9ecab437ee702b8c2407ee";
			}
		} else {
			access_token = "21dee75ff8a82ad376e93e027c6ed1281579b845fe9ecab437ee702b8c2407ee";
		}
		http_2();
	}

	public void http_2() {
		String url = "http://api.meizhouliu.com/v1/authors/covers.json?";
		RequestParams params = new RequestParams();
		params.put("access_token",access_token);
		AsyncHttpUtil.get(url, params, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
				System.out.println("*****************************************"
						+ arg2);
				pList =GsonUtil.getAdminPic(arg2);
				adapter = new AdminGridAdapter(AdminImgActivity.this, pList);
				admin_gridview.setAdapter(adapter);
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub

			}
		});
	}

	
	public void http_set(String id) {
		String url = "http://api.meizhouliu.com/v1/authors/cover.json";
		RequestParams params = new RequestParams();
		params.put("access_token",access_token);
		params.put("author[cover_id]",id);
		
		AsyncHttpUtil.put(url, params, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
				finish();
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub

			}
		});
	}
	

}
