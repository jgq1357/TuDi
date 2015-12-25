package com.land.domain;

public class Dalb {
	private int lbh;
	private String lbName;
	private String searchCondition;
	private int lmId;
	public int getLbh() {
		return lbh;
	}
	public void setLbh(int lbh) {
		this.lbh = lbh;
	}
	public String getLbName() {
		return lbName;
	}
	public void setLbName(String lbName) {
		this.lbName = lbName;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public int getLmId() {
		return lmId;
	}
	public void setLmId(int lmId) {
		this.lmId = lmId;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return lbh+"-"+lbName+"-"+searchCondition+"-"+lmId;
	}
	
	
}
