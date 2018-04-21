package com.jxwz.action;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jxwz.entity.User;
import com.jxwz.service.UserService;
import com.jxwz.util.Constants;

public class UserAction extends BaseAction {

	private static final long serialVersionUID = -6311519164721432184L;
	@Autowired
	private UserService userService;

	private String result;
	private InputStream inputStream;

	public String findAllTeacher() {
		logger.info("findAllTeacher");
		List<User> userList = userService.findByType(Constants.USER_TYPE_TEACHER);
		setAttr("teachers", userList);
		return "allTeacher";
	}

	public String findAllStudent() {
		logger.info("findAllStudent");
		List<User> userList = userService.findByType(Constants.USER_TYPE_STUDENT);
		setAttr("students", userList);
		return "allStudent";
	}

//	public String delete() {
//		logger.info("delete");
//		Long id = Long.valueOf(ServletActionContext.getRequest().getParameter("id"));
//		logger.info("id:"+id);
//		boolean result = userService.deleteById(id);
//		System.out.println(result);
//		return "allStudent";
//	}

//	public void testAjax() throws IOException {
//		List<String> list = new ArrayList<String>();
//		list.add("1");
//		list.add("Englist");
//		JSONObject obj = new JSONObject();
//		obj.put("list", list);
//		super.executeAjax(obj);
//	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
