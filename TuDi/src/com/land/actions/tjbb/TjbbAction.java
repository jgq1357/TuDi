package com.land.actions.tjbb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.daos.AppLogDAO;
import com.land.daos.AppLogDAOImp;
import com.land.domain.Tb;
import com.land.domain.Tjbb;
import com.land.domain.Tjlog;
import com.land.util.TimeUtil;

public class TjbbAction extends LandSupport{
	private String tjlb;
	private List<Tjbb> tjbbs = new ArrayList<Tjbb>();
	private String timeType="year";
	private String startDate="2013-01-01";
	private String endDate=TimeUtil.getCurrentDate();
	private Logger log = Logger.getLogger(TjbbAction.class);
	private AppLogDAO dao = new AppLogDAOImp();
	private Tb A = new Tb();
	private Tb B = new Tb();
	private Tb C = new Tb();
	private Tb D = new Tb();
	private Tb E = new Tb();
	private Tb F = new Tb();
	private Tb G = new Tb();
	private Tb H = new Tb();
	private Tb I = new Tb();
	private List<Integer> z_index = new ArrayList<Integer>();
	
	
	public String execute() {
		String tmpstart = startDate;
		String tmpend = endDate;
		try {
			List<Tjlog> tjlogs = new ArrayList<Tjlog>();
			startDate = calDate(startDate,true);
			endDate = calDate(endDate,false);
			String condition = "where otime>=to_date('"+startDate+"','yyyy-mm-dd hh24:mi:ss') and otime<to_date('"+endDate+"','yyyy-mm-dd hh24:mi:ss')";
			if("V".equalsIgnoreCase(tjlb)) {
				condition = condition+ " and ways='影像查看'";
			}else if("P".equalsIgnoreCase(tjlb)) {
				condition = condition+ " and ways='影像打印'";
			}else if("R".equalsIgnoreCase(tjlb)) {
				condition = condition+ " and ways='影像入库'";
			}
			if("year".equalsIgnoreCase(timeType)) {
				tjlogs = dao.getTjLogs("yyyy", condition);
				timeType="t1";
			}else if("month".equalsIgnoreCase(timeType)) {
				tjlogs = dao.getTjLogs("yyyy-mm", condition);
				timeType="t2";
			}else if("day".equalsIgnoreCase(timeType)) {
				tjlogs = dao.getTjLogs("yyyy-mm-dd", condition);
				timeType="t3";
			}
			parseTjlogs(tjlogs);
			generateChart(tjlogs);
			startDate = tmpstart;
			endDate = tmpend;
			
		} catch (SQLException e) {
			log.error("统计失败!");
			log.error(e);
		}
		
		return "success";
	}
	
	private void generateChart(List<Tjlog> tjlogs) {
		int max=0;
		for(int i=0;i<tjlogs.size();i++) {
			Tjlog tjlog = tjlogs.get(i);
			int m=0;
			if("A".equals(tjlog.getCategory())) {
				m=A.getValue()+tjlog.getNum();
				A.setValue(m);
			}else if("B".equals(tjlog.getCategory())) {
				m=B.getValue()+tjlog.getNum();
				B.setValue(m);
			}else if("C".equals(tjlog.getCategory())) {
				m=C.getValue()+tjlog.getNum();
				C.setValue(m);
			}else if("D".equals(tjlog.getCategory())) {
				m=D.getValue()+tjlog.getNum();
				D.setValue(m);
			}else if("E".equals(tjlog.getCategory())) {
				m=E.getValue()+tjlog.getNum();
				E.setValue(m);
			}else if("F".equals(tjlog.getCategory())) {
				m=F.getValue()+tjlog.getNum();
				F.setValue(m);
			}else if("G".equals(tjlog.getCategory())) {
				m=G.getValue()+tjlog.getNum();
				G.setValue(m);
			}else if("H".equals(tjlog.getCategory())) {
				m=H.getValue()+tjlog.getNum();
				H.setValue(m);
			}else if("I".equals(tjlog.getCategory())) {
				m=I.getValue()+tjlog.getNum();
				I.setValue(m);
			}
			if(m>max) {
				max=m;
			}
		}//end for
		setZAndPx(max);
	}
	
	private void setZAndPx(int m) {
		m=(m/10+1)*10;
		int avg = m/5;
		for(int i=m;i>=avg;i-=avg) {
			z_index.add(i);
		}
		A.setPx(A.getValue()*300/m);
		B.setPx(B.getValue()*300/m);
		C.setPx(C.getValue()*300/m);
		D.setPx(D.getValue()*300/m);
		E.setPx(E.getValue()*300/m);
		F.setPx(F.getValue()*300/m);
		G.setPx(G.getValue()*300/m);
		H.setPx(H.getValue()*300/m);
		I.setPx(I.getValue()*300/m);
	}
	
	private void parseTjlogs(List<Tjlog> tjlogs) {
		for(int i=0;i<tjlogs.size();i++) {
			Tjlog tjlog = tjlogs.get(i);
			boolean exist = false;
			for(int j=0;j<tjbbs.size();j++) {
				Tjbb tjbb = tjbbs.get(j);
				if(tjbb.getDate().equals(tjlog.getDate())) {
					setparameters(tjbb, tjlog, false);
					exist = true;
					break;
				}
			}
			if(!exist) {
				Tjbb tjbb = new Tjbb();
				setparameters(tjbb, tjlog, true);
				tjbbs.add(tjbb);
			}
		}
	}
	
	private void setparameters(Tjbb tjbb, Tjlog tjlog, boolean isnew) {
		if(isnew) {
			tjbb.setDate(tjlog.getDate());
		}
		
		if("A".equals(tjlog.getCategory())) {
			tjbb.setZhl(tjlog.getNum());
		}else if("B".equals(tjlog.getCategory())) {
			tjbb.setCwl(tjlog.getNum());
		}else if("C".equals(tjlog.getCategory())) {
			tjbb.setDjl(tjlog.getNum());
		}else if("D".equals(tjlog.getCategory())) {
			tjbb.setLyghl(tjlog.getNum());
		}else if("E".equals(tjlog.getCategory())) {
			tjbb.setJsydl(tjlog.getNum());
		}else if("F".equals(tjlog.getCategory())) {
			tjbb.setZyjcl(tjlog.getNum());
		}else if("G".equals(tjlog.getCategory())) {
			tjbb.setKjxxl(tjlog.getNum());
		}else if("H".equals(tjlog.getCategory())) {
			tjbb.setDzsxl(tjlog.getNum());
		}else if("I".equals(tjlog.getCategory())) {
			tjbb.setDzgcl(tjlog.getNum());
		}
		
	}
	
	
	private String calDate(String date,boolean start) {
		if("year".equalsIgnoreCase(timeType)) {
			date = date.substring(0,4);
			if(start) {
				date = date+"-01-01 00:00:00";
			}else {
				date = (Integer.parseInt(date)+1)+"-01-01 00:00:00";
			}
		}else if("month".equalsIgnoreCase(timeType)) {
			date = date.substring(0,7);
			if(start) {
				date = date+"-01 00:00:00";
			}else {
				date = parsedate(date);
			}
		}else if("day".equalsIgnoreCase(timeType)) {
			if(start) {
				date = date+" 00:00:00";
			}else {
				date = date+"23:59:59";
			}
			
		}
		return date;
	}
	
	private String parsedate(String date) {
		String[] d = date.split("-");
		if(Integer.parseInt(d[1])>=12) {
			date = (Integer.parseInt(d[0])+1)+"-01-01 00:00:00";
		}else if(Integer.parseInt(d[1])>=9) {
			date = d[0]+"-"+(Integer.parseInt(d[1])+1)+"-01 00:00:00";
		}else {
			date = d[0]+"-0"+(Integer.parseInt(d[1])+1)+"-01 00:00:00";
		}
		return date;
	}
	
	public static void main(String args[]) {
		int a =14;
		System.out.println((a/10+1)*10);
	}
	
	public String getTjlb() {
		return tjlb;
	}

	public void setTjlb(String tjlb) {
		this.tjlb = tjlb;
	}



	public List<Tjbb> getTjbbs() {
		return tjbbs;
	}

	public void setTjbbs(List<Tjbb> tjbbs) {
		this.tjbbs = tjbbs;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Tb getA() {
		return A;
	}

	public void setA(Tb a) {
		A = a;
	}

	public Tb getB() {
		return B;
	}

	public void setB(Tb b) {
		B = b;
	}

	public Tb getC() {
		return C;
	}

	public void setC(Tb c) {
		C = c;
	}

	public Tb getD() {
		return D;
	}

	public void setD(Tb d) {
		D = d;
	}

	public Tb getE() {
		return E;
	}

	public void setE(Tb e) {
		E = e;
	}

	public Tb getF() {
		return F;
	}

	public void setF(Tb f) {
		F = f;
	}

	public Tb getG() {
		return G;
	}

	public void setG(Tb g) {
		G = g;
	}

	public Tb getH() {
		return H;
	}

	public void setH(Tb h) {
		H = h;
	}

	public Tb getI() {
		return I;
	}

	public void setI(Tb i) {
		I = i;
	}

	public List<Integer> getZ_index() {
		return z_index;
	}

	public void setZ_index(List<Integer> zIndex) {
		z_index = zIndex;
	}


	
	
}
