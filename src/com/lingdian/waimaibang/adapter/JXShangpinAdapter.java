package com.lingdian.waimaibang.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.listener.UpdateListener;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.activity.jingxuan.JingxuanShangpinlActivity;
import com.lingdian.waimaibang.activity.waimai.BigImageActivity;
import com.lingdian.waimaibang.activity.waimai.XcfWebActivity1;
import com.lingdian.waimaibang.model.JingxuanShangpinModel;
import com.lingdian.waimaibang.model.JingxuanWanfaModel;
import com.lingdian.waimaibang.model.Product;
import com.lingdian.waimaibang.utils.OptionsUtil;
import com.lingdian.waimaibang.utils.PhoneUtils;
import com.lingdian.waimaibang.utils.SharedpreferncesUtil;
import com.lingdian.waimaibang.view.ChooseRemindDialog;
import com.lingdian.waimaibang.waimaimodel.goods;
import com.mob.tools.utils.SharePrefrenceHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

public class JXShangpinAdapter extends BaseAdapter {

	public List<goods> list;
	public Context context;

	public JXShangpinAdapter(Context context, List<goods> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;

	}

	public void onReference(List<goods> list) {
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
					R.layout.listitem_jingxuan_shangpin_layout, null);
			viewHolder.jingxuan_shangpin_money = (TextView) convertView
					.findViewById(R.id.jingxuan_shangpin_money);
			viewHolder.jingxuan_shangpin_jianjie = (TextView) convertView
					.findViewById(R.id.jingxuan_shangpin_jianjie);
			viewHolder.jingxuan_shangpin_img = (ImageView) convertView
					.findViewById(R.id.jingxuan_shangpin_img);
			viewHolder.jingxuan_shangpin_all_layout = (LinearLayout) convertView
					.findViewById(R.id.jingxuan_shangpin_all_layout);

			viewHolder.xiangchi_text = (TextView) convertView
					.findViewById(R.id.xiangchi_text);
			viewHolder.buxiangchi_text = (TextView) convertView
					.findViewById(R.id.buxiangchi_text);
			viewHolder.xiangchi_num = (TextView) convertView
					.findViewById(R.id.xiangchi_num);
			viewHolder.xiangchi_maxnum = (TextView) convertView
					.findViewById(R.id.xiangchi_maxnum);
			viewHolder.tel_phone = (ImageView) convertView
					.findViewById(R.id.tel_phone);
			viewHolder.jingxuan_tel = (TextView) convertView
					.findViewById(R.id.jingxuan_tel);
			viewHolder.jingxuan_weixin = (TextView) convertView
					.findViewById(R.id.jingxuan_weixin);

			viewHolder.tel_layout = (LinearLayout) convertView
					.findViewById(R.id.tel_layout);
			viewHolder.weixin_layout = (LinearLayout) convertView
					.findViewById(R.id.weixin_layout);
			viewHolder.enter_text = (TextView) convertView
					.findViewById(R.id.enter_text);

			viewHolder.mudidi_layout = (RelativeLayout) convertView
					.findViewById(R.id.mudidi_layout);

			
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.jingxuan_shangpin_jianjie.setText(list.get(position)
				.getGoodsJianjie());
		viewHolder.jingxuan_shangpin_money.setText(list.get(position)
				.getGoodsName());
		viewHolder.jingxuan_tel.setText(list.get(position).getTel());
		viewHolder.xiangchi_num.setText(list.get(position).getXiang() + "");
		viewHolder.xiangchi_maxnum.setText(list.get(position).getMaxXiang()
				+ "");
		viewHolder.jingxuan_weixin.setText(list.get(position).getWeixin() + "");

		if (TextUtils.isEmpty(list.get(position).getTel())
				|| list.get(position).getTel().equals("暂未上架")
				|| list.get(position).getTel().equals("暂未上线")) {
			viewHolder.tel_layout.setVisibility(View.GONE);
		} else {
			viewHolder.tel_layout.setVisibility(View.VISIBLE);
		}

		if (TextUtils.isEmpty(list.get(position).getWeixin())
				|| list.get(position).getWeixin().equals("暂未上架")
				|| list.get(position).getWeixin().equals("暂未上线")) {
			viewHolder.weixin_layout.setVisibility(View.GONE);
		} else {
			viewHolder.weixin_layout.setVisibility(View.VISIBLE);
		}

		if (list.get(position).getAdPic() != null) {
			ImageLoader.getInstance().displayImage(
					list.get(position).getAdPic().getFileUrl(context),
					viewHolder.jingxuan_shangpin_img,
					OptionsUtil.PicMudidiNormal());
		}

		viewHolder.mudidi_layout
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(context, BigImageActivity.class);
						if (list.get(position).getAdPic() != null) {
							i.putExtra("imgPath", list.get(position).getAdPic()
									.getFileUrl(context));
						} else {
							i.putExtra("imgPath", "");
						}
						i.putExtra("name", list.get(position).getGoodsName());
						i.putExtra("detail", list.get(position)
								.getGoodsJianjie());
						context.startActivity(i);
					}
				});

		viewHolder.xiangchi_text.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int xiang = list.get(position).getXiang();
				xiang += 1;
				viewHolder.xiangchi_num.setText(xiang + "");
				viewHolder.xiangchi_text.setClickable(false);
				viewHolder.xiangchi_text.setTextColor(Color.GRAY);
				viewHolder.buxiangchi_text.setClickable(false);
				upData("xiang", list.get(position).getObjectId());
				SharedpreferncesUtil.saveXiaoxiInfo(context, list.get(position)
						.getGoodsName(), list.get(position).getGoodsName());
			}
		});

		viewHolder.buxiangchi_text.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				viewHolder.xiangchi_text.setClickable(false);
				viewHolder.buxiangchi_text.setClickable(false);
				viewHolder.buxiangchi_text.setTextColor(Color.GRAY);
				upData("buxiang", list.get(position).getObjectId());
				SharedpreferncesUtil.saveXiaoxiInfo(context, list.get(position)
						.getGoodsName(), list.get(position).getGoodsName());
			}
		});

		if (SharedpreferncesUtil.isHaveNoRead(context, list.get(position)
				.getGoodsName(), list.get(position).getGoodsName())) {
			viewHolder.xiangchi_text.setClickable(false);
			viewHolder.buxiangchi_text.setClickable(false);
			viewHolder.xiangchi_text.setTextColor(Color.GRAY);
			viewHolder.buxiangchi_text.setTextColor(Color.GRAY);
		} else {
			viewHolder.xiangchi_text.setClickable(true);
			viewHolder.buxiangchi_text.setClickable(true);
			viewHolder.xiangchi_text.setTextColor(Color.WHITE);
			viewHolder.buxiangchi_text.setTextColor(Color.WHITE);
		}

		viewHolder.jingxuan_shangpin_all_layout
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if (TextUtils.isEmpty(list.get(position).getGoodsUrl())) {
							Toast.makeText(context, "小主请耐心等待，正在进货装修店铺中",
									Toast.LENGTH_SHORT).show();
						} else {
							Intent i3 = new Intent(context,
									XcfWebActivity1.class);
							i3.putExtra("name", list.get(position)
									.getGoodsName());
							i3.putExtra("webUrl", list.get(position)
									.getGoodsUrl());
							context.startActivity(i3);
						}
					}
				});

		viewHolder.enter_text.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (TextUtils.isEmpty(list.get(position).getGoodsUrl())) {
					Toast.makeText(context, "小主请耐心等待，正在进货装修店铺中",
							Toast.LENGTH_SHORT).show();
				} else {
					Intent i3 = new Intent(context, XcfWebActivity1.class);
					i3.putExtra("name", list.get(position).getGoodsName());
					i3.putExtra("webUrl", list.get(position).getGoodsUrl());
					context.startActivity(i3);
				}
			}
		});

		if (!TextUtils.isEmpty(list.get(position).getMaxXiang())
				&& !TextUtils.isEmpty(viewHolder.xiangchi_num.getText()
						.toString())) {
			int max = Integer.valueOf(list.get(position).getMaxXiang());
			int now = Integer.valueOf(viewHolder.xiangchi_num.getText()
					.toString());
			if (now >= max) {
				viewHolder.jingxuan_shangpin_all_layout.setClickable(true);
				viewHolder.enter_text.setVisibility(View.VISIBLE);
				viewHolder.xiangchi_text.setVisibility(View.GONE);
				viewHolder.buxiangchi_text.setVisibility(View.GONE);
			} else {
				viewHolder.jingxuan_shangpin_all_layout.setClickable(false);
				viewHolder.enter_text.setVisibility(View.GONE);
				viewHolder.xiangchi_text.setVisibility(View.VISIBLE);
				viewHolder.buxiangchi_text.setVisibility(View.VISIBLE);
			}
		}

		viewHolder.tel_phone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				callDialog(
						"是否拨打该商家的电话进行预定？电话号码：" + list.get(position).getTel(),
						list.get(position).getTel());
			}
		});

		return convertView;
	}

	public static class ViewHolder {
		public LinearLayout jingxuan_shangpin_all_layout;
		public ImageView jingxuan_shangpin_img, tel_phone;
		public TextView jingxuan_shangpin_money, jingxuan_weixin;
		public TextView jingxuan_shangpin_jianjie, jingxuan_tel;
		public TextView xiangchi_text, buxiangchi_text, xiangchi_num,
				xiangchi_maxnum, enter_text;
		public LinearLayout tel_layout, weixin_layout;
		public RelativeLayout mudidi_layout;

	}

	public void upData(String key, String id) {
		goods goods = new goods();
		goods.increment(key);
		goods.update(context, id, new UpdateListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
			}
		});
	}

	/** *打电话弹出的dialog */
	public void callDialog(String msg, final String mobilePh) {
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