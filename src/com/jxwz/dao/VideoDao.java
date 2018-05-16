package com.jxwz.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
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

}
