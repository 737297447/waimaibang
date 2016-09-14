package com.lingdian.waimaibang.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.model.XiaoxiModel;

public class XiaoxiAdapter extends BaseAdapter {

	public List<XiaoxiModel> list;
	public Context context;

	public XiaoxiAdapter(Context context, List<XiaoxiModel> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;

	}

	public void onReference(List<XiaoxiModel> list){
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
					R.layout.listitem_wo_xiaoxi, null);
			viewHolder.xiaoxi_isread_pic = (ImageView) convertView
					.findViewById(R.id.xiaoxi_isread_pic);
			viewHolder.xiaoxi_neirong = (TextView) convertView
					.findViewById(R.id.xiaoxi_neirong);
			viewHolder.xiaoxi_layout = (LinearLayout) convertView
					.findViewById(R.id.xiaoxi_layout);
			
			viewHolder.xiaoxi_title= (TextView) convertView
					.findViewById(R.id.xiaoxi_title);
			viewHolder.xiaoxi_time= (TextView) convertView
					.findViewById(R.id.xiaoxi_time);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.xiaoxi_neirong.setText(list.get(position).getContent_sanitize());
		viewHolder.xiaoxi_title.setText(list.get(position).getTitle());
		viewHolder.xiaoxi_time.setText(list.get(position).getEdited_date());
		
		
		if(list.get(position).isRead()){
			viewHolder.xiaoxi_isread_pic.setVisibility(View.INVISIBLE);
		}else{
			viewHolder.xiaoxi_isread_pic.setVisibility(View.VISIBLE);
		}
		
		
		
//		viewHolder.xiaoxi_layout
//				.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View arg0) {
//						// TODO Auto-generated method stub
////						Intent i = new Intent(context,
////								WanfaDetailMenuActivity.class);
////						i.putExtra("diming", viewHolder.zhoubian_localname.getText().toString());
////						context.startActivity(i);
//					}
//				});

		return convertView;
	}

	public static class ViewHolder {
		public LinearLayout xiaoxi_layout;
		public ImageView xiaoxi_isread_pic;
		public TextView xiaoxi_neirong;
		public TextView xiaoxi_title;
		public TextView xiaoxi_time;
		
	}

}