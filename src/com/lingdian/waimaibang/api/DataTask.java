package com.lingdian.waimaibang.api;

/**
 * 拥有id的任务
 * 
 * @author 
 */
public abstract class DataTask {

	protected int taskID;

	public DataTask(int id) {
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
	
	public void run() {
	
	}
	
}
