package com.jxwz.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.Note;
@Repository
public class NoteDao extends BaseDao<Note> {

	@SuppressWarnings("unchecked")
	public List<Note> findByUserId(Long userId, int startNum, int pageSize) {
		
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("student.id",userId));
		return (List<Note>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	public int queryCountByUser(Long userId) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("student.id",userId));
		criteria.setProjection(Projections.rowCount());
		Number num = (Number)criteria.getExecutableCriteria(this.currentSession()).uniqueResult();
		return num.intValue();
	}
	
}
