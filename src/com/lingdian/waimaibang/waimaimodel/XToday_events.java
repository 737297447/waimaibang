package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;
import java.util.List;

public class XToday_events implements Serializable{
	
	private String n_dishes;
	private String id;
	private XDishes1 dishes;
	private String name;
	public String getN_dishes() {
		return n_dishes;
	}
	public void setN_dishes(String n_dishes) {
		this.n_dishes = n_dishes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public XDishes1 getDishes() {
		return dishes;
	}
	public void setDishes(XDishes1 dishes) {
		this.dishes = dishes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
