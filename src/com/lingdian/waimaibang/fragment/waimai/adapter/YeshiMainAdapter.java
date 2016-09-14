package com.lingdian.waimaibang.fragment.waimai.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.waimai.YedianActivity;
import com.lingdian.waimaibang.utils.OptionsUtil;
import com.lingdian.waimaibang.utils.PhoneUtils;
import com.lingdian.waimaibang.view.ChooseRemindDialog;
import com.lingdian.waimaibang.waimaimodel.Stores;
import com.nostra13.universalimageloader.core.ImageLoader;

public class YeshiMainAdapter extends BaseAdapter {

	public List<Stores> list;
	public Context context;

	public YeshiMainAdapter(Context context, List<Stores> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;

	}

	public void onReference(List<Stores> list){
		this.list = list;
        notifyDataSetChanged();
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
		final ViewHolder viewHolder;
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = layoutInflater.inflate(
					R.layout.listitem_jingxuan_wanfa_layout, null);
			viewHolder.yeshi_name = (TextView) convertView
					.findViewById(R.id.yeshi_name);
			viewHolder.yeshi_dizhi1 = (TextView) convertView
					.findViewById(R.id.yeshi_dizhi1);
			viewHolder.yeshi_img = (ImageView) convertView
					.findViewById(R.id.yeshi_img);
			viewHolder.yeshi_yingye_time = (TextView) convertView
					.findViewById(R.id.yeshi_yingye_time);
			
			viewHolder.yeshi_all_layout = (LinearLayout) convertView.findViewById(R.id.yeshi_all_layout);
			
			viewHolder.tel_phone = (ImageView) convertView
					.findViewById(R.id.tel_phone);
			
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final Stores stores = list.get(position);
		
		
		if (context != null) {
			if (stores.getImg() != null) {
				ImageLoader.getInstance()
						.displayImage(
								stores.getImg()
										.getFileUrl(context) == null ? ""
										: stores.getImg().getFileUrl(context),
										viewHolder.yeshi_img,OptionsUtil.PicCamera());
			}
		}
		
		viewHolder.yeshi_name.setText(stores.getName());
		viewHolder.yeshi_dizhi1.setText(stores.getDiming1());
		viewHolder.yeshi_yingye_time.setText("营业时间："+stores.getYingyeTime());
		
		
		viewHolder.tel_phone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callDialog("是否拨打该商家的电话进行订餐？电话号码："+stores.getTel(), stores.getTel());
			}
		});
		
		viewHolder.yeshi_all_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(context,YedianActivity.class);
				i.putExtra("stores", stores);
				context.startActivity(i);
			}
		});
		
		return convertView;
	}

	public static class ViewHolder {
		public LinearLayout yeshi_all_layout;
		public ImageView yeshi_img;
		public TextView yeshi_name;
		public TextView yeshi_dizhi1;
		public TextView yeshi_yingye_time;
		public ImageView tel_phone;
		
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