package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;

public class Position implements Serializable{
	
	private String name;
	private String address;
	private double latitude;
	private double longitude;
	private String geohash;
	private String ad_info;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getGeohash() {
		return geohash;
	}
	public void setGeohash(String geohash) {
		this.geohash = geohash;
	}
	public String getAd_info() {
		return ad_info;
	}
	public void setAd_info(String ad_info) {
		this.ad_info = ad_info;
	}
	
	

}
