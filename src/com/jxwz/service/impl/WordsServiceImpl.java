package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.WordsDao;
import com.jxwz.entity.Words;
import com.jxwz.service.WordsService;

@Service
public class WordsServiceImpl implements WordsService {
	
	@Autowired
	private WordsDao wordsDao;

	@Override
	public void saveOrUpdate(Words words) {
		wordsDao.saveOrUpdate(words);
	}

	@Override
	public List<Words> findAll() {
		return wordsDao.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		return wordsDao.deleteById(id);
	}

	@Override
	public Words findById(Long id) {
		return wordsDao.findById(id);
	}

	@Override
	public int queryCountByUser(Long id) {
		return wordsDao.queryCountByUser(id);
	}

	@Override
	public List<Words> findByUserId(Long userId, int startNum, int pageSize) {
		return wordsDao.findByUserId(userId, startNum, pageSize);
	}

}
