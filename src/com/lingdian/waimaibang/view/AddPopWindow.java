package com.lingdian.waimaibang.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.lingdian.waimaibang.R;

public class AddPopWindow extends PopupWindow {
	private View conentView;
	RelativeLayout re_layout1, re_layout2, re_layout3, re_layout4, re_layout5;
	public AddPopWindow(final Activity context) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.popupwindow_add, null);

		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);

		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);

		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.AnimationPreview);

		re_layout1 = (RelativeLayout) conentView.findViewById(R.id.re_layout1);
		re_layout2 = (RelativeLayout) conentView.findViewById(R.id.re_layout2);
		re_layout3=(RelativeLayout)conentView.findViewById(R.id.re_layout3);	
		re_layout4=(RelativeLayout)conentView.findViewById(R.id.re_layout4);
		re_layout5=(RelativeLayout)conentView.findViewById(R.id.re_layout5);

		re_layout1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//			 context.startActivity(new Intent(context,BenGuanGaiKuangActivity.class));
//			 context.overridePendingTransition(R.anim.activity_move_in_start, R.anim.activity_move_out_start);
//			 //Toast.makeText(context, "本馆概况", 1).show();
//				AddPopWindow.this.dismiss();
			}

		});
		re_layout2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}

		});
		re_layout3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				 context.startActivity(new Intent(context,GuanCangBuJuActivity.class));
//				 context.overridePendingTransition(R.anim.activity_move_in_start, R.anim.activity_move_out_start);
//				 //Toast.makeText(context, "馆藏布局", 1).show();
//				AddPopWindow.this.dismiss();

			}

		});
		re_layout4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				 context.startActivity(new Intent(context,GuiZhangZhiDuActivity.class));
//				 context.overridePendingTransition(R.anim.activity_move_in_start, R.anim.activity_move_out_start);
//				 //Toast.makeText(context, "规章制度", 1).show();
//				AddPopWindow.this.dismiss();

			}

		});
		re_layout5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

	}

	/**
	 * 显示popupWindow
	 * 
	 * @param parent
	 */
	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			// 以下拉方式显示popupwindow
			//mPop.showAsDropDown(anchor, 0, 0);//设置显示PopupWindow的位置位于View的左下方，x,y表示坐标偏移量
			this.showAsDropDown(parent, 0, 30);
			
		} else {
			this.dismiss();
		}
	}
}
