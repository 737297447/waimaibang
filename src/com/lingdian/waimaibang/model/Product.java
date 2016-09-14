package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class Product  implements Serializable{
	private int id;
	private String title;
	private int show_price;
	private Photo photo;
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
	public int getShow_price() {
		return show_price;
	}
	public void setShow_price(int show_price) {
		this.show_price = show_price;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
}
