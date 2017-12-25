package com.rbms.common.vo;

public class ProductVo {
	String PID;
	String name;
	String qoh;
	int qohThreshold;
	String originalPrice;
	int discntCategory;
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQoh() {
		return qoh;
	}
	public void setQoh(String qoh) {
		this.qoh = qoh;
	}
	public int getQohThreshold() {
		return qohThreshold;
	}
	public void setQohThreshold(int qohThreshold) {
		this.qohThreshold = qohThreshold;
	}
	public String getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	public int getDiscntCategory() {
		return discntCategory;
	}
	public void setDiscntCategory(int discntCategory) {
		this.discntCategory = discntCategory;
	}
	
	
}
