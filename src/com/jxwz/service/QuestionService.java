package com.jxwz.service;

import java.util.List;

import com.jxwz.entity.Question;

public interface QuestionService extends BaseService<Question> {
//	void saveOrUpdate(Question question);
//
//	List<Question> findAll();
//
//	boolean deleteById(Long id);
//
//	Question findById(Long id);
	
	List<Question> findQuestion(Long sectionId);
	
}
