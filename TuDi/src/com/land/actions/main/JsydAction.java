package com.land.actions.main;

import java.util.ArrayList;
import java.util.List;

import com.land.actions.LandSupport;
import com.land.daos.JsydDAO;
import com.land.daos.JsydDAOImp;
import com.land.domain.Jsyd;
import com.land.domain.User;
import com.land.services.JsydService;
import com.land.services.JsydServiceImpl;
import com.land.util.Constant;
import com.land.util.LogUtil;

public class JsydAction extends LandSupport{
	private Jsyd jsyd;
	private List<Jsyd> jsyds;
	private int pageNum;
	private int total;
	private List<Integer> totals = new ArrayList<Integer>();
	private String view;
	private int lbh;
	
	private Logger log = Logger.getLogger(.class);

	public Jsyd getJsyd() {
		return jsyd;
	}

	public void setJsyd(Jsyd jsyd) {
		this.jsyd = jsyd;
	}

	public List<Jsyd> getJsyds() {
		return jsyds;
	}

	public void setJsyds(List<Jsyd> jsyds) {
		this.jsyds = jsyds;
	}

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



	
	public String execute() {
		JsydDAO dao = new JsydDAOImp();
		User user = (User)session.get("user");
		view = user.includePermission("档案浏览");
		int every = Constant.ROWSOFSEARCHRESULT;
		pageNum=1;
		try {
			if(lbh==0) {
				jsyds = dao.getAllJsyd(1,every);
				total = dao.countTotal("All");
				session.put("conditions", "All");
			}else {
				JsydService jsydServices = new JsydServiceImpl();
				int end = every*pageNum;
				int start = end-every+1;
				String conditions = "lbh="+lbh;
				jsyds = jsydServices.searchBy(conditions, start, end);
				session.put("conditions", conditions);
			}
		} catch (Exception e) {
			logIntoDb("系统出错", "搜索建设用地档案信息失败。");
			log.error("搜索建设用地档案信息失败。"+e.getMessage());
			log.error(e);
		}
		
		
		countTotals();
		
		logIntoDb("档案搜索", "显示全部建设用地类的档案");
		return "success";
	}
	
	private void countTotals() {
		for(int i=1;i<=total;i++) {
			totals.add(i);
		}
	}

	

	
	
}
