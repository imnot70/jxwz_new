package com.jxwz.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jxwz.entity.User;
import com.jxwz.service.UserService;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String loginName;
	private String password;
	private int userType;

	@Autowired
	private UserService userService;
	
	public void login() {
		logger.info("login,loginName:"+loginName+",password:"+password+",userType:"+userType);
		
		JSONObject obj = new JSONObject();
		
		User user = userService.findByUserName(loginName, password, userType);
		if(user == null) {
			obj.put("code", "1");
			obj.put("msg", "用户名或密码错误");
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			obj.put("code", "1");
			obj.put("msg", "登录成功");
			obj.put("name", user.getLoginName());
		}
		executeAjaxString(obj.toJSONString());
	}
	
	public String logout() {
		return "home";
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

}
