package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class WantStatus implements Serializable {
	private int error;
	private Want want;
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public Want getWant() {
		return want;
	}
	public void setWant(Want want) {
		this.want = want;
	}
}
