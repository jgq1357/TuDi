package com.land.actions.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.daos.AppLogDAO;
import com.land.daos.AppLogDAOImp;
import com.land.domain.AppLog;
import com.land.util.Constant;

public class AppLogAction extends LandSupport{
	private List<AppLog> applogs;
	private int pageNum;
	private int total;
	private List<Integer> totals = new ArrayList<Integer>();
	
	Logger log = Logger.getLogger(AppLogAction.class);

	public List<Integer> getTotals() {
		return totals;
	}

	public void setTotals(List<Integer> totals) {
		this.totals = totals;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<AppLog> getApplogs() {
		return applogs;
	}

	public void setApplogs(List<AppLog> applogs) {
		this.applogs = applogs;
	}

	
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String execute() {
		AppLogDAO dao = new AppLogDAOImp();
		int every = Constant.ROWSOFLOG;
		
		int end = pageNum*every;
		int start = end-every+1;
		try {
			applogs = dao.getLogs(start,end);
			total = dao.getTotal();
			countTotal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("读取日志记录出错。");
			log.error(e);
		}
		return "success";
	}
	
	private void countTotal() {
		for(int i=1;i<=total;i++) {
			totals.add(i);
		}
	}
}
