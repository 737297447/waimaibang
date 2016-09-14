package com.lingdian.waimaibang.activity.waimai;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.GeolocationPermissions;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ZoomButtonsController;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.BaseActivity;
import com.lingdian.waimaibang.utils.SharedpreferncesUtil;
import com.lingdian.waimaibang.view.ProgressWebView;
import com.umeng.analytics.MobclickAgent;

public class BaiduActivity extends BaseActivity implements OnClickListener {

	private ProgressWebView webView;
	private WebSettings webSettings;

	public String webUrl = "";
	public String name,latitude,longitude;
	
	
	public RelativeLayout title_bar_left_layout;
	public TextView title_bar_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waimai_fragment_meituan);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub

		title_bar_left_layout = (RelativeLayout) findViewById(R.id.title_bar_left_layout);
		title_bar_left_layout.setOnClickListener(this);
		title_bar_text = (TextView) findViewById(R.id.title_bar_text);
		title_bar_text.setText("百度外卖");
		
		webView = (ProgressWebView) findViewById(R.id.meituan_webview);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		// 设置webView点击监听
		webView.setWebViewClient(new webViewClient());
		// 隐藏滚动条
		webView.setHorizontalScrollBarEnabled(false);
		webView.setVerticalScrollBarEnabled(false);

		webSettings = webView.getSettings();

		String userAgentString = webSettings.getUserAgentString();
		webSettings.setUserAgentString(userAgentString + " XiaoBan");
		// 自适应屏幕
		webSettings.setJavaScriptEnabled(true);
		webSettings.setBuiltInZoomControls(true);// 隐藏缩放按钮
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setBuiltInZoomControls(true);

	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		// 隐藏放大缩小按钮
		setZoomControlGone(webView);
		name = getIntent().getStringExtra("name");
		latitude = getIntent().getStringExtra("latitude");
		longitude = getIntent().getStringExtra("longitude");
		
		webView.loadUrl("http://waimai.baidu.com/mobile/waimai?qt=shoplist&address="+name+"&lat="+latitude+"&lng="+longitude+"&third_party=0");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_bar_left_layout:
			finish();
			break;
		}
	}

	@Override
	// 设置回退
	// 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			if (webView.canGoBack()) {
				webView.goBack(); // goBack()表示返回WebView的上一页面
			} else {
				finish();
			}

			return true;
		}
		return false;
	}

	class webViewClient extends WebViewClient {
		// 重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开。
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			// 如果不需要其他对点击链接事件的处理返回true，否则返回false
			return false;
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
		}
	}

	// Android 3.0(11) 以下使用以下方法:
	// 利用java的反射机制
	public void setZoomControlGone(View view) {
		Class classType;
		Field field;
		try {
			classType = WebView.class;
			field = classType.getDeclaredField("mZoomButtonsController");
			field.setAccessible(true);
			ZoomButtonsController mZoomButtonsController = new ZoomButtonsController(
					view);
			mZoomButtonsController.getZoomControls().setVisibility(View.GONE);
			try {
				field.set(view, mZoomButtonsController);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 当横竖屏切换时会调用该方法
	 * 
	 * @author
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		boolean islandport;
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			islandport = false;
		} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			islandport = true;
		}
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPause(this);
		try {
			webView.getClass().getMethod("onPause")
					.invoke(webView, (Object[]) null);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onResume(this);
		try {
			webView.getClass().getMethod("onResume")
					.invoke(webView, (Object[]) null);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
