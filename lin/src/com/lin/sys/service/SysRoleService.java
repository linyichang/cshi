package com.lin.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lin.sys.dao.SysRoleDao;
import com.lin.sys.entity.SysRole;
import com.lin.service.BaseService;



@Service
@Transactional(readOnly = true)
public class SysRoleService extends BaseService<SysRoleDao, SysRole> {

	
}