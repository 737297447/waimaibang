package com.lingdian.waimaibang.model;

import java.util.List;

public class MudidiModel {

	/** 目的地的图片地址*/
	private String imgUrl;
	/** 目的地名字*/
	private String address;
	/** 目的地名字*/
	private String localName;
	/** 目的地的商店书*/
	private String shangdianNum;
	/** 目的地的玩法*/
	private String wanfaNum;
	/** 目的地的游玩简介*/
	private List<MudidiTextModel> textModelEs;
	
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public String getShangdianNum() {
		return shangdianNum;
	}
	public void setShangdianNum(String shangdianNum) {
		this.shangdianNum = shangdianNum;
	}
	public String getWanfaNum() {
		return wanfaNum;
	}
	public void setWanfaNum(String wanfaNum) {
		this.wanfaNum = wanfaNum;
	}
	public List<MudidiTextModel> getTextModelEs() {
		return textModelEs;
	}
	public void setTextModelEs(List<MudidiTextModel> textModelEs) {
		this.textModelEs = textModelEs;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
