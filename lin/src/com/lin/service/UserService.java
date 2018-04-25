package com.lin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lin.dao.UserDao;
import com.lin.entity.User;

@Service
public class UserService implements UserDao{

	@Autowired
	private UserDao userDao;
	
	public List<User> find(User user){
	  return userDao.find(user);
	}
	
	public User login(User user){
		return userDao.login(user);
	}

	public User findById(Integer id) {
		return userDao.findById(id);
	}
	
	
}
