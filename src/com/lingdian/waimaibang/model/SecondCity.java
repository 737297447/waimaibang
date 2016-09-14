package com.lingdian.waimaibang.model;

/**
 * 二级城市model,用于目的地头部下拉选择城市
 * @author 1818
 *
 */
public class SecondCity extends SuperBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 城市id*/
	private String id;
	/** 城市name*/
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
