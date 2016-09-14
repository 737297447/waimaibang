package com.lingdian.waimaibang.model;

import com.lingdian.waimaibang.model.common.Destination;

public class CreateDestionModel extends SuperBean{

	private int error;
	private Destination destination;
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	
}
