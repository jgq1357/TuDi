package com.land.actions.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.land.actions.LandSupport;
import com.land.domain.Print;
import com.land.util.Constant;
import com.land.util.DangAnFileViewer;

public class PrintAction extends LandSupport{
	private List<Print> prints = new ArrayList<Print>();
	private String dangAnHao;
	private String dalb;
	
	public String execute() {
		getDealPrints();
		return "success";
	}
	
	private void getDealPrints() {
		Map<String, String> prePathMap = Constant.PREPATHMAP;
		String prepath = "";
		prepath = prePathMap.get(dalb);
		/*if("djyw".equalsIgnoreCase(dalb)) {
			prepath = Constant.PREPATH;
		}else if("jsyd".equalsIgnoreCase(dalb)) {
			prepath = Constant.JSYD_PREPATH;
		}else if("gtzyjc".equalsIgnoreCase(dalb)) {
			prepath = Constant.GTZYJC_PREPATH;
		}*/
		prepath = prepath + dangAnHao;
		DangAnFileViewer d = new DangAnFileViewer(prepath, "jpg", true);
		d.start();
		d.sort();
		int total = d.getFileList().size();
		for(int i=0;i<total;i++) {
			String str[] = d.getFileList().get(i).split(",");
			Print p = new Print();
			p.setDangAnHao(dangAnHao);
			p.setPageNum(str[0]);
			p.setTmId(str[1]);
			prints.add(p);
		}
	}

	public List<Print> getPrints() {
		return prints;
	}

	public void setPrints(List<Print> prints) {
		this.prints = prints;
	}

	public String getDangAnHao() {
		return dangAnHao;
	}

	public void setDangAnHao(String dangAnHao) {
		this.dangAnHao = dangAnHao;
	}

	public String getDalb() {
		return dalb;
	}

	public void setDalb(String dalb) {
		this.dalb = dalb;
	}
	
	
}
