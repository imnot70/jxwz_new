package com.jxwz.entity;

public class HomeWorkTeacherItem extends BaseEntity {
	private HomeWorkTeacher homeWorkTeacher;
	private String name;
	private String content;

	public HomeWorkTeacher getHomeWorkTeacher() {
		return homeWorkTeacher;
	}

	public void setHomeWorkTeacher(HomeWorkTeacher homeWorkTeacher) {
		this.homeWorkTeacher = homeWorkTeacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
