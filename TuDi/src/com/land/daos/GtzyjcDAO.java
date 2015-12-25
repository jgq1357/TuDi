package com.land.daos;

import java.sql.SQLException;
import java.util.List;

import com.land.domain.Gtzyjc;

public interface GtzyjcDAO {
	public List<Gtzyjc> getAllGtzyjc(int start, int end) throws Exception;
	public Gtzyjc getGtzyjcById(String id) throws Exception;
	public List<Gtzyjc> searchGtzyjcBy(String conditions, int start, int end) throws Exception;
	public int countTotal(String conditions) throws Exception;
	public boolean insertGtzyjc(List<Gtzyjc> gtzyjc) throws SQLException;
}
