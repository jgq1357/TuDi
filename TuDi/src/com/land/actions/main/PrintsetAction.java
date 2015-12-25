package com.land.actions.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.domain.ML;
import com.land.domain.Print;
import com.land.services.MlService;
import com.land.services.MlServiceImpl;
import com.land.util.Constant;
import com.land.util.DangAnFileViewer;

@SuppressWarnings("unchecked")
public class PrintsetAction extends LandSupport{
	private String dangAnHao;
	private Map<String, List<Print>> damaps = (Map<String, List<Print>>)new ListOrderedMap();
	private String dalb;
	private int djywId;
	private MlService djljService = new MlServiceImpl();
	private List<ML> djljs = new ArrayList<ML>();
	Logger log = Logger.getLogger(PrintsetAction.class);
	
	public String execute() {
		try {
			djljs = djljService.getDjmlByID(dangAnHao);
			getDealPrints();
			logToDB();
		} catch (Exception e) {
			log.error("获取打印信息失败.");
			log.error(e);
		}
		
		return "success";
	}
	
	private void logToDB() {
		if("djyw".equalsIgnoreCase(dalb)) {
			logIntoDb("影像打印", "打印了档案号为 ["+dangAnHao+"] 的档案。","C");
		}else if("jsyd".equalsIgnoreCase(dalb)) {
			logIntoDb("影像打印", "打印了档案号为 ["+dangAnHao+"] 的档案。","E");
		}else if("gtzyjcl".equalsIgnoreCase(dalb)) {
			logIntoDb("影像打印", "打印了档案号为 ["+dangAnHao+"] 的档案。","F");
		}
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
		
		String[] datass = dangAnHao.split("-");
//		String qzh=datass[0];
		String mlh=datass[1];
		String flh=datass[2];
//		String ajh=datass[3];
		

		
		prepath = prepath + flh + "\\" + mlh+"\\"+dangAnHao;
		System.out.println("getDealPrints()----"+prepath);
		DangAnFileViewer d = new DangAnFileViewer(prepath, "jpg", true);
		d.start();
		d.sort();
		int total = d.getFileList().size();
		for(int j=0;j<djljs.size();j++) 
		{
			ML dj = djljs.get(j);
			String key=dj.getTm();
			List<Print> list = new ArrayList<Print>();
			for(int i=0;i<total;i++) {
				String str[] = d.getFileList().get(i).split(",");
				int pageNum = Integer.valueOf(str[0]);
				//int tmid = Integer.valueOf(str[1]);
				System.out.println("pageNum = " + str[0] + "---TmId = " + str[1]);
				System.out.println("djlj = " + dj.getLj().trim());
				String djlj = dj.getLj().trim();
				if(djlj.equalsIgnoreCase("all")){
					Print p = new Print();
					p.setDangAnHao(dangAnHao);
					p.setPageNum(Integer.parseInt(str[0])+"");
					p.setTmId(str[1]);
					list.add(p);
				}
				else
				{
					String[] datas = djlj.split("-");
					for (int k = 0; k < datas.length; k++)
					{
						System.out.println(datas[0]);
					}
					int start = Integer.valueOf(datas[0]);
					int stop = Integer.valueOf(datas[1]);
					if(pageNum >= start && pageNum <= stop)
					{
						Print p = new Print();
						p.setDangAnHao(dangAnHao);
						p.setPageNum(Integer.parseInt(str[0])+"");
						p.setTmId(str[1]);
						list.add(p);
					}
				}
//				if(dj.getLj().trim().equalsIgnoreCase(str[1].trim())) {
//					Print p = new Print();
//					p.setDangAnHao(dangAnHao);
//					p.setPageNum(Integer.parseInt(str[0])+"");
//					p.setTmId(str[1]);
//					list.add(p);
//				}
			}
			damaps.put(key, list);
		}
		
	}

	
	public int getDjywId() {
		return djywId;
	}

	public void setDjywId(int djywId) {
		this.djywId = djywId;
	}

	public String getDangAnHao() {
		return dangAnHao;
	}

	public void setDangAnHao(String dangAnHao) {
		this.dangAnHao = dangAnHao;
	}

	

	public Map<String, List<Print>> getDamaps() {
		return damaps;
	}

	public void setDamaps(Map<String, List<Print>> damaps) {
		this.damaps = damaps;
	}

	public String getDalb() {
		return dalb;
	}

	public void setDalb(String dalb) {
		this.dalb = dalb;
	}
	
	
}
