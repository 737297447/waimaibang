package com.lingdian.waimaibang.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
/**
 * 自定义Loading Dialog样式
 * @author shenxy
 *
 */
public class LoadingTextDialog extends Dialog implements OnClickListener {

	private LayoutInflater factory;
	private TextView loading_remark;
	private TextView loading_text;
	private String remarkStr = "";
	private String jinduText;
	
	
	public LoadingTextDialog(Context context,int theme) {
		super(context,theme);
		factory = LayoutInflater.from(context);
	}
	
	public LoadingTextDialog(Context context,int theme,String remark) {
		super(context,theme);
		factory = LayoutInflater.from(context);
		this.remarkStr = remark;
	}

	public LoadingTextDialog(Context context, int theme,String remark,String jinduText) {
		super(context, theme);
		factory = LayoutInflater.from(context);
		this.remarkStr = remark;
		this.jinduText = jinduText;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setCanceledOnTouchOutside(false);
		this.setContentView(factory.inflate(R.layout.oa_loading_text, null));
		loading_remark = (TextView) this.findViewById(R.id.loading_remark);
		loading_text = (TextView) this.findViewById(R.id.loading_text);
		loading_remark.setText(remarkStr);
		loading_text.setText(jinduText);
		
	}

	public void setRemarkText(String text){
		loading_remark.setText(text);
	}
	public void setJinduText(String text){
		loading_text.setText(text);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_btn_1:
			break;
		case R.id.dialog_btn_2:
			break;
		}
	}
}
