package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class WantDestination implements Serializable {
	private int id;
	private String name;
	private float latitude;
	private float longitude;
	private int products_count;
	private int short_articles_count;
	private String tags_str;
	private Photo cover;
	private DestinationDetail destination_detail;

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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Destination [id=" + id + ", name=" + name + "]";
	}
}
