package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;
import java.util.List;

public class XContact implements Serializable{
	
	private String count;
	private List<XIssues> issues;
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public List<XIssues> getIssues() {
		return issues;
	}
	public void setIssues(List<XIssues> issues) {
		this.issues = issues;
	}
	

}
