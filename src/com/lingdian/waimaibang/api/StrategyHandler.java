package com.lingdian.waimaibang.api;


/**
 * 用于实施策略的锦囊 
 * @author 
 *
 */
public abstract class StrategyHandler {

	protected IStrategy mStrategy;

	/**
	 * 构造函数,要实施哪个策略
	 * 
	 * @param strategy
	 */
	public StrategyHandler(IStrategy strategy) {
		this.mStrategy = strategy;
	}
	
	/**
	 * 实施策略
	 */
	public void operate() {
		this.mStrategy.operate();
	}
	
}
