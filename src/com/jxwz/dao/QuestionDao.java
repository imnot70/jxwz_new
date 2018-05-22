package com.jxwz.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.Question;
@Repository
public class QuestionDao extends BaseDao<Question> {
	
	@SuppressWarnings("unchecked")
	public List<Question> findTest(Long sectionId){
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("section.id", sectionId));
		criteria.addOrder(Order.asc("id"));
		return (List<Question>) getHibernateTemplate().findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Question> findByUserId(Long userId,int startNum,int pageSize){
		String queryString = "select * from t_question q left join t_user_que u on q.id = u.q_id where u.s_id="+userId;
		SQLQuery sq =  getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(queryString);
		sq.addEntity(Question.class);
		sq.setFirstResult(startNum);
		sq.setMaxResults(pageSize);
		return sq.list();
	}
	
	@SuppressWarnings("unchecked")
	public int queryCountByUserId(Long userId) {
		String queryString = "select count(*) from t_question q left join t_user_que u on q.id = u.q_id where u.s_id="+userId;
		SQLQuery sq =  getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(queryString);
		List<BigInteger> list = sq.list();
		return (list == null || list.size() == 0) ? 0 : list.get(0).intValue();
	}

	public void deleteUserIncor(Long userId, Long queId) {
		String queryString = "delete from t_user_que where q_id = "+queId + " and s_id=" + userId;
		SQLQuery query =  getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(queryString);
		query.executeUpdate();
	}
}
