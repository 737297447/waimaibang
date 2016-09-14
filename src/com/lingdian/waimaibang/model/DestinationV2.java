package com.lingdian.waimaibang.model;

import java.io.Serializable;
import java.util.List;

public class DestinationV2 implements Serializable{
	private int id;
	private String name;
	private double latitude;
	private double longitude;
	private int products_count;
	private Photo cover;
	private List<TagType> tag_types;
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

	public Photo getCover() {
		return cover;
	}

	public void setCover(Photo cover) {
		this.cover = cover;
	}

	public List<TagType> getTag_types() {
		return tag_types;
	}

	public void setTag_types(List<TagType> tag_types) {
		this.tag_types = tag_types;
	}

	public DestinationDetail getDestination_detail() {
		return destination_detail;
	}

	public void setDestination_detail(DestinationDetail destination_detail) {
		this.destination_detail = destination_detail;
	}

}
