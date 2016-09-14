package com.lingdian.waimaibang.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
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
import com.lingdian.waimaibang.model.Descriptionop;
import com.lingdian.waimaibang.model.Destination;
import com.lingdian.waimaibang.model.MudidiTextModel;
import com.lingdian.waimaibang.tools.CircleImageView;
import com.lingdian.waimaibang.tools.HorizontalListView;
import com.lingdian.waimaibang.utils.OptionsUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MudidiHorAdapter extends BaseAdapter {

	private Context context;
	/** 列表. */
	private List<Descriptionop> lstDate;

	// 每页显示的Item个数
	public static final int SIZE = 1;

	public MudidiHorAdapter(Context mContext, List<Descriptionop> list, int page) {
		this.context = mContext;
		lstDate = new ArrayList<Descriptionop>();
		int i = page * SIZE;
		int iEnd = i + SIZE;
		while ((i < list.size()) && (i < iEnd)) {
			lstDate.add(list.get(i));
			i++;
		}
	}

	@Override
	public int getCount() {
		return lstDate.size();
	}

	@Override
	public Object getItem(int position) {
		return lstDate.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = layoutInflater.inflate(
					R.layout.listitem_mudidi_hor_text, null);

			viewHolder.mudidi_hor_shuoshuo = (TextView) convertView
					.findViewById(R.id.mudidi_hor_shuoshuo);
			viewHolder.mudidi_user_layout = (LinearLayout) convertView
					.findViewById(R.id.mudidi_user_layout);
			viewHolder.mudidi_userimg = (CircleImageView) convertView
					.findViewById(R.id.mudidi_userimg);
			viewHolder.mudidi_username = (TextView) convertView
					.findViewById(R.id.mudidi_username);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.mudidi_hor_shuoshuo.setText(lstDate.get(position)
				.getContent());

		if (lstDate.get(position).getUser() != null) {
			viewHolder.mudidi_username.setText(lstDate.get(position).getUser()
					.getNickname());
			if (lstDate.get(position).getUser().getPhoto() != null) {
				ImageLoader.getInstance().displayImage(
						lstDate.get(position).getUser().getPhoto()
								.getFile_url(), viewHolder.mudidi_userimg,
						OptionsUtil.PicNormal());
			}

		} else {
			viewHolder.mudidi_username.setVisibility(View.GONE);
			viewHolder.mudidi_userimg.setVisibility(View.GONE);
		}

		return convertView;
	}

	public static class ViewHolder {
		public TextView mudidi_hor_shuoshuo;
		public LinearLayout mudidi_user_layout;
		public CircleImageView mudidi_userimg;
		public TextView mudidi_username;
	}

}
