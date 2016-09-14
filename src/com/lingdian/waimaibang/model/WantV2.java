package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class WantV2 implements Serializable {
	private int id;
	private int destination_id;
	private WantDestination destination;
	private String machine_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDestination_id() {
		return destination_id;
	}

	public void setDestination_id(int destination_id) {
		this.destination_id = destination_id;
	}

	public WantDestination getDestination() {
		return destination;
	}

	public void setDestination(WantDestination destination) {
		this.destination = destination;
	}

	public String getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
	}

}
