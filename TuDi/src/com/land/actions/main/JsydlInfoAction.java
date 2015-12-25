package com.land.actions.main;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.domain.Jsydl;
import com.land.services.JsydlService;
import com.land.services.JsydlServiceImp;

public class JsydlInfoAction extends LandSupport {
	private JsydlService jsydlService = new JsydlServiceImp();
	private Jsydl jsydl;
//	private List<Djyw> olds;
//	private List<Djyw> futures;
	private String dah;
	Logger log = Logger.getLogger(JsydlInfoAction.class);
	@Override
	public String execute(){
		
		// TODO Auto-generated method stub
		try {
			jsydl = jsydlService.getJsydlByDah(dah);
			
//			getOlds(djyw.getYqsm());
//			getFutures(djyw.getQsm());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("查看出错"+"显示地籍档案信息失败。");
			log.error("显示地籍档案信息失败。"+e.getMessage());
			log.error(e);
		}
		String dah = jsydl.getQzh()+"-"+jsydl.getMlh()+"-"+jsydl.getFlh()+"-"+jsydl.getAjh();
		log.info("影像查看"+"查看了档案号为 ["+dah+"] 的所有属性。");
		return "success";
	}
	
//
//	
//	private void getOlds(String yqsm) {
//		String[] yq = yqsm.split(",");
//		String conditions = "";
//		int total = yq.length;
//		if(total==0) {
//			return;
//		}
//		for(int i=0;i<total;i++) {
//			conditions = conditions +" lower(qsm)='"+yq[i].toLowerCase()+"'";
//			if(i!=(total-1)) {
//				conditions = conditions +" or";
//			}
//		}
//		try {
//			olds = djywService.searchBy(conditions,1,100);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private void getFutures(String qsm) {
//		 String conditions = " lower(yqsm) like '%"+qsm.toLowerCase()+"%'";
//		 try {
//			futures = djywService.searchBy(conditions,1,100);
//			for(int i=futures.size()-1;i>=0;i--) {
//				Djyw dj = futures.get(i);
//				String[] qs = dj.getYqsm().split(",");
//				boolean flag = true;
//				for(int j=0;j<qs.length;j++) {
//					if(qsm.equals(qs[j])){
//						flag = false;
//						break;
//					}
//				}
//				if(flag) {
//					futures.remove(i);
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	
	public String getDah() {
		return dah;
	}



	public Jsydl getJsydl() {
		return jsydl;
	}

	public void setJsydl(Jsydl jsydl) {
		this.jsydl = jsydl;
	}



	public void setDah(String dah) {
		this.dah = dah;
	}

//	public Djyw getDjyw() {
//		return djyw;
//	}
//
//	public void setDjyw(Djyw djyw) {
//		this.djyw = djyw;
//	}
//
//	public List<Djyw> getOlds() {
//		return olds;
//	}
//
//	public void setOlds(List<Djyw> olds) {
//		this.olds = olds;
//	}
//
//	public List<Djyw> getFutures() {
//		return futures;
//	}
//
//	public void setFutures(List<Djyw> futures) {
//		this.futures = futures;
//	}
	
	
	

}
