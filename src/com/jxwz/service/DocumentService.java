package com.jxwz.service;

import java.util.List;

import com.jxwz.entity.Document;

public interface DocumentService extends BaseService<Document>{

	List<Document> getDocsWithPage(int startNum, int pageSize);

	int queryCount();
}
