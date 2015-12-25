package com.land.services;

import java.util.List;

import com.land.daos.JsydlDAO;
import com.land.daos.JsydlDAOImp;
import com.land.domain.Jsydl;

public class JsydlServiceImp implements JsydlService{
	JsydlDAO jsydlDaos = new JsydlDAOImp();

	@Override
	public int countTotal(String conditions) throws Exception {
		// TODO Auto-generated method stub
		return jsydlDaos.countTotal(conditions);
	}

	@Override
	public Jsydl getJsydlByDah(String dah) throws Exception {
		return jsydlDaos.getJsydlByDah(dah);
	}

	@Override
	public List<Jsydl> searchBy(String conditions, int start, int end)
			throws Exception {
		if("All".equals(conditions)) {
			return jsydlDaos.getAllJsydl(start , end);
		}
		return jsydlDaos.searchJsydlBy(conditions,start,end);
	}
	
}
