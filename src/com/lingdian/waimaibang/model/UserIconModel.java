package com.lingdian.waimaibang.model;

public class UserIconModel extends SuperBean{

	private int error;
	private Photo photo;
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
}
