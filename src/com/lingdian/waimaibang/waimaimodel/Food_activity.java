package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;

public class Food_activity implements Serializable{

	private int id;
	private String name;
	private String icon_name;
	private String description;
	private int must_pay_online;
	private String image_text;
	private String image_text_color;
	private String fixed_price;
	private int must_new_user;
	private Limitation limitation;
	private String icon_color;
	private String tips;
	private int is_need_filling;
	private int is_multi_choice;
	private String filter_key;
	private int filter_value;
	private boolean is_price_changed;
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
	public String getIcon_name() {
		return icon_name;
	}
	public void setIcon_name(String icon_name) {
		this.icon_name = icon_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMust_pay_online() {
		return must_pay_online;
	}
	public void setMust_pay_online(int must_pay_online) {
		this.must_pay_online = must_pay_online;
	}
	public String getImage_text() {
		return image_text;
	}
	public void setImage_text(String image_text) {
		this.image_text = image_text;
	}
	public String getImage_text_color() {
		return image_text_color;
	}
	public void setImage_text_color(String image_text_color) {
		this.image_text_color = image_text_color;
	}
	public String getFixed_price() {
		return fixed_price;
	}
	public void setFixed_price(String fixed_price) {
		this.fixed_price = fixed_price;
	}
	public int getMust_new_user() {
		return must_new_user;
	}
	public void setMust_new_user(int must_new_user) {
		this.must_new_user = must_new_user;
	}
	public Limitation getLimitation() {
		return limitation;
	}
	public void setLimitation(Limitation limitation) {
		this.limitation = limitation;
	}
	public String getIcon_color() {
		return icon_color;
	}
	public void setIcon_color(String icon_color) {
		this.icon_color = icon_color;
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
	public boolean isIs_price_changed() {
		return is_price_changed;
	}
	public void setIs_price_changed(boolean is_price_changed) {
		this.is_price_changed = is_price_changed;
	}
	
	
	
	
}
