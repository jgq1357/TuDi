package com.land.services;

import java.util.List;

import com.land.daos.DjywDAO;
import com.land.daos.DjywDAOImpl;
import com.land.daos.GtzyjclDAO;
import com.land.daos.GtzyjclDAOImp;
import com.land.domain.Djyw;
import com.land.domain.Gtzyjcl;

public class GtzyjclServiceImp implements GtzyjclService {
	GtzyjclDAO gtzyjclDAO = new GtzyjclDAOImp();
	
	@Override
	public int countTotal(String conditions) throws Exception {
		return gtzyjclDAO.countTotal(conditions);
	}

	@Override
	public Gtzyjcl getGtzyjclByDah(String dah) throws Exception {
		// TODO Auto-generated method stub
		return gtzyjclDAO.getGtzyjclByDah(dah);
	}

	@Override
	public List<Gtzyjcl> searchBy(String conditions, int start, int end)
			throws Exception {
		if("All".equals(conditions)) {
			return gtzyjclDAO.getAllGtzyjcl(start , end);
		}
		return gtzyjclDAO.searchGtzyjclBy(conditions,start,end);
	}

}
