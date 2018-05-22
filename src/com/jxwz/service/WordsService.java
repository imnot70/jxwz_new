package com.jxwz.service;

import java.util.List;

import com.jxwz.entity.Words;

public interface WordsService extends BaseService<Words> {

	int queryCountByUser(Long id);

	List<Words> findByUserId(Long userId,int startNum,int pageSize);
}
