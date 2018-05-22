package com.jxwz.service;

import java.util.List;

import com.jxwz.entity.Post;
import com.jxwz.entity.PostReply;

public interface PostService extends BaseService<Post> {

	List<Post> getPostWithPage(int startNum,int pageSize);
	
	List<PostReply> getReplysByPostId(Long postId);

	int queryCount();

	int queryCountByUser(Long id);

	List<Post> findByUser(Long userId, int startNum, int pageSize);
	
}
