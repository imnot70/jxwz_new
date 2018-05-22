package com.jxwz.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.Words;
@Repository
public class WordsDao extends BaseDao<Words> {
	
	@SuppressWarnings("unchecked")
	public List<Words> findByUserId(Long userId,int startNum,int pageSize){
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("student.id",userId));
		return (List<Words>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	public int queryCountByUser(Long id) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("student.id",id));
		criteria.setProjection(Projections.rowCount());
		Number num = (Number)criteria.getExecutableCriteria(this.currentSession()).uniqueResult();
		return num.intValue();
	}

}
