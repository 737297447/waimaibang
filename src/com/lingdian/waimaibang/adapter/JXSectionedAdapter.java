package com.lingdian.waimaibang.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.model.JingxuanDetailModel;
import com.lingdian.waimaibang.utils.OptionsUtil;
import com.lingdian.waimaibang.view.pinnedheaderlistview.SectionedBaseAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

public class JXSectionedAdapter extends SectionedBaseAdapter {

	public Context context;
	public List<JingxuanDetailModel> jingxuanDetailList;

	public JXSectionedAdapter(Context context,
			List<JingxuanDetailModel> jingxuanDetailList) {
		this.context = context;
		this.jingxuanDetailList = jingxuanDetailList;
	}

	@Override
	public Object getItem(int section, int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int section, int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSectionCount() {
		return jingxuanDetailList.size();
	}

	@Override
	public int getCountForSection(int section) {

		return jingxuanDetailList.get(section).getItemNeirong().size();
	}

	@Override
	public View getItemView(final int section, final int position,
			View convertView, ViewGroup parent) {

		LayoutInflater layoutInflater = LayoutInflater.from(context);
		ViewHolderChild childHoler = null;
		if (convertView == null) {
			childHoler = new ViewHolderChild();
			convertView = layoutInflater.inflate(
					R.layout.listitem_jingxuan_detail_view, parent, false);
			childHoler.jingxuan_detail_img = (ImageView) convertView
					.findViewById(R.id.jingxuan_detail_img);
			childHoler.jingxuan_detail_text = (TextView) convertView
					.findViewById(R.id.jingxuan_detail_text);

			convertView.setTag(childHoler);
		} else {
			childHoler = (ViewHolderChild) convertView.getTag();
		}

		childHoler.jingxuan_detail_text.setText(jingxuanDetailList.get(section)
				.getItemNeirong().get(position).getJianjie());

		ImageLoader.getInstance().displayImage(
				jingxuanDetailList.get(section).getItemNeirong().get(position)
						.getImgUrl(), childHoler.jingxuan_detail_img,
				OptionsUtil.PicMudidiNormal());
		return convertView;
	}

	@Override
	public View getSectionHeaderView(int section, View convertView,
			ViewGroup parent) {
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		ViewHolderGroup group = null;
		if (convertView == null) {
			convertView = layoutInflater.inflate(
					R.layout.listitem_jingxuan_detail_header, parent, false);
			group = new ViewHolderGroup();
			group.textItem = (TextView) convertView.findViewById(R.id.textItem);
			group.jingxuan_header_layout = (LinearLayout)convertView.findViewById(R.id.jingxuan_header_layout);
			convertView.setTag(group);
		} else {
			group = (ViewHolderGroup) convertView.getTag();
		}
		group.textItem.setText(jingxuanDetailList.get(section).getGroupName());
//		group.jingxuan_header_layout.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//          
//			}
//		});
		return convertView;
	}

	public final class ViewHolderChild {
		public ImageView jingxuan_detail_img;
		public TextView jingxuan_detail_text;
	}

	public final class ViewHolderGroup {
        public LinearLayout jingxuan_header_layout;
		public TextView textItem;
	}
	
	

}
