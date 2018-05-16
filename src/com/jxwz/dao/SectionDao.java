package com.jxwz.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.Section;
import com.jxwz.util.Constants;
@Repository
public class SectionDao extends BaseDao<Section> {

	public List<Section> findSectionsByChapterId(Long id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Section.class);
		criteria.add(Restrictions.eq("status",Constants.STATUS_ENABLE));
		criteria.add(Restrictions.eq("chapter.id",id));
		criteria.addOrder(Order.asc("sort"));
		return findByCriteria(criteria);
	}

}
