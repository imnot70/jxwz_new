package com.jxwz.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jxwz.entity.User;
import com.jxwz.entity.Words;
import com.jxwz.entity.WordsReply;
import com.jxwz.service.UserService;
import com.jxwz.service.WordsReplyService;
import com.jxwz.service.WordsService;

public class TeacherAction extends BaseAction {

	private static final long serialVersionUID = -7414843094579245768L;

	@Autowired
	private UserService userService;
	@Autowired
	private WordsService wordsService;
	@Autowired
	private WordsReplyService wordsReplyService;

	private User teacher;
	private Words words;
	private WordsReply wordsReply;
	private Long wordsId;
	private Long wordsReplyId;

	// 修改个人信息
	public void modifyInfo() {
		JSONObject obj = new JSONObject();
		try {
			userService.saveOrUpdate(teacher);
		} catch (Exception e) {
			obj.put("success", false);
			executeAjax(obj);
			e.printStackTrace();
		}
		obj.put("success", true);
		executeAjax(obj);
	}

	// 回复留言
	public void replay() {
		JSONObject obj = new JSONObject();
		Words w = wordsService.findById(wordsId);
		try {
			wordsReply.setWords(w);
			wordsReplyService.saveOrUpdate(wordsReply);
		} catch (Exception e) {
			obj.put("success", false);
			executeAjax(obj);
			e.printStackTrace();
		}

		obj.put("success", true);
		executeAjax(obj);
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public Words getWords() {
		return words;
	}

	public void setWords(Words words) {
		this.words = words;
	}

	public WordsReply getWordsReply() {
		return wordsReply;
	}

	public void setWordsReply(WordsReply wordsReply) {
		this.wordsReply = wordsReply;
	}

	public Long getWordsId() {
		return wordsId;
	}

	public void setWordsId(Long wordsId) {
		this.wordsId = wordsId;
	}

	public Long getWordsReplyId() {
		return wordsReplyId;
	}

	public void setWordsReplyId(Long wordsReplyId) {
		this.wordsReplyId = wordsReplyId;
	}

}
