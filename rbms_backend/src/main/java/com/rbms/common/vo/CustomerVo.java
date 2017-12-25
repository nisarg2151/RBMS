package com.rbms.common.vo;

import java.io.Serializable;

public class CustomerVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String cid;
	String name;
	String telephone;
	int visitsMade;
	String lastVisitDate;
	
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getVisitsMade() {
		return visitsMade;
	}
	public void setVisitsMade(int visitsMade) {
		this.visitsMade = visitsMade;
	}
	public String getLastVisitDate() {
		return lastVisitDate;
	}
	public void setLastVisitDate(String lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}
	
}
