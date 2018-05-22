package com.jxwz.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.BaseEntity;
import com.jxwz.util.Constants;
@Repository
public abstract class BaseDao<T> extends HibernateDaoSupport{
	
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BaseDao() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public DetachedCriteria getCriteria() {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		criteria.add(Restrictions.eq("status",Constants.STATUS_ENABLE));
		return criteria;
	}
	
	@Resource    
    public void setSessionFacotry(SessionFactory sessionFacotry) {    
        super.setSessionFactory(sessionFacotry);    
    }    
	
	public void saveOrUpdate(Object obj) {
//		this.getHibernateTemplate().getSessionFactory().getCurrentSession().refresh(obj);
//		this.getHibernateTemplate().getSessionFactory().getCurrentSession().merge(obj);
		this.getHibernateTemplate().saveOrUpdate(obj);
	}
	
	public T findById(Long id) {
		return this.getHibernateTemplate().get(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public boolean deleteById(Long id) {
		T t = this.findById(id);
		if(t instanceof BaseEntity) {
			BaseEntity be = (BaseEntity) t;
			be.setStatus(Constants.STATUS_DISABLE);
			saveOrUpdate((T) be);
			return true;
		}else {
			return false;
		}
		
	}
	
	public List<T> findAll(){
		return findAllSortByCreateTime(0);
	}
	
	
	// sort排序方式,0:倒叙,1:正序,其他值不指定排序
	@SuppressWarnings("unchecked")
	public List<T> findAllSortByCreateTime(int sort){
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		criteria.add(Restrictions.eq("status",Constants.STATUS_ENABLE));
		if(sort == 0) {
			criteria.addOrder(Order.desc("createTime"));
		}
		if(sort == 1) {
			criteria.addOrder(Order.asc("createTime"));
		}
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria criteria) {
		return (List<T>) getHibernateTemplate().findByCriteria(criteria);
	}
	
	public int queryCount() {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		criteria.add(Restrictions.eq("status",Constants.STATUS_ENABLE));
		criteria.setProjection(Projections.rowCount());
		Number num = (Number)criteria.getExecutableCriteria(this.currentSession()).uniqueResult();
		return num.intValue();
	}
	
}
