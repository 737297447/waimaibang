package com.lingdian.waimaibang.activity;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.model.DestinationV2;
import com.lingdian.waimaibang.tools.CircleImageView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;

public class JingdianAboutActivity extends BaseActivity implements OnClickListener{

	public ImageView title_bar_left;
	public TextView title_bar_text;
	public CircleImageView user_ima;
	public DestinationV2 destinationV2;
	
	public TextView fenlei_tag1,fenlei_tag2,fenlei_contact1,fenlei_contact2,jianjie;
    
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jingdian_about);
		findViewById();
		setListener();
		init();
	}
	
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		destinationV2 = (DestinationV2)getIntent().getSerializableExtra("destinationV2");
		
		title_bar_left = (ImageView) findViewById(R.id.title_bar_left);
		title_bar_text = (TextView) findViewById(R.id.title_bar_text);
		user_ima = (CircleImageView) findViewById(R.id.user_ima);
		title_bar_text.setVisibility(View.GONE);
		user_ima.setVisibility(View.GONE);
		
		fenlei_tag1 = (TextView) findViewById(R.id.fenlei_tag1);
		fenlei_tag2 = (TextView) findViewById(R.id.fenlei_tag2);
		fenlei_contact1 = (TextView) findViewById(R.id.fenlei_contact1);
		fenlei_contact2 = (TextView) findViewById(R.id.fenlei_contact2);
		jianjie = (TextView) findViewById(R.id.jianjie);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		title_bar_left.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		if(destinationV2 != null){
			if(destinationV2.getDestination_detail() != null){
				if(!TextUtils.isEmpty(destinationV2.getDestination_detail().getDescription())){
					jianjie.setText(destinationV2.getDestination_detail().getDescription());
				}else{
					jianjie.setText("");
				}
			}else{
				jianjie.setText("");
			}
		
			
			
			if(destinationV2.getTag_types() != null){
				if(destinationV2.getTag_types().size() ==1){
					fenlei_tag1.setText(destinationV2.getTag_types().get(0).getName());
					fenlei_contact1.setText(destinationV2.getTag_types().get(0).getTags());
					fenlei_tag2.setText("");
					fenlei_contact2.setText("");
				}else if(destinationV2.getTag_types().size() >1){
					fenlei_tag1.setText(destinationV2.getTag_types().get(0).getName());
					fenlei_contact1.setText(destinationV2.getTag_types().get(0).getTags());
					fenlei_tag2.setText(destinationV2.getTag_types().get(1).getName());
					fenlei_contact2.setText(destinationV2.getTag_types().get(1).getTags());
				}
			}else{
				fenlei_tag1.setText("");
				fenlei_contact1.setText("");
				fenlei_tag2.setText("");
				fenlei_contact2.setText("");
			}
			
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_bar_left:
			finish();
			break;

		default:
			break;
		}
		
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
