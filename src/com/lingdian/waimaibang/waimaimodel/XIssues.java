package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;
import java.util.List;

public class XIssues implements Serializable{
	
	private String items_count;
	private String title;
	private List<XItems> items;
	private String issue_id;
	private String publish_date;
	private List<XToday_events> today_events;
	public String getItems_count() {
		return items_count;
	}
	public void setItems_count(String items_count) {
		this.items_count = items_count;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<XItems> getItems() {
		return items;
	}
	public void setItems(List<XItems> items) {
		this.items = items;
	}
	public String getIssue_id() {
		return issue_id;
	}
	public void setIssue_id(String issue_id) {
		this.issue_id = issue_id;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public List<XToday_events> getToday_events() {
		return today_events;
	}
	public void setToday_events(List<XToday_events> today_events) {
		this.today_events = today_events;
	}
	
	

}
