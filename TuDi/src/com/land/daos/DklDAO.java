package com.land.daos;

import java.sql.SQLException;
import java.util.List;

import com.land.domain.Dkl;


public interface DklDAO {
	public List<Dkl> getAllDkl(int start, int end) throws Exception;
	public Dkl getDklByDah(String dah) throws Exception;
	public List<Dkl> searchDklsBy(String conditions, int start, int end) throws Exception;
	public int countTotal(String conditions) throws Exception;
	public boolean insertDkl(List<Dkl> djyws) throws SQLException;
}
