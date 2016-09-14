package com.lingdian.waimaibang.model;

import java.io.Serializable;

public class Photo  implements Serializable{
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
