package com.jxwz.service;

import java.util.List;

import com.jxwz.entity.Section;

public interface SectionService extends BaseService<Section>{

	List<Section> findSectionsByChapterId(Long id);
//	void saveOrUpdate(Section section);
//
//	List<Section> findAll();
//
//	boolean deleteById(Long id);
//
//	Section findById(Long id);

}
