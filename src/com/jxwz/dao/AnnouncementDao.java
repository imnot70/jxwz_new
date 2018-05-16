package com.jxwz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.Announcement;
@Repository
public class AnnouncementDao extends BaseDao<Announcement> {

	@SuppressWarnings("unchecked")
	public List<Announcement> findLast() {
		String sql = "from Announcement a where a.status = 1 order by a.createTime desc";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(sql).setMaxResults(10);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Announcement> findWithPage(int startNum,int pageSize){
		DetachedCriteria criteria = getCriteria();
		criteria.addOrder(Order.desc("createTime"));
		return (List<Announcement>) this.getHibernateTemplate().findByCriteria(criteria, startNum, pageSize);
	}
	
}
