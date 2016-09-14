package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class TopDestinationV2  implements Serializable{
	private int id;
	private String name;
	private String latitude;
	private String longitude;
	private int short_articles_count;
	private int products_count;
	private City top_parent;

	public int getShort_articles_count() {
		return short_articles_count;
	}

	public void setShort_articles_count(int short_articles_count) {
		this.short_articles_count = short_articles_count;
	}

	public City getTop_parent() {
		return top_parent;
	}

	public void setTop_parent(City top_parent) {
		this.top_parent = top_parent;
	}

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

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getProducts_count() {
		return products_count;
	}

	public void setProducts_count(int products_count) {
		this.products_count = products_count;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Destination [id=" + id + ", name=" + name + "]";
	}
}
