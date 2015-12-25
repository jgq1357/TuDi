package com.land.services;

import java.util.List;

import com.land.daos.DjywDAO;
import com.land.daos.DjywDAOImpl;
import com.land.daos.ZhwslDAO;
import com.land.daos.ZhwslDAOImp;
import com.land.domain.Zhwsl;

public class ZhwslServiceImp implements ZhwslService {
	ZhwslDAO zhwslDaos = new ZhwslDAOImp();
	@Override
	public int countTotal(String conditions) throws Exception {
		// TODO Auto-generated method stub
		return zhwslDaos.countTotal(conditions);
	}

	@Override
	public Zhwsl getZhwslByDah(String dah) throws Exception {
		// TODO Auto-generated method stub
		return zhwslDaos.getZhwslByDah(dah);
	}

	@Override
	public List<Zhwsl> searchBy(String conditions, int start, int end)
			throws Exception {
		if("All".equals(conditions)) {
			return zhwslDaos.getAllZhwsl(start , end);
		}
		return zhwslDaos.searchZhwslBy(conditions,start,end);
	}

}
