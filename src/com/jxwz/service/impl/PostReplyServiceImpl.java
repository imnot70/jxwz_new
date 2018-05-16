package com.jxwz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxwz.dao.PostReplyDao;
import com.jxwz.entity.PostReply;
import com.jxwz.service.PostReplyService;
@Service
public class PostReplyServiceImpl implements PostReplyService {

	@Autowired
	private PostReplyDao postReplyDao;
	
	@Override
	public void saveOrUpdate(PostReply postReply) {
		postReplyDao.saveOrUpdate(postReply);
	}

	@Override
	public List<PostReply> findAll() {
		return postReplyDao.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		return postReplyDao.deleteById(id);
	}

	@Override
	public PostReply findById(Long id) {
		return postReplyDao.findById(id);
	}

	@Override
	public List<PostReply> selectByPostId(Long postId, int startNum, int pageSize) {
		return postReplyDao.selectByPostId(postId,startNum,pageSize);
	}

	@Override
	public int findMaxFloorByPostId(Long postId) {
		return postReplyDao.findMaxFloorByPostId(postId);
	}

}
