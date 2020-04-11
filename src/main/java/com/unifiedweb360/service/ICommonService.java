package com.unifiedweb360.service;

import java.util.List;

public interface ICommonService<T,ID> {
	
	public T find(ID id);
	
	public List<T> findAll();
	
	public void save(T t);
	
	public void delete(ID id);
}
