package com.jxwz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 9036765526123733625L;
	public Logger logger = Logger.getLogger(this.getClass());
	protected int pageNum;
	protected int pageSize;
	@SuppressWarnings("unused")
	private int startNum;

	protected void setAttr(String key, Object value) {
		ServletActionContext.getRequest().setAttribute(key, value);
	}

	protected void setSession(String key, Object value) {
		ServletActionContext.getRequest().getSession().setAttribute(key, value);
	}

	protected void executeAjax(Object obj) {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setCharacterEncoding("UTF-8");
//			response.setHeader("contentType", "text/html; charset=utf-8");
			response.setContentType("text/html;charset=UTF-8");  
			PrintWriter pw = response.getWriter();
			pw.print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void executeAjaxString(String jsonStr) {
		String str = null;
		try {
			str = new String(jsonStr.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.executeAjax(str);
	}
	
	protected void executeAjaxString(JSONObject jsonObj) {
		try {
			String jsonStr = new String(jsonObj.toJSONString().getBytes("ISO-8859-1"),"utf-8");
			executeAjax(jsonStr);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	// 获取总页数
	protected int getTotalPage(int totalCount, int pageSize) {
		return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
	}
	
	protected int getStartNum() {
		pageNum = pageNum <=0 ? 1 : pageNum;
		int startNum = (pageNum - 1) * pageSize;
		return startNum;
	}

	public int getPageNum() {
		return pageNum <= 0 ? 1 : pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize == 0 ? 10 : pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
