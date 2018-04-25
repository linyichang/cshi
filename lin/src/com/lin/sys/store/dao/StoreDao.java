package com.lin.sys.store.dao;

import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lin.dao.BaseDao;
import com.lin.sys.entity.SysCity;
import com.lin.sys.store.entity.Store;


@Repository
public interface StoreDao extends BaseDao<Store>{

}
