package com.jxwz.entity;

import java.util.Set;

public class Section extends BaseEntity {

	private int sort;
	private String title;
	private String subTitle;
	private String remark;
	private Chapter chapter;
	// private List<Document> documents;
	private Set<Video> videos;
	private Set<Question> ques;
	private Set<Knowledge> knows;

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public Set<Video> getVideos() {
		return videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

	public Set<Question> getQues() {
		return ques;
	}

	public void setQues(Set<Question> ques) {
		this.ques = ques;
	}

	public Set<Knowledge> getKnows() {
		return knows;
	}

	public void setKnows(Set<Knowledge> knows) {
		this.knows = knows;
	}

	/*
	 * public List<Document> getDocuments() { return documents; }
	 * 
	 * public void setDocuments(List<Document> documents) { this.documents =
	 * documents; }
	 */

}
