package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class TagType  implements Serializable{
	private int id;
	private int tags_count;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTags_count() {
		return tags_count;
	}
	public void setTags_count(int tags_count) {
		this.tags_count = tags_count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	private String name;
	private String tags;
	private String created_at;
	private String updated_at;
	
}
