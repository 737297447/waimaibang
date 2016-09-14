package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;

public class Support implements Serializable{
	
	private int id;
	private String name;
	private String description;
	private String icon_color;
	private String icon_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon_color() {
		return icon_color;
	}
	public void setIcon_color(String icon_color) {
		this.icon_color = icon_color;
	}
	public String getIcon_name() {
		return icon_name;
	}
	public void setIcon_name(String icon_name) {
		this.icon_name = icon_name;
	}
	

}
