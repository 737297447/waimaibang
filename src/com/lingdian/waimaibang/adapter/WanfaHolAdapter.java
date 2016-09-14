package com.lingdian.waimaibang.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.model.common.Photos;
import com.lingdian.waimaibang.tools.CircleImageView;
import com.lingdian.waimaibang.utils.OptionsUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

public class WanfaHolAdapter extends BaseAdapter {
	
	public List<Photos> photos;
	public Context mContext;
	public ImageView imageView;
	public Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
	
	
	

	public WanfaHolAdapter(Context context, List<Photos> photos,ImageView imageView) {
		this.mContext = context;
		this.photos = photos;
		this.imageView = imageView;
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
		final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(
					R.layout.listitem_wanfa_ho_layout, parent, false);

			holder.ho_image = (ImageView) convertView
					.findViewById(R.id.ho_image);
			holder.back_layout = (LinearLayout) convertView
					.findViewById(R.id.back_layout);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		ImageLoader.getInstance().displayImage(
				photos.get(position).getFile_url()+"!iphone.cut.210.210",
				holder.ho_image, OptionsUtil.PicNormal());
		
		holder.ho_image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ImageLoader.getInstance().displayImage(
						photos.get(position).getFile_url()+"!photo.scale.big",
						imageView, OptionsUtil.PicNormal());
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
		private ImageView ho_image;
		private LinearLayout back_layout;
	}
}