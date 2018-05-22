package com.jxwz.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jxwz.entity.Document;
import com.jxwz.entity.Question;
import com.jxwz.entity.User;
import com.jxwz.entity.Words;
import com.jxwz.entity.WordsReply;
import com.jxwz.service.DocumentService;
import com.jxwz.service.QuestionService;
import com.jxwz.service.UserService;
import com.jxwz.service.WordsReplyService;
import com.jxwz.service.WordsService;
import com.jxwz.util.Constants;

public class TeacherAction extends BaseAction {

	private static final long serialVersionUID = -7414843094579245768L;

	@Autowired
	private UserService userService;
	@Autowired
	private WordsService wordsService;
	@Autowired
	private WordsReplyService wordsReplyService;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private QuestionService questionService;

	private User teacher;
	private Words words;
	private WordsReply wordsReply;
	private Question question;
	private Long wordsId;
	private Long wordsReplyId;
	private Long teacherId;
	private Long docId;

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
		logger.info("replay");
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

	// 查询文档
	public void getDocs() {
		logger.info("getDocs,teacherId:" + teacherId);
		JSONObject obj = new JSONObject();
		int pageNum = getPageNum() <= 0 ? 1 : getPageNum();
		int pageSize = getPageSize() <= 0 ? 10 : getPageSize();
		int startNum = (pageNum - 1) * pageSize;
		List<Document> docs = documentService.findByTeacher(teacherId, startNum, pageSize);
		List<Document> result = new ArrayList<Document>();
		if (docs != null && docs.size() != 0) {
			for (Document temp : docs) {
				Document d = new Document();
				d.setAuthor(temp.getAuthor());
				d.setCreateDateStr(temp.getCreateDateStr());
				d.setDocUrl(Constants.URL_FREFIX + temp.getDocUrl());
				d.setId(temp.getId());
				d.setSubTitle(temp.getSubTitle());
				d.setTitle(temp.getTitle());
				d.setTeacher(temp.getTeacher());
				result.add(d);
			}
		}
		obj.put("docs", result);
		executeAjax(obj);
	}

	// 删除文档
	public void delDoc() {
		logger.info("delDoc,docId:" + docId);
		JSONObject obj = new JSONObject();
		try {
			documentService.deleteById(docId);
		} catch (Exception e) {
			obj.put("success", false);
			executeAjax(obj);
			e.printStackTrace();
		}
		obj.put("success", true);
		executeAjax(obj);
	}

	// 添加问题
	public void addTest() {
		logger.info("addTest");
		JSONObject obj = new JSONObject();
		try {
			questionService.saveOrUpdate(question);
		} catch (Exception e) {
			obj.put("success", false);
			executeAjax(obj);
			e.printStackTrace();
		}
		obj.put("success", true);
		executeAjax(obj);
	}

	// setter and getter
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

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
