package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;

public class Ads implements Serializable{
	
	private int id;
	private String image_path;
	private String link;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	

}
