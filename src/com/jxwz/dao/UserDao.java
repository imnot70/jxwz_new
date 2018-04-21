package com.jxwz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jxwz.entity.User;

@Repository
public class UserDao extends BaseDao<User> {

	@SuppressWarnings("unchecked")
	public List<User> findByType(int userType) {
		String queryString = "from User where status = ? and userType = ?";
		return (List<User>) this.getHibernateTemplate().find(queryString, 1, userType);
	}

	public User findByUserName(String userName, String password, int userType) {
		String queryString = "from User where stauts = ? and login_name = ? and password = ? and user_type = ?";
		return (User) this.getHibernateTemplate().find(queryString, 1, userName, password, userType).get(0);
	}

}
