package com.jxwz.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jxwz.entity.WordsReply;
@Repository
public class WordsReplyDao extends BaseDao<WordsReply> {

	@SuppressWarnings("unchecked")
	public WordsReply findByWrodsId(Long wordsId) {
		DetachedCriteria criteria = super.getCriteria();
		criteria.add(Restrictions.eqOrIsNull("words.id", wordsId));
		List<WordsReply> list = (List<WordsReply>) this.getHibernateTemplate().findByCriteria(criteria);
		return (list == null ||list.size() == 0) ? null : list.get(0);
	}

}
