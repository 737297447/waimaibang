package com.lingdian.waimaibang.fragment.waimai.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.WebActivity;
import com.lingdian.waimaibang.model.wanfa.WanfaModel;
import com.lingdian.waimaibang.utils.ElmBaseUrl;
import com.lingdian.waimaibang.utils.OptionsUtil;
import com.lingdian.waimaibang.utils.PhoneUtils;
import com.lingdian.waimaibang.view.ChooseRemindDialog;
import com.lingdian.waimaibang.waimaimodel.ShangJiaMainBean;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ElmAdapter extends BaseAdapter {

	public Context context;
	public List<ShangJiaMainBean> list = new ArrayList<ShangJiaMainBean>();
	

	public ElmAdapter(Context context,List<ShangJiaMainBean> list) {
		this.context = context;
		this.list = list;
	}

	
	public void notifyDataSetChanged(List<ShangJiaMainBean> str) {
		// TODO Auto-generated method stub
		if (str != null) {
			list.clear();
			list.addAll(str);
			notifyDataSetChanged();

		}
	}

	public void notifyDataSetChangedAppend(List<ShangJiaMainBean> str) {
		// TODO Auto-generated method stub
		if (str != null) {
			list.addAll(str);
			notifyDataSetChanged();
		}
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(
					R.layout.listitem_waimaibang_elm, null);
			holder.elm_shangjia_img = (ImageView) convertView
					.findViewById(R.id.elm_shangjia_img);
			holder.elm_shangjia_name = (TextView) convertView
					.findViewById(R.id.elm_shangjia_name);

			holder.elm_shangjia_prise = (TextView) convertView
					.findViewById(R.id.elm_shangjia_prise);
			holder.small_ratingbar = (RatingBar) convertView
					.findViewById(R.id.small_ratingbar);

			holder.elm_shangjia_saledetail = (TextView) convertView
					.findViewById(R.id.elm_shangjia_saledetail);
			holder.elm_peisong_prise = (TextView) convertView
					.findViewById(R.id.elm_peisong_prise);

			holder.elm_shangjia_mianfei = (TextView) convertView
					.findViewById(R.id.elm_shangjia_mianfei);

			holder.elm_shangjia_itimeandmi = (TextView) convertView
					.findViewById(R.id.elm_shangjia_itimeandmi);
			holder.peisong_layout = (LinearLayout) convertView
					.findViewById(R.id.peisong_layout);
			
			holder.elm_huodong1_text = (TextView) convertView
					.findViewById(R.id.elm_huodong1_text);
			holder.elm_huodong1_neirong = (TextView) convertView
					.findViewById(R.id.elm_huodong1_neirong);
			holder.elm_huodong2_text = (TextView) convertView
					.findViewById(R.id.elm_huodong2_text);
			holder.elm_huodong2_neirong = (TextView) convertView
					.findViewById(R.id.elm_huodong2_neirong);
			
			holder.elm_huodong1_back = (LinearLayout) convertView
					.findViewById(R.id.elm_huodong1_back);
			holder.elm_huodong2_back = (LinearLayout) convertView
					.findViewById(R.id.elm_huodong2_back);
			
			holder.elm_huodong1_layout = (LinearLayout) convertView
					.findViewById(R.id.elm_huodong1_layout);
			holder.elm_huodong2_layout = (LinearLayout) convertView
					.findViewById(R.id.elm_huodong2_layout);

			holder.bottom_layout = (LinearLayout) convertView
					.findViewById(R.id.bottom_layout);
			
			holder.center_line = (View) convertView
					.findViewById(R.id.center_line);
			holder.elm_item_all_layut = (LinearLayout) convertView
					.findViewById(R.id.elm_item_all_layut);
			holder.elm_shangjia_tel = (ImageView) convertView
					.findViewById(R.id.elm_shangjia_tel);
			
			
			
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final ShangJiaMainBean mainBean = list.get(position);
		if (!TextUtils.isEmpty(mainBean.getImage_path())) {
			ImageLoader.getInstance().displayImage(
					ElmBaseUrl.ELM_PIC_BASE_URL + mainBean.getImage_path(),
					holder.elm_shangjia_img,OptionsUtil.PicMudidiNormal());
		} else {
			holder.elm_shangjia_img
					.setImageResource(R.drawable.camerasdk_pic_loading);
		}
		holder.elm_shangjia_name.setText(mainBean.getName());
		holder.elm_shangjia_prise.setText("¥"+mainBean.getMinimum_order_amount()
				+ " ");

		float rating = Float.valueOf(mainBean.getRating());
		holder.small_ratingbar.setRating(rating);
		holder.elm_shangjia_saledetail.setText(mainBean.getFood_tips());
		if (mainBean.getDelivery_fee() != 0) {
			holder.elm_peisong_prise.setText("¥"+mainBean.getDelivery_fee() + " ");
			holder.elm_shangjia_mianfei.setText(" 配送费");
		} else {
			holder.elm_peisong_prise.setText("");
			holder.elm_shangjia_mianfei.setText("免费配送");
		}

		String time_mi = "";
        int juli = mainBean.getDistance();
        float mi = (float) (juli / 1000.0);
		
		if (mainBean.getOrder_lead_time() != 0) {
			time_mi = mainBean.getOrder_lead_time() + "分钟 / " + mi + "km";
		} else {
			time_mi = +mi + "km";
		}
		holder.elm_shangjia_itimeandmi.setText(time_mi);
		
		if(mainBean.getRestaurant_activity().size() == 0){
			holder.bottom_layout.setVisibility(View.GONE);
			holder.center_line.setVisibility(View.GONE);
		}else if(mainBean.getRestaurant_activity().size() == 1){
			holder.bottom_layout.setVisibility(View.VISIBLE);
			holder.center_line.setVisibility(View.VISIBLE);
			holder.elm_huodong1_layout.setVisibility(View.VISIBLE);
			holder.elm_huodong2_layout.setVisibility(View.GONE);
			
			holder.elm_huodong1_text.setText(mainBean.getRestaurant_activity().get(0).getIcon_name());
			holder.elm_huodong1_neirong.setText(mainBean.getRestaurant_activity().get(0).getDescription());
			
			
		}else if(mainBean.getRestaurant_activity().size() > 1){
			holder.bottom_layout.setVisibility(View.VISIBLE);
			holder.center_line.setVisibility(View.VISIBLE);
			holder.elm_huodong1_layout.setVisibility(View.VISIBLE);
			holder.elm_huodong2_layout.setVisibility(View.VISIBLE);
			
			holder.elm_huodong1_text.setText(mainBean.getRestaurant_activity().get(0).getIcon_name());
			holder.elm_huodong1_neirong.setText(mainBean.getRestaurant_activity().get(0).getDescription());
			
			holder.elm_huodong2_text.setText(mainBean.getRestaurant_activity().get(1).getIcon_name());
			holder.elm_huodong2_neirong.setText(mainBean.getRestaurant_activity().get(1).getDescription());
			
		}
		
		holder.elm_item_all_layut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(context,WebActivity.class);
				i.putExtra("titleText", mainBean.getName());
				i.putExtra("webUrl", ElmBaseUrl.ELM_DIANPU_BASE_URL+mainBean.getName_for_url());
				context.startActivity(i);
				
			}
		});
		
		holder.elm_shangjia_tel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				callDialog("是否拨打该商家的电话进行订餐？电话号码："+mainBean.getPhone(), mainBean.getPhone());
			}
		});

		return convertView;
	}

	private static class ViewHolder {

		public TextView elm_shangjia_name;
		public ImageView elm_shangjia_img;
		public TextView elm_shangjia_prise;

		public RatingBar small_ratingbar;
		public TextView elm_shangjia_saledetail;
		public TextView elm_peisong_prise;
		public TextView elm_shangjia_mianfei;

		public TextView elm_shangjia_itimeandmi;
		public LinearLayout peisong_layout;

		public TextView elm_huodong1_text, elm_huodong1_neirong;
		public TextView elm_huodong2_text, elm_huodong2_neirong;

		public LinearLayout elm_huodong1_layout, elm_huodong2_layout;
		public LinearLayout elm_huodong1_back, elm_huodong2_back;
		
		public ImageView elm_shangjia_tel;
		
		public LinearLayout bottom_layout;
		public LinearLayout elm_item_all_layut;
		public View center_line;

	}
	
	/** *打电话弹出的dialog */
	public void callDialog(String msg,final String mobilePh) {
		ChooseRemindDialog rmdDlg = new ChooseRemindDialog(context,
				R.style.meizhouliu_MyDialogStyleTop, "请确认", msg, "确定", "取消") {
			@Override
			public void doBtn1Click() {
				PhoneUtils.callPhone(mobilePh, context);
				this.dismiss();
			}

			@Override
			public void doBtn2Click() {
				this.dismiss();
			}
		};
		rmdDlg.show();
	}

}
