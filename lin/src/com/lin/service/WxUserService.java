package com.lin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lin.dao.UserDao;
import com.lin.dao.WxUserDao;
import com.lin.entity.User;
import com.lin.entity.WxUser;

@Service
public class WxUserService implements WxUserDao{

	@Autowired
	private WxUserDao wxUserDao;
	
	public void saveUser(WxUser wxUser) {
	
		wxUserDao.saveUser(wxUser);;
	}
}
