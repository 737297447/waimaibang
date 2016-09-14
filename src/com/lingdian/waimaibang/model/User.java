package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class User extends SuperBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7252795569588474826L;
	private int id;
	private String nickname;
	private Photo photo;
	private int level;
	private int sex;
	private String intro;
	private String tags_str;
	
	
	
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

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	


	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTags_str() {
		return tags_str;
	}

	public void setTags_str(String tags_str) {
		this.tags_str = tags_str;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "User [id=" + id + ", nickname=" + nickname + "]";
	}
}
