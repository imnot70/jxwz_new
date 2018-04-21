package com.jxwz.entity;

import java.util.Date;

public class BaseEntity {
	private Long id; // id
	private Date createTime; // 创建时间
	private String createDateStr;// 创建时间 yyyy-MM-dd
	private int status; // 可用状态,1:可用,0:可用

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
