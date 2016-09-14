package com.lingdian.waimaibang.fragment.waimai.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.waimai.BaiduActivity;
import com.lingdian.waimaibang.waimaimodel.BaiduContent;

public class BaiduAdapter extends BaseAdapter {

	public List<BaiduContent> list;
	public Context context;

	public BaiduAdapter(Context context, List<BaiduContent> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;

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
					R.layout.listitem_waimaibang_baidu, null);
			
			viewHolder.baidu_dizhi = (TextView) convertView
					.findViewById(R.id.baidu_dizhi);
			viewHolder.baidu_layout = (LinearLayout) convertView
					.findViewById(R.id.baidu_layout);
			
			viewHolder.baidu_shangjia= (TextView) convertView
					.findViewById(R.id.baidu_shangjia);
			viewHolder.baidu_xiangxidizhi= (TextView) convertView
					.findViewById(R.id.baidu_xiangxidizhi);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final BaiduContent baiduContent = list.get(position);
		
		viewHolder.baidu_dizhi.setText(baiduContent.getName());
		viewHolder.baidu_shangjia.setText(baiduContent.getShopnum()+"");
		viewHolder.baidu_xiangxidizhi.setText(baiduContent.getAddress());
		
		
		viewHolder.baidu_layout
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent i = new Intent(context,
								BaiduActivity.class);
						i.putExtra("name", baiduContent.getName());
						i.putExtra("latitude", baiduContent.getLatitude());
						i.putExtra("longitude", baiduContent.getLongitude());
						context.startActivity(i);
					}
				});

		return convertView;
	}

	public static class ViewHolder {
		public LinearLayout baidu_layout;
		
		public TextView baidu_dizhi;
		public TextView baidu_shangjia;
		public TextView baidu_xiangxidizhi;
		
	}

}