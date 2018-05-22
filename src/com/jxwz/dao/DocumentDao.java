package com.jxwz.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.Document;
@Repository
public class DocumentDao extends BaseDao<Document>{

	@SuppressWarnings("unchecked")
	public List<Document> getDocsWithPage(int startNum, int pageSize) {
		DetachedCriteria criteria = super.getCriteria();
		return (List<Document>) this.getHibernateTemplate().findByCriteria(criteria,startNum,pageSize);
	}

	public int queryCountByUser(Long id) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("teacher.id",id));
		criteria.setProjection(Projections.rowCount());
		Number num = (Number)criteria.getExecutableCriteria(this.currentSession()).uniqueResult();
		return num == null ? 0 : num.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Document> findByTeacher(Long id, int startNum, int pageSize) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("teacher.id",id));
		return (List<Document>) getHibernateTemplate().findByCriteria(criteria, startNum, pageSize);
	}

}
