package com.land.services;

import java.util.List;

import com.land.daos.DjywDAO;
import com.land.daos.DjywDAOImpl;
import com.land.daos.TdlyghlDAO;
import com.land.daos.TdlyghlDAOImp;
import com.land.domain.Tdlyghl;

public class TdlyghlServiceImp implements TdlyghlService {
	TdlyghlDAO tdlyghlDaos = new TdlyghlDAOImp();
	@Override
	public int countTotal(String conditions) throws Exception {
		// TODO Auto-generated method stub
		return tdlyghlDaos.countTotal(conditions);
	}

	@Override
	public Tdlyghl getTdlyghlByDah(String dah) throws Exception {
		// TODO Auto-generated method stub
		return tdlyghlDaos.getTdlyghlByDah(dah);
	}

	@Override
	public List<Tdlyghl> searchBy(String conditions, int start, int end)
			throws Exception {
		if("All".equals(conditions)) {
			return tdlyghlDaos.getAllTdlyghl(start , end);
		}
		return tdlyghlDaos.searchTdlyghlBy(conditions,start,end);
	}

}
