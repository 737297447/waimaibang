package com.lingdian.waimaibang.api;

import android.view.KeyEvent;

/**
 * 对软键盘事件进行处理的策略接口 需要自行对软键盘事件进行处理的fragment需要实现此接口
 * 
 * @author 
 */
public interface IKeyEventStrategy {
	/**
	 * 
	 * @return true：吃掉事件,不交给上层处理。false：未处理返回事件,上交给上传处理
	 */
	public boolean eventOperate(KeyEvent event);
}