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
import com.lingdian.waimaibang.model.SecondCity;

public class PopupListCityWindow {
	public static final String TYPE_GROUP_ID = "TYPE_GROUP_NAME";
	public static final String TYPE_PHASE = "TYPE_PHASE";
	public static final String TYPE_SORT = "TYPE_SORT";
	private LinearLayout below_list;
	private Context mActivity;
	private List<SecondCity> mListData = new ArrayList<SecondCity>();
	private ListView mListView;
	private PopupListWindowListener mListener = null;
	public PopupWindow mPopupWindow;
	private LinearLayout mRoot;
	private int selectPosition;

	public PopupListCityWindow(Context mActivity,
			PopupListWindowListener paramPopupListWindowListener) {
		this.mActivity = mActivity;
		this.mListener = paramPopupListWindowListener;
	}

	
	public void showPopupWindow(View paramView,
			List<SecondCity> paramArrayList,String selectCityName) {
		if (this.mPopupWindow != null)
			return;
		this.mListData.clear();
		SecondCity departBean = new SecondCity();
		departBean.setName("全部城市");
		departBean.setId("");
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
//				if (PopupListWindow.this.mPopupWindow != null)
//					PopupListWindow.this.mPopupWindow.dismiss();
			}
		});
		this.below_list = ((LinearLayout) localView
				.findViewById(R.id.below_list));
		below_list.setVisibility(View.GONE);
		this.below_list.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				
//				if (PopupListWindow.this.mListener != null)
//					PopupListWindow.this.mListener
//							.onBelowListItemClick();
//				if (PopupListWindow.this.mPopupWindow != null){
//					PopupListWindow.this.mPopupWindow.dismiss();
//				}
					
			}
		});
		this.mListView = ((ListView) localView.findViewById(R.id.list));
		this.mListView.setAdapter(new ListAdapter());
		setCurrentSelect(selectCityName);
		this.mPopupWindow
				.setOnDismissListener(new PopupWindow.OnDismissListener() {
					public void onDismiss() {
						PopupListCityWindow.this.mPopupWindow = null;
						if (PopupListCityWindow.this.mListener != null)
							PopupListCityWindow.this.mListener.onDismiss();
					}
				});
		this.mPopupWindow.showAsDropDown(paramView);
	}
	
	private void setCurrentSelect(String selectCityName) {
		if ((this.mListData != null) && (this.mListData != null)) {
			for (int i = 0;; i++) {
				if (i >= this.mListData.size())
					return;
				if (selectCityName.equals(this.mListData.get(i).getName())) {
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
			if (PopupListCityWindow.this.mListData == null)
				return 0;
			return PopupListCityWindow.this.mListData.size();
		}

		public Object getItem(int paramInt) {
			if (PopupListCityWindow.this.mListData == null)
				return null;
			return PopupListCityWindow.this.mListData.get(paramInt);
		}

		public long getItemId(int paramInt) {
			return 0L;
		}

		public View getView(int paramInt, View paramView,
				ViewGroup paramViewGroup) {
			ViewHolder localViewHolder = null;
			if (paramView == null) {
				localViewHolder = new ViewHolder();
				paramView = LayoutInflater.from(PopupListCityWindow.this.mActivity)
						.inflate(R.layout.listarray_popup_list_window, null);
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
			final SecondCity str = (SecondCity) getItem(paramInt);
			localViewHolder.item_root
					.setOnClickListener(new View.OnClickListener() {
						public void onClick(View paramAnonymousView) {
							if (PopupListCityWindow.this.mListener != null)
								PopupListCityWindow.this.mListener
										.onListItemClick(str);
							PopupListCityWindow.this.mPopupWindow.dismiss();
						}
					});
			
			localViewHolder.item_content.setText(mListData.get(paramInt)
					.getName());
			if(selectPosition == paramInt){
				localViewHolder.imageView1.setVisibility(View.VISIBLE);
				localViewHolder.item_content.setTextColor(Color.parseColor("#FDD54A"));
			}else{
				localViewHolder.imageView1.setVisibility(View.GONE);
				localViewHolder.item_content.setTextColor(Color.BLACK);
			}
			
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
		public abstract void onListItemClick(SecondCity secondCity);
		public abstract void onBelowListItemClick();
	}
}
