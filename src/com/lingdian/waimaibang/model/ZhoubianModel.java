package com.lingdian.waimaibang.model;

public class ZhoubianModel {
	/** 图片url*/
	private String imgUrl;
	/** 地名*/
	private String localName;
	/** 详细地址*/
	private String address;
	/** 适合种类，3中*/
	private String zhonglei;
	/** 标签：文艺、小清新等*/
	private String tag;
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZhonglei() {
		return zhonglei;
	}
	public void setZhonglei(String zhonglei) {
		this.zhonglei = zhonglei;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	

}
