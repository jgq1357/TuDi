package com.land.daos;

import java.sql.SQLException;
import java.util.List;

import com.land.domain.Gtzyjcl;;

public interface GtzyjclDAO {
	public List<Gtzyjcl> getAllGtzyjcl(int start, int end) throws Exception;
	public Gtzyjcl getGtzyjclByDah(String dah) throws Exception;
	public List<Gtzyjcl> searchGtzyjclBy(String conditions, int start, int end) throws Exception;
	public int countTotal(String conditions) throws Exception;
	public boolean insertGtzycjl(List<Gtzyjcl> tzyjcls) throws SQLException;
}
