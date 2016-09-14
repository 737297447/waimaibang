package com.lingdian.waimaibang.model;

import java.io.Serializable;
import java.util.List;

public class ShortArticle  implements Serializable{
	private int id; // 主题ID
	private String content; // 主题内容
	private String tags_str; // 标签组 例如：tags_str=单身 浪漫 清净 文艺 户外
	private int published_at;
	private Destination destination;
	private List<Photo> photos;
	private String photo_order;
	private User user;
	private int consumption;
	private int ups_count;
	private int comments_count;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTags_str() {
		return tags_str;
	}

	public void setTags_str(String tags_str) {
		this.tags_str = tags_str;
	}

	public int getPublished_at() {
		return published_at;
	}

	public void setPublished_at(int published_at) {
		this.published_at = published_at;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public String getPhoto_order() {
		return photo_order;
	}

	public void setPhoto_order(String photo_order) {
		this.photo_order = photo_order;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getConsumption() {
		return consumption;
	}

	public void setConsumption(int consumption) {
		this.consumption = consumption;
	}

	public int getUps_count() {
		return ups_count;
	}

	public void setUps_count(int ups_count) {
		this.ups_count = ups_count;
	}

	public int getComments_count() {
		return comments_count;
	}

	public void setComments_count(int comments_count) {
		this.comments_count = comments_count;
	}

	@Override
	public String toString() {
		return "ShortArticle [id=" + id + ", content=" + content + "]";
	}
}
