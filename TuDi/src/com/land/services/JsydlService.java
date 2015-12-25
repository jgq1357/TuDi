package com.land.services;

import java.util.List;

import com.land.domain.Jsydl;


public interface JsydlService {
	public List<Jsydl> searchBy(String conditions,int start, int end) throws Exception;
	public Jsydl getJsydlByDah(String dah) throws Exception;
	public int countTotal(String conditions) throws Exception;
}
