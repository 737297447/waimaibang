package com.lingdian.waimaibang.api;

/**
 * 网络异常重试网络操作接口
 * 
 * @author 
 * 
 */
public interface RetryNetwork {
	/**
	 * 重试需要的网络操作
	 */
	void retry();

	/**
	 * 断网通知
	 */
	void netError();

}