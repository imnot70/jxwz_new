package com.jxwz.service;

import java.util.List;

import com.jxwz.entity.Note;

public interface NoteService extends BaseService<Note> {

	List<Note> findByUserId(Long userId, int startNum, int pageSize);

	int queryCountByUser(Long userId);

}
