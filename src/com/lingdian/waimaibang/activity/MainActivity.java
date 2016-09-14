package com.lingdian.waimaibang.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import cn.bmob.v3.update.BmobUpdateAgent;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.fragment.waimai.TeseFragment;
import com.lingdian.waimaibang.fragment.waimai.WimaiMainFragment;
import com.lingdian.waimaibang.fragment.waimai.XiachufangFragment;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends SuperFragmentActivity implements
		View.OnClickListener, OnCheckedChangeListener {

	private long mExitTime;

	private RadioGroup rg;
	private RadioButton rb1, rb2, rb3;
	private FragmentTransaction ft;
	private WimaiMainFragment wanfaFragment;
	private XiachufangFragment mudidiFragment;
	private TeseFragment jingxuanFragment;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BmobUpdateAgent.update(MainActivity.this);		
		setUpMenu();
		setListener();
	}

	private void setUpMenu() {
		rg = (RadioGroup) findViewById(R.id.rg);
		rb1 = (RadioButton) findViewById(R.id.rb1);
		rb2 = (RadioButton) findViewById(R.id.rb2);
		rb3 = (RadioButton) findViewById(R.id.rb3);
		rg.setOnCheckedChangeListener(this);
		rb1.setChecked(true);
	}

	

	private void setListener() {

	}

	// 点击按钮显示左边侧滑栏
	public void onClickLiftMenu(View v) {
	}

	@Override
	public void onClick(View view) {
	}

	// 监听手机上的BACK键
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 判断菜单是否关闭
			// 判断两次点击的时间间隔（默认设置为2秒）
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
				super.onBackPressed();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId) {
		ft = getSupportFragmentManager().beginTransaction();
		hideFragments(ft);
		if (rb1.getId() == checkedId) {
			if (wanfaFragment == null) {
				wanfaFragment = new WimaiMainFragment();
				ft.add(R.id.main_fragment, wanfaFragment);
			} else {
				ft.show(wanfaFragment);
			}
		} else if (rb2.getId() == checkedId) {
			if (mudidiFragment == null) {
				mudidiFragment = new XiachufangFragment();
				ft.add(R.id.main_fragment, mudidiFragment);
			} else {
				ft.show(mudidiFragment);
			}
		} else if (rb3.getId() == checkedId) {
			if (jingxuanFragment == null) {
				jingxuanFragment = new TeseFragment();
				ft.add(R.id.main_fragment, jingxuanFragment);
			} else {
				ft.show(jingxuanFragment);
			}
		}
		ft.commitAllowingStateLoss();
	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (wanfaFragment != null) {
			transaction.hide(wanfaFragment);
		}
		if (mudidiFragment != null) {
			transaction.hide(mudidiFragment);
		}
		if (jingxuanFragment != null) {
			transaction.hide(jingxuanFragment);
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
