package com.lingdian.waimaibang.adapter;

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
import com.lingdian.waimaibang.activity.jingxuan.JingxuanShangpinlActivity;
import com.lingdian.waimaibang.model.Product;
import com.lingdian.waimaibang.utils.OptionsUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ShangpinAdapter extends BaseAdapter {

	public List<Product> list;
	public Context context;

	public ShangpinAdapter(Context context, List<Product> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;

	}

	public void onReference(List<Product> list) {
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
			convertView = layoutInflater.inflate(R.layout.listitem_shangpin,
					null);
			viewHolder.shangpin_localname = (TextView) convertView
					.findViewById(R.id.shangpin_localname);
			viewHolder.shangpin_money = (TextView) convertView
					.findViewById(R.id.shangpin_money);
			viewHolder.shangpin_image = (ImageView) convertView
					.findViewById(R.id.shangpin_image);
			viewHolder.shangpin_linearlayout = (LinearLayout) convertView
					.findViewById(R.id.shangpin_linearlayout);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.shangpin_localname.setText(list.get(position).getTitle());
		viewHolder.shangpin_money.setText(list.get(position).getShow_price()
				+ "");
		
		if(list.get(position).getPhoto() !=null){
			ImageLoader.getInstance().displayImage(
					list.get(position).getPhoto().getFile_url()
							+ "!iphone.cut.210.210", viewHolder.shangpin_image,
					OptionsUtil.PicNormal());
		}
		

		viewHolder.shangpin_linearlayout
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent i = new Intent(context,
								JingxuanShangpinlActivity.class);
						i.putExtra("weburl", "http://meizhouliu.com/products/"
								+ list.get(position).getId());
						context.startActivity(i);
					}
				});

		return convertView;
	}

	public static class ViewHolder {
		public LinearLayout shangpin_linearlayout;
		public ImageView shangpin_image;
		public TextView shangpin_localname;
		public TextView shangpin_money;
	}

}