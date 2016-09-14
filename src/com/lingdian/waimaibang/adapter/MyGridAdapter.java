package com.lingdian.waimaibang.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.model.common.Photos;
import com.lingdian.waimaibang.tools.GlobalFuction;
import com.lingdian.waimaibang.utils.OptionsUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MyGridAdapter extends BaseAdapter {
	private List<Photos> photos;

	private LayoutInflater mLayoutInflater;
	private Context context;
	

	public MyGridAdapter(Context context,List<Photos> photos) {
		this.photos = photos;
		this.context = context;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return photos == null ? 0 : photos.size();
	}

	@Override
	public Object getItem(int position) {
		return photos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyGridViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new MyGridViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.gridview_item,
					parent, false);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.album_image);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (MyGridViewHolder) convertView.getTag();
		}
		ImageLoader.getInstance().displayImage(
				photos.get(position).getFile_url()+"!iphone.cut.210.210",
				viewHolder.imageView, OptionsUtil.PicNormal());
		
		
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) viewHolder.imageView
				.getLayoutParams(); // 取控件textView当前的布局参数

		linearParams.height = GlobalFuction.getScreenMaxWidth(context, 70) / 3;// 控件的高强制设成
		linearParams.width = GlobalFuction.getScreenMaxWidth(context, 70) / 3;// 控件的宽强制设成

		viewHolder.imageView.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件</pre>

		
		
		
//		String url = liModels.get(position).getImgUrl();

//		ImageLoader.getInstance().displayImage(url, viewHolder.imageView);
//		ImageLoader.getInstance().displayImage(url, viewHolder.imageView, new ImageLoadingListener() {
//			
//			@Override
//			public void onLoadingStarted(String arg0, View arg1) {
//				// TODO Auto-generated method stub
//			}
//			
//			@Override
//			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
//				// TODO Auto-generated method stub
//			}
//			
//			@Override
//			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
//				// TODO Auto-generated method stub
//			}
//			
//			@Override
//			public void onLoadingCancelled(String arg0, View arg1) {
//				// TODO Auto-generated method stub
//			}
//		});
		return convertView;
	}

	private static class MyGridViewHolder {
		ImageView imageView;
	}
}
