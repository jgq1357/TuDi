package com.land.actions.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.daos.DjywDAO;
import com.land.daos.DjywDAOImpl;
import com.land.domain.Djyw;
import com.land.domain.User;
import com.land.services.DjywService;
import com.land.services.DjywServiceImpl;
import com.land.util.Constant;

public class DjywAction extends LandSupport{
	private Djyw djyw;
	private int id;
	private List<Djyw> djyws;
	private int pageNum;
	private int total;
	private List<Integer> totals = new ArrayList<Integer>();
	private String view;
	private int lbh;
	
	Logger log = Logger.getLogger(DjywAction.class);

	public int getLbh() {
		return lbh;
	}

	public void setLbh(int lbh) {
		this.lbh = lbh;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
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

	public List<Integer> getTotals() {
		return totals;
	}

	public void setTotals(List<Integer> totals) {
		this.totals = totals;
	}

	public List<Djyw> getDjyws() {
		return djyws;
	}

	public void setDjyws(List<Djyw> djyws) {
		this.djyws = djyws;
	}

	
	public String execute(){
		DjywDAO dao = new DjywDAOImpl();
		User user = (User)session.get("user");
		view = user.includePermission("档案浏览");
		int every = Constant.ROWSOFSEARCHRESULT;
		pageNum=1;
		try {
			if(lbh==0) {
				djyws = dao.getAllDjyw(1,every);
				total = dao.countTotal("All");
				session.put("conditions", "All");
			}else {
				DjywService djywServices = new DjywServiceImpl();
				int end = every*pageNum;
				int start = end-every+1;
				String conditions = "lbh="+lbh;
				djyws = djywServices.searchBy(conditions, start, end);
				session.put("conditions", conditions);
			}
		} catch (Exception e) {
			log.error("系统出错"+"搜索地籍档案信息失败。");
			log.error("搜索地籍档案信息失败。"+e.getMessage());
			log.error(e);
		}
		
		countTotals();
		
		log.info("档案搜索"+"显示全部地籍管理类的档案");
		return "success";
	}
	
	private void countTotals() {
		for(int i=1;i<=total;i++) {
			totals.add(i);
		}
	}

	public Djyw getDjyw() {
		return djyw;
	}

	public void setDjyw(Djyw djyw) {
		this.djyw = djyw;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
