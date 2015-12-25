package com.land.daos;

import java.sql.SQLException;
import java.util.List;

import com.land.domain.Djyw;

public interface DjywDAO {
	
	public List<Djyw> getAllDjyw(int start, int end) throws Exception;
	public Djyw getDjywByDah(String dah) throws Exception;
	public List<Djyw> searchDjywsBy(String conditions, int start, int end) throws Exception;
	public int countTotal(String conditions) throws Exception;
	public boolean insertDjyw(List<Djyw> djyws) throws SQLException;
	
}
