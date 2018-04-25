package com.lin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.lin.entity.User;
import com.lin.entity.WxUser;
import com.lin.utils.MybatiseDao;

@MybatiseDao
public interface WxUserDao {

	
	//public User findById(@Param("id")Integer id);
	
	public void saveUser(WxUser wxUser);
	
	
}
