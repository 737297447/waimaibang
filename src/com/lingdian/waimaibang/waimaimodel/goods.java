package com.lingdian.waimaibang.waimaimodel;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class goods extends BmobObject{
	
	private String goodsName;
	private String goodsJianjie;
	private Integer xiang;
	private Integer buxiang;
	private String goodsUrl;
	private String tel;
	private String weixin;
	private BmobFile adPic;
	private String maxXiang;
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsJianjie() {
		return goodsJianjie;
	}
	public void setGoodsJianjie(String goodsJianjie) {
		this.goodsJianjie = goodsJianjie;
	}
	public String getGoodsUrl() {
		return goodsUrl;
	}
	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public BmobFile getAdPic() {
		return adPic;
	}
	public void setAdPic(BmobFile adPic) {
		this.adPic = adPic;
	}
	public Integer getXiang() {
		return xiang;
	}
	public void setXiang(Integer xiang) {
		this.xiang = xiang;
	}
	public Integer getBuxiang() {
		return buxiang;
	}
	public void setBuxiang(Integer buxiang) {
		this.buxiang = buxiang;
	}
	public String getMaxXiang() {
		return maxXiang;
	}
	public void setMaxXiang(String maxXiang) {
		this.maxXiang = maxXiang;
	}
	
	
	

}
