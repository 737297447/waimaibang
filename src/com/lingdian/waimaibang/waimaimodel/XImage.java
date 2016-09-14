package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;

public class XImage implements Serializable{

	private String url;
	private String width;
	private String height;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	
}
