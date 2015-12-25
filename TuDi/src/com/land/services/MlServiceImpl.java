package com.land.services;

import java.util.List;

import com.land.daos.MlDAO;
import com.land.daos.MlDAOImpl;
import com.land.domain.ML;

public class MlServiceImpl implements MlService{
	MlDAOImpl dao = new MlDAOImpl();


	public List<ML> getDjmlByID(String dah) throws Exception{
		
		return dao.getDjMlByID(dah);
	}
	
	public List<ML> getJsydlmlByID(String dah) throws Exception{
			
			return dao.getJsydlMlByID(dah);
		}
	
	public List<ML> getGtzyjclmlByID(String dah) throws Exception{
		
		return dao.getGtzyjclMlByID(dah);
	}
	
	public List<ML> getDklmlByID(String dah) throws Exception{
		
		return dao.getDklMlByID(dah);
	}
	
	public List<ML> getTdlyghlmlByID(String dah) throws Exception{
		
		return dao.getTdlyghlMlByID(dah);
	}
	
	public List<ML> getZhwslmlByID(String dah) throws Exception{
		
		return dao.getZhwslMlByID(dah);
	}

}
