package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class RelateDestination implements Serializable {
	private int id;
	private String name;
	private float latitude;
	private float longitude;
	private int products_count;
	private int short_articles_count;
	private String tags_str;

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

	public int getShort_articles_count() {
		return short_articles_count;
	}

	public void setShort_articles_count(int short_articles_count) {
		this.short_articles_count = short_articles_count;
	}

	public String getTags_str() {
		return tags_str;
	}

	public void setTags_str(String tags_str) {
		this.tags_str = tags_str;
	}

}
