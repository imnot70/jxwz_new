package com.jxwz.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
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

}
