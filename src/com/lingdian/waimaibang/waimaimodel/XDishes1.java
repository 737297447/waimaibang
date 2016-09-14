package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;
import java.util.List;

public class XDishes1 implements Serializable{
	
	private List<XDishes2> dishes;

	public List<XDishes2> getDishes() {
		return dishes;
	}

	public void setDishes(List<XDishes2> dishes) {
		this.dishes = dishes;
	}
	

}
