package com.jxwz.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.Knowledge;

@Repository
public class KnowledgeDao extends BaseDao<Knowledge> {
	
	@SuppressWarnings("unchecked")
	public List<Knowledge> findBySectionId(Long secId,int startNum,int pageSize){
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("section.id",secId));
		criteria.addOrder(Order.desc("createTime"));
		List<Knowledge> list = (List<Knowledge>) this.getHibernateTemplate().findByCriteria(criteria, startNum, pageSize);
		criteria.setProjection(null);
		return list;
	}
	
	public int countBySectionID(Long secId) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("section.id",secId));
		Long totalCount = (Long) criteria.setProjection(Projections.rowCount()).getExecutableCriteria(this.getHibernateTemplate().getSessionFactory().getCurrentSession()).uniqueResult();
		criteria.setProjection(null);
		Number num = (Number) totalCount;
		return num.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Knowledge> findWithPage(int startNum, int pageSize) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.addOrder(Order.desc("createTime"));
		List<Knowledge> list = (List<Knowledge>) this.getHibernateTemplate().findByCriteria(criteria, startNum, pageSize);
		return list;
	}

}
