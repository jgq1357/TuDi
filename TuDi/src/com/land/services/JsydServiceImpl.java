package com.land.services;

import java.util.List;

import com.land.daos.JsydDAO;
import com.land.daos.JsydDAOImp;
import com.land.domain.Jsyd;

public class JsydServiceImpl implements JsydService{
	JsydDAO jsydDaos = new JsydDAOImp();
	@Override
	public List<Jsyd> searchBy(String conditions,int start, int end) throws Exception {
		if("All".equals(conditions)) {
			return jsydDaos.getAllJsyd(start , end);
		}
		return jsydDaos.searchJsydBy(conditions,start,end);
	}
	
	public Jsyd getJsydById(String id) throws Exception {
		return jsydDaos.getJsydById(id);
	}
 
	@Override
	public int countTotal(String conditions) throws Exception {
		// TODO Auto-generated method stub
		return jsydDaos.countTotal(conditions);
	}

}
