package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class Destination  extends SuperBean{
	private int id;
	private String name;
	private double latitude;
	private double longitude;
	private int products_count;
	private boolean is_viewpoint;
	private int articles_count;
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

	public boolean isIs_viewpoint() {
		return is_viewpoint;
	}

	public void setIs_viewpoint(boolean is_viewpoint) {
		this.is_viewpoint = is_viewpoint;
	}

	public int getArticles_count() {
		return articles_count;
	}

	public void setArticles_count(int articles_count) {
		this.articles_count = articles_count;
	}

	public DestinationDetail getDestination_detail() {
		return destination_detail;
	}

	public void setDestination_detail(DestinationDetail destination_detail) {
		this.destination_detail = destination_detail;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Destination [id=" + id + ", name=" + name + "]";
	}
}
