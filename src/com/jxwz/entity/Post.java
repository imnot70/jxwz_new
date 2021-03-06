package com.jxwz.entity;

import java.util.Set;

public class Post extends BaseEntity {

	private String theme; // 帖子主题
	private String content; // 帖子内容
	private User user; // 发帖人
	private String attachmentUrl;// 附件
	private Set<PostReply> replys;

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public Set<PostReply> getReplys() {
		return replys;
	}

	public void setReplys(Set<PostReply> replys) {
		this.replys = replys;
	}

}
