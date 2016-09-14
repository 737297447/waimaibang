package com.lingdian.waimaibang.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.tools.GlobalFuction;

/**
 * 自定义dialog 包含标题、提示内容、两个按钮，开放按钮点击事件调用的方法
 * 
 * @author shenxy
 * 
 */
public class ChooseOneBtDialog extends Dialog implements OnClickListener {

	private LayoutInflater factory;
	private TextView dialog_remind;
	private Button dialog_btn_1;
	private String remindStr = "";
	private int remindGravity = Gravity.CENTER;

	public ChooseOneBtDialog(Context context,String remind) {
		super(context);
		factory = LayoutInflater.from(context);
		this.remindStr = remind;
		this.remindGravity = Gravity.CENTER;
	}

	public ChooseOneBtDialog(Context context, int theme, String remind) {
		super(context, theme);
		factory = LayoutInflater.from(context);
		this.remindStr = remind;
		this.remindGravity = Gravity.CENTER;
	}

	public ChooseOneBtDialog(Context context, int theme, String remind ,int gravity) {
		super(context, theme);
		factory = LayoutInflater.from(context);
		this.remindStr = remind;
		this.remindGravity = gravity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(factory.inflate(R.layout.dialog_one_bt_remind, null));
		dialog_remind = (TextView) this.findViewById(R.id.dialog_remind);
		dialog_btn_1 = (Button) this.findViewById(R.id.dialog_btn_1);
		dialog_btn_1.setOnClickListener(this);

		dialog_remind.setGravity(remindGravity);
		dialog_remind.setText(Html.fromHtml(remindStr));

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_btn_1:
			doBtn1Click();
			break;
		}
	}

	public void doBtn1Click() {
	}

	public void doBtn2Click() {
	}
}
