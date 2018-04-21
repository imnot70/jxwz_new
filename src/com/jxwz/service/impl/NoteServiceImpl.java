package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jxwz.dao.NoteDao;
import com.jxwz.entity.Note;
import com.jxwz.service.NoteService;

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

}
