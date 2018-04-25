package com.lin.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lin.dao.BaseDao;
import com.lin.sys.entity.SysMenu;
import com.lin.sys.entity.SysUser;
import com.lin.utils.MybatiseDao;


@MybatiseDao
public interface SysUserDao extends BaseDao<SysUser>{

	public List<SysMenu> findMenu(@Param("id")String id);
}
