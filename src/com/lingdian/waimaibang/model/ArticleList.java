package com.lingdian.waimaibang.model;

import java.io.Serializable;
import java.util.List;

public class ArticleList implements Serializable{
	private List<Article> articles;
	private boolean is_system_articles;
	
	
	
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	public boolean isIs_system_articles() {
		return is_system_articles;
	}
	public void setIs_system_articles(boolean is_system_articles) {
		this.is_system_articles = is_system_articles;
	}
}
