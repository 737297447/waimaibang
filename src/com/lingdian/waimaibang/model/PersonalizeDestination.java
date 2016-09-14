package com.lingdian.waimaibang.model;

import java.io.Serializable;
import java.util.List;

public class PersonalizeDestination implements Serializable{
	private int id;
	private String name;
	private double latitude;
	private double longitude;
	private int products_count;
	private Photo cover;
	
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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getProducts_count() {
		return products_count;
	}

	public void setProducts_count(int products_count) {
		this.products_count = products_count;
	}

	public Photo getCover() {
		return cover;
	}

	public void setCover(Photo cover) {
		this.cover = cover;
	}
}
