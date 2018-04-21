package com.jxwz.entity;

import java.util.HashSet;
import java.util.Set;

public class PostReply extends BaseEntity {

	private Post post;
	private User user;
	private String content;
	private String attachmentUrl;
	private PostReply reply;
	private Set<PostReply> replys = new HashSet<PostReply>();

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public PostReply getReply() {
		return reply;
	}

	public void setReply(PostReply reply) {
		this.reply = reply;
	}

	public Set<PostReply> getReplys() {
		return replys;
	}

	public void setReplys(Set<PostReply> replys) {
		this.replys = replys;
	}

}
