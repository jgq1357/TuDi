package com.land.services;

import java.util.List;

import com.land.daos.DjywDAO;
import com.land.daos.DjywDAOImpl;
import com.land.daos.DklDAO;
import com.land.daos.DklDAOImp;
import com.land.domain.Dkl;

public class DklServiceImp implements DklService {
	DklDAO dklDaos = new DklDAOImp();
	@Override
	public int countTotal(String conditions) throws Exception {
		return dklDaos.countTotal(conditions);
	}

	@Override
	public Dkl getDklByDah(String dah) throws Exception {
		// TODO Auto-generated method stub
		return dklDaos.getDklByDah(dah);
	}

	@Override
	public List<Dkl> searchBy(String conditions, int start, int end)
			throws Exception {
		if("All".equals(conditions)) {
			return dklDaos.getAllDkl(start , end);
		}
		return dklDaos.searchDklsBy(conditions,start,end);
	}

}
