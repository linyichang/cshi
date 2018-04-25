package com.lin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface BaseDao<T> {
	
	public T get(String id);
	
	public List<T> findListSQL(@Param("sql") String sql);
	
	public List<Map<String,Object>> findListSQLMap(@Param("sql") String sql);
	
	public List<T> findList(T entity);
	
	public int insert(T entity);
	
	public int update(T entity);
	
	public int delete(T entity);
	
	public int getCount(T entity);
	
}
