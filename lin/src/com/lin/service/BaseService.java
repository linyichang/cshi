package com.lin.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lin.dao.BaseDao;
import com.lin.entity.BaseEntity;
import com.lin.utils.Global;
import com.lin.utils.SysPage;
import com.lin.utils.Tool;



@Transactional(readOnly = true)
public abstract class BaseService<D extends BaseDao<T>, T extends BaseEntity>{
	
	@Autowired
	private D dao;
	
	public T get(String id){
		return dao.get(id);
	}
	
	public List<T> findListSQL(String sql){
		return dao.findListSQL(sql);
	}
	
	public List<Map<String,Object>> findListSQLMap(String sql){
		return dao.findListSQLMap(sql);
	}
	
	public List<T> findList(T entity){
		return dao.findList(entity);
	}
	
	@Transactional(readOnly = false)
	public int save(T entity){
		if(Tool.isBlank(entity.getId())){
			entity.setId(UUID.randomUUID().toString().replace("-", ""));
			entity.setCreateDate(new Date());
			entity.setUpdateDate(new Date());
			return dao.insert(entity);
		}else{
			entity.setUpdateDate(new Date());
			return dao.update(entity);
		}
	}
	
	@Transactional(readOnly = false)
	public int delete(T entity){
		return dao.delete(entity);
	}
	
	@Transactional(readOnly = false)
	public void delete(List<T> list){
		if(list != null && list.size()>0){
			for(T entity : list){
				if(entity != null){
					dao.delete(entity);
				}
			}
		}
	}
	
	public int getCount(T entity){
		return dao.getCount(entity);
	}
	
	public SysPage<T> findPage(T entity,HttpServletRequest request){
		SysPage<T> sysPage = new SysPage<T>();
		if(entity.getFirst() == null){
			entity.setFirst(0);
		}
		entity.setMax(sysPage.getPageSize());
		
		List<T> list = dao.findList(entity);
		Integer count = dao.getCount(entity);
		
		if(entity.getFirst() < 1){
			sysPage.setPage(0);
		}else{
			sysPage.setPage(Tool.ceil(entity.getFirst(), sysPage.getPageSize()));
		}
		
		if(entity.getMax() != null && count>0){
			sysPage.setPageTotal(Tool.ceil(count, entity.getMax())); 
		}
		if(sysPage.getPage() > 0){
			sysPage.setPrevPage(sysPage.getPage()-1);
		}
		if(sysPage.getPage() < (sysPage.getPageTotal()-1)){
			sysPage.setNextPage(sysPage.getPage()+1);
		}else{
			sysPage.setNextPage(sysPage.getPage());
		}
		sysPage.setList(list);
		sysPage.setCount(count);
		sysPage.setUrl(request.getContextPath() + request.getServletPath());
		//System.out.println(sysPage.toString());
		return sysPage;
	}
	
	
	
}
