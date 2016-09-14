package com.lingdian.waimaibang.activity.waimai;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.BaseActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BigImageActivity extends BaseActivity{

	public ImageView big_img;
	public TextView good_name,good_detail;
	public String imgPath;
	public String name;
	public String detail;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_big_pic);
		findViewById();
		setListener();
		init();
	}
	
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		big_img = (ImageView) findViewById(R.id.big_img);
		good_name = (TextView) findViewById(R.id.good_name);
		good_detail = (TextView) findViewById(R.id.good_detail);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		big_img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		imgPath = getIntent().getStringExtra("imgPath");
		name = getIntent().getStringExtra("name");
		detail = getIntent().getStringExtra("detail");
		if(!TextUtils.isEmpty(imgPath)){
			ImageLoader.getInstance().displayImage(imgPath, big_img);
		}
		
		good_name.setText(name+"");
		good_detail.setText(detail+"");
	}

}
