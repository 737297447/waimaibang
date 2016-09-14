package com.lingdian.waimaibang.activity;

import java.util.List;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.model.common.Photos;
import com.lingdian.waimaibang.utils.OptionsUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class GalleryPicActivity extends Activity implements OnGestureListener {
	private GestureDetector detector;

	private ViewFlipper flipper;
	private  List<Photos> photos;
	private RelativeLayout title_menu;
	private TextView num;
	private TextView queding;
	private boolean isClick = false;
	private int position = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_pic_gallery);
		detector = new GestureDetector(this);
		init();
		for (int i = 0; i < photos.size(); i++) {
			flipper.addView(getImageView(photos.get(i).getFile_url()+"!photo.scale.big"));
		}
		
	}

	private void init() {
		photos = (List<Photos>) getIntent().getSerializableExtra("mImage");
		
		flipper = (ViewFlipper) findViewById(R.id.flipper);
		title_menu = (RelativeLayout) findViewById(R.id.title_menu);
		num = (TextView) findViewById(R.id.num);
		queding = (TextView) findViewById(R.id.queding);
		num.setText("1/"+photos.size());
		
		if(isClick){
			title_menu.setVisibility(View.GONE);
		}else{
			title_menu.setVisibility(View.VISIBLE);
		}
//		flipper.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				if(isClick){
//					title_menu.setVisibility(View.GONE);
//					isClick = false;
//				}else{
//					title_menu.setVisibility(View.VISIBLE);
//					isClick = true;
//				}
//			}
//		});
		
		queding.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private View getImageView(String url) {
		ImageView view = new ImageView(this);
		ImageLoader.getInstance().displayImage(
				url,
				view, OptionsUtil.PicMudidiNormal());
//		view.setImageResource(id);
		return view;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if (e1.getX() - e2.getX() > 120) {
			// 向右滑动
			flipper.setInAnimation(AnimationUtils.loadAnimation(this,
					R.drawable.push_left_in));
			flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.drawable.push_left_out));
			flipper.showNext();// 显示下一个
			position++;
			if(position > photos.size()){
				position = 1;
			}
			num.setText(position+"/"+photos.size());
			return true;
		} else if (e2.getX() - e1.getX() > 120) {
			// 向左滑动
			flipper.setInAnimation(AnimationUtils.loadAnimation(this,
					R.drawable.push_right_in));
			flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.drawable.push_right_out));
			flipper.showPrevious(); // 显示上一个
			position--;
			if(position < 1){
				position = photos.size();
			}
			num.setText(position+"/"+photos.size());
			return true;
		}

		return false;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onTouchEvent(MotionEvent event) {
		return detector.onTouchEvent(event);
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
