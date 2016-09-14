package com.lingdian.waimaibang.model.wanfa;

import com.lingdian.waimaibang.model.SuperBean;

public class WriteWanfaModel extends SuperBean{

	private int error;
	private WanfaModel short_article;
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public WanfaModel getShort_article() {
		return short_article;
	}
	public void setShort_article(WanfaModel short_article) {
		this.short_article = short_article;
	}
	
}
