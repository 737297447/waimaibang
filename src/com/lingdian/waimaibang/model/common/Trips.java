package com.lingdian.waimaibang.model.common;

import java.util.List;

import com.lingdian.waimaibang.model.Destination;
import com.lingdian.waimaibang.model.SuperBean;

public class Trips extends SuperBean{

	private int id;
	private int day;
	private List<Destination> destinations;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public List<Destination> getDestinations() {
		return destinations;
	}
	public void setDestinations(List<Destination> destinations) {
		this.destinations = destinations;
	}
	
	
}
