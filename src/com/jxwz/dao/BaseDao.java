package com.jxwz.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.BaseEntity;
import com.jxwz.util.Constants;
@Repository
public abstract class BaseDao<T> extends HibernateDaoSupport{
	
	private Class<T> clazz;
	private DetachedCriteria criteria;
	
	@SuppressWarnings("unchecked")
	public BaseDao() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.criteria = DetachedCriteria.forClass(clazz);
	}
	
	public DetachedCriteria getCriteria() {
		criteria.add(Restrictions.eq("status",Constants.STATUS_ENABLE));
		return this.criteria;
	}
	
	@Resource    
    public void setSessionFacotry(SessionFactory sessionFacotry) {    
        super.setSessionFactory(sessionFacotry);    
    }    
	
	public void saveOrUpdate(Object obj) {
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
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		criteria.add(Restrictions.eq("status",Constants.STATUS_ENABLE));
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria criteria) {
		return (List<T>) getHibernateTemplate().findByCriteria(criteria);
	}
}
