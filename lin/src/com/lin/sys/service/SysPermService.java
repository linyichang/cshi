package com.lin.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lin.service.BaseService;
import com.lin.sys.dao.SysPermDao;
import com.lin.sys.entity.SysPerm;



@Service
@Transactional(readOnly = true)
public class SysPermService extends BaseService<SysPermDao, SysPerm> {

	
}