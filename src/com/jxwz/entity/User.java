package com.jxwz.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.jxwz.util.Constants;
import com.jxwz.util.Utils;

public class User extends BaseEntity {
	private String name; // 用户名
	private int userType; // 用户类型,0:管理员,1:学生,2:老师
	private String loginName;// 登录名
	private String password; // 用户密码
	private int gender; // 性别 0:男,1:女
	private Date birthDay; // 生日
	private User teacher; // 所属教师,userType=1时使用

	private Set<Question> incrrects = new HashSet<Question>(); // 学生错集

	public User() {
		super.setStatus(Constants.STATUS_ENABLE);
		super.setCreateTime(new Date());
		super.setCreateDateStr(Utils.formatDate(new Date(), Utils.DATE_ONLY));
	}

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

}
