package com.land.actions.quanxian;

import java.util.ArrayList;
import java.util.List;

import com.land.actions.LandSupport;
import com.land.daos.QuanXianDAO;
import com.land.daos.QuanXianDAOImp;
import com.land.domain.User;

public class QueryUserAction extends LandSupport{
	private List<User> users = new ArrayList<User>();
	private int pageNum=1;
	private int total=1;
	private String queryName="";
	private QuanXianDAO dao = new QuanXianDAOImp();
	public String execute() {
		if(queryName.trim().length()!=0) {
			users = dao.queryUsers(queryName.trim());
		}
		
		return "success";
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
