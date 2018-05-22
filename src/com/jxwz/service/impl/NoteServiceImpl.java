package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.NoteDao;
import com.jxwz.entity.Note;
import com.jxwz.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	private NoteDao noteDao;

	@Override
	public void saveOrUpdate(Note note) {
		noteDao.saveOrUpdate(note);
	}

	@Override
	public List<Note> findAll() {
		return noteDao.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		return noteDao.deleteById(id);
	}

	@Override
	public Note findById(Long id) {
		return noteDao.findById(id);
	}

	@Override
	public List<Note> findByUserId(Long userId, int startNum, int pageSize) {
		return noteDao.findByUserId(userId,startNum,pageSize);
	}

	@Override
	public int queryCountByUser(Long userId) {
		return noteDao.queryCountByUser(userId);
	}

}
