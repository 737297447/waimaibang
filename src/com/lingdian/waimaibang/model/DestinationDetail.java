package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class DestinationDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7215745272958989445L;
	
	private String description;
	private String full_address;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFull_address() {
		return full_address;
	}

	public void setFull_address(String full_address) {
		this.full_address = full_address;
	}

}
