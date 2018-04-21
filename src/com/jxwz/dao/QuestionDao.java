package com.jxwz.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.Question;
@Repository
public class QuestionDao extends BaseDao<Question> {
	
	public List<Question> findTest(Long sectionId){
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("section.id", sectionId));
		criteria.addOrder(Order.asc("id"));
		return (List<Question>) getHibernateTemplate().findByCriteria(criteria);
	}
	
}
