package com.lingdian.waimaibang.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.lingdian.waimaibang.api.DataTask;
import com.lingdian.waimaibang.api.PwdErrorListener;
import com.lingdian.waimaibang.api.RetryNetwork;
import com.lingdian.waimaibang.utils.BitmapUtils;
import com.lingdian.waimaibang.utils.NetWorkCenter;
import com.lingdian.waimaibang.utils.ThreadPoolManager;
import com.lingdian.waimaibang.utils.UserController;

public abstract class SuperActivity extends Activity{
	
	protected boolean isAvtive = false;
	
	protected Context mContext;
	
	protected boolean isPwdError = false;
	
	protected InputMethodManager imm;
	
	
	protected UserController mUserController;
	
	ThreadPoolManager mThreadPoolManager;
	
	ProgressDialog progress;
	
	protected BitmapUtils mBitmapUtils;
	
	/**
	 * 用于判断APP是否是正常启动,还是奔溃后自动重启的
	 */
	protected static boolean isNormalStart = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mContext = this;
		isAvtive = true;
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		mUserController = UserController.getInstance(mContext.getApplicationContext());
		mBitmapUtils = new BitmapUtils(mContext);
	}
	
	
	/**
	 * UI线程执行一个任务
	 * 
	 * @param run
	 */
	protected void runOnUi(Runnable run) {
		runOnUiThread(run);
	}
	
	protected DataTask dataTask = new DataTask(0) {
		
	};
	
	/**
	 * 显示进度条
	 */
	public void showProgressDialog(){
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				if (progress == null) {
					progress = new ProgressDialog(mContext);
					progress.setMessage("正在加载,请稍后...");
					progress.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
					progress.setCancelable(true);
				}
				progress.show();
			}
		});
	}
	
	
	/**
	 * 显示进度条
	 */
	public void showProgressDialog(final String text){
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				if ( progress == null) {
					progress = new ProgressDialog(mContext);
					progress.setMessage(text);
					progress.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
					progress.setCancelable(true);
				}
				progress.show();
			}
		});
	}
	
	/**
	 * 隐藏进度条
	 */
	public void dismissProgressDialog(){
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				if( progress!=null ){
					progress.dismiss();
				}
			}
		});
	}
	
	/**
	 * 得到一个进度条
	 * @param msg
	 * @return
	 */
	public ProgressDialog getProgressDialog(String msg) {
		progress = new ProgressDialog(this);
//		progressDialog.setIndeterminate(true);
		progress.setMessage(msg);
		progress.setCancelable(true);
		return progress;
	}
	
	/**
	 * 弹出吐司
	 * @param msg
	 */
	protected void showToast(final String msg) {
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		isAvtive = false;
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		isAvtive = true;
		
		if( !isNormalStart ){
			//非正常启动
//			Intent i = new Intent(this, FlashActivity.class);
//        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        	startActivity(i);
		}
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		isAvtive = false;
		
	}
	
	public boolean getAvtive(){
		return isAvtive;
	}
	
	
	protected void hintNoNetWork(){
	}
	
	protected float getDimen(int id){
		float dimension = getResources().getDimension(id);
		return dimension;
	}
	
	protected void executeTask(Runnable run){
		mThreadPoolManager.executeTask(run);
	}

	/**
	 * 返回对象本身
	 * 
	 * @return
	 */
	public Context This() {
		return this;
	}
	
	
	/**
	 * 隐藏输入法
	 * 
	 * @param context
	 * @param achor
	 */
	public static void hideSoftInput(Context context, View achor) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(achor.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}
	

}
