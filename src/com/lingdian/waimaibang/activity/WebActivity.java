package com.lingdian.waimaibang.activity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.apache.http.Header;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ZoomButtonsController;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.R.id;
import com.lingdian.waimaibang.R.layout;
import com.lingdian.waimaibang.R.string;
import com.lingdian.waimaibang.model.GsonUtil;
import com.lingdian.waimaibang.model.JXDetailModel;
import com.lingdian.waimaibang.utils.AsyncHttpUtil;
import com.lingdian.waimaibang.utils.BaseUrlAndKey;
import com.lingdian.waimaibang.view.ProgressWebView;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.mob.tools.utils.UIHandler;
import com.umeng.analytics.MobclickAgent;

/**
 * 网页浏览
 * 
 * @author nzl
 */
public class WebActivity extends BaseActivity implements OnClickListener {

	private ProgressWebView webView;
	private String webUrl;
	private WebSettings webSettings;

	private RelativeLayout title_bar_left_layout;
	private TextView title_bar_text;
	
	public String titleText;

	public static final String REFRESH = "com.lingdian.waimaibang.activity.jingxuan.JingxuanDetailWebActivity.refresh";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_waimiabang_webview);
		findViewById();
		setListener();
		init();
	}

	public void findViewById() {
		
		titleText = getIntent().getStringExtra("titleText");
		webUrl = getIntent().getStringExtra("webUrl");
		
		title_bar_left_layout = (RelativeLayout) findViewById(R.id.title_bar_left_layout);
		title_bar_text = (TextView) findViewById(R.id.title_bar_text);

		title_bar_text.setText(titleText);
		
		webView = (ProgressWebView) findViewById(R.id.webview);
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

	public void setListener() {
		title_bar_left_layout.setOnClickListener(this);

	}

	public void init() {
		// 隐藏放大缩小按钮
		setZoomControlGone(webView);
		webView.loadUrl(webUrl);

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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_left_layout:
			finish();
			break;
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

	@Override
	public void onBackPressed() {
		finish();
	}

	@Override
	// 设置回退
	// 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			if(webView.canGoBack()){
				webView.goBack(); // goBack()表示返回WebView的上一页面
			}else{
				finish();
			}
		
			return true;
		}
		return false;
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
