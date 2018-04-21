package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.OptionDao;
import com.jxwz.entity.Option;
import com.jxwz.service.OptionService;
@Service
public class OptioinServiceImpl implements OptionService {
	
	@Autowired
	private OptionDao optionDao;

	@Override
	public void saveOrUpdate(Option option) {
		optionDao.saveOrUpdate(option);
	}

	@Override
	public List<Option> findAll() {
		return optionDao.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		return optionDao.deleteById(id);
	}

	@Override
	public Option findById(Long id) {
		return optionDao.findById(id);
	}

}
