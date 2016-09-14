package com.lingdian.waimaibang.model;

/**
 * 点赞的信息
 * @author 1818
 *
 */
public class ZanModel extends SuperBean{

	private int error;
	private Up up;
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public Up getUp() {
		return up;
	}
	public void setUp(Up up) {
		this.up = up;
	}
	
}
