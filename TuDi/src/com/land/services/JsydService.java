package com.land.services;

import java.util.List;

import com.land.domain.Jsyd;

public interface JsydService {
	public List<Jsyd> searchBy(String conditions,int start, int end) throws Exception;
	public Jsyd getJsydById(String id) throws Exception;
	public int countTotal(String conditions) throws Exception;
}
