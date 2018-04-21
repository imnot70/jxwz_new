package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.QuestionDao;
import com.jxwz.entity.Question;
import com.jxwz.service.QuestionService;
@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionDao questionDao;

	@Override
	public void saveOrUpdate(Question question) {
		questionDao.saveOrUpdate(question);
	}

	@Override
	public List<Question> findAll() {
		return questionDao.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		return questionDao.deleteById(id);
	}

	@Override
	public Question findById(Long id) {
		return questionDao.findById(id);
	}

	@Override
	public List<Question> findQuestion(Long sectionId) {
		
		return questionDao.findTest(sectionId);
	}

}
