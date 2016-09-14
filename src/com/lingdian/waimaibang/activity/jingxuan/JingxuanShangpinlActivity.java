package com.lingdian.waimaibang.activity.jingxuan;



import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ZoomButtonsController;

import com.lingdian.waimaibang.R;
import com.umeng.analytics.MobclickAgent;

/**
 * 网页浏览
 * 
 * @author shenxy
 */
public class JingxuanShangpinlActivity extends Activity implements OnClickListener {

	private WebView webView;
	private String weburl;
	private WebSettings webSettings;

	private ImageView title_bar_left;
	private TextView title_bar_text;
	private TextView title_right_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notici_detail);
		findViewById();
		setListener();
		init();
	}

	public void findViewById() {

		title_bar_left = (ImageView) findViewById(R.id.title_bar_left);
		title_bar_text = (TextView) findViewById(R.id.title_bar_text);
		title_bar_text.setText("相关商品");

		title_right_text = (TextView) findViewById(R.id.title_right_text);
		title_right_text.setText("关闭");
		title_right_text.setOnClickListener(this);
		
		webView = (WebView) findViewById(R.id.webview);
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
		title_bar_left.setOnClickListener(this);
		title_right_text.setOnClickListener(this);
	}

	public void init() {
		Intent fromIntent = getIntent();
		weburl = fromIntent.getStringExtra("weburl");
		// 隐藏放大缩小按钮
		setZoomControlGone(webView);
		webView.loadUrl(weburl);
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
		case R.id.title_bar_left:
			finish();
			break;
		case R.id.title_right_text:
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
			finish();
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
	protected void onPause() {
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
	protected void onResume() {
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
