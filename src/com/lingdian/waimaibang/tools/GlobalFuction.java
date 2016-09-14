package com.lingdian.waimaibang.tools;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;

public class GlobalFuction {
	private static final double EARTH_RADIUS = 6378137.0D;
	private static final String[] mMonthDisplayName = { "January", "February",
			"March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };
	public static String[] strWeekDay = { "日", "一", "二", "三", "四", "五", "六" };

	/**
	 * dip 转px px 转dip
	 * 
	 * @return
	 */
	public static int dip2px(Context context, double oneCenterWidth) {
		final double scale = context.getResources().getDisplayMetrics().density;
		return (int) (oneCenterWidth * scale + 0.5f);
	}

	public static String formateHourMinuteTimeDisplay(String paramString) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		try {
			Date localDate = localSimpleDateFormat.parse(paramString);
			String str = new SimpleDateFormat("HH:mm").format(localDate);
			return str;
		} catch (ParseException localParseException) {
			localParseException.printStackTrace();
		}
		return "";
	}

	public static int getScreenMaxHeight(Context paramContext, int paramInt) {
		Object localObject = new DisplayMetrics();
		try {
			DisplayMetrics localDisplayMetrics = paramContext
					.getApplicationContext().getResources().getDisplayMetrics();
			localObject = localDisplayMetrics;
			return ((DisplayMetrics) localObject).heightPixels
					- dip2px(paramContext, paramInt);
		} catch (Exception localException) {
			while (true) {
				localException.printStackTrace();
				((DisplayMetrics) localObject).heightPixels = 960;
			}
		}
	}

	public static int getScreenMaxWidth(Context paramContext, int paramInt) {
		Object localObject = new DisplayMetrics();
		try {
			DisplayMetrics localDisplayMetrics = paramContext
					.getApplicationContext().getResources().getDisplayMetrics();
			localObject = localDisplayMetrics;
			return ((DisplayMetrics) localObject).widthPixels
					- dip2px(paramContext, paramInt);
		} catch (Exception localException) {
			while (true) {
				localException.printStackTrace();
				((DisplayMetrics) localObject).widthPixels = 640;
			}
		}
	}

	/** 获取状态栏的高度 */
	public static int getStatusHeight(Activity paramActivity) {
		Rect localRect = new Rect();
		paramActivity.getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(localRect);
		int i = localRect.top;
		if (i == 0)
			;
		try {
			Class localClass = Class.forName("com.android.internal.R$dimen");
			Object localObject = localClass.newInstance();
			int j = Integer.parseInt(localClass.getField("status_bar_height")
					.get(localObject).toString());
			int k = paramActivity.getResources().getDimensionPixelSize(j);
			i = k;
			return i;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return i;
	}
	
	
	private final static int UPPER_LEFT_X = 0;
	private final static int UPPER_LEFT_Y = 0;

	public static Drawable convertViewToDrawable(View view) {
		int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		view.measure(spec, spec);
		view.layout(UPPER_LEFT_X, UPPER_LEFT_Y, view.getMeasuredWidth(), view.getMeasuredHeight());
		Bitmap b = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(b);
		c.translate(-view.getScrollX(), -view.getScrollY());
		view.draw(c);
		view.setDrawingCacheEnabled(true);
		Bitmap cacheBmp = view.getDrawingCache();
		Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
		cacheBmp.recycle();
		view.destroyDrawingCache();
		return new BitmapDrawable(viewBmp);
	}

}