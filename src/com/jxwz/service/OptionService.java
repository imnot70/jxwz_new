package com.jxwz.service;

import java.util.List;

import com.jxwz.entity.Option;

public interface OptionService extends BaseService<Option>{

	List<Option> findByQueId(Long id);
//	void saveOrUpdate(Option option);
//
//	List<Option> findAll();
//
//	boolean deleteById(Long id);
//
//	Option findById(Long id);
}
