package com.lingdian.waimaibang.api;

/**
 * 拥有id的任务
 * 
 * @author 
 */
public abstract class Task implements Runnable {

	protected int taskID;

	public Task(int id) {
		this.taskID = id;
	}

	/**
	 * 得到任务ID
	 * 
	 * @return
	 */
	public int getID() {
		return taskID;
	}

}
