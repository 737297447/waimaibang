package com.lingdian.waimaibang.activity.jingxuan;




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
import android.widget.TextView;
import android.widget.ZoomButtonsController;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.BaseActivity;
import com.lingdian.waimaibang.model.GsonUtil;
import com.lingdian.waimaibang.model.JXDetailModel;
import com.lingdian.waimaibang.utils.AsyncHttpUtil;
import com.lingdian.waimaibang.utils.BaseUrlAndKey;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.mob.tools.utils.UIHandler;
import com.umeng.analytics.MobclickAgent;

/**
 * 网页浏览
 * 
 * @author shenxy
 */
public class JingxuanDetailWebActivity extends BaseActivity implements OnClickListener, PlatformActionListener, Callback {

	private WebView webView;
	private String webUrl;
	private WebSettings webSettings;

	private ImageView title_bar_left;
	public int id;
	public JXDetailModel jxDetailModel;
	public LinearLayout jingxuan_header_layout;
	
	public ImageView title_bar_share;
	public LinearLayout title_bar_pinglun;
	public TextView title_bar_pinglun_text;
	
	
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
		id = getIntent().getIntExtra("id", -1);
		title_bar_left = (ImageView) findViewById(R.id.title_bar_left);

		jingxuan_header_layout = (LinearLayout) findViewById(R.id.jingxuan_header_layout);
		
		
		title_bar_share = (ImageView) findViewById(R.id.title_bar_share);
		title_bar_pinglun = (LinearLayout) findViewById(R.id.title_bar_pinglun);
		title_bar_pinglun_text = (TextView) findViewById(R.id.title_bar_pinglun_text);
		
		
		
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
		http_get_jingwan_detail();
		initBroadCast();
	}

	public void setListener() {
		title_bar_left.setOnClickListener(this);
		title_bar_share.setOnClickListener(this);
		title_bar_pinglun.setOnClickListener(this);
		
	}

	public void init() {
		Intent fromIntent = getIntent();
		
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
			hideTextLoadingDlg();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_left:
			finish();
			break;
		case R.id.title_bar_share:
			if(jxDetailModel != null){
				showShare(jxDetailModel.getTitle());
			}
			break;
		}
	}

	
	/** 精选玩法详情 */
	public void http_get_jingwan_detail() {
		showLoadingDlg("正在加载中...", true);
		String url = BaseUrlAndKey.BAES_URL + "/v1/articles/" + id + ".json";
		final RequestParams params = new RequestParams();
		params.put("id", id);

		AsyncHttpUtil.get(url, params, new TextHttpResponseHandler() {

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
				System.out.println("********************"+arg2);
				jxDetailModel = GsonUtil.getJXDetailModelModel(arg2);
				if(jxDetailModel.getComments_count() == 0){
					title_bar_pinglun_text.setText("");
				}else{
					title_bar_pinglun_text.setText(jxDetailModel.getComments_count()+"");
				}
				
				webUrl = jxDetailModel.getUrl();
				// 隐藏放大缩小按钮
				setZoomControlGone(webView);
				webView.loadUrl(webUrl);
			}
			
		});

	}

	
	/** 刷新评论 */
	public void http_onference_pinglun() {
		String url = BaseUrlAndKey.BAES_URL + "/v1/articles/" + id + ".json";
		final RequestParams params = new RequestParams();
		params.put("id", id);

		AsyncHttpUtil.get(url, params, new TextHttpResponseHandler() {

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				// TODO Auto-generated method stub
				jxDetailModel = GsonUtil.getJXDetailModelModel(arg2);
				if(jxDetailModel.getComments_count() == 0){
					title_bar_pinglun_text.setText("");
				}else{
					title_bar_pinglun_text.setText(jxDetailModel.getComments_count()+"");
				}
			}
			
		});

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
	
	

	
	
	private final  static int SHARE_SUCCESS = 1;
	private final  static int SHARE_CANCEL = 2;
	private final  static int SHARE_ERROR = 3;
	
	private void showShare(String text) {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(getString(R.string.share));
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl(webUrl);
		// text是分享文本，所有平台都需要这个字段
		oks.setText(text);
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		oks.setImagePath("/sdcard/test.jpg");// 确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl(webUrl);
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
//		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl(webUrl);

		// 启动分享GUI
		oks.show(this);
	}


	@Override
	public void onCancel(Platform platf, int arg1) {
		Log.i("", "onCancel");
		if(arg1 == Platform.ACTION_SHARE){
			UIHandler.sendEmptyMessage(SHARE_CANCEL, this);
		}
	}

	@Override
	public void onComplete(Platform platf, int arg1,
			HashMap<String, Object> arg2) {
		Log.i("", "onComplete");
		if(arg1 == Platform.ACTION_SHARE){
			UIHandler.sendEmptyMessage(SHARE_SUCCESS, this);
			Log.i("", "响应分享事件");
		}
		if(arg1 == Platform.SHARE_TEXT){
			Log.i("", "响应分享文本事件");
			UIHandler.sendEmptyMessage(SHARE_SUCCESS, this);
			Log.i("", "..");
		}
	}

	@Override
	public void onError(Platform platf, int arg1, Throwable arg2) {
		// TODO Auto-generated method stub
		Log.i("", "onError");
		if(arg1 == Platform.ACTION_SHARE){
			UIHandler.sendEmptyMessage(SHARE_ERROR, this);
		}
	}

	@Override
	public boolean handleMessage(Message msg) {
		Log.i("", "what = " + msg.what);
		switch(msg.what) {
		case SHARE_SUCCESS: {
			Log.i("", "arg1 = " + "分享成功");
//			Toast.makeText(this, "分享成功", Toast.LENGTH_SHORT).show();
		}
		break;
		case SHARE_CANCEL: {
			Log.i("", "arg1 = " + "取消分享");
//			Toast.makeText(this, "取消分享", Toast.LENGTH_SHORT).show();
		}
		break;
		case SHARE_ERROR: {
			Log.i("", "arg1 = " + "分享错误");
//			Toast.makeText(this, "分享错误", Toast.LENGTH_SHORT).show();
		}
		break;
		
	}

		return false;
	}
	
	
	private CheckInListReceiver checkInListReceiver;
	protected void initBroadCast() {
		checkInListReceiver = new CheckInListReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(REFRESH);
		LocalBroadcastManager.getInstance(this).registerReceiver(checkInListReceiver, intentFilter);
	}

	class CheckInListReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (isFinishing()) {
				return;
			}
			String action = intent.getAction();
			if (REFRESH.equals(action)) {
				http_onference_pinglun();
			}
		}

	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (checkInListReceiver != null) {
			LocalBroadcastManager.getInstance(this).unregisterReceiver(checkInListReceiver);
		}
	}

}
