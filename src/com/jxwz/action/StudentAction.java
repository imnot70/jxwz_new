package com.jxwz.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jxwz.entity.Note;
import com.jxwz.entity.Question;
import com.jxwz.entity.User;
import com.jxwz.service.QuestionService;
import com.jxwz.service.UserService;

public class StudentAction extends BaseAction {

	private static final long serialVersionUID = -1405498545043083940L;
	@Autowired
	private UserService userSerivce;
	@Autowired
	private QuestionService questionService;
	private Long userId;
	private Long queId;
	
	private User user;
	private Note note;
	
	public String modifyInfo() {
		userSerivce.saveOrUpdate(user);
		return SUCCESS;
	}
	
	public String toIncorrect() {
		User user = userSerivce.findById(userId);
		if(user != null) {
			setAttr("incr",user.getIncrrects());
		}
		return "incorrect";
	}
	
	public void inocrrectDetail() {
		Question q=questionService.findById(queId);
		
		JSONObject obj = new JSONObject();
		obj.put("data", q);
		super.executeAjax(obj);
	}

	public void addNot() {
		
	}
	
	public void delNote() {
		
	}
	
	public void updateNot() {
		
	}
	
	public void findNote() {
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getQueId() {
		return queId;
	}

	public void setQueId(Long queId) {
		this.queId = queId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
	
	
	
}
