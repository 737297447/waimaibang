package com.lingdian.waimaibang.activity;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.update.BmobUpdateAgent;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.MainActivity;
import com.lingdian.waimaibang.utils.SharedpreferncesUtil;
import com.umeng.analytics.MobclickAgent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class WelcomeActivity extends BaseActivity {
	private static final int WAIT_TIME = 3000;
	private static final int GO_HOME = 1000;
	private static final int GO_GUIDE = 1001;
	private boolean isFirstIn = false;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case GO_HOME:
				goHomeActivity();
				break;
			case GO_GUIDE:
				goGuideActivity();
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		Bmob.initialize(this, "6aea4789112554b3053e1e0700a9c558");
		// 使用推送服务时的初始化操作
		BmobInstallation.getCurrentInstallation(this).save();
		// 启动推送服务
		BmobPush.startWork(this, "6aea4789112554b3053e1e0700a9c558");
//		BmobUpdateAgent.initAppVersion(this);
	
		init();
	}

	protected void init() {

		SharedPreferences sharedPreferences = getSharedPreferences(
				"meizhouliu", MODE_PRIVATE);
		isFirstIn = sharedPreferences.getBoolean("isFirstIn", true);
		SharedpreferncesUtil.saveGeoceode(WelcomeActivity.this, null);
		SharedpreferncesUtil.saveLAT(WelcomeActivity.this, null);
		SharedpreferncesUtil.saveLONG(WelcomeActivity.this, null);
		SharedpreferncesUtil.saveDingweiName(WelcomeActivity.this, null);
		// if (!isFirstIn) {
		mHandler.sendEmptyMessageDelayed(GO_HOME, WAIT_TIME);
		// } else {
		// mHandler.sendEmptyMessageDelayed(GO_GUIDE, WAIT_TIME);
		// Editor editor = sharedPreferences.edit();
		// editor.putBoolean("isFirstIn", false);
		// editor.commit();
		//
		// String times = Long.toString(System.currentTimeMillis());
		// String time1 = times.substring(0, times.length() - 3) + "."
		// + times.substring(times.length() - 3, times.length()) + "000";
		// SharedpreferncesUtil.saveFirstTime(WelcomeActivity.this, time1);
		// }
	}

	private void goHomeActivity() {
		Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	private void goGuideActivity() {
		Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setListener() {
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
