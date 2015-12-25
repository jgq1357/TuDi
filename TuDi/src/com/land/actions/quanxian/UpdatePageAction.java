package com.land.actions.quanxian;

import java.util.ArrayList;
import java.util.List;

import com.land.actions.LandSupport;
import com.land.daos.QuanXianDAO;
import com.land.daos.QuanXianDAOImp;
import com.land.domain.User;
import com.land.util.Constant;

public class UpdatePageAction extends LandSupport{
	private List<User> users = new ArrayList<User>();
	private int pageNum=1;
	private int total;
	private QuanXianDAO dao = new QuanXianDAOImp();
	public String execute() {
		int end = pageNum*Constant.ROWSOFSEARCHRESULT;
		int start = end-Constant.ROWSOFSEARCHRESULT+1;
		
		users = dao.getAllUsers(start,end);
		total = dao.countUsers();
		
		return "success";
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
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
