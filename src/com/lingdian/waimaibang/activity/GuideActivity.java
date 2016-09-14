package com.lingdian.waimaibang.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.MainActivity;
import com.lingdian.waimaibang.adapter.ViewPagerAdapter;
import com.lingdian.waimaibang.utils.LocationBase;
import com.lingdian.waimaibang.utils.LocationBase.LocationContent;
import com.lingdian.waimaibang.utils.SharedpreferncesUtil;
import com.umeng.analytics.MobclickAgent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GuideActivity extends BaseActivity implements OnClickListener,
		OnPageChangeListener {
	private ViewPager viewPager;
	private ViewPagerAdapter viewPagerAdapter;
	private List<View> views;
	private List<RelativeLayout> relativeLayouts;
	private List<ImageView> imageViews;
	private List<Button> buttons;
	private ImageView imageView;
	
	private ImageView imageView1,imageView2,imageView3,imageView4;
	

	private Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();

	
	public List<Integer> positionList1 = new ArrayList<Integer>();
	public List<Integer> positionList2 = new ArrayList<Integer>();
	public String destion = "1";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		initViews();
		setListener();
	}

	protected void setListener() {
		relativeLayouts = new ArrayList<RelativeLayout>();
		relativeLayouts.add((RelativeLayout) views.get(0).findViewById(
				R.id.pic_crowd_01));
		relativeLayouts.add((RelativeLayout) views.get(0).findViewById(
				R.id.pic_crowd_02));
		relativeLayouts.add((RelativeLayout) views.get(0).findViewById(
				R.id.pic_crowd_03));
		relativeLayouts.add((RelativeLayout) views.get(0).findViewById(
				R.id.pic_crowd_04));
		relativeLayouts.add((RelativeLayout) views.get(1).findViewById(
				R.id.pic_feature_01));
		relativeLayouts.add((RelativeLayout) views.get(1).findViewById(
				R.id.pic_feature_02));
		relativeLayouts.add((RelativeLayout) views.get(1).findViewById(
				R.id.pic_feature_03));
		relativeLayouts.add((RelativeLayout) views.get(1).findViewById(
				R.id.pic_feature_04));
		relativeLayouts.add((RelativeLayout) views.get(1).findViewById(
				R.id.pic_feature_05));
		relativeLayouts.add((RelativeLayout) views.get(1).findViewById(
				R.id.pic_feature_06));
		relativeLayouts.add((RelativeLayout) views.get(1).findViewById(
				R.id.pic_feature_07));
		relativeLayouts.add((RelativeLayout) views.get(1).findViewById(
				R.id.pic_feature_08));
		relativeLayouts.add((RelativeLayout) views.get(1).findViewById(
				R.id.pic_feature_09));
		relativeLayouts.add((RelativeLayout) views.get(2).findViewById(
				R.id.pic_distance_01));
		relativeLayouts.add((RelativeLayout) views.get(2).findViewById(
				R.id.pic_distance_02));
		relativeLayouts.add((RelativeLayout) views.get(2).findViewById(
				R.id.pic_distance_03));
		relativeLayouts.add((RelativeLayout) views.get(2).findViewById(
				R.id.pic_distance_04));
		imageViews = new ArrayList<ImageView>();
		imageViews
				.add((ImageView) views.get(0).findViewById(R.id.sel_crowd_01));
		imageViews
				.add((ImageView) views.get(0).findViewById(R.id.sel_crowd_02));
		imageViews
				.add((ImageView) views.get(0).findViewById(R.id.sel_crowd_03));
		imageViews
				.add((ImageView) views.get(0).findViewById(R.id.sel_crowd_04));
		imageViews.add((ImageView) views.get(1).findViewById(
				R.id.sel_feature_01));
		imageViews.add((ImageView) views.get(1).findViewById(
				R.id.sel_feature_02));
		imageViews.add((ImageView) views.get(1).findViewById(
				R.id.sel_feature_03));
		imageViews.add((ImageView) views.get(1).findViewById(
				R.id.sel_feature_04));
		imageViews.add((ImageView) views.get(1).findViewById(
				R.id.sel_feature_05));
		imageViews.add((ImageView) views.get(1).findViewById(
				R.id.sel_feature_06));
		imageViews.add((ImageView) views.get(1).findViewById(
				R.id.sel_feature_07));
		imageViews.add((ImageView) views.get(1).findViewById(
				R.id.sel_feature_08));
		imageViews.add((ImageView) views.get(1).findViewById(
				R.id.sel_feature_09));
		imageViews.add((ImageView) views.get(2).findViewById(
				R.id.sel_distance_01));
		imageViews.add((ImageView) views.get(2).findViewById(
				R.id.sel_distance_02));
		imageViews.add((ImageView) views.get(2).findViewById(
				R.id.sel_distance_03));
		imageViews.add((ImageView) views.get(2).findViewById(
				R.id.sel_distance_04));
		
		
		imageView1 = imageViews.get(imageViews.indexOf(views.get(2)
				.findViewById(R.id.sel_distance_01)));
		imageView2 = imageViews.get(imageViews.indexOf(views.get(2)
				.findViewById(R.id.sel_distance_02)));
		imageView3 = imageViews.get(imageViews.indexOf(views.get(2)
				.findViewById(R.id.sel_distance_03)));
		imageView4 = imageViews.get(imageViews.indexOf(views.get(2)
				.findViewById(R.id.sel_distance_04)));
		
		
		buttons = new ArrayList<Button>();
		buttons.add((Button) views.get(0).findViewById(R.id.btn_crowd));
		buttons.add((Button) views.get(1).findViewById(R.id.btn_feature));
		buttons.add((Button) views.get(2).findViewById(R.id.btn_distance));

		for (RelativeLayout relativeLayout : relativeLayouts) {
			relativeLayout.setOnClickListener(this);
		}

		for (ImageView imageView : imageViews) {
			imageView.setOnClickListener(this);
		}
		for (Button button : buttons) {
			button.setOnClickListener(this);
		}
	}

	private void initViews() {
		LayoutInflater inflater = LayoutInflater.from(this);
		views = new ArrayList<View>();
		views.add(inflater.inflate(R.layout.guide_crowd, null));
		views.add(inflater.inflate(R.layout.guide_feature, null));
		views.add(inflater.inflate(R.layout.guide_distance, null));
		viewPagerAdapter = new ViewPagerAdapter(views, this);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setAdapter(viewPagerAdapter);
		viewPager.setOnPageChangeListener(this);

		for (int i = 0; i < 4; i++) {
			map.put(i, false);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		Log.d("onPageSelected", String.valueOf(arg0));
		AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(1000);
		LayoutAnimationController lac = new LayoutAnimationController(
				animation, 0.5f);
		((LinearLayout) views.get(arg0).findViewById(R.id.layout_anim))
				.setLayoutAnimation(lac);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.pic_crowd_01:
			imageView = imageViews.get(imageViews.indexOf(views.get(0)
					.findViewById(R.id.sel_crowd_01)));
			if (imageView.getVisibility() == View.INVISIBLE) {
				imageView.setVisibility(View.VISIBLE);
				positionList1.add(3);
			} else {
				imageView.setVisibility(View.INVISIBLE);
				for(int i=0;i<positionList1.size();i++){
					if(positionList1.get(i) == 3){
						positionList1.remove(i);
					}
				}
			}

			break;
		case R.id.pic_crowd_02:
			imageView = imageViews.get(imageViews.indexOf(views.get(0)
					.findViewById(R.id.sel_crowd_02)));
			if (imageView.getVisibility() == View.INVISIBLE) {
				imageView.setVisibility(View.VISIBLE);
				positionList1.add(1);
			} else {
				imageView.setVisibility(View.INVISIBLE);
				for(int i=0;i<positionList1.size();i++){
					if(positionList1.get(i) == 1){
						positionList1.remove(i);
					}
				}
			}

			break;
		case R.id.pic_crowd_03:
			imageView = imageViews.get(imageViews.indexOf(views.get(0)
					.findViewById(R.id.sel_crowd_03)));
			if (imageView.getVisibility() == View.INVISIBLE) {
				imageView.setVisibility(View.VISIBLE);
				positionList1.add(5);
			} else {
				imageView.setVisibility(View.INVISIBLE);
				for(int i=0;i<positionList1.size();i++){
					if(positionList1.get(i) == 5){
						positionList1.remove(i);
					}
				}
			}

			break;
		case R.id.pic_crowd_04:
			imageView = imageViews.get(imageViews.indexOf(views.get(0)
					.findViewById(R.id.sel_crowd_04)));
			if (imageView.getVisibility() == View.INVISIBLE) {
				imageView.setVisibility(View.VISIBLE);
				positionList1.add(0);
			} else {
				imageView.setVisibility(View.INVISIBLE);
				for(int i=0;i<positionList1.size();i++){
					if(positionList1.get(i) == 0){
						positionList1.remove(i);
					}
				}
			}
			break;
		case R.id.pic_feature_01:
			imageView = imageViews.get(imageViews.indexOf(views.get(1)
					.findViewById(R.id.sel_feature_01)));
			if (imageView.getVisibility() == View.INVISIBLE) {
				
				positionList1.add(10);
				imageView.setVisibility(View.VISIBLE);
			} else {
				imageView.setVisibility(View.INVISIBLE);
				for(int i=0;i<positionList1.size();i++){
					if(positionList1.get(i) == 10){
						positionList1.remove(i);
					}
				}
			}

			break;
		case R.id.pic_feature_02:
			imageView = imageViews.get(imageViews.indexOf(views.get(1)
					.findViewById(R.id.sel_feature_02)));
			if (imageView.getVisibility() == View.INVISIBLE) {
				imageView.setVisibility(View.VISIBLE);
				positionList1.add(12);
			} else {
				imageView.setVisibility(View.INVISIBLE);
				for(int i=0;i<positionList1.size();i++){
					if(positionList1.get(i) == 12){
						positionList1.remove(i);
					}
				}
			}

			break;
		case R.id.pic_feature_03:
			imageView = imageViews.get(imageViews.indexOf(views.get(1)
					.findViewById(R.id.sel_feature_03)));
			if (imageView.getVisibility() == View.INVISIBLE) {
				imageView.setVisibility(View.VISIBLE);
				positionList1.add(14);
			} else {
				imageView.setVisibility(View.INVISIBLE);
				for(int i=0;i<positionList1.size();i++){
					if(positionList1.get(i) == 14){
						positionList1.remove(i);
					}
				}
			}

			break;
		case R.id.pic_feature_04:
			imageView = imageViews.get(imageViews.indexOf(views.get(1)
					.findViewById(R.id.sel_feature_04)));
			if (imageView.getVisibility() == View.INVISIBLE) {
				imageView.setVisibility(View.VISIBLE);
				positionList1.add(13);
			} else {
				imageView.setVisibility(View.INVISIBLE);
				for(int i=0;i<positionList1.size();i++){
					if(positionList1.get(i) == 13){
						positionList1.remove(i);
					}
				}
			}

			break;
		case R.id.pic_feature_05:
			imageView = imageViews.get(imageViews.indexOf(views.get(1)
					.findViewById(R.id.sel_feature_05)));
			if (imageView.getVisibility() == View.INVISIBLE) {
				imageView.setVisibility(View.VISIBLE);
				positionList1.add(9);
			} else {
				imageView.setVisibility(View.INVISIBLE);
				for(int i=0;i<positionList1.size();i++){
					if(positionList1.get(i) == 9){
						positionList1.remove(i);
					}
				}
			}

			break;
		case R.id.pic_feature_06:
			imageView = imageViews.get(imageViews.indexOf(views.get(1)
					.findViewById(R.id.sel_feature_06)));
			if (imageView.getVisibility() == View.INVISIBLE) {
				positionList1.add(11);
				imageView.setVisibility(View.VISIBLE);
			} else {
				imageView.setVisibility(View.INVISIBLE);
				for(int i=0;i<positionList1.size();i++){
					if(positionList1.get(i) == 11){
						positionList1.remove(i);
					}
				}
			}

			break;
		case R.id.pic_feature_07:
			imageView = imageViews.get(imageViews.indexOf(views.get(1)
					.findViewById(R.id.sel_feature_07)));
			if (imageView.getVisibility() == View.INVISIBLE) {
				imageView.setVisibility(View.VISIBLE);
				positionList2.add(1);
			} else {
				imageView.setVisibility(View.INVISIBLE);
				for(int i=0;i<positionList2.size();i++){
					if(positionList2.get(i) == 1){
						positionList2.remove(i);
					}
				}
			}

			break;
		case R.id.pic_feature_08:
			imageView = imageViews.get(imageViews.indexOf(views.get(1)
					.findViewById(R.id.sel_feature_08)));
			if (imageView.getVisibility() == View.INVISIBLE) {
				imageView.setVisibility(View.VISIBLE);
				positionList1.add(7);
			} else {
				imageView.setVisibility(View.INVISIBLE);
				for(int i=0;i<positionList1.size();i++){
					if(positionList1.get(i) == 7){
						positionList1.remove(i);
					}
				}
			}

			break;
		case R.id.pic_feature_09:
			imageView = imageViews.get(imageViews.indexOf(views.get(1)
					.findViewById(R.id.sel_feature_09)));
			if (imageView.getVisibility() == View.INVISIBLE) {
				imageView.setVisibility(View.VISIBLE);
				positionList1.add(16);
			} else {
				imageView.setVisibility(View.INVISIBLE);
				for(int i=0;i<positionList1.size();i++){
					if(positionList1.get(i) == 16){
						positionList1.remove(i);
					}
				}
				
			}

			break;
		case R.id.pic_distance_01:

//			if (!map.get(0)) {
				destion = "5";
//				checkClick(0);
//				imageView1 = imageViews.get(imageViews.indexOf(views.get(2)
//						.findViewById(R.id.sel_distance_01)));
				if (imageView1.getVisibility() == View.INVISIBLE) {
					imageView1.setVisibility(View.VISIBLE);
					imageView2.setVisibility(View.INVISIBLE);
					imageView3.setVisibility(View.INVISIBLE);
					imageView4.setVisibility(View.INVISIBLE);
					
				} else {
					imageView1.setVisibility(View.INVISIBLE);
				}
//			}

			break;
		case R.id.pic_distance_02:

//			if (!map.get(1)) {
				destion = "4";
//				checkClick(1);
//				imageView2 = imageViews.get(imageViews.indexOf(views.get(2)
//						.findViewById(R.id.sel_distance_02)));
				if (imageView2.getVisibility() == View.INVISIBLE) {
					imageView2.setVisibility(View.VISIBLE);
					imageView1.setVisibility(View.INVISIBLE);
					imageView3.setVisibility(View.INVISIBLE);
					imageView4.setVisibility(View.INVISIBLE);
					
				} else {
					imageView2.setVisibility(View.INVISIBLE);
				}
//			}
			break;
		case R.id.pic_distance_03:

//			if (!map.get(2)) {
				destion = "3";
//				checkClick(2);
//				imageView3 = imageViews.get(imageViews.indexOf(views.get(2)
//						.findViewById(R.id.sel_distance_03)));
				if (imageView3.getVisibility() == View.INVISIBLE) {
					imageView3.setVisibility(View.VISIBLE);
					imageView1.setVisibility(View.INVISIBLE);
					imageView2.setVisibility(View.INVISIBLE);
					imageView4.setVisibility(View.INVISIBLE);
					
				} else {
					imageView3.setVisibility(View.INVISIBLE);
				}
//			}

			break;
		case R.id.pic_distance_04:

//			if (!map.get(3)) {
				destion = "2";
//				checkClick(3);
//				imageView4 = imageViews.get(imageViews.indexOf(views.get(2)
//						.findViewById(R.id.sel_distance_04)));
				if (imageView4.getVisibility() == View.INVISIBLE) {
					imageView4.setVisibility(View.VISIBLE);
					imageView1.setVisibility(View.INVISIBLE);
					imageView2.setVisibility(View.INVISIBLE);
					imageView3.setVisibility(View.INVISIBLE);
				} else {
					imageView4.setVisibility(View.INVISIBLE);
				}
//			}
			break;
		case R.id.btn_crowd:
			viewPager.setCurrentItem(1);
			break;

		case R.id.btn_feature:
			viewPager.setCurrentItem(2);
			break;

		case R.id.btn_distance:
			Intent intent = new Intent(GuideActivity.this, MainActivity.class);
			
			if(positionList1 != null){
				intent.putExtra("positionList1", (Serializable)positionList1);
			}else{
				positionList1 = new ArrayList<Integer>();
				intent.putExtra("positionList1", (Serializable)positionList1);
			}
			if(positionList2 != null){
				intent.putExtra("positionList2", (Serializable)positionList2);
			}else{
				positionList2 = new ArrayList<Integer>();
				intent.putExtra("positionList2", (Serializable)positionList2);
			}
			intent.putExtra("destion", destion);
			
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
	}

	public void checkClick(int position) {
		for (int i = 0; i < map.size(); i++) {
			if(position == i){
				map.put(position, true);
			}else{
				map.put(position, false);
			}
		}
	}


	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

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
