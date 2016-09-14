package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class City  implements Serializable{
	private int id;
	private String name;

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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "City [id=" + id + ", name=" + name + "]";
	}
}
