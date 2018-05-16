package com.jxwz.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
		String queryString = "from User where status = ? and loginName = ? and password = ? and userType = ?";
		return (User) this.getHibernateTemplate().find(queryString, 1, userName, password, userType).get(0);
	}

	public int checkUnique(String email, int userType) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("userType", userType));
		Number num = (Number) criteria.getExecutableCriteria(this.currentSession()).uniqueResult();
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	public User findByEmailAndPw(String email, String password, int userType) {
		StringBuffer sb = new StringBuffer();
		List<User> users = null;
		sb.append("from User where status = ? and email = ?  and user_type = ?");
		if(password == null || "".equals(password)) {
			users = (List<User>) this.getHibernateTemplate().find(sb.toString(), 1, email, userType);
		}else {
			sb.append("and password = ?");
			users = (List<User>) this.getHibernateTemplate().find(sb.toString(), 1, email, userType,password);
		}
		return (users == null || users.size() == 0) ? null : users.get(0);
	}

	@SuppressWarnings("unchecked")
	public User findByCode(String code, String pwd, int type) {
		String queryString = "from User where status = ? and userCode = ? and password = ? and userType = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(queryString, 1, code, pwd, type);
		if(list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public User findByCode(String code,int type) {
		String queryString  = "from User where status = ? and userCode = ? and userType = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(queryString, 1, code, type);
		if(list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findByTypeWithPage(int type,int startNum,int pageSize){
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("userType", type));
		criteria.addOrder(Order.desc("createTime"));
		return (List<User>) this.getHibernateTemplate().findByCriteria(criteria, startNum, pageSize);
	}
	
	public int countByType(int type) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.eq("userType", type));
		Number num = (Number) criteria.getExecutableCriteria(this.currentSession()).uniqueResult();
		return num.intValue();
	}
	
}
