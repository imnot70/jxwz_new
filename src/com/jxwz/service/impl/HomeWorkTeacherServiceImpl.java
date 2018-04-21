package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.HomeWorkTeacherDao;
import com.jxwz.entity.HomeWorkTeacher;
import com.jxwz.service.HomeWorkTeacherService;
@Service
public class HomeWorkTeacherServiceImpl implements HomeWorkTeacherService {

	@Autowired
	private HomeWorkTeacherDao homeWorkTeacherDao;
	
	@Override
	public void saveOrUpdate(HomeWorkTeacher homeWorkTeacher) {
		homeWorkTeacherDao.saveOrUpdate(homeWorkTeacher);
	}

	@Override
	public List<HomeWorkTeacher> findAll() {
		return homeWorkTeacherDao.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		return homeWorkTeacherDao.deleteById(id);
	}

	@Override
	public HomeWorkTeacher findById(Long id) {
		return homeWorkTeacherDao.findById(id);
	}

}
