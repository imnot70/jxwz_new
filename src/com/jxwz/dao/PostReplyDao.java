package com.jxwz.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.PostReply;

@Repository
public class PostReplyDao extends BaseDao<PostReply> {

	@SuppressWarnings("unchecked")
	public List<PostReply> selectByPostId(Long postId, int startNum, int pageSize) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("post.id",postId));
		criteria.addOrder(Order.asc("floorNum"));
		
		return (List<PostReply>) this.getHibernateTemplate().findByCriteria(criteria, startNum, pageSize);
	}

	public int findMaxFloorByPostId(Long postId) {
		Integer l = (Integer) this.getSessionFactory().getCurrentSession().createCriteria(PostReply.class).setProjection( Projections.projectionList().add(Projections.max("floorNum"))).uniqueResult();
		return l; 

	}

}
