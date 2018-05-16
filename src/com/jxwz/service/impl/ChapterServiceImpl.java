package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.ChapterDao;
import com.jxwz.entity.Chapter;
import com.jxwz.service.ChapterService;

@Service
public class ChapterServiceImpl implements ChapterService {
	
	@Autowired
	private ChapterDao chapterDao;
	
	@Override
	public void saveOrUpdate(Chapter chapter) {
		chapterDao.saveOrUpdate(chapter);
	}

	@Override
	public List<Chapter> findAll() {
		return chapterDao.findAllSortByCreateTime(1);
	}

	@Override
	public boolean deleteById(Long id) {
		return chapterDao.deleteById(id);
	}

	@Override
	public Chapter findById(Long id) {
		return chapterDao.findById(id);
	}

	
}
