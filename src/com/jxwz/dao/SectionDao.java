package com.jxwz.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.Section;
@Repository
public class SectionDao extends BaseDao<Section> {

	public List<Section> findSectionsByChapterId(Long id) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("chapter.id",id));
		return findByCriteria(criteria);
	}

}
