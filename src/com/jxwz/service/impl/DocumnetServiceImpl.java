package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.DocumentDao;
import com.jxwz.entity.Document;
import com.jxwz.service.DocumentService;

@Service
public class DocumnetServiceImpl implements DocumentService {

	@Autowired
	private DocumentDao documentDao;
	
	@Override
	public void saveOrUpdate(Document document) {
		documentDao.saveOrUpdate(document);
	}

	@Override
	public List<Document> findAll() {
		return documentDao.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		return documentDao.deleteById(id);
	}

	@Override
	public Document findById(Long id) {
		return documentDao.findById(id);
	}

	@Override
	public List<Document> getDocsWithPage(int startNum, int pageSize) {
		return documentDao.getDocsWithPage(startNum,pageSize);
	}

	@Override
	public int queryCount() {
		return documentDao.queryCount();
	}

	@Override
	public int queryCountByUser(Long id) {
		return documentDao.queryCountByUser(id);
	}

	@Override
	public List<Document> findByTeacher(Long id, int startNum, int pageSize) {
		return documentDao.findByTeacher(id,startNum,pageSize);
	}

}
