package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;
import java.util.List;

public class XItems implements Serializable{
	
	private String publish_time;
	private String url;
	private String template;
	private String id;
	private XContents contents;
	private String column_name;
	public String getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public XContents getContents() {
		return contents;
	}
	public void setContents(XContents contents) {
		this.contents = contents;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	

}
