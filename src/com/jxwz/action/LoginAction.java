package com.jxwz.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jxwz.entity.User;
import com.jxwz.service.UserService;
import com.jxwz.util.Constants;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String loginName;
	private String email;
	private String userCode;
	private String password;
	private int userType;
	private User user;

	@Autowired
	private UserService userService;

	public void login() {
		logger.info("login,email:" + email + ",password:" + password + ",userType:" + userType);

		JSONObject obj = new JSONObject();

		User user = userService.findByUserName(loginName, password, userType);
		if (user == null) {
			obj.put("code", 0);
			obj.put("msg", "用户名或密码错误");
		} else {
			if (user.getVerifyStatus() == Constants.USER_VERIFY_STATUS_CHECKED) {
				ServletActionContext.getRequest().getSession().setAttribute("user", user);
				obj.put("code", 1);
				obj.put("msg", "登录成功");
				obj.put("name", user.getLoginName());
				obj.put("userId", user.getId());
				obj.put("email", user.getEmail());
			} else {
				obj.put("code", 1);
				obj.put("msg", "用户待验证");
				obj.put("userId", user.getId());
				obj.put("email", user.getEmail());
			}
		}
		executeAjaxString(obj.toJSONString());
	}

	public void logout() {
		JSONObject obj = new JSONObject();

		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user != null) {
			obj.put("userType", user.getUserType());
		}
		ServletActionContext.getRequest().getSession().removeAttribute("user");

		executeAjaxString(obj.toJSONString());
	}

	public void checkLogin() {
		JSONObject obj = new JSONObject();
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user != null) {
			obj.put("login", true);
		} else {
			obj.put("login", false);
		}
		executeAjax(obj);
	}

	public void loginWithCode() {
		logger.info("loginWithCode,userCode:"+userCode+",password:"+password+",userType:"+userType);
		HttpSession session = ServletActionContext.getRequest().getSession();
		JSONObject obj = new JSONObject();

		try {
			User u = userService.findByCode(userCode,password,userType);
			if (u == null) {
				obj.put("success", false);
				obj.put("msg", "用户不存在");
				executeAjax(obj);
				return;
			}
			session.setAttribute("user", u);
			session.setAttribute("userName", u.getName());
			session.setAttribute("userId", u.getId());
			session.setAttribute("userType", u.getUserType());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obj.put("success", true);
		obj.put("msg", "登录成功");
		executeAjax(obj);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
