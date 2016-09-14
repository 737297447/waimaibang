package com.lingdian.waimaibang.fragment.waimai;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.ActivitySelectCity;
import com.lingdian.waimaibang.activity.waimai.XcfWebActivity1;
import com.lingdian.waimaibang.fragment.BaseFragment;
import com.lingdian.waimaibang.fragment.jingxuan.JingxuanShangpinFragment;

public class TeseFragment extends BaseFragment implements OnClickListener {

	private TextView jingxuan_wanfa_text, jingxuan_baokuan_text;
	private ImageView jingxuan_wanfa_img, jingxuan_baokuan_img,title_bar_right;
	private PinYeshiFragment jWanfaFragment;
	private JingxuanShangpinFragment jShangpinFragment;
	private FragmentTransaction ft;
	private Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_jingxuan_fragment,
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
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		title_bar_left = (ImageView) this.getView().findViewById(
				R.id.title_bar_left);
		title_bar_right = (ImageView) this.getView().findViewById(
				R.id.title_bar_right);
		title_bar_text = (TextView) this.getView().findViewById(
				R.id.title_bar_text);
		title_bar_text.setText("精选");
		jingxuan_wanfa_text = (TextView) this.getView().findViewById(
				R.id.jingxuan_wanfa_text);
		jingxuan_baokuan_text = (TextView) this.getView().findViewById(
				R.id.jingxuan_baokuan_text);
		jingxuan_wanfa_img = (ImageView) this.getView().findViewById(
				R.id.jingxuan_wanfa_img);
		jingxuan_baokuan_img = (ImageView) this.getView().findViewById(
				R.id.jingxuan_baokuan_img);
		title_bar_right= (ImageView) this.getView().findViewById(
				R.id.title_bar_right);
		
		
		setTabSelection(0);
		
		
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		jingxuan_wanfa_text.setOnClickListener(this);
		jingxuan_baokuan_text.setOnClickListener(this);
		title_bar_left.setOnClickListener(this);
		title_bar_right.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

	// 设置选择的fragment
	private void setTabSelection(int index) {
		// 开启一个Fragment事务
		ft = getActivity().getSupportFragmentManager().beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(ft);
		if (index == 0) {
			if (jWanfaFragment == null) {
				jWanfaFragment = new PinYeshiFragment();
				ft.add(R.id.jingxuan_layout, jWanfaFragment);
			} else {
				ft.show(jWanfaFragment);
			}
			
		} else if (index == 1) {
			if (jShangpinFragment == null) {
				jShangpinFragment = new JingxuanShangpinFragment();
				ft.add(R.id.jingxuan_layout, jShangpinFragment);
			} else {
				ft.show(jShangpinFragment);
			}
		}
		ft.commit();
	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (jWanfaFragment != null) {
			transaction.hide(jWanfaFragment);
		}
		if (jShangpinFragment != null) {
			transaction.hide(jShangpinFragment);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.jingxuan_wanfa_text:
			jingxuan_wanfa_text.setTextColor(Color.BLACK);
			jingxuan_baokuan_text.setTextColor(Color.GRAY);
			jingxuan_wanfa_img.setImageResource(R.drawable.jingxuan_tabline);
			jingxuan_baokuan_img.setImageResource(R.color.transparent);
			setTabSelection(0);
			break;
		case R.id.jingxuan_baokuan_text:
			jingxuan_wanfa_text.setTextColor(Color.GRAY);
			jingxuan_baokuan_text.setTextColor(Color.BLACK);
			jingxuan_wanfa_img.setImageResource(R.color.transparent);
			jingxuan_baokuan_img.setImageResource(R.drawable.jingxuan_tabline);
			setTabSelection(1);
			break;
		case R.id.title_bar_right:
			Intent i = new Intent(context, XcfWebActivity1.class);
			i.putExtra("name", "吃的诗篇");
			i.putExtra("webUrl", "http://x.eqxiu.com/s/swnA7pBM");
			startActivity(i);
			break;
		}

	}



	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		if (!hidden) {
			// 当回到当前页面判断换从中的城市是否跟之前进来的一致不，不一致则按新的城市名字请求数据
		}
	}
	

}
