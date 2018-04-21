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

}
