package com.jxwz.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.Post;

@Repository
public class PostDao extends BaseDao<Post> {

	@SuppressWarnings("unchecked")
	public List<Post> getPostWithPage(int startNum, int pageSize) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.addOrder(Order.desc("createTime"));
		return (List<Post>) this.getHibernateTemplate().findByCriteria(criteria,startNum,pageSize);
	}

	public int queryCountByUser(Long id) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("user.id",id));
		criteria.setProjection(Projections.rowCount());
		Number num = (Number)criteria.getExecutableCriteria(this.currentSession()).uniqueResult();
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Post> findByUser(Long userId, int startNum, int pageSize) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eqOrIsNull("user.id", userId));
		criteria.addOrder(Order.desc("createTime"));
		return (List<Post>) this.getHibernateTemplate().findByCriteria(criteria,startNum,pageSize);
	}

	
}
