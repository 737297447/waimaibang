
package com.lingdian.waimaibang.model;

import java.util.List;

public class JingxuanDetailModel {
	
	private String groupName;
	private List<JingxuanItemModel> itemNeirong;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<JingxuanItemModel> getItemNeirong() {
		return itemNeirong;
	}
	public void setItemNeirong(List<JingxuanItemModel> itemNeirong) {
		this.itemNeirong = itemNeirong;
	}

}
