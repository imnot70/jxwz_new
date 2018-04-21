package com.jxwz.entity;

import java.util.List;

public class HomeWorkTeacher extends BaseEntity {

	private User teacher;
	private String title;
	private List<HomeWorkTeacherItem> items;

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<HomeWorkTeacherItem> getItems() {
		return items;
	}

	public void setItems(List<HomeWorkTeacherItem> items) {
		this.items = items;
	}

}
