package com.lingdian.waimaibang.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * ScrollView 和 ListView 或ExpandableListView显示冲突问题
 * 
 * @author
 */
public class CustomListView extends ListView {

	public CustomListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomListView(Context context) {
		super(context);
	}

	public CustomListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
