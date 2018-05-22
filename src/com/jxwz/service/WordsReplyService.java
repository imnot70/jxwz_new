package com.jxwz.service;

import com.jxwz.entity.WordsReply;

public interface WordsReplyService extends BaseService<WordsReply> {

	WordsReply findByWrodsId(Long wordsId);

}
