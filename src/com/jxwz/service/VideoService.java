package com.jxwz.service;

import java.util.List;

import com.jxwz.entity.Video;

public interface VideoService extends BaseService<Video> {
	
	int queryCount();

	List<Video> findWithPage(int startNum, int pageSize);

}
