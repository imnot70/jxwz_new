package com.jxwz.service;

import java.util.List;

import com.jxwz.entity.Knowledge;

public interface KnowledgeService extends BaseService<Knowledge> {
	List<Knowledge> findBysectionId(Long secId,int startNum,int pageSize);
	int countBySectionId(Long secId);
	List<Knowledge> findWithPage(int startNum, int pageSize);
	int queryCount();
}
