package com.jxwz.service;

import java.util.List;

import com.jxwz.entity.Announcement;

public interface AnnouncementService extends BaseService<Announcement>{

	List<Announcement> findLast();
	
	List<Announcement> findWithPage(int startNum,int pageSize);
	
	int queryCount();
//	void saveOrUpdate(Announcement announcement);
//
//	List<Announcement> findAll();
//
//	boolean deleteById(Long id);
//
//	Announcement findById(Long id);
}
