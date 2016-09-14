package com.lingdian.waimaibang.model.common;

import java.io.Serializable;

import com.lingdian.waimaibang.model.SuperBean;

public class Photos extends SuperBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String file_url;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	
}
