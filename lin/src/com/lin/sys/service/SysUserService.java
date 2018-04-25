package com.lin.sys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lin.service.BaseService;
import com.lin.sys.dao.SysUserDao;
import com.lin.sys.entity.SysMenu;
import com.lin.sys.entity.SysUser;

@Service
public class SysUserService extends BaseService<SysUserDao, SysUser> {

	@Autowired
	private SysUserDao userDao;

	public List<SysMenu> findMenu(String id) {
		return userDao.findMenu(id);
	}
}
