package com.land.services;

import java.util.List;

import com.land.daos.GtzyjcDAO;
import com.land.daos.GtzyjcDAOImp;
import com.land.domain.Gtzyjc;

public class GtzyjcServiceImpl implements GtzyjcService{

	GtzyjcDAO gtzyjcDaos = new GtzyjcDAOImp();
	@Override
	public List<Gtzyjc> searchBy(String conditions,int start, int end) throws Exception {
		if("All".equals(conditions)) {
			return gtzyjcDaos.getAllGtzyjc(start , end);
		}
		return gtzyjcDaos.searchGtzyjcBy(conditions,start,end);
	}
	
	public Gtzyjc getGtzyjcById(String id) throws Exception {
		return gtzyjcDaos.getGtzyjcById(id);
	}
 
	@Override
	public int countTotal(String conditions) throws Exception {
		// TODO Auto-generated method stub
		return gtzyjcDaos.countTotal(conditions);
	}
}
