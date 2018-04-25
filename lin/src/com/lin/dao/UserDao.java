package com.lin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.lin.entity.User;
import com.lin.utils.MybatiseDao;

@MybatiseDao
public interface UserDao {

	public List<User> find(User user);
	
	public User login(User user);
	
	public User findById(@Param("id")Integer id);
}
