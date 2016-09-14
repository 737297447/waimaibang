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
public class ChooseRemindDialog extends Dialog implements OnClickListener {

	private LayoutInflater factory;
	private TextView dialog_title;
	private TextView dialog_remind;
	private Button dialog_btn_1;
	private Button dialog_btn_2;
	private String titleStr = "";
	private String remindStr = "";
	private String btn1Str = "";
	private String btn2Str = "";
	private ScrollView scrollView1;
	private int remindGravity = Gravity.CENTER;

	public ChooseRemindDialog(Context context, String title, String remind, String btn1Name,
			String btn2Name) {
		super(context);
		factory = LayoutInflater.from(context);
		this.titleStr = title;
		this.remindStr = remind;
		this.btn1Str = btn1Name;
		this.btn2Str = btn2Name;
		this.remindGravity = Gravity.CENTER;
	}

	public ChooseRemindDialog(Context context, int theme, String title, String remind,
			String btn1Name, String btn2Name) {
		super(context, theme);
		factory = LayoutInflater.from(context);
		this.titleStr = title;
		this.remindStr = remind;
		this.btn1Str = btn1Name;
		this.btn2Str = btn2Name;
		this.remindGravity = Gravity.CENTER;
	}

	public ChooseRemindDialog(Context context, int theme, String title, String remind,
			String btn1Name, String btn2Name, int gravity) {
		super(context, theme);
		factory = LayoutInflater.from(context);
		this.titleStr = title;
		this.remindStr = remind;
		this.btn1Str = btn1Name;
		this.btn2Str = btn2Name;
		this.remindGravity = gravity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(factory.inflate(R.layout.dialog_choose_remind, null));
		dialog_title = (TextView) this.findViewById(R.id.dialog_title);
		dialog_remind = (TextView) this.findViewById(R.id.dialog_remind);
		dialog_btn_1 = (Button) this.findViewById(R.id.dialog_btn_1);
		dialog_btn_2 = (Button) this.findViewById(R.id.dialog_btn_2);
		scrollView1 = (ScrollView) this.findViewById(R.id.scrollView1);
		dialog_btn_1.setOnClickListener(this);
		dialog_btn_2.setOnClickListener(this);

		dialog_title.setText(titleStr);
		dialog_remind.setGravity(remindGravity);
		dialog_remind.setText(Html.fromHtml(remindStr));
		dialog_btn_1.setText(btn1Str);
		dialog_btn_2.setText(btn2Str);
		dialog_remind.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (dialog_remind.getLineCount() >= 8) {
					scrollView1.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
							GlobalFuction.dip2px(getContext(), 160)));
				}
			}
		});

		if (TextUtils.isEmpty(btn2Str)) {
			dialog_btn_2.setVisibility(View.GONE);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_btn_1:
			doBtn1Click();
			break;
		case R.id.dialog_btn_2:
			doBtn2Click();
			break;
		}
	}

	public void doBtn1Click() {
	}

	public void doBtn2Click() {
	}
}
