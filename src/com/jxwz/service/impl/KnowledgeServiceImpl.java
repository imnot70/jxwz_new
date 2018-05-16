package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.KnowledgeDao;
import com.jxwz.entity.Knowledge;
import com.jxwz.service.KnowledgeService;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
	
	@Autowired
	private KnowledgeDao knowledgeDao;

	@Override
	public void saveOrUpdate(Knowledge knowledge) {
		knowledgeDao.saveOrUpdate(knowledge);
	}

	@Override
	public List<Knowledge> findAll() {
		return knowledgeDao.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		return knowledgeDao.deleteById(id);
	}

	@Override
	public Knowledge findById(Long id) {
		return (Knowledge) knowledgeDao.findById(id);
	}

	@Override
	public List<Knowledge> findBysectionId(Long secId, int startNum, int pageSize) {
		return knowledgeDao.findBySectionId(secId, startNum, pageSize);
	}

	@Override
	public int countBySectionId(Long secId) {
		return knowledgeDao.countBySectionID(secId);
	}

	@Override
	public List<Knowledge> findWithPage(int startNum, int pageSize) {
		
		return knowledgeDao.findWithPage(startNum,pageSize);
	}

	@Override
	public int queryCount() {
		return knowledgeDao.queryCount();
	}

}
