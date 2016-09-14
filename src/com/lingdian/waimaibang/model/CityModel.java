package com.lingdian.waimaibang.model;

public class CityModel {

	public String name;
	public String pinyi;

	public CityModel(String name, String pinyi) {
		super();
		this.name = name;
		this.pinyi = pinyi;
	}

	public CityModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyi() {
		return pinyi;
	}

	public void setPinyi(String pinyi) {
		this.pinyi = pinyi;
	}

}
