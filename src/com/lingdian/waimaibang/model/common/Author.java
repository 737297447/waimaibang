package com.lingdian.waimaibang.model.common;

import com.lingdian.waimaibang.model.SuperBean;

public class Author extends SuperBean{

	private int id;
	private String name;
	/** 别名，个性名称*/
	private String nickname;
	private Photos photo;
	private int level;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Photos getPhoto() {
		return photo;
	}
	public void setPhoto(Photos photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
