package com.lingdian.waimaibang.model;

import java.util.List;

public class Register extends SuperBean {
	
	
	private AccessToken access_token;
	private int error;
	private User user;
	private String message;
	private List<Message> messages;
	
	
    
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public AccessToken getAccess_token() {
		return access_token;
	}

	public void setAccess_token(AccessToken access_token) {
		this.access_token = access_token;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
