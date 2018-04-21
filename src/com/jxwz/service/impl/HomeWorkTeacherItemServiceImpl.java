package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.HomeWorkTeacherItemDao;
import com.jxwz.entity.HomeWorkTeacherItem;
import com.jxwz.service.HomeWorkTeacherItemService;
@Service
public class HomeWorkTeacherItemServiceImpl implements HomeWorkTeacherItemService {

	@Autowired
	private HomeWorkTeacherItemDao itemDao;
	
	@Override
	public void saveOrUpdate(HomeWorkTeacherItem item) {
		itemDao.saveOrUpdate(item);
	}

	@Override
	public List<HomeWorkTeacherItem> findAll() {
		return itemDao.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		return itemDao.deleteById(id);
	}

	@Override
	public HomeWorkTeacherItem findById(Long id) {
		return itemDao.findById(id);
	}

}
