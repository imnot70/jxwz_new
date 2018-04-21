package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.AnnouncementDao;
import com.jxwz.entity.Announcement;
import com.jxwz.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
	private AnnouncementDao announcementDao;
	
	@Override
	public void saveOrUpdate(Announcement announcement) {
		announcementDao.saveOrUpdate(announcement);
	}

	@Override
	public List<Announcement> findAll() {
		return announcementDao.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		return announcementDao.deleteById(id);
	}

	@Override
	public Announcement findById(Long id) {
		return announcementDao.findById(id);
	}

}
