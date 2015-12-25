package com.land.actions.main;

import java.util.ArrayList;
import java.util.List;

import com.land.actions.LandSupport;
import com.land.daos.GtzyjcDAO;
import com.land.daos.GtzyjcDAOImp;
import com.land.domain.Gtzyjc;
import com.land.domain.User;
import com.land.services.GtzyjcService;
import com.land.services.GtzyjcServiceImpl;
import com.land.util.Constant;
import com.land.util.LogUtil;

public class GtzyjcAction extends LandSupport{

	private Gtzyjc gtzyjc;
	private List<Gtzyjc> gtzyjcs;
	private int pageNum;
	private int total;
	private List<Integer> totals = new ArrayList<Integer>();
	private String view;
	private int lbh;
	
	private Logger log = Logger.getLogger(.class);

	
	public Gtzyjc getGtzyjc() {
		return gtzyjc;
	}

	public void setGtzyjc(Gtzyjc gtzyjc) {
		this.gtzyjc = gtzyjc;
	}

	public List<Gtzyjc> getGtzyjcs() {
		return gtzyjcs;
	}

	public void setGtzyjcs(List<Gtzyjc> gtzyjcs) {
		this.gtzyjcs = gtzyjcs;
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




	public String execute()  {
		GtzyjcDAO dao = new GtzyjcDAOImp();
		User user = (User)session.get("user");
		view = user.includePermission("档案浏览");
		int every = Constant.ROWSOFSEARCHRESULT;
		pageNum=1;
		try {
			if(lbh==0) {
				gtzyjcs = dao.getAllGtzyjc(1,every);
				total = dao.countTotal("All");
				session.put("conditions", "All");
			}else {
				GtzyjcService gtzyjcServices = new GtzyjcServiceImpl();
				int end = every*pageNum;
				int start = end-every+1;
				String conditions = "lbh="+lbh;
				gtzyjcs = gtzyjcServices.searchBy(conditions, start, end);
				session.put("conditions", conditions);
			}
		} catch (Exception e) {
			logIntoDb("系统出错", "搜索国土资源档案信息失败。");
			log.error("搜索国土资源档案信息失败。"+e.getMessage());
			log.error(e);
		}
		
		
		countTotals();
		
		logIntoDb("档案搜索", "显示全部国土资源监察类的档案");
		return "success";
	}
	
	private void countTotals() {
		for(int i=1;i<=total;i++) {
			totals.add(i);
		}
	}
}
