package com.lingdian.waimaibang.waimaimodel;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Stores extends BmobObject{
	
	private String area;
	private String category;
	private String name;
	
	private String diming1;
	private String diming2;
	private String jianjie;
	private String yingyeTime;
	private String tel;
	private String tuijian;
	private String latitude;
	private String longitude;
	
	
	private BmobFile img;
	private String imgPath;
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiming1() {
		return diming1;
	}
	public void setDiming1(String diming1) {
		this.diming1 = diming1;
	}
	public String getDiming2() {
		return diming2;
	}
	public void setDiming2(String diming2) {
		this.diming2 = diming2;
	}
	public String getJianjie() {
		return jianjie;
	}
	public void setJianjie(String jianjie) {
		this.jianjie = jianjie;
	}
	public String getYingyeTime() {
		return yingyeTime;
	}
	public void setYingyeTime(String yingyeTime) {
		this.yingyeTime = yingyeTime;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTuijian() {
		return tuijian;
	}
	public void setTuijian(String tuijian) {
		this.tuijian = tuijian;
	}
	public BmobFile getImg() {
		return img;
	}
	public void setImg(BmobFile img) {
		this.img = img;
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
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	

}
