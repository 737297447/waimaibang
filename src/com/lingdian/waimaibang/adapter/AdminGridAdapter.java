package com.lingdian.waimaibang.adapter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.GalleryPicActivity;
import com.lingdian.waimaibang.model.common.Photos;
import com.lingdian.waimaibang.tools.GlobalFuction;
import com.lingdian.waimaibang.utils.OptionsUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

public class AdminGridAdapter extends BaseAdapter {

	public List<Photos> photos;
	public Context context;
	public Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
	public int selectPosition = 0;
	
	public AdminGridAdapter(Context context, List<Photos> photos) {
		this.context = context;
		this.photos = photos;
		setFalse();
	}

	
	public void setFalse(){
		for(int i=0;i<photos.size();i++){
			if(i == 0){
				map.put(i, true);
			}else{
				map.put(i, false);
			}
		}
	}
	
	public void setPositionClicl(int position){
		for(int i=0;i<photos.size();i++){
			if(i == position){
				map.put(i, true);
			}else{
				map.put(i, false);
			}
		}
	}
	
	
	@Override
	public int getCount() {
		return photos.size();
	}

	@Override
	public Object getItem(int position) {
		return photos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		final LayoutInflater layoutInflater = LayoutInflater.from(context);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.admin_gridview_item,
					parent, false);

			holder.gridview_item_layout = (ImageView) convertView
					.findViewById(R.id.gridview_item_layout);
			holder.back_layout = (LinearLayout) convertView
					.findViewById(R.id.back_layout);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) holder.gridview_item_layout
				.getLayoutParams(); // 取控件textView当前的布局参数

		int one = (GlobalFuction.getScreenMaxWidth(context, 10) / 2)/3;
		
		linearParams.height = one * 4;// 控件的高强制设成
		linearParams.width = one * 3;// 控件的宽强制设成
		holder.gridview_item_layout.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件</pre>

		
		ImageLoader.getInstance().displayImage(
				photos.get(position).getFile_url()+"!iphone.cut.210.210",
				holder.gridview_item_layout, OptionsUtil.PicNormal());

		holder.gridview_item_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				selectPosition = position;
				setPositionClicl(position);
				notifyDataSetChanged();
			}
		});
		
		if(map.get(position)){
			holder.back_layout.setBackgroundResource(R.drawable.wanfa_yellow_bg);
		}else{
			holder.back_layout.setBackgroundResource(R.color.white);
		}
		

		return convertView;
	}

	private static class ViewHolder {
		private ImageView gridview_item_layout;
		private LinearLayout back_layout;
	}
	
	public int getMap(){
		return selectPosition;
	}
}