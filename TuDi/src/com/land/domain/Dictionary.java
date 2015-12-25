package com.land.domain;

public class Dictionary {
	private String col_name;
	private String realName;
	private String alpha;
	public String getCol_name() {
		return col_name;
	}
	public void setCol_name(String colName) {
		col_name = colName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getAlpha() {
		return alpha;
	}
	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}
	
	public String toString() {
		return col_name+"|"+realName+"|"+alpha;
	}
}
