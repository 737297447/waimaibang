package com.lingdian.waimaibang.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
/**
 * scrollview没有冲突
 * @author daijy
 *
 */
public class HarmoniousGridView extends GridView {
	public HarmoniousGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HarmoniousGridView(Context context) {
		super(context);
	}

	public HarmoniousGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	
}
