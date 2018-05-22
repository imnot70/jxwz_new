package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.WordsReplyDao;
import com.jxwz.entity.WordsReply;
import com.jxwz.service.WordsReplyService;

@Service
public class WordsReplyServiceImpl implements WordsReplyService {
	
	@Autowired
	private WordsReplyDao wordsReplyDao;

	@Override
	public void saveOrUpdate(WordsReply wordsReply) {
		wordsReplyDao.saveOrUpdate(wordsReply);
	}

	@Override
	public List<WordsReply> findAll() {
		return wordsReplyDao.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		return wordsReplyDao.deleteById(id);
	}

	@Override
	public WordsReply findById(Long id) {
		return wordsReplyDao.findById(id);
	}

	@Override
	public WordsReply findByWrodsId(Long wordsId) {
		return wordsReplyDao.findByWrodsId(wordsId);
	}

}
