package com.land.domain;

import java.sql.Date;
import java.sql.Timestamp;


public class AppLog {
	private String userId;
	private String userName;
	private String ways;
	private String ip;
	private String pcName;
	private Timestamp oTime;
	private String category;
	private String context;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWays() {
		return ways;
	}
	
	
	
	public Timestamp getoTime() {
		return oTime;
	}
	public void setoTime(Timestamp oTime) {
		this.oTime = oTime;
	}
	public void setWays(String ways) {
		this.ways = ways;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPcName() {
		return pcName;
	}
	public void setPcName(String pcName) {
		this.pcName = pcName;
	}
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		if(context.getBytes().length>=500) {
			context = context.substring(0,300)+"...";
		}
		this.context = context;
	}

	
	public String toString() {
		return userName + ":" + oTime;
	}
	
}
