package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.UserDao;
import com.jxwz.entity.User;
import com.jxwz.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> findByType(int userType) {
		
		return userDao.findByType(userType);
	}

	@Override
	public User findByUserName(String userName, String password, int userType) {
		
		return userDao.findByUserName(userName,password,userType);
	}

	@Override
	public void saveOrUpdate(User user) {
		userDao.saveOrUpdate(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		return userDao.deleteById(id);
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id);
	}

}
