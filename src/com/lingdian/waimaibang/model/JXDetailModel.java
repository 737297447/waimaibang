package com.lingdian.waimaibang.model;

import java.util.List;

import com.lingdian.waimaibang.model.common.Author;
import com.lingdian.waimaibang.model.common.Cover;
import com.lingdian.waimaibang.model.common.Trips;

public class JXDetailModel extends SuperBean{

	private int id;
	private String title;
	private String tags_str;
	private Cover cover;
	private Destination destination;
	private boolean essence;
	private Author author;
	private List<Trips> trips;
	private String summary;
	private int comments_count;
	private String url;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTags_str() {
		return tags_str;
	}
	public void setTags_str(String tags_str) {
		this.tags_str = tags_str;
	}
	public Cover getCover() {
		return cover;
	}
	public void setCover(Cover cover) {
		this.cover = cover;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public boolean isEssence() {
		return essence;
	}
	public void setEssence(boolean essence) {
		this.essence = essence;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public List<Trips> getTrips() {
		return trips;
	}
	public void setTrips(List<Trips> trips) {
		this.trips = trips;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getComments_count() {
		return comments_count;
	}
	public void setComments_count(int comments_count) {
		this.comments_count = comments_count;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
