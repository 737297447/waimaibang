package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;

public class XiachufangModel implements Serializable{
	
	private String status;
	private XContact content;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public XContact getContent() {
		return content;
	}
	public void setContent(XContact content) {
		this.content = content;
	}
	

}
