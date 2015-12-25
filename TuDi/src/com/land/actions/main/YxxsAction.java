package com.land.actions.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.domain.BigPic;
import com.land.domain.SmallPic;
import com.land.util.Constant;
import com.land.util.DangAnFileViewer;

public class YxxsAction extends LandSupport{
	private List<SmallPic> smalls = new ArrayList<SmallPic>();
	private List<BigPic> bigs = new ArrayList<BigPic>();
	private String dangAnHao;
	private String dalb;
	private String tmId;
	private String errormessage;
	Logger log = Logger.getLogger(YxxsAction.class);
	
	public String execute() {
		
		if(tmId.trim().length()==0) {
			errormessage = "对不起，该宗档案["+dangAnHao+"]暂无影像。";
			return "input";
		}
		if(tmId.equals("tmId")) {
			errormessage = "系统出错，获取["+dangAnHao+"]的路径失败。";
			return "input";
		}
		
		String prepath = "";
		Map<String, String> prePathMap = Constant.PREPATHMAP;
		prepath = prePathMap.get(dalb);
		String[] datas = dangAnHao.split("-");
//		String qzh=datas[0];
		String mlh=datas[1];
		String flh=datas[2];
		
		prepath = prepath + flh + "\\" + mlh+"\\"+dangAnHao;

		log.info("影像路径："+prepath);
		DangAnFileViewer d = new DangAnFileViewer(prepath, "jpg", true);
		d.start();
		
		int total = d.getFileList().size();
		System.out.println("total = " + total);
		//tmid保存该目录的起始页数,如果为all表示所有影像都在用一个目录下
		int startNum = 0;
		int stopNum = 0;
		if(tmId.equalsIgnoreCase("all"))
		{
			startNum = 0;
			stopNum = total - 1;
		}
		else
		{
			try{
				String[] nums = tmId.split("-");
				startNum = Integer.valueOf(nums[0]) - 1;
				stopNum = Integer.valueOf(nums[1]) - 1;
			}catch(Exception e){
				log.info("影像路径："+tmId + "格式 不正确");
				errormessage = "对不起，影像路径["+tmId+"]格式不正确。";
				return "input";
			}
		}
		
		for(int i=startNum;i<=stopNum;i+=3) {
			String str[] = d.getFileList().get(i).split(",");
			SmallPic sp = new SmallPic();
			sp.setDangAnHao(dangAnHao);
			sp.setTm(tmId);
			sp.setId("pic_");
			sp.setPageNum1(Integer.parseInt(str[0])+"");
			if(i+1<=stopNum) {
				str = d.getFileList().get(i+1).split(",");
				sp.setPageNum2(Integer.parseInt(str[0])+"");
			}else {
				sp.setPageNum2("N");
			}
			if(i+2<=stopNum) {
				str = d.getFileList().get(i+2).split(",");
				sp.setPageNum3(Integer.parseInt(str[0])+"");
			}else {
				sp.setPageNum3("N");
			}
			smalls.add(sp);
		}
		
		for(int i=startNum;i<=stopNum;i++) {
			BigPic bp = new BigPic();
			String str[] = d.getFileList().get(i).split(",");
			bp.setDangAnHao(dangAnHao);
			bp.setTm(tmId);
			bp.setId("pic_");
			bp.setPageNum(Integer.parseInt(str[0])+"");
			bigs.add(bp);
		}
		return "success";
	}
	
	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public List<SmallPic> getSmalls() {
		return smalls;
	}

	public void setSmalls(List<SmallPic> smalls) {
		this.smalls = smalls;
	}

	public List<BigPic> getBigs() {
		return bigs;
	}

	public void setBigs(List<BigPic> bigs) {
		this.bigs = bigs;
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

	public String getTmId() {
		return tmId;
	}

	public void setTmId(String tmId) {
		this.tmId = tmId;
	}
	
	
}
