package com.lingdian.waimaibang;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.tencent.bugly.crashreport.CrashReport;

public class MyApplication extends Application {

	public static String TAG;

	private static MyApplication myApplication = null;

	private ExecutorService singleThreadExecutor;

	public ImageLoader imageLoader;

	private static Context mContext;

	public static Context getContextObject() {
		return mContext;
	}

	public static MyApplication getInstance() {
		return myApplication;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mContext = getApplicationContext();
		CrashReport.initCrashReport(mContext, "900011361", false);
		TAG = this.getClass().getSimpleName();
		// 由于Application类本身已经单例，所以直接按以下处理即可。
		myApplication = this;
		singleThreadExecutor = Executors.newFixedThreadPool(1);
		initImageLoader();
	}

	public static MyApplication getApp() {
		return myApplication;
	}

	/**
	 * 获取线程队列对象
	 * 
	 * @return
	 */
	public ExecutorService getSignalThread() {
		return singleThreadExecutor;
	}

	/**
	 * 初始化imageLoader
	 */
	public void initImageLoader() {
		File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.memoryCache(new LruMemoryCache(5 * 1024 * 1024))
				.memoryCacheSize(10 * 1024 * 1024)
				.discCache(new UnlimitedDiscCache(cacheDir))
				.discCacheFileNameGenerator(new HashCodeFileNameGenerator())
				.build();
		ImageLoader.getInstance().init(config);
	}

	public DisplayImageOptions getOptions(int drawableId) {
		return new DisplayImageOptions.Builder().showImageOnLoading(drawableId)
				.showImageForEmptyUri(drawableId).showImageOnFail(drawableId)
				.resetViewBeforeLoading(true).cacheInMemory(true)
				.cacheOnDisc(true).imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	// 获取图片处理对象
	public ImageLoader getImageLoader() {
		if (imageLoader == null) {
			imageLoader = ImageLoader.getInstance();
		}
		return imageLoader;
	}

	// 释放图片占用内存
	public void recyleImageLoader() {
		if (imageLoader == null) {
			imageLoader.clearMemoryCache();
		}
	}
}
