package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;
import java.util.List;

public class BaiduResult implements Serializable{
	
	private int total;
	private int page_num;
	private List<BaiduContent> content;
	private String url;
	private int city_id;
	private String name;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage_num() {
		return page_num;
	}
	public void setPage_num(int page_num) {
		this.page_num = page_num;
	}
	public List<BaiduContent> getContent() {
		return content;
	}
	public void setContent(List<BaiduContent> content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
