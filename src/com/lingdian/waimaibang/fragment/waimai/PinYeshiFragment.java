package com.lingdian.waimaibang.fragment.waimai;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.handmark.pulltorefresh.library.PullToRefreshAdapterViewBase.OnPreRefreshingAnimListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.api.DataTask;
import com.lingdian.waimaibang.fragment.BaseFragment;
import com.lingdian.waimaibang.fragment.waimai.adapter.YeshiMainAdapter;
import com.lingdian.waimaibang.waimaimodel.Stores;

public class PinYeshiFragment extends BaseFragment implements OnClickListener {

	private Context context;
	private PullToRefreshListView fragment_quanbu_list;
	private YeshiMainAdapter yeshiMainAdapter;
	private List<Stores> list = new ArrayList<Stores>();
	private static boolean isPage = true; // 是否还有下一页

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_jingxuan_yeshi,
				container, false);
		context = getActivity();
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		fragment_quanbu_list = (PullToRefreshListView) this.getView()
				.findViewById(R.id.fragment_quanbu_list);
		yeshiMainAdapter = new YeshiMainAdapter(context, list);

		fragment_quanbu_list.setAdapter(yeshiMainAdapter);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		fragment_quanbu_list
				.setOnRefreshListener(new OnRefreshListener<ListView>() {

					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {

						if (refreshView.isHeaderShown()) {
							// 下拉刷新 业务代码

						} else {
							// 上拉加载更多 业务代码
							queryData(false);
						}
					}
				});

	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		showLoadingDlg("正在加载中...", true);
		queryData(false);
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		if (!hidden) {

		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	private int page = 0;

	private void queryData(final boolean isClean) {
		BmobQuery<Stores> query = new BmobQuery<Stores>();
		query.setLimit(20); // 设置每页多少条数据
		query.order("-updatedAt");
		query.setSkip(page * 20); // 从第几条数据开始，
		query.findObjects(context, new FindListener<Stores>() {

			@Override
			public void onSuccess(List<Stores> arg0) {
				// TODO Auto-generated method stub
				hideTextLoadingDlg();
				fragment_quanbu_list.onRefreshComplete();
				if (isClean) {
					// 当是下拉刷新操作时，将当前页的编号重置为0，并把bankCards清空，重新添加
					page = 0;
					isPage = true;
					list.clear();
				}
				// 将本次查询的数据添加到bankCards中
				for (Stores td : arg0) {
					list.add(td);
				}
				// 这里在每次加载完数据后，将当前页码+1，这样在上拉刷新的onPullUpToRefresh方法中就不需要操作curPage了
				page++;
				yeshiMainAdapter.onReference(list);

				if (arg0.size() < 20) {
					isPage = false;
				}

			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				hideTextLoadingDlg();
				fragment_quanbu_list.onRefreshComplete();
			}
		});
	}

}
