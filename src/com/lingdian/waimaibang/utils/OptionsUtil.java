package com.lingdian.waimaibang.utils;

import android.graphics.Bitmap;

import com.lingdian.waimaibang.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public class OptionsUtil {

	private static DisplayImageOptions PicNormal = null;

	/** 图片未加载图像 */
	public static DisplayImageOptions PicNormal() {
		if (PicNormal == null) {
			PicNormal = new DisplayImageOptions.Builder()
					.showStubImage(R.color.ECECEC) // 设置图片下载期间显示的图片
					.showImageForEmptyUri(R.color.ECECEC) // 设置图片Uri为空或是错误的时候显示的图片
					.showImageOnFail(R.color.ECECEC) // 设置图片加载或解码过程中发生错误显示的图片
					.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
					.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
					.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// 降低图像质量
					.bitmapConfig(Bitmap.Config.RGB_565).build(); // 创建配置过得DisplayImageOption对象
		}
		return PicNormal;
	}

	/** 目的地图片未加载图像 */
	public static DisplayImageOptions PicMudidiNormal() {
		if (PicNormal == null) {
			PicNormal = new DisplayImageOptions.Builder()
					.showStubImage(R.drawable.camerasdk_pic_loading) // 设置图片下载期间显示的图片
					.showImageForEmptyUri(R.drawable.camerasdk_pic_loading) // 设置图片Uri为空或是错误的时候显示的图片
					.showImageOnFail(R.drawable.camerasdk_pic_loading) // 设置图片加载或解码过程中发生错误显示的图片
					.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
					.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
					.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// 降低图像质量
					.bitmapConfig(Bitmap.Config.RGB_565).build(); // 创建配置过得DisplayImageOption对象
		}
		return PicNormal;
	}

	/** 相册图片未加载图像 */
	public static DisplayImageOptions PicCamera() {
		if (PicNormal == null) {
			PicNormal = new DisplayImageOptions.Builder()
					.showStubImage(R.drawable.camerasdk_pic_loading) // 设置图片下载期间显示的图片
					.showImageForEmptyUri(R.drawable.camerasdk_pic_loading) // 设置图片Uri为空或是错误的时候显示的图片
					.showImageOnFail(R.drawable.camerasdk_pic_loading) // 设置图片加载或解码过程中发生错误显示的图片
					.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
					.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
					.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// 降低图像质量
					.bitmapConfig(Bitmap.Config.RGB_565).build(); // 创建配置过得DisplayImageOption对象
		}
		return PicNormal;
	}

	/** 相册图片未加载图像 */
	public static DisplayImageOptions PicCamera1() {
		if (PicNormal == null) {
			PicNormal = new DisplayImageOptions.Builder()
					.showImageOnLoading(R.drawable.camerasdk_pic_loading)
					.showImageForEmptyUri(R.drawable.camerasdk_pic_loading)
					.showImageOnFail(R.drawable.camerasdk_pic_loading)
					.resetViewBeforeLoading(true).cacheOnDisc(true)
					.cacheInMemory(true).cacheOnDisc(true)
					.imageScaleType(ImageScaleType.EXACTLY)
					.bitmapConfig(Bitmap.Config.RGB_565)
					.considerExifParams(true)
					.displayer(new SimpleBitmapDisplayer()).build();
		}
		return PicNormal;
	}

	
	/** 广告 */
	public static DisplayImageOptions PicAds() {
		if (PicNormal == null) {
			PicNormal = new DisplayImageOptions.Builder()
					.showStubImage(R.drawable.waimaibang_moren_pic) // 设置图片下载期间显示的图片
					.showImageForEmptyUri(R.drawable.waimaibang_moren_pic) // 设置图片Uri为空或是错误的时候显示的图片
					.showImageOnFail(R.drawable.waimaibang_moren_pic) // 设置图片加载或解码过程中发生错误显示的图片
					.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
					.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
					.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// 降低图像质量
					.bitmapConfig(Bitmap.Config.RGB_565).build(); // 创建配置过得DisplayImageOption对象
		}
		return PicNormal;
	}
	
}
