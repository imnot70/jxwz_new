package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.SectionDao;
import com.jxwz.entity.Section;
import com.jxwz.service.SectionService;
@Service
public class SectionServiceImpl implements SectionService{

	@Autowired
	private SectionDao sectionDao;
	
	@Override
	public void saveOrUpdate(Section section) {
		sectionDao.saveOrUpdate(section);
	}

	@Override
	public List<Section> findAll() {
		return sectionDao.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		return sectionDao.deleteById(id);
	}

	@Override
	public Section findById(Long id) {
		return sectionDao.findById(id);
	}

	@Override
	public List<Section> findSectionsByChapterId(Long id) {
		return sectionDao.findSectionsByChapterId(id);
	}

}
