package com.jxwz.entity;

public class WordsReply extends BaseEntity {
	// 留言回复
	private Words words;
	private String content;
	private String attachmentUrl;

	public Words getWords() {
		return words;
	}

	public void setWords(Words words) {
		this.words = words;
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

}
