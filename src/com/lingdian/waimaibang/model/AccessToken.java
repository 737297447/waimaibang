package com.lingdian.waimaibang.model;

import java.io.Serializable;
import java.util.List;

public class AccessToken  extends SuperBean{
	private int expires_at;
	private String refresh_token;
	private String token;
	public int getExpires_at() {
		return expires_at;
	}
	public void setExpires_at(int expires_at) {
		this.expires_at = expires_at;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
