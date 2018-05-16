package com.jxwz.service;

import java.util.List;

import com.jxwz.entity.PostReply;

public interface PostReplyService extends BaseService<PostReply> {
	List<PostReply> selectByPostId(Long postId,int startNum,int pageSize);

	int findMaxFloorByPostId(Long postId);
}
