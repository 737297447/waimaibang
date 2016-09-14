package com.lingdian.waimaibang.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewPagerAdapter extends PagerAdapter {
	private List<View> views;
	private Context context;

	public ViewPagerAdapter(List<View> views, Context context) {
		this.views = views;
		this.context = context;
	}

	// ��view����Ҫʱ��������
	@Override
	public void destroyItem(View container, int position, Object object) {
		// removeView�����Ƴ�view
		((ViewPager) container).removeView(views.get(position));
	}

	// ����instantiateItem����������BaseAdapter�����getView����
	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(views.get(position));
		return views.get(position);
	}

	// ����view��ǰ������
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return views.size();
	}

	// �жϵ�ǰ��view�ǲ�����Ҫ��һ������
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return (arg0 == arg1);
	}
}
