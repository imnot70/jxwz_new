package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.PostDao;
import com.jxwz.entity.Post;
import com.jxwz.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDao postDao;

	@Override
	public void saveOrUpdate(Post post) {
		postDao.saveOrUpdate(post);
	}

	@Override
	public List<Post> findAll() {
		return postDao.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		return postDao.deleteById(id);
	}

	@Override
	public Post findById(Long id) {
		return postDao.findById(id);
	}

}
