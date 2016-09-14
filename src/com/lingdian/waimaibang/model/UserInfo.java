package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class UserInfo implements Serializable {
	private int id;
	private String nickname;
	private String intro;
	private int sex;
	private int level;
	private String tags_str;
	private Photo cover;
	private Photo photo;
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
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getTags_str() {
		return tags_str;
	}
	public void setTags_str(String tags_str) {
		this.tags_str = tags_str;
	}
	public Photo getCover() {
		return cover;
	}
	public void setCover(Photo cover) {
		this.cover = cover;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "User [id=" + id + ", nickname=" + nickname + "]";
	}
	
}
