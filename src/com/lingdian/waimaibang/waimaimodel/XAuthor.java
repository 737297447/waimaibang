package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;

public class XAuthor implements Serializable{
	
	private String url;
	private String photo;
	private String id;
	private String name;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
