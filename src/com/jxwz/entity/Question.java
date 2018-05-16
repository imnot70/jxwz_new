package com.jxwz.entity;

import java.util.Set;

public class Question extends BaseEntity {

	private String content;
	private String author;
	private String answerCode;
	private String remark;
	private Section section;
	private Set<Option> options;
	private Set<User> stus;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAnswerCode() {
		return answerCode;
	}

	public void setAnswerCode(String answerCode) {
		this.answerCode = answerCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Set<Option> getOptions() {
		return options;
	}

	public void setOptions(Set<Option> options) {
		this.options = options;
	}

	public Set<User> getStus() {
		return stus;
	}

	public void setStus(Set<User> stus) {
		this.stus = stus;
	}

}
