package com.land.actions.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.domain.ML;
import com.land.domain.Permission;
import com.land.domain.User;
import com.land.services.MlServiceImpl;

public class YxmlAction extends LandSupport {

	private MlServiceImpl mlService = new MlServiceImpl();
	private List<ML> djljs = new ArrayList<ML>();
	private int djywId;
	private String dangAnHao;
	private String pageNum;
	private String dalb;
	private int tmAmount;
	private String allowprint="N";
	Logger log = Logger.getLogger(YxmlAction.class);
	
	public String execute(){
		try {
			System.out.println("YxmlAction excute() dalb = " + dalb);
			if(dalb.equalsIgnoreCase("djyw"))
			{
				djljs = mlService.getDjmlByID(dangAnHao);
			}
			else if(dalb.equalsIgnoreCase("jsydl"))
			{
				djljs = mlService.getJsydlmlByID(dangAnHao);
			}
			else if(dalb.equalsIgnoreCase("dkl"))
			{
				djljs = mlService.getDklmlByID(dangAnHao);
			}
			else if(dalb.equalsIgnoreCase("gtzyjcl"))
			{
				djljs = mlService.getGtzyjclmlByID(dangAnHao);
			}
			else if(dalb.equalsIgnoreCase("zhwsl"))
			{
				djljs = mlService.getZhwslmlByID(dangAnHao);
			}
			else if(dalb.equalsIgnoreCase("tdlyghl"))
			{
				djljs = mlService.getTdlyghlmlByID(dangAnHao);
			}
			
			tmAmount = djljs.size();
			User user = (User) session.get("user");
			List<Permission> pers = user.getPermissions();
			for(int i=0;i<pers.size();i++) {
				Permission p = pers.get(i);
				if(p.getId()==25) {
					allowprint="Y";
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("查看出错"+"影像目录读取出错。");
			log.error("影像目录读取出错。"+e.getMessage());
			log.error(e);
		}
		return "success";
	}

	public String getAllowprint() {
		return allowprint;
	}


	public void setAllowprint(String allowprint) {
		this.allowprint = allowprint;
	}


	public List<ML> getDjljs() {
		return djljs;
	}
	public void setDjljs(List<ML> djljs) {
		this.djljs = djljs;
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
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public String getDalb() {
		return dalb;
	}
	public void setDalb(String dalb) {
		this.dalb = dalb;
	}
	public int getTmAmount() {
		return tmAmount;
	}
	public void setTmAmount(int tmAmount) {
		this.tmAmount = tmAmount;
	}

	
}
