package com.land.actions.main;

import java.util.ArrayList;
import java.util.List;

import com.land.actions.LandSupport;
import com.land.domain.Gtzyjc;
import com.land.domain.User;
import com.land.services.GtzyjcService;
import com.land.services.GtzyjcServiceImpl;
import com.land.util.Constant;
import com.land.util.LogUtil;

public class GtzyjcSearchAction extends LandSupport{
	
	private String dah;
	private String dahm;
	private String tdsyz;
	private String tdsyzm;
	private String tdzl;
	private String tdzlm;
	private String zdh;
	private String zdhm;
	private String tdzh;
	private String tdzhm;
	
	private List<Gtzyjc> gtzyjcs;
	private GtzyjcService gtzyjcServices;
	private String flag; //Y:下一页时从session中提取conditions
	private int pageNum=1;
	private int total;
	private List<Integer> totals = new ArrayList<Integer>();
	private String view;
	private int lbh;
	private String wfdw;
	private String wfxz;
	private String wh;
	private String bgqx;
	private String zrz;
	private String tm;
	private String xcsj;
	private String bz;
	private String wfdwm;
	private String wfxzm;
	private String whm;
	private String bgqxm;
	private String zrzm;
	private String tmm;
	private String xcsjm;
	private String bzm;
	private Logger log = Logger.getLogger(.class);
	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view.trim();
	}
	
	public String execute() {
		User user = (User)session.get("user");
		view = user.includePermission("档案浏览");
		gtzyjcServices = new GtzyjcServiceImpl();
		gtzyjcs = new ArrayList<Gtzyjc>();
		String conditions;
		if("Y".equals(flag)) {
			conditions = (String) session.get("conditions");
		}else {
			conditions = getConditions();
		}
		int every = Constant.ROWSOFSEARCHRESULT;
		
		int end = every*pageNum;
		int start = end-every+1;
		try {
			gtzyjcs = gtzyjcServices.searchBy(conditions,start,end);
			total = gtzyjcServices.countTotal(conditions);
			countTotals();
			logIntoDb("档案搜索", "搜索成功");
		} catch (Exception e) {
			logIntoDb("搜索出错", e.getMessage());
			log.error(e);
			return "error";
		}	
		return "success";
	}
	
	private void countTotals() {
		for(int i=1;i<=total;i++) {
			totals.add(i);
		}
	}
	
	private String getConditions() {
		
		String conditions = " 1=1 and";
		if(dah!=null && dah.trim().length()!=0) {
			if("on".equals(dahm)){
				conditions = conditions+getFuzzyConditionsByDah();
			}else {
				conditions = conditions+getExactConditionsByDah();
			}
		}
		
		
		if(tdsyz!=null && tdsyz.trim().length()!=0) {
			if("on".equals(tdsyzm)){
				conditions = conditions + " tdsyz like '%"+tdsyz+"%' and";
			}else {
				conditions = conditions + " tdsyz = '"+tdsyz+"' and";
			}
		}
		if(tdzl!=null && tdzl.trim().length()!=0) {
			if("on".equals(tdzlm)){
				conditions = conditions + " tdzl like '%"+tdzl+"%' and";
			}else {
				conditions = conditions + " tdzl = '"+tdzl+"' and";
			}
		}
		if(zdh!=null && zdh.trim().length()!=0) {
			if("on".equals(zdhm)){
				conditions = conditions + " zdh like '%"+zdh+"%' and";
			}else {
				conditions = conditions + " zdh = '"+zdh+"' and";
			}
		}
		
		if(tdzh!=null && tdzh.trim().length()!=0) {
			if("on".equals(tdzhm)){
				conditions = conditions + " tdzh like '%"+tdzh+"%' and";
			}else {
				conditions = conditions + " tdzh = '"+tdzh+"' and";
			}
		}
		
		if(wfdw!=null && wfdw.trim().length()!=0) {
			if("on".equals(wfdwm)){
				conditions = conditions + " wfdw like '%"+wfdw+"%' and";
			}else {
				conditions = conditions + " wfdw = '"+wfdw+"' and";
			}
		}
		
		if(wfxz!=null && wfxz.trim().length()!=0) {
			if("on".equals(wfxzm)){
				conditions = conditions + " wfxz like '%"+wfxz+"%' and";
			}else {
				conditions = conditions + " wfxz = '"+wfxz+"' and";
			}
		}
		
		if(wh!=null && wh.trim().length()!=0) {
			if("on".equals(whm)){
				conditions = conditions + " wh like '%"+wh+"%' and";
			}else {
				conditions = conditions + " wh = '"+wh+"' and";
			}
		}
		if(bgqx!=null && bgqx.trim().length()!=0) {
			if("on".equals(bgqxm)){
				conditions = conditions + " bgqx like '%"+bgqx+"%' and";
			}else {
				conditions = conditions + " bgqx = '"+bgqx+"' and";
			}
		}
		if(zrz!=null && zrz.trim().length()!=0) {
			if("on".equals(zrzm)){
				conditions = conditions + " zrz like '%"+zrz+"%' and";
			}else {
				conditions = conditions + " zrz = '"+zrz+"' and";
			}
		}
		if(tm!=null && tm.trim().length()!=0) {
			if("on".equals(tmm)){
				conditions = conditions + " tm like '%"+tm+"%' and";
			}else {
				conditions = conditions + " tm = '"+tm+"' and";
			}
		}
		if(xcsj!=null && xcsj.trim().length()!=0) {
			if("on".equals(xcsjm)){
				conditions = conditions + " xcsj like '%"+xcsj+"%' and";
			}else {
				conditions = conditions + " xcsj = '"+xcsj+"' and";
			}
		}
		if(bz!=null && bz.trim().length()!=0) {
			if("on".equals(bzm)){
				conditions = conditions + " bz like '%"+bz+"%' and";
			}else {
				conditions = conditions + " bz = '"+bz+"' and";
			}
		}
		conditions = conditions.substring(0,conditions.length()-3);
		
		if(!conditions.contains("and")) {
			conditions = " 1=2";
		}
		if(lbh!=0) {
			conditions = conditions+" and lbh="+lbh;
		}
		session.put("conditions", conditions);
		return conditions;
	}
	
	private String getExactConditionsByDah() {
		String vals[] = dah.trim().toUpperCase().split("-");
		if(vals.length!=4) {
			return " 1=2 and";
		}
		String condition = " upper(qzh)='" + vals[0] + "' and upper(mlh)='" + vals[1]
				+ "' and upper(flh)='" + vals[2] + "' and upper(ajh)='" + vals[3]
				+ "' and";
		return condition;
	}
	
	private String getFuzzyConditionsByDah() {
		String vals[] = dah.trim().toUpperCase().split("-");
		String condition = "";
		if (vals.length == 1) {
			condition = " (upper(qzh) like '%" + vals[0] + "%' or upper(mlh) like '%"
					+ vals[0] + "%' or upper(flh) like '%" + vals[0]
					+ "%' or upper(ajh) like '%" + vals[0] + "%') and";
		} else if (vals.length == 2) {
			condition = " ((upper(qzh) like '%" + vals[0] + "' and upper(mlh) like '"
					+ vals[1] + "%') or (upper(mlh) like '%" + vals[0]
					+ "' and upper(flh) like '" + vals[1]
					+ "%') or (upper(flh) like '%" + vals[0]
					+ "' and upper(ajh) like '" + vals[1] + "%')) and";
		} else if (vals.length == 3) {
			condition = " ((upper(qzh) like '%" + vals[0] + "' and upper(mlh) ='"
					+ vals[1] + "' and upper(flh) like '" + vals[2]
					+ "%') or (upper(mlh) like '%" + vals[0] + "' and upper(flh) ='"
					+ vals[1] + "' and upper(ajh) like '" + vals[2] + "%')) and";
		} else if (vals.length == 4) {
			condition = " ((upper(qzh) like '%" + vals[0] + "' and upper(mlh) ='"
					+ vals[1] + "' and upper(flh) = '" + vals[2]
					+ "' and upper(ajh) like '" + vals[3] + "%')) and";
		}
		return condition;
	}
	

	public String getDah() {
		return dah;
	}

	public void setDah(String dah) {
		this.dah = dah.trim();
	}

	public String getDahm() {
		return dahm;
	}

	public void setDahm(String dahm) {
		this.dahm = dahm;
	}

	public String getTdsyz() {
		return tdsyz;
	}

	public void setTdsyz(String tdsyz) {
		this.tdsyz = tdsyz.trim();
	}

	public String getTdsyzm() {
		return tdsyzm;
	}

	public void setTdsyzm(String tdsyzm) {
		this.tdsyzm = tdsyzm;
	}

	public String getTdzl() {
		return tdzl;
	}

	public void setTdzl(String tdzl) {
		this.tdzl = tdzl.trim();
	}

	public String getTdzlm() {
		return tdzlm;
	}

	public void setTdzlm(String tdzlm) {
		this.tdzlm = tdzlm;
	}

	public String getZdh() {
		return zdh;
	}

	public void setZdh(String zdh) {
		this.zdh = zdh.trim();
	}

	public String getZdhm() {
		return zdhm;
	}

	public void setZdhm(String zdhm) {
		this.zdhm = zdhm;
	}

	public String getTdzh() {
		return tdzh;
	}

	public void setTdzh(String tdzh) {
		this.tdzh = tdzh.trim();
	}

	public String getTdzhm() {
		return tdzhm;
	}

	public void setTdzhm(String tdzhm) {
		this.tdzhm = tdzhm;
	}

	public List<Gtzyjc> getGtzyjcs() {
		return gtzyjcs;
	}

	public void setGtzyjcs(List<Gtzyjc> gtzyjcs) {
		this.gtzyjcs = gtzyjcs;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public int getLbh() {
		return lbh;
	}

	public void setLbh(int lbh) {
		this.lbh = lbh;
	}

	public String getWfdw() {
		return wfdw;
	}

	public void setWfdw(String wfdw) {
		this.wfdw = wfdw.trim();
	}

	public String getWfxz() {
		return wfxz;
	}

	public void setWfxz(String wfxz) {
		this.wfxz = wfxz.trim();
	}

	public String getWh() {
		return wh;
	}

	public void setWh(String wh) {
		this.wh = wh.trim();
	}

	public String getBgqx() {
		return bgqx;
	}

	public void setBgqx(String bgqx) {
		this.bgqx = bgqx.trim();
	}

	public String getZrz() {
		return zrz;
	}

	public void setZrz(String zrz) {
		this.zrz = zrz.trim();
	}

	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm.trim();
	}

	public String getXcsj() {
		return xcsj;
	}

	public void setXcsj(String xcsj) {
		this.xcsj = xcsj.trim();
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz.trim();
	}

	public String getWfdwm() {
		return wfdwm;
	}

	public void setWfdwm(String wfdwm) {
		this.wfdwm = wfdwm;
	}

	public String getWfxzm() {
		return wfxzm;
	}

	public void setWfxzm(String wfxzm) {
		this.wfxzm = wfxzm;
	}

	public String getWhm() {
		return whm;
	}

	public void setWhm(String whm) {
		this.whm = whm;
	}

	public String getBgqxm() {
		return bgqxm;
	}

	public void setBgqxm(String bgqxm) {
		this.bgqxm = bgqxm;
	}

	public String getZrzm() {
		return zrzm;
	}

	public void setZrzm(String zrzm) {
		this.zrzm = zrzm;
	}

	public String getTmm() {
		return tmm;
	}

	public void setTmm(String tmm) {
		this.tmm = tmm;
	}

	public String getXcsjm() {
		return xcsjm;
	}

	public void setXcsjm(String xcsjm) {
		this.xcsjm = xcsjm;
	}

	public String getBzm() {
		return bzm;
	}

	public void setBzm(String bzm) {
		this.bzm = bzm;
	}
	
	
	
	
	


}
