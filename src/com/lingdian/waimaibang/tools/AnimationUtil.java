package com.lingdian.waimaibang.tools;


import android.app.Activity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.lingdian.waimaibang.R;

/***
 * 动画工具类
 * 
 * @author daijy
 * 
 */
public class AnimationUtil {
	/** 控制动画是否打开 */
	private static boolean isAnimationOpen = true;

	/** 从下往上 */
	public static void lowerIn(Activity a) {
		if (isAnimationOpen == true) {
			a.overridePendingTransition(R.anim.push_lower_in, R.anim.push_lower);
		}
	}

	/** 从上往下退出 */
	public static void lowerOut(Activity a) {
		if (isAnimationOpen == true) {
			a.overridePendingTransition(R.anim.push_lower, R.anim.push_lower_out);
		}
	}

	/** 左右滑动 */
	public static void rightIn(Activity a) {
		if (isAnimationOpen == true) {
			a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
		}
	}

	/** 左右滑动 */
	public static void LeftIn(Activity a) {
		if (isAnimationOpen == true) {
			a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
		}
	}

	public static void scale(Activity a) {
		if (isAnimationOpen == true) {
			a.overridePendingTransition(R.anim.scale_action, R.anim.alpha_action);
		}
	}

	public static void scaleLogin(Activity a) {
		if (isAnimationOpen == true) {
			a.overridePendingTransition(R.anim.scale_action_login, R.anim.alpha_action);
		}
	}
	
	public static void scaleLogin2(Activity a) {
		if (isAnimationOpen == true) {
			a.overridePendingTransition(R.anim.scale_action_login2, R.anim.alpha_action);
		}
	}

	public static void scaleLogin3(Activity a) {
		if (isAnimationOpen == true) {
			a.overridePendingTransition(R.anim.alpha_action, R.anim.scale_action_login2);
		}
	}
	
	/**界面透明切换*/
	public static void scaleLogin4(Activity a) {
		if (isAnimationOpen == true) {
			a.overridePendingTransition(R.anim.scale_action_login4_in, R.anim.scale_action_login4_out);
		}
	}

	/** 从自己的上方，从下往上 */
	public static void TransUpIn(View v, AnimationListener listener, long durationMillis) {
		TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0);
		animation.setDuration(durationMillis);
		if (listener != null) {
			animation.setAnimationListener(listener);
		}
		v.startAnimation(animation);
	}

	/** 从自己开始从下往上 */
	public static void TransUpOut(View v, AnimationListener listener, long durationMillis) {
		TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1.0f);
		animation.setDuration(durationMillis);
		if (listener != null) {
			animation.setAnimationListener(listener);
		}
		v.startAnimation(animation);
	}

	/** 从自己的开始，从上往下 */
	public static void TransUpInOfLower(View v, AnimationListener listener, long durationMillis) {
		TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1.0f);
		animation.setDuration(durationMillis);
		if (listener != null) {
			animation.setAnimationListener(listener);
		}
		v.startAnimation(animation);
	}

	/** 从自己的下方 从下往上 */
	public static void TransUpOutOfLower(View v, AnimationListener listener, long durationMillis) {
		TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0);
		animation.setDuration(durationMillis);
		if (listener != null) {
			animation.setAnimationListener(listener);
		}
		v.startAnimation(animation);
	}

	/** 从自己的下方 从下往上 */
	public static void TransUpOutOfLower(View v, AnimationListener listener, long durationMillis, float toXValue) {
		TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, toXValue, Animation.RELATIVE_TO_SELF, 0);
		animation.setDuration(durationMillis);
		if (listener != null) {
			animation.setAnimationListener(listener);
		}
		v.startAnimation(animation);
	}

	/** 旋转动画，以自己的中心为参照 */
	public static void rotateAnimation(View v, float fromDegrees, float toDegrees, long durationMillis, boolean fillAfter) {
		RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(durationMillis);
		animation.setFillAfter(fillAfter);
		animation.setInterpolator(new AccelerateDecelerateInterpolator());
		animation.setRepeatMode(Animation.REVERSE);
		v.startAnimation(animation);
	}

	/**
	 * AccelerateDecelerateInterpolator 在动画开始与结束的地方速率改变比较慢，在中间的时候加速
	 * AccelerateInterpolator 在动画开始的地方速率改变比较慢，然后开始加速 AnticipateInterpolator
	 * 开始的时候向后然后向前甩 AnticipateOvershootInterpolator 开始的时候向后然后向前甩一定值后返回最后的值
	 * BounceInterpolator 动画结束的时候弹起 CycleInterpolator 动画循环播放特定的次数，速率改变沿着正弦曲线
	 * DecelerateInterpolator 在动画开始的地方快然后慢 LinearInterpolator 以常量速率改变
	 * OvershootInterpolator 向前甩一定值后再回到原来位置
	 * 如果android定义的interpolators不符合你的效果也可以自定义interpolators 平移动画 相对于自己,X 轴不变，Y轴不变
	 */
	public static void transyAnimation(View v, float fromYValue, float toYValue, long durationMillis, boolean fillAfter, Interpolator i, AnimationListener listener) {
		TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, fromYValue, Animation.RELATIVE_TO_SELF,
				toYValue);
		animation.setDuration(durationMillis);
		animation.setFillAfter(fillAfter);
		if (i != null) {
			animation.setInterpolator(i);
		}
		if (listener != null) {
			animation.setAnimationListener(listener);
		}
		animation.setRepeatMode(Animation.REVERSE);
		v.startAnimation(animation);
	}

	public static void transxAnimation(View v, float fromxalue, float toxValue, long durationMillis, boolean fillAfter, Interpolator i, AnimationListener listener) {
		TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, fromxalue, Animation.RELATIVE_TO_SELF, toxValue, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(durationMillis);
		animation.setFillAfter(fillAfter);
		if (i != null) {
			animation.setInterpolator(i);
		}
		if (listener != null) {
			animation.setAnimationListener(listener);
		}
		animation.setRepeatMode(Animation.REVERSE);
		v.startAnimation(animation);
	}

	/**
	 * the animation. 透明度变化
	 * @param fromAlpha Starting alpha value for the animation, where 1.0 means fully
	 * opaque and 0.0 means fully transparent.  
	 * @param toAlpha Ending alpha value for
	 * @param view
	 */
	public static void alphaAnimation(View v, float fromAlpha, float toAlpha, long durationMillis, boolean fillAfter) {
		AlphaAnimation animation = new AlphaAnimation(fromAlpha, toAlpha);
		animation.setDuration(durationMillis);
		animation.setFillAfter(fillAfter);
		animation.setRepeatMode(Animation.REVERSE);
		v.startAnimation(animation);
	}
	/**缩放动画*/
	public static void scaleAnimation(View v,AnimationListener listener){
		ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(800);
		animation.setRepeatCount(2);
		animation.setInterpolator(new BounceInterpolator());
		animation.setRepeatMode(Animation.REVERSE);
		animation.setAnimationListener(listener);
		v.startAnimation(animation);
	}
	/**Y轴缩放动画*/
	public static void scaleYAnimation(View v, float fromYalue, float toYValue, long durationMillis, boolean fillAfter, Interpolator i, AnimationListener listener){
		ScaleAnimation animation = new ScaleAnimation(1.0f, 1.0f, fromYalue, toYValue, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(durationMillis);
		if(i!=null){
			animation.setInterpolator(i);
		}
		animation.setFillAfter(fillAfter);
		animation.setRepeatMode(Animation.REVERSE);
		if(listener!=null){
			animation.setAnimationListener(listener);
		}
		v.startAnimation(animation);
	}
}
