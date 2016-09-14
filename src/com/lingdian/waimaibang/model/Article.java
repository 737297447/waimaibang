package com.lingdian.waimaibang.model;

import java.io.Serializable;
import java.util.List;

public class Article  implements Serializable{
	private int id; // 主题ID
	private String title; // 主题内容
	private String tags_str; // 标签组 例如：tags_str=单身 浪漫 清净 文艺 户外
	private Photo cover;
	private Destination destination;
	private boolean essence;
	private User author;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTags_str() {
		return tags_str;
	}

	public void setTags_str(String tags_str) {
		this.tags_str = tags_str;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Photo getCover() {
		return cover;
	}

	public void setCover(Photo cover) {
		this.cover = cover;
	}

	public boolean isEssence() {
		return essence;
	}

	public void setEssence(boolean essence) {
		this.essence = essence;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + "]";
	}
}
