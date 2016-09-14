package com.lingdian.waimaibang.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.wo.RegistActivity;
import com.lingdian.waimaibang.utils.LocationBase;
import com.lingdian.waimaibang.view.ChooseOneBtDialog;
import com.lingdian.waimaibang.view.LoadingTextDialog;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class BaseActivity extends SuperActivity {
	protected ImageView title_bar_left;
	protected ImageView title_bar_right;
	protected TextView title_bar_text;
	protected TextView title_right_text;
	protected static LoadingTextDialog lTextDialog;
	protected LocationBase mLocationBase = null;
	protected String destination_name;
	protected String dingwei_name;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("BaseActivity", getClass().getSimpleName());
		ActivityCollector.addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}

	/** 绑定界面UI **/
	protected abstract void findViewById();

	/** 界面UI事件监听 **/
	protected abstract void setListener();

	/** 界面数据初始化 **/
	protected abstract void init();
	
	/**
	 * 可以设置是否点击的时候会取消
	 * 
	 * @param remark
	 * @param cancelable =false点击不可取消
	 */
	public void showLoadingDlg(String remark,boolean cancelable) {
		lTextDialog = new LoadingTextDialog(this, R.style.meizhouliu_MyDialogStyleTop,remark);
		lTextDialog.setCancelable(cancelable);
		lTextDialog.show();
	}

	public void setLoadingText(String text) {
		if (lTextDialog != null) {
			lTextDialog.setRemarkText(text);
		}
	}

	public void setLoadingJinduText(String text) {
		if (lTextDialog != null) {
			lTextDialog.setJinduText(text);
		}
	}

	/** 取消菊花转dialog*/
	public static void hideTextLoadingDlg() {
		if (lTextDialog != null) {
			lTextDialog.dismiss();
		}
	}
	
	
	/** 验证错误的dialog*/
	public void errorDialog(String msg) {
		ChooseOneBtDialog rmdDlg = new ChooseOneBtDialog(this,
				R.style.meizhouliu_MyDialogStyleTop, msg) {
			@Override
			public void doBtn1Click() {
				this.dismiss();
			}

			@Override
			public void doBtn2Click() {
				this.dismiss();
			}
		};
		rmdDlg.show();
	}
	
	/** 得到字符串的长度，字符一个，中文两个*/
	public int getWordCount(String s){
        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
    }
	
	/** 检查密码中是否有中文*/
	 public boolean checkfilename(String s){  
		 boolean isChina = false;
         s=new String(s.getBytes());//用GBK编码
         String pattern="[\u4e00-\u9fa5]+";  
         Pattern p=Pattern.compile(pattern);  
         Matcher result=p.matcher(s);                  
         while (result.find()) {
        	 isChina = true;
         }
		return isChina;
   }
}
