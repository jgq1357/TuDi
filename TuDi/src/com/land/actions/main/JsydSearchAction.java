package com.land.actions.main;

import java.util.ArrayList;
import java.util.List;

import com.land.actions.LandSupport;
import com.land.domain.Jsyd;
import com.land.domain.User;
import com.land.services.JsydService;
import com.land.services.JsydServiceImpl;
import com.land.util.Constant;

public class JsydSearchAction extends LandSupport{
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
	
	private List<Jsyd> jsyds;
	private JsydService jsydServices;
	private String flag; //Y:下一页时从session中提取conditions
	private int pageNum=1;
	private int total;
	private List<Integer> totals = new ArrayList<Integer>();
	private String view;
	private int lbh;
	private String zrf;
	private String srf;
	private String wh;
	private String sqr;
	private String pfwh;
	private String pzwh;
	private String yt;
	private String bz;
	private String zrfm;
	private String srfm;
	private String whm;
	private String sqrm;
	private String pfwhm;
	private String pzwhm;
	private String ytm;
	private String bzm;

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view.trim();
	}
	
	public String execute() {
		User user = (User)session.get("user");
		view = user.includePermission("档案浏览");
		jsydServices = new JsydServiceImpl();
		jsyds = new ArrayList<Jsyd>();
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
			jsyds = jsydServices.searchBy(conditions,start,end);
			total = jsydServices.countTotal(conditions);
			countTotals();
			logIntoDb("档案搜索", "搜索成功");
		} catch (Exception e) {
			logIntoDb("搜索出错", e.getMessage());
			e.printStackTrace();
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
		
		if(zrf!=null && zrf.trim().length()!=0) {
			if("on".equals(zrfm)){
				conditions = conditions + " zrf like '%"+zrf+"%' and";
			}else {
				conditions = conditions + " zrf = '"+zrf+"' and";
			}
		}
		
		if(srf!=null && srf.trim().length()!=0) {
			if("on".equals(srfm)){
				conditions = conditions + " srf like '%"+srf+"%' and";
			}else {
				conditions = conditions + " srf = '"+srf+"' and";
			}
		}
		
		if(wh!=null && wh.trim().length()!=0) {
			if("on".equals(whm)){
				conditions = conditions + " wh like '%"+wh+"%' and";
			}else {
				conditions = conditions + " wh = '"+wh+"' and";
			}
		}
		if(sqr!=null && sqr.trim().length()!=0) {
			if("on".equals(sqrm)){
				conditions = conditions + " sqr like '%"+sqr+"%' and";
			}else {
				conditions = conditions + " sqr = '"+sqr+"' and";
			}
		}
		if(pfwh!=null && pfwh.trim().length()!=0) {
			if("on".equals(pfwhm)){
				conditions = conditions + " pfwh like '%"+pfwh+"%' and";
			}else {
				conditions = conditions + " pfwh = '"+pfwh+"' and";
			}
		}
		if(pzwh!=null && pzwh.trim().length()!=0) {
			if("on".equals(pzwhm)){
				conditions = conditions + " pzwh like '%"+pzwh+"%' and";
			}else {
				conditions = conditions + " pzwh = '"+pzwh+"' and";
			}
		}
		if(yt!=null && yt.trim().length()!=0) {
			if("on".equals(ytm)){
				conditions = conditions + " yt like '%"+yt+"%' and";
			}else {
				conditions = conditions + " yt = '"+yt+"' and";
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
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
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
	
	
	

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getLbh() {
		return lbh;
	}

	public void setLbh(int lbh) {
		this.lbh = lbh;
	}


	public String getSqr() {
		return sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr.trim();
	}
	

	public String getSqrm() {
		return sqrm;
	}

	public void setSqrm(String sqrm) {
		this.sqrm = sqrm;
	}

	public String getZrf() {
		return zrf;
	}

	public void setZrf(String zrf) {
		this.zrf = zrf.trim();
	}

	public String getSrf() {
		return srf;
	}

	public void setSrf(String srf) {
		this.srf = srf.trim();
	}

	public String getWh() {
		return wh;
	}

	public void setWh(String wh) {
		this.wh = wh.trim();
	}

	public String getPfwh() {
		return pfwh;
	}

	public void setPfwh(String pfwh) {
		this.pfwh = pfwh.trim();
	}

	public String getPzwh() {
		return pzwh;
	}

	public void setPzwh(String pzwh) {
		this.pzwh = pzwh;
	}

	public String getYt() {
		return yt;
	}

	public void setYt(String yt) {
		this.yt = yt.trim();
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz.trim();
	}

	public String getZrfm() {
		return zrfm;
	}

	public void setZrfm(String zrfm) {
		this.zrfm = zrfm;
	}

	public String getSrfm() {
		return srfm;
	}

	public void setSrfm(String srfm) {
		this.srfm = srfm;
	}

	public String getWhm() {
		return whm;
	}

	public void setWhm(String whm) {
		this.whm = whm;
	}

	public String getPfwhm() {
		return pfwhm;
	}

	public void setPfwhm(String pfwhm) {
		this.pfwhm = pfwhm;
	}

	public String getPzwhm() {
		return pzwhm;
	}

	public void setPzwhm(String pzwhm) {
		this.pzwhm = pzwhm;
	}

	public String getYtm() {
		return ytm;
	}

	public void setYtm(String ytm) {
		this.ytm = ytm;
	}

	public String getBzm() {
		return bzm;
	}

	public void setBzm(String bzm) {
		this.bzm = bzm;
	}

	public List<Jsyd> getJsyds() {
		return jsyds;
	}

	public void setJsyds(List<Jsyd> jsyds) {
		this.jsyds = jsyds;
	}

	
	
}
