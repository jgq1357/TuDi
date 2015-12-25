package com.land.services;

import java.util.List;

import com.land.domain.Djyw;
import com.land.domain.Gtzyjcl;

public interface GtzyjclService {
	public List<Gtzyjcl> searchBy(String conditions,int start, int end) throws Exception;
	public Gtzyjcl getGtzyjclByDah(String dah) throws Exception;
	public int countTotal(String conditions) throws Exception;
}
