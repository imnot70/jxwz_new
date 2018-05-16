package com.jxwz.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.jxwz.util.Constants;

public class User extends BaseEntity {
	private String name; // 用户名
	private int userType; // 用户类型,0:管理员,1:学生,2:老师
	private String loginName;// 登录名
	private String password = Constants.DEFAULT_PWD; // 用户密码
	private String code; // 验证码
	private String email; // 邮箱
	private int gender; // 性别 0:男,1:女
	private int verifyStatus = Constants.USER_VERIFY_STATUS_UNCHECK; // 验证状态 0:待验证 1:已验证
	private Date birthDay; // 生日
	private User teacher; // 所属教师,userType=1时使用
	private String userCode; // 学号或老师编号

	private Set<Question> incrrects = new HashSet<Question>(); // 学生错集

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public Set<Question> getIncrrects() {
		return incrrects;
	}

	public void setIncrrects(Set<Question> incrrects) {
		this.incrrects = incrrects;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(int verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
