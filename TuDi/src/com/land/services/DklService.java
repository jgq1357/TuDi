package com.land.services;

import java.util.List;

import com.land.domain.Dkl;


public interface DklService {
	public List<Dkl> searchBy(String conditions,int start, int end) throws Exception;
	public Dkl getDklByDah(String dah) throws Exception;
	public int countTotal(String conditions) throws Exception;
}
