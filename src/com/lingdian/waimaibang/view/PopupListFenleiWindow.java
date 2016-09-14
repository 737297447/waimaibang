package com.lingdian.waimaibang.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.model.PopupFenleiModel;
import com.lingdian.waimaibang.model.SecondCity;

public class PopupListFenleiWindow {
	public static final String TYPE_GROUP_ID = "TYPE_GROUP_NAME";
	public static final String TYPE_PHASE = "TYPE_PHASE";
	public static final String TYPE_SORT = "TYPE_SORT";
	private LinearLayout below_list;
	private Context mActivity;
	private List<PopupFenleiModel> mListData = new ArrayList<PopupFenleiModel>();
	private ListView mListView;
	private PopupListWindowListener mListener = null;
	public PopupWindow mPopupWindow;
	private LinearLayout mRoot;
	private int selectPosition;

	public PopupListFenleiWindow(Context mActivity,
			PopupListWindowListener paramPopupListWindowListener) {
		this.mActivity = mActivity;
		this.mListener = paramPopupListWindowListener;
	}

	public void showPopupWindow(View paramView,
			List<PopupFenleiModel> paramArrayList, String selectCityName) {
		if (this.mPopupWindow != null)
			return;
		this.mListData.clear();
		PopupFenleiModel departBean = new PopupFenleiModel();
		departBean.setName("全部分类");
		departBean.setId("0");
		this.mListData.add(departBean);
		this.mListData.addAll(paramArrayList);
		View localView = LayoutInflater.from(this.mActivity).inflate(
				R.layout.dialog_popup_list_window, null, false);
		this.mPopupWindow = new PopupWindow(localView, -1, -2);
		this.mPopupWindow.setFocusable(true);
		this.mPopupWindow.setOutsideTouchable(true);
		this.mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		this.mRoot = ((LinearLayout) localView.findViewById(R.id.root));
		this.mRoot.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				// if (PopupListWindow.this.mPopupWindow != null)
				// PopupListWindow.this.mPopupWindow.dismiss();
			}
		});
		this.below_list = ((LinearLayout) localView
				.findViewById(R.id.below_list));
		this.below_list.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {

				// if (PopupListWindow.this.mListener != null)
				// PopupListWindow.this.mListener
				// .onBelowListItemClick();
				// if (PopupListWindow.this.mPopupWindow != null){
				// PopupListWindow.this.mPopupWindow.dismiss();
				// }

			}
		});
		this.mListView = ((ListView) localView.findViewById(R.id.list));
		this.mListView.setAdapter(new ListAdapter());
		// setCurrentSelect(selectCityName);
		this.mPopupWindow
				.setOnDismissListener(new PopupWindow.OnDismissListener() {
					public void onDismiss() {
						PopupListFenleiWindow.this.mPopupWindow = null;
						if (PopupListFenleiWindow.this.mListener != null)
							PopupListFenleiWindow.this.mListener.onDismiss();
					}
				});
		this.mPopupWindow.showAsDropDown(paramView);
	}

	private void setCurrentSelect(String selectCityName) {
		if ((this.mListData != null) && (this.mListData != null)) {
			for (int i = 0;; i++) {
				if (i >= this.mListData.size())
					return;
				if (selectCityName
						.equals(this.mListData.get(i).getName())) {
					this.mListView.setSelection(i);
					selectPosition = i;
					return;
				}
			}

		}
	}

	public class ListAdapter extends BaseAdapter {
		public ListAdapter() {
		}

		public int getCount() {
			if (PopupListFenleiWindow.this.mListData == null)
				return 0;
			return PopupListFenleiWindow.this.mListData.size();
		}

		public Object getItem(int paramInt) {
			if (PopupListFenleiWindow.this.mListData == null)
				return null;
			return PopupListFenleiWindow.this.mListData.get(paramInt);
		}

		public long getItemId(int paramInt) {
			return 0L;
		}

		public View getView(int paramInt, View paramView,
				ViewGroup paramViewGroup) {
			ViewHolder localViewHolder = null;
			if (paramView == null) {
				localViewHolder = new ViewHolder();
				paramView = LayoutInflater.from(
						PopupListFenleiWindow.this.mActivity).inflate(
						R.layout.listarray_popup_list_window, null);
				localViewHolder.item_root = ((RelativeLayout) paramView
						.findViewById(R.id.item_root));
				localViewHolder.item_content = ((TextView) paramView
						.findViewById(R.id.item_content));
				localViewHolder.imageView1 = ((ImageView) paramView
						.findViewById(R.id.imageView1));
				paramView.setTag(localViewHolder);
			} else {
				localViewHolder = (ViewHolder) paramView.getTag();
			}
			final PopupFenleiModel str = (PopupFenleiModel) getItem(paramInt);
			localViewHolder.item_root
					.setOnClickListener(new View.OnClickListener() {
						public void onClick(View paramAnonymousView) {
							if (PopupListFenleiWindow.this.mListener != null)
								PopupListFenleiWindow.this.mListener
										.onListItemClick(str);
							PopupListFenleiWindow.this.mPopupWindow.dismiss();
						}
					});

			localViewHolder.item_content.setText(mListData.get(paramInt)
					.getName());
            if(mListData.get(paramInt).getId().equals("0")){
            	localViewHolder.imageView1.setImageResource(R.drawable.fenlei_quanbu);
            }else if(mListData.get(paramInt).getId().equals("1")){
            	localViewHolder.imageView1.setImageResource(R.drawable.fenlei_jingdian);
            }else if(mListData.get(paramInt).getId().equals("2")){
            	localViewHolder.imageView1.setImageResource(R.drawable.fenlei_zhusu);
            }else if(mListData.get(paramInt).getId().equals("3")){
            	localViewHolder.imageView1.setImageResource(R.drawable.fenlei_meishi);
            }else if(mListData.get(paramInt).getId().equals("4")){
            	localViewHolder.imageView1.setImageResource(R.drawable.fenlei_qita);
            }
			
			
			
			
			localViewHolder.item_content.setTextColor(Color.BLACK);

			return paramView;
		}

		public class ViewHolder {
			TextView item_content;
			RelativeLayout item_root;
			ImageView imageView1;

			public ViewHolder() {
			}
		}
	}

	public static abstract interface PopupListWindowListener {
		public abstract void onDismiss();

		public abstract void onListItemClick(PopupFenleiModel fenleiModel);

		public abstract void onBelowListItemClick();
	}
}
