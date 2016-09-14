package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;

public class Limitation implements Serializable{
	
	private String text;
	private String color;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	

}
