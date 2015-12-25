package com.land.actions.main;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.land.actions.LandSupport;
import com.land.domain.Djyw;
import com.land.domain.User;
import com.land.services.DjywService;
import com.land.services.DjywServiceImpl;
import com.land.util.Constant;

public class SuperSearch extends LandSupport{
	
	private String mlh;
	private String flh;
	private String ajh;
	
	private String mlhm;
	private String flhm;
	private String ajhm;
	
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
	
	
	private List<Djyw> djyws;
	private DjywService djywServices;
	private String flag; //Y:下一页时从session中提取conditions
	private int pageNum=1;
	private int total;
	private List<Integer> totals = new ArrayList<Integer>();
	private String view;
	private int lbh;
	private String wtdw;
	private String bpgdw;
	private String gjbgbh;
	private String sqr;
	private String dyzsh;
	private String wtdwm;
	private String bpgdwm;
	private String gjbgbhm;
	private String sqrm;
	private String dyzshm;
	private Logger log = Logger.getLogger(SuperSearch.class);
	
	public String getMlhm() {
		return mlhm;
	}

	public void setMlhm(String mlhm) {
		this.mlhm = mlhm;
	}

	public String getFlhm() {
		return flhm;
	}

	public void setFlhm(String flhm) {
		this.flhm = flhm;
	}

	public String getAjhm() {
		return ajhm;
	}

	public void setAjhm(String ajhm) {
		this.ajhm = ajhm;
	}

	public String getMlh() {
		return mlh;
	}

	public void setMlh(String mlh) {
		this.mlh = mlh;
	}

	public String getFlh() {
		return flh;
	}

	public void setFlh(String flh) {
		this.flh = flh;
	}

	public String getAjh() {
		return ajh;
	}

	public void setAjh(String ajh) {
		this.ajh = ajh;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view.trim();
	}
	
	public String execute() {
		//dah = mlh+"-"+"-"+flh + "-"+ajh;
		
		User user = (User)session.get("user");
		view = user.includePermission("档案浏览");
		djywServices = new DjywServiceImpl();
		djyws = new ArrayList<Djyw>();
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
			djyws = djywServices.searchBy(conditions,start,end);
			total = djywServices.countTotal(conditions);
			countTotals();
			log.info("档案搜索"+"搜索成功");
		} catch (Exception e) {
			log.error("搜索出错"+e.getMessage());
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
//		if(dah!=null && dah.trim().length()!=0) {
//			if("on".equals(dahm)){
//				conditions = conditions+getFuzzyConditionsByDah();
//			}else {
//				conditions = conditions+getExactConditionsByDah();
//			}
//		}
		if(mlh!=null && mlh.trim().length()!=0) {
			if("on".equals(mlhm)){
				conditions = conditions + " mlh like '%"+mlh+"%' and";
			}else {
				conditions = conditions + " mlh = '"+mlh+"' and";
			}
		}
		
		if(flh!=null && flh.trim().length()!=0) {
			if("on".equals(flhm)){
				conditions = conditions + " flh like '%"+flh+"%' and";
			}else {
				conditions = conditions + " flh = '"+flh+"' and";
			}
		}
		
		if(ajh!=null && ajh.trim().length()!=0) {
			if("on".equals(ajhm)){
				conditions = conditions + " ajh like '%"+ajh+"%' and";
			}else {
				conditions = conditions + " ajh = '"+ajh+"' and";
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
		
		if(wtdw!=null && wtdw.trim().length()!=0) {
			if("on".equals(wtdwm)){
				conditions = conditions + " wtdw like '%"+wtdw+"%' and";
			}else {
				conditions = conditions + " wtdw = '"+wtdw+"' and";
			}
		}
		
		if(bpgdw!=null && bpgdw.trim().length()!=0) {
			if("on".equals(bpgdwm)){
				conditions = conditions + " bpgdw like '%"+bpgdw+"%' and";
			}else {
				conditions = conditions + " bpgdw = '"+bpgdw+"' and";
			}
		}
		
		if(gjbgbh!=null && gjbgbh.trim().length()!=0) {
			if("on".equals(gjbgbhm)){
				conditions = conditions + " gjbgbh like '%"+gjbgbh+"%' and";
			}else {
				conditions = conditions + " gjbgbh = '"+gjbgbh+"' and";
			}
		}
		if(sqr!=null && sqr.trim().length()!=0) {
			if("on".equals(sqrm)){
				conditions = conditions + " sqr like '%"+sqr+"%' and";
			}else {
				conditions = conditions + " sqr = '"+sqr+"' and";
			}
		}
		if(dyzsh!=null && dyzsh.trim().length()!=0) {
			if("on".equals(dyzshm)){
				conditions = conditions + " dyzsh like '%"+dyzsh+"%' and";
			}else {
				conditions = conditions + " dyzsh = '"+dyzsh+"' and";
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
		//String vals[] = dah.trim().toUpperCase().split("-");
		String condition = "";
//		if (vals.length == 1) {
//			condition = " (upper(mlh) like '%"
//					+ mlh + "%' or upper(flh) like '%" + flh
//					+ "%' or upper(ajh) like '%" + ajh + "%') and";
//		} else if (vals.length == 2) {
//			condition = " ((upper(qzh) like '%" + vals[0] + "' and upper(mlh) like '"
//					+ vals[1] + "%') or (upper(mlh) like '%" + vals[0]
//					+ "' and upper(flh) like '" + vals[1]
//					+ "%') or (upper(flh) like '%" + vals[0]
//					+ "' and upper(ajh) like '" + vals[1] + "%')) and";
//		} else if (vals.length == 3) {
//			condition = " ((upper(qzh) like '%" + vals[0] + "' and upper(mlh) ='"
//					+ vals[1] + "' and upper(flh) like '" + vals[2]
//					+ "%') or (upper(mlh) like '%" + vals[0] + "' and upper(flh) ='"
//					+ vals[1] + "' and upper(ajh) like '" + vals[2] + "%')) and";
//		} else if (vals.length == 4) {
//			condition = " ((upper(qzh) like '%" + vals[0] + "' and upper(mlh) ='"
//					+ vals[1] + "' and upper(flh) = '" + vals[2]
//					+ "' and upper(ajh) like '" + vals[3] + "%')) and";
//		}
//		
//		condition = " (upper(mlh) like '%"
//			+ mlh + "%' or upper(flh) like '%" + flh
//			+ "%' or upper(ajh) like '%" + ajh + "%') and";
		
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
	
	
	public List<Djyw> getDjyws() {
		return djyws;
	}

	public void setDjyws(List<Djyw> djyws) {
		this.djyws = djyws;
	}

	public DjywService getDjywServices() {
		return djywServices;
	}

	public void setDjywServices(DjywService djywServices) {
		this.djywServices = djywServices;
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

	public String getWtdw() {
		return wtdw;
	}

	public void setWtdw(String wtdw) {
		this.wtdw = wtdw.trim();
	}

	public String getBpgdw() {
		return bpgdw;
	}

	public void setBpgdw(String bpgdw) {
		this.bpgdw = bpgdw.trim();
	}

	public String getGjbgbh() {
		return gjbgbh;
	}

	public void setGjbgbh(String gjbgbh) {
		this.gjbgbh = gjbgbh.trim();
	}

	public String getSqr() {
		return sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr.trim();
	}

	public String getDyzsh() {
		return dyzsh;
	}

	public void setDyzsh(String dyzsh) {
		this.dyzsh = dyzsh.trim();
	}

	public String getWtdwm() {
		return wtdwm;
	}

	public void setWtdwm(String wtdwm) {
		this.wtdwm = wtdwm;
	}

	public String getBpgdwm() {
		return bpgdwm;
	}

	public void setBpgdwm(String bpgdwm) {
		this.bpgdwm = bpgdwm;
	}

	public String getGjbgbhm() {
		return gjbgbhm;
	}

	public void setGjbgbhm(String gjbgbhm) {
		this.gjbgbhm = gjbgbhm;
	}

	public String getSqrm() {
		return sqrm;
	}

	public void setSqrm(String sqrm) {
		this.sqrm = sqrm;
	}

	public String getDyzshm() {
		return dyzshm;
	}

	public void setDyzshm(String dyzshm) {
		this.dyzshm = dyzshm;
	}
	
	
	
}
