package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;

public class BaiduPosition implements Serializable{
	
	private int error_no;
	private String error_msg;
	private BaiduResult result;
	public int getError_no() {
		return error_no;
	}
	public void setError_no(int error_no) {
		this.error_no = error_no;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public BaiduResult getResult() {
		return result;
	}
	public void setResult(BaiduResult result) {
		this.result = result;
	}
	

}
