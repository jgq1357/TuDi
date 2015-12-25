package com.land.daos;

import java.sql.SQLException;
import java.util.List;

import com.land.domain.Djyw;
import com.land.domain.Tdlyghl;

public interface TdlyghlDAO {
	public List<Tdlyghl> getAllTdlyghl(int start, int end) throws Exception;
	public Tdlyghl getTdlyghlByDah(String dah) throws Exception;
	public List<Tdlyghl> searchTdlyghlBy(String conditions, int start, int end) throws Exception;
	public int countTotal(String conditions) throws Exception;
	public boolean insertTdlyghl(List<Tdlyghl> Tdlyghls) throws SQLException;
}
