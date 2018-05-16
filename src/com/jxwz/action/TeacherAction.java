package com.jxwz.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.jxwz.entity.User;
import com.jxwz.service.UserService;

public class TeacherAction extends BaseAction {

	private static final long serialVersionUID = -7414843094579245768L;

	@Autowired
	private UserService userService;
	
	private User teacher;

	// 修改个人信息
	public String modifyInfo() {
		userService.saveOrUpdate(teacher);
		return SUCCESS;
	}

	// 回复留言
	public void replay() {
		
	}

	public void uploadVideo() {

	}

	public void uploadDoc() {

	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

}
