package com.jxwz.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.Video;
@Repository
public class VideoDao extends BaseDao<Video>{

	@SuppressWarnings("unchecked")
	public List<Video> findWithPage(int startNum, int pageSize) {
		DetachedCriteria criteria = super.getCriteria();
		List<Video> list = (List<Video>) this.getHibernateTemplate().findByCriteria(criteria,startNum,pageSize);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Video> findWithPage(Long sectionId, int startNum, int pageSize) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("section.id",sectionId));
		return (List<Video>) this.getHibernateTemplate().findByCriteria(criteria,startNum,pageSize);
	}

	public int queryCountBySec(Long sectionId) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("section.id",sectionId));
		Number num = (Number)criteria.getExecutableCriteria(this.currentSession()).uniqueResult();
		return num == null ? 0 : num.intValue();
	}

}
