package com.lingdian.waimaibang.fragment.waimai;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.WebActivity;
import com.lingdian.waimaibang.activity.waimai.MeituanActivity;
import com.lingdian.waimaibang.fragment.BaseFragment;
import com.lingdian.waimaibang.model.GsonUtil;
import com.lingdian.waimaibang.tools.ad.AdBannerView;
import com.lingdian.waimaibang.utils.AsyncHttpUtil;
import com.lingdian.waimaibang.waimaimodel.Ads;
import com.lingdian.waimaibang.waimaimodel.Position;
import com.loopj.android.http.TextHttpResponseHandler;

public class WimaiMainFragment extends BaseFragment implements OnClickListener {

	public static final String REFRESH = "com.lingdian.waimaibang.fragment.waimai.JingxuanDetailWebActivity.refresh";

	private LinearLayout wo_quguo_layout;
	private LinearLayout wo_xiangqu_layout;
	private LinearLayout wo_xiaoxi_layout;
	private TextView wo_quguo_text;
	private TextView wo_xiangqu_text;
	private TextView wo_xiaoxi_text;

	private WimaiElmFragment elmFragment;
	private WaimaiBaiduFragment woXiaoxiFragment;
	private FragmentTransaction ft;
	private Context context;

	private AdBannerView adPages;
	private final static int AD_ONCLICK = 0X44;
	public List<Ads> adsList = new ArrayList<Ads>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_fa_xian_fragment,
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
		initBroadCast();
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		title_bar_text = (TextView) this.getView().findViewById(
				R.id.title_bar_text);

		title_bar_text.setText("正在定位中...");

		wo_quguo_layout = (LinearLayout) this.getView().findViewById(
				R.id.wo_quguo_layout);
		wo_xiangqu_layout = (LinearLayout) this.getView().findViewById(
				R.id.wo_xiangqu_layout);
		wo_xiaoxi_layout = (LinearLayout) this.getView().findViewById(
				R.id.wo_xiaoxi_layout);
		wo_quguo_text = (TextView) this.getView().findViewById(
				R.id.wo_quguo_text);
		wo_xiangqu_text = (TextView) this.getView().findViewById(
				R.id.wo_xiangqu_text);
		wo_xiaoxi_text = (TextView) this.getView().findViewById(
				R.id.wo_xiaoxi_text);

		adPages = (AdBannerView) this.getView().findViewById(
				R.id.waimaibang_huodong);
		adPages.getLayoutParams().height = 180;

		wo_quguo_layout.setVisibility(View.VISIBLE);

		wo_quguo_text.setTextColor(Color.parseColor("#ffd521"));
		wo_xiangqu_text.setTextColor(Color.BLACK);
		wo_xiaoxi_text.setTextColor(Color.BLACK);

		setTabSelection(0);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		wo_quguo_layout.setOnClickListener(this);
		wo_xiangqu_layout.setOnClickListener(this);
		wo_xiaoxi_layout.setOnClickListener(this);
	}
	

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

	/** 得到elm广告信息 */
	public void http_get_elm_ad() {

		String geocode = elmFragment.geocode;

		if (!TextUtils.isEmpty(geocode)) {
			String url = "http://restapi.ele.me/v1/app_banners?geohash="
					+ geocode;
			AsyncHttpUtil.get(url, new TextHttpResponseHandler() {

				@Override
				public void onSuccess(int arg0, Header[] arg1, String arg2) {
					// TODO Auto-generated method stub
					List<Ads> adsList1 = GsonUtil.getAdsList(arg2);
					if (adsList1 != null) {
						adsList.addAll(adsList1);
					}
					adPages.setClickFlag(AD_ONCLICK);
					adPages.init(mHandler, adsList);
				}

				@Override
				public void onFailure(int arg0, Header[] arg1, String arg2,
						Throwable arg3) {
					// TODO Auto-generated method stub

				}

			});
		}

	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case AD_ONCLICK:
				int position = Integer.valueOf(msg.obj.toString());
				// 广告位点击时间
				Intent i = new Intent(context, WebActivity.class);
				i.putExtra("titleText", "精彩活动");
				i.putExtra("webUrl", adsList.get(position).getLink());
				startActivity(i);
				break;

			}
		}
	};

	// 设置选择的fragment
	private void setTabSelection(int index) {
		// 开启一个Fragment事务
		ft = getActivity().getSupportFragmentManager().beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(ft);
		if (index == 0) {
			if (elmFragment == null) {
				elmFragment = new WimaiElmFragment();
				ft.add(R.id.wo_layout, elmFragment);
			} else {
				ft.show(elmFragment);
			}
		} else if (index == 1) {
			Intent i = new Intent(context,MeituanActivity.class);
			startActivityForResult(i, 99);
		} else if (index == 2) {
			if (woXiaoxiFragment == null) {
				woXiaoxiFragment = new WaimaiBaiduFragment();
				ft.add(R.id.wo_layout, woXiaoxiFragment);
			} else {
				ft.show(woXiaoxiFragment);
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
		if (elmFragment != null) {
			transaction.hide(elmFragment);
		}
		if (woXiaoxiFragment != null) {
			transaction.hide(woXiaoxiFragment);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.wo_quguo_layout:
			wo_quguo_text.setTextColor(Color.parseColor("#ffd521"));
			wo_xiangqu_text.setTextColor(Color.BLACK);
			wo_xiaoxi_text.setTextColor(Color.BLACK);

			setTabSelection(0);
			break;
		case R.id.wo_xiangqu_layout:
			wo_quguo_text.setTextColor(Color.BLACK);
			wo_xiangqu_text.setTextColor(Color.parseColor("#ffd521"));
			wo_xiaoxi_text.setTextColor(Color.BLACK);

			setTabSelection(1);
			break;
		case R.id.wo_xiaoxi_layout:
			wo_quguo_text.setTextColor(Color.BLACK);
			wo_xiangqu_text.setTextColor(Color.BLACK);
			wo_xiaoxi_text.setTextColor(Color.parseColor("#ffd521"));

			setTabSelection(2);
			break;
		}

	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		if (!hidden) {

		}
	}

	/** 得到elm地址信息 */
	public void http_get_elm_position() {

		String geocode = elmFragment.geocode;
		if (!TextUtils.isEmpty(geocode)) {
			String url = "http://restapi.ele.me/v1/pois/" + geocode;
			AsyncHttpUtil.get(url, new TextHttpResponseHandler() {

				@Override
				public void onSuccess(int arg0, Header[] arg1, String arg2) {
					// TODO Auto-generated method stub
					Position position = GsonUtil.getPosition(arg2);
					title_bar_text.setText(position.getName());
				}

				@Override
				public void onFailure(int arg0, Header[] arg1, String arg2,
						Throwable arg3) {
					// TODO Auto-generated method stub
					System.out.println("**********error**********" + arg2);

				}

			});
		}

	}

	private CheckInListReceiver checkInListReceiver;

	protected void initBroadCast() {
		checkInListReceiver = new CheckInListReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(REFRESH);
		LocalBroadcastManager.getInstance(context).registerReceiver(
				checkInListReceiver, intentFilter);
	}

	class CheckInListReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (getActivity() != null) {
				if (getActivity().isFinishing()) {
					return;
				}
			}

			String action = intent.getAction();
			if (REFRESH.equals(action)) {
				http_get_elm_position();
				http_get_elm_ad();
			}
		}

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (checkInListReceiver != null) {
			LocalBroadcastManager.getInstance(context).unregisterReceiver(
					checkInListReceiver);
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 99:
			wo_quguo_text.setTextColor(Color.parseColor("#ffd521"));
			wo_xiangqu_text.setTextColor(Color.BLACK);
			wo_xiaoxi_text.setTextColor(Color.BLACK);

			setTabSelection(0);
			break;

		default:
			break;
		}
	}
}
