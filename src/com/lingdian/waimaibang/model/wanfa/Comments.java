package com.lingdian.waimaibang.model.wanfa;

import com.lingdian.waimaibang.model.SuperBean;
import com.lingdian.waimaibang.model.common.Author;

public class Comments extends SuperBean{
	
	private int id;
	private Author user;
	private String machine_id;
	private String content;
	private String user_nickname;
	private long created_at;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Author getUser() {
		return user;
	}
	public void setUser(Author user) {
		this.user = user;
	}
	public String getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public long getCreated_at() {
		return created_at;
	}
	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}
	

}
