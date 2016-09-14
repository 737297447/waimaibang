package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;

public class BaiduContent implements Serializable{
	
	private String address;
	private String uid;
	private String name;
	private String longitude;
	private String latitude;
	private int shopnum;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public int getShopnum() {
		return shopnum;
	}
	public void setShopnum(int shopnum) {
		this.shopnum = shopnum;
	}
	
}
