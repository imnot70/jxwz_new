package com.jxwz.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.Document;
@Repository
public class DocumentDao extends BaseDao<Document>{

	@SuppressWarnings("unchecked")
	public List<Document> getDocsWithPage(int startNum, int pageSize) {
		DetachedCriteria criteria = super.getCriteria();
		return (List<Document>) this.getHibernateTemplate().findByCriteria(criteria,startNum,pageSize);
	}

}
