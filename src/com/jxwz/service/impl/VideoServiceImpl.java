package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.VideoDao;
import com.jxwz.entity.Video;
import com.jxwz.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoDao videoDao;
	
	@Override
	public void saveOrUpdate(Video video) {
		videoDao.saveOrUpdate(video);
	}

	@Override
	public List<Video> findAll() {
		return videoDao.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		return videoDao.deleteById(id);
	}

	@Override
	public Video findById(Long id) {
		return videoDao.findById(id);
	}

}
