package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class Upvote  implements Serializable{
	private int id;
	private String machine_id;
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Upvote [id=" + id + "]";
	}
	
}
