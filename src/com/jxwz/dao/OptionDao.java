package com.jxwz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jxwz.entity.Option;
@Repository
public class OptionDao extends BaseDao<Option> {

	public List<Option> findByQueId(Long id) {
		return null;
	}

}
