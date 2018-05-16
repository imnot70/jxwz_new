package com.jxwz.service;

import java.util.List;

import com.jxwz.entity.User;

public interface UserService extends BaseService<User>{
//	void saveOrUpdate(User user);

	List<User> findAll();
	
//	boolean deleteById(Long id);

//	User findById(Long id);
	
	List<User> findByType(int userType);
	
	User findByUserName(String userName,String password,int userType);

	int checkUnique(String email, int userType);
	
	User findByEmailAndPw(String email,String password,int userType);
	
	User findByCode(String code,String pwd,int type);
	
	List<User> findByTypeWithPage(int type,int startNum,int pageSize);
	
	int countByType(int type);
	
	User findByCode(String code,int type);
}
