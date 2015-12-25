package com.land.domain;

public class Condition {
	private String display;
	private String alias;
	private String name;
	private String value;
	private String alpha;
	private int belongId;
	
	
	public int getBelongId() {
		return belongId;
	}
	public void setBelongId(int belongId) {
		this.belongId = belongId;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public void setDisplay() {
		if("S".equalsIgnoreCase(this.value)) {
			this.display = "inline";
		}else {
			this.display = "none";
		}
		
	}
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlpha() {
		return alpha;
	}
	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return alias+"-"+name+"-"+value+"-"+alpha+"-"+display;
	}
	
	
}
