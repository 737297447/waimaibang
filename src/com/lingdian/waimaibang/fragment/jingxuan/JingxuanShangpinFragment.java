package com.lingdian.waimaibang.fragment.jingxuan;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.adapter.JXShangpinAdapter;
import com.lingdian.waimaibang.api.DataTask;
import com.lingdian.waimaibang.fragment.BaseFragment;
import com.lingdian.waimaibang.waimaimodel.goods;

public class JingxuanShangpinFragment extends BaseFragment implements
		OnClickListener {

	public Context context;
	public static PullToRefreshListView fragment_jingxuanshangpin_list;
	public static JXShangpinAdapter jShangpinAdapter;
	public static List<goods> productList = new ArrayList<goods>();

	private static int page = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_jingxuan_shangpin,
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
		fragment_jingxuanshangpin_list = (PullToRefreshListView) this.getView()
				.findViewById(R.id.fragment_jingxuanshangpin_list);
		jShangpinAdapter = new JXShangpinAdapter(context, productList);
		fragment_jingxuanshangpin_list.setAdapter(jShangpinAdapter);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

		fragment_jingxuanshangpin_list
				.setOnRefreshListener(new OnRefreshListener<ListView>() {

					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {

						if (refreshView.isHeaderShown()) {
							// 下拉刷新 业务代码

						} else {
							// 上拉加载更多 业务代码
							refreshData(false);

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

	private void queryData(final boolean isClean) {
		BmobQuery<goods> query = new BmobQuery<goods>();
		query.setLimit(20); // 设置每页多少条数据
		query.order("-updatedAt");
		query.setSkip(page * 20); // 从第几条数据开始，
		query.findObjects(context, new FindListener<goods>() {
			@Override
			public void onSuccess(List<goods> arg0) {
				// TODO Auto-generated method stub
				hideTextLoadingDlg();
				fragment_jingxuanshangpin_list.onRefreshComplete();
				if (isClean) {
					// 当是下拉刷新操作时，将当前页的编号重置为0，并把bankCards清空，重新添加
					page = 0;
					productList.clear();
				}
				// 将本次查询的数据添加到bankCards中
				for (goods td : arg0) {
					productList.add(td);
				}
				// 这里在每次加载完数据后，将当前页码+1，这样在上拉刷新的onPullUpToRefresh方法中就不需要操作curPage了
				page++;
				jShangpinAdapter.onReference(productList);
			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				hideTextLoadingDlg();
				fragment_jingxuanshangpin_list.onRefreshComplete();
			}
		});
	}

	private void refreshData(boolean clean) {
		dataTask = new RefreshDataTask(dataTask.getID() + 1, clean);
		dataTask.run();
	}

	class RefreshDataTask extends DataTask {

		boolean clean = false; // 是否清空原数据

		public RefreshDataTask(int id, boolean flag) {
			super(id);
			this.clean = flag;
		}

		@Override
		public void run() {
			// 判断是不是用户的最后操作,最后任务的ID如果比此任务的ID大则丢弃请求结果
			if (getID() < dataTask.getID()) {
				return;
			} else {
				queryData(clean);
			}

		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		}
	}

	/**
	 * 左右切换fragment的时候执行的生命周期
	 * 
	 * @param hidden
	 *            当为false的时候，说明当前的界面在前台战士
	 */
	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		if (!hidden) {
			// 当回到当前页面判断换从中的城市是否跟之前进来的一致不，不一致则按新的城市名字请求数据

		}
	}

}
