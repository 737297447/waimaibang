package com.lingdian.waimaibang.fragment.waimai.adapter;

import java.util.List;

import android.content.ClipData.Item;
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
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.jingxuan.JingxuanShangpinlActivity;
import com.lingdian.waimaibang.activity.waimai.XcfWebActivity;
import com.lingdian.waimaibang.model.JingxuanShangpinModel;
import com.lingdian.waimaibang.model.JingxuanWanfaModel;
import com.lingdian.waimaibang.model.Product;
import com.lingdian.waimaibang.utils.OptionsUtil;
import com.lingdian.waimaibang.waimaimodel.XItems;
import com.nostra13.universalimageloader.core.ImageLoader;

public class XiaChufangAdapter extends BaseAdapter {

	public List<XItems> list;
	public Context context;
	public String topText;
	

	public XiaChufangAdapter(Context context, List<XItems> list,String topText) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		this.topText = topText;

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
					R.layout.listitem_waimai_xiachufang_main, null);
			viewHolder.xcf_img_text1 = (TextView) convertView
					.findViewById(R.id.xcf_img_text1);
			viewHolder.xcf_img_text2 = (TextView) convertView
					.findViewById(R.id.xcf_img_text2);
			viewHolder.xcf_img_text3 = (TextView) convertView
					.findViewById(R.id.xcf_img_text3);
			viewHolder.xcf_img_text4 = (TextView) convertView
					.findViewById(R.id.xcf_img_text4);
			viewHolder.xcf_img_text5 = (TextView) convertView
					.findViewById(R.id.xcf_img_text5);
			viewHolder.xcf_img_text_top = (TextView) convertView
					.findViewById(R.id.xcf_img_text_top);

			viewHolder.xcf_img = (ImageView) convertView
					.findViewById(R.id.xcf_img);
			viewHolder.play_image = (ImageView) convertView
					.findViewById(R.id.play_image);

			viewHolder.xcf_layout1 = (LinearLayout) convertView
					.findViewById(R.id.xcf_layout1);
			viewHolder.xcf_layout2 = (LinearLayout) convertView
					.findViewById(R.id.xcf_layout2);
			viewHolder.xcf_layout_top = (LinearLayout) convertView
					.findViewById(R.id.xcf_layout_top);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final XItems items = list.get(position);

		
		if(position == 0){
			viewHolder.xcf_layout_top.setVisibility(View.VISIBLE);
			viewHolder.xcf_img_text_top.setText(topText);
		}else{
			viewHolder.xcf_layout_top.setVisibility(View.GONE);
		}
		
		
		if (items.getContents().getImage() != null) {
			String imagePath = items.getContents().getImage().getUrl();
			ImageLoader.getInstance().displayImage(imagePath,
					viewHolder.xcf_img, OptionsUtil.PicMudidiNormal());
		} else {
			viewHolder.xcf_img
					.setImageResource(R.drawable.camerasdk_pic_loading);
		}

		if (!TextUtils.isEmpty(items.getContents().getTitle_1st())) {
			viewHolder.xcf_img_text1.setVisibility(View.VISIBLE);
			viewHolder.xcf_img_text1.setText(items.getContents().getTitle_1st()
					+ "");
		} else {
			viewHolder.xcf_img_text1.setVisibility(View.GONE);
		}
		if (!TextUtils.isEmpty(items.getContents().getTitle_2nd())) {
			viewHolder.xcf_img_text2.setText(items.getContents().getTitle_2nd()
					+ "");
			viewHolder.xcf_img_text2.setVisibility(View.VISIBLE);
		} else {
			viewHolder.xcf_img_text2.setVisibility(View.GONE);
		}

		if (!TextUtils.isEmpty(items.getContents().getTitle())) {
			viewHolder.xcf_layout2.setVisibility(View.VISIBLE);
			viewHolder.xcf_img_text3.setText(items.getContents().getTitle()
					+ "");
		} else {
			viewHolder.xcf_img_text3.setText("");
			viewHolder.xcf_layout2.setVisibility(View.GONE);
		}

		if (!TextUtils.isEmpty(items.getContents().getVideo_url())
				&& !items.getContents().getVideo_url().equals("")) {
			viewHolder.play_image.setVisibility(View.VISIBLE);
		} else {
			viewHolder.play_image.setVisibility(View.GONE);
		}

		if (!TextUtils.isEmpty(items.getContents().getN_cooked())) {
			viewHolder.xcf_img_text4.setText(items.getContents().getN_cooked()
					+ "人做过");
			viewHolder.xcf_img_text4.setVisibility(View.VISIBLE);
		} else {
			viewHolder.xcf_img_text4.setVisibility(View.GONE);
		}

		viewHolder.xcf_img_text5.setText(items.getContents().getDesc() + "");

		viewHolder.xcf_layout1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(context, XcfWebActivity.class);
				if (!TextUtils.isEmpty(items.getContents().getTitle())) {
					i.putExtra("name", items.getContents().getTitle() + "");
				} else if (!TextUtils.isEmpty(items.getContents().getTitle_1st())) {
					i.putExtra("name", items.getContents().getTitle_1st() + "");
				}else{
					i.putExtra("name", "^_^");
				}

				i.putExtra("webUrl", items.getUrl());
				context.startActivity(i);
			}
		});

		return convertView;
	}

	public static class ViewHolder {
		public LinearLayout xcf_layout1, xcf_layout2, xcf_layout_top;
		public ImageView xcf_img, play_image;
		public TextView xcf_img_text1, xcf_img_text2, xcf_img_text3,
				xcf_img_text4, xcf_img_text5, xcf_img_text_top;
	}

}