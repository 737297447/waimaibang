package com.lingdian.waimaibang.model;

public class XiaoxiModel extends SuperBean{

	private int id;
	private String title;
	private String content_sanitize;
	private String edited_date;
	private boolean isRead = false;
	
	
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent_sanitize() {
		return content_sanitize;
	}
	public void setContent_sanitize(String content_sanitize) {
		this.content_sanitize = content_sanitize;
	}
	public String getEdited_date() {
		return edited_date;
	}
	public void setEdited_date(String edited_date) {
		this.edited_date = edited_date;
	}
     
	
}
