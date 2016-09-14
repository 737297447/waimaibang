package com.lingdian.waimaibang.model.wanfa;

import java.util.List;

import com.lingdian.waimaibang.model.SuperBean;
import com.lingdian.waimaibang.model.common.Author;
import com.lingdian.waimaibang.model.common.Destination;
import com.lingdian.waimaibang.model.common.Photos;

public class WanfaModel extends SuperBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 图片集合*/
	private List<Photos> photos;
	/** 标签*/
	private String tags_str;
	/** id*/
	private int id;
	/** 内容*/
	private String content;
	/** （图片id list）*/
	private String photo_order;
	/** 评论总数*/
	private String comments_count;
	/** 点赞总数*/
	private String ups_count;
	/** 消费钱数*/
	private String consumption;
	/** 发表的用户*/
	private Author user;
	/** 地方的详细信息*/
	private Destination destination;
	/** 发表的时间，需要转换*/
	private long published_at;
	
	public List<Photos> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}
	public String getTags_str() {
		return tags_str;
	}
	public void setTags_str(String tags_str) {
		this.tags_str = tags_str;
	}
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
	public String getPhoto_order() {
		return photo_order;
	}
	public void setPhoto_order(String photo_order) {
		this.photo_order = photo_order;
	}
	public String getComments_count() {
		return comments_count;
	}
	public void setComments_count(String comments_count) {
		this.comments_count = comments_count;
	}
	public String getUps_count() {
		return ups_count;
	}
	public void setUps_count(String ups_count) {
		this.ups_count = ups_count;
	}
	public String getConsumption() {
		return consumption;
	}
	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}
	public Author getUser() {
		return user;
	}
	public void setUser(Author user) {
		this.user = user;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public long getPublished_at() {
		return published_at;
	}
	public void setPublished_at(long published_at) {
		this.published_at = published_at;
	}
	
	
	
	
}
