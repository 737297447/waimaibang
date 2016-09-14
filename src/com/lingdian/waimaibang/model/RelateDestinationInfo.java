package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class RelateDestinationInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1903738189716033346L;
	private int id;
	private String name;
	private float latitude;
	private float longitude;
	private int products_count;
	private Photo cover;
	private DestinationDetail destination_detail;
	
	
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
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
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
	public DestinationDetail getDestination_detail() {
		return destination_detail;
	}
	public void setDestination_detail(DestinationDetail destination_detail) {
		this.destination_detail = destination_detail;
	}
	
}
