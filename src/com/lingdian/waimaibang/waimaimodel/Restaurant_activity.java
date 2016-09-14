package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;

public class Restaurant_activity implements Serializable{
	
	private int id;
	private int type;
	private String attribute;
	private String description;
	private String name;
	private String icon_color;
	private String icon_name;
	private String tips;
	private int is_need_filling;
	private int is_multi_choice;
	private String filter_key;
	private int filter_value;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public int getIs_need_filling() {
		return is_need_filling;
	}
	public void setIs_need_filling(int is_need_filling) {
		this.is_need_filling = is_need_filling;
	}
	public int getIs_multi_choice() {
		return is_multi_choice;
	}
	public void setIs_multi_choice(int is_multi_choice) {
		this.is_multi_choice = is_multi_choice;
	}
	public String getFilter_key() {
		return filter_key;
	}
	public void setFilter_key(String filter_key) {
		this.filter_key = filter_key;
	}
	public int getFilter_value() {
		return filter_value;
	}
	public void setFilter_value(int filter_value) {
		this.filter_value = filter_value;
	}
	
	

}
