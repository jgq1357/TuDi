package com.land.services;

import java.util.List;

import com.land.daos.DjywDAO;
import com.land.daos.DjywDAOImpl;
import com.land.domain.Djyw;

public class DjywServiceImpl implements DjywService{
	DjywDAO djywDaos = new DjywDAOImpl();
	@Override
	public List<Djyw> searchBy(String conditions,int start, int end) throws Exception {
		if("All".equals(conditions)) {
			return djywDaos.getAllDjyw(start , end);
		}
		return djywDaos.searchDjywsBy(conditions,start,end);
	}
	
	public Djyw getDjywByDah(String dah) throws Exception {
		return djywDaos.getDjywByDah(dah);
	}
 
	@Override
	public int countTotal(String conditions) throws Exception {
		// TODO Auto-generated method stub
		return djywDaos.countTotal(conditions);
	}


}
