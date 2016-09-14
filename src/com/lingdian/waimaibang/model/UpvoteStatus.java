package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class UpvoteStatus  implements Serializable{
	private int error;
	private Upvote up;
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public Upvote getUp() {
		return up;
	}
	public void setUp(Upvote up) {
		this.up = up;
	}
	
}
