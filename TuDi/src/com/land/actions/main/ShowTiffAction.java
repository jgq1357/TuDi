package com.land.actions.main;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.daos.MlDAO;
import com.land.daos.MlDAOImpl;

public class ShowTiffAction extends LandSupport{
	private String dangAnHao;
	private String pageNum;
	private String tmId;
	private String dalb;
	

	Logger log = Logger.getLogger(ShowTiffAction.class);

	

	public String getDalb() {
		return dalb;
	}

	public void setDalb(String dalb) {
		this.dalb = dalb;
	}


	public String getTmId() {
		return tmId;
	}

	public void setTmId(String tmId) {
		this.tmId = tmId;
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

	@Override
	public String execute(){
		
		try {
			MlDAO dao = new MlDAOImpl();
			
			if(dalb.equalsIgnoreCase("djyw"))
			{
				tmId = dao.getFirstDjMl(dangAnHao,"DJLJ");
			}
			else if(dalb.equalsIgnoreCase("jsydl"))
			{
				tmId = dao.getFirstDjMl(dangAnHao,"JSYDLML");
			}
			else if(dalb.equalsIgnoreCase("dkl"))
			{
				tmId = dao.getFirstDjMl(dangAnHao,"DKLML");
			}
			else if(dalb.equalsIgnoreCase("gtzyjcl"))
			{
				tmId = dao.getFirstDjMl(dangAnHao,"GTZYJCLML");
			}
			else if(dalb.equalsIgnoreCase("zhwsl"))
			{
				tmId = dao.getFirstDjMl(dangAnHao,"ZHWSLML");
			}
			else if(dalb.equalsIgnoreCase("tdlyghl"))
			{
				tmId = dao.getFirstDjMl(dangAnHao,"TDLYGHLML");
			}
			
			System.out.println("ShowTiffAction :: tmId = " + tmId);
			
		} catch (SQLException e) {
			log.error("系统出错"+ "获取档案号为["+dangAnHao+"]的首个路径失败。");
			tmId = "tmId";
			log.error("获取档案号为["+dangAnHao+"]的首个路径失败。"+e.getMessage());
			log.error(e);
		}
		System.out.println("ShowTiff() dalb=" + dalb);
		System.out.println("ShowTiff() tmId=" + tmId);
		if("djyw".equalsIgnoreCase(dalb)) {
			logIntoDb("影像查看", "浏览了档案号为 ["+dangAnHao+"] 的档案。","C");
		}else if("jsydl".equalsIgnoreCase(dalb)) {
			logIntoDb("影像查看", "浏览了档案号为 ["+dangAnHao+"] 的档案。","E");
		}else if("gtzyjcl".equalsIgnoreCase(dalb)) {
			logIntoDb("影像查看", "浏览了档案号为 ["+dangAnHao+"] 的档案。","F");
		}else if("tdlyghl".equalsIgnoreCase(dalb)) {
			logIntoDb("影像查看", "浏览了档案号为 ["+dangAnHao+"] 的档案。","D");
		}else if("dkl".equalsIgnoreCase(dalb)) {
			logIntoDb("影像查看", "浏览了档案号为 ["+dangAnHao+"] 的档案。","I");
		}else if("zhwsl".equalsIgnoreCase(dalb)) {
			logIntoDb("影像查看", "浏览了档案号为 ["+dangAnHao+"] 的档案。","A");
		}else if("111111".equalsIgnoreCase(dalb)) {
			logIntoDb("影像查看", "浏览了档案号为 ["+dangAnHao+"] 的档案。","F");
		}
		
		return "success";
	}
	

	
	

}
