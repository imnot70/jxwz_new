package com.jxwz.service;

import java.util.List;

public interface BaseService<T> {
	void saveOrUpdate(T t);

	List<T> findAll();

	boolean deleteById(Long id);

	T findById(Long id);
}
