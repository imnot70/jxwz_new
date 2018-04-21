package com.jxwz.action;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{

	private static final long serialVersionUID = 9036765526123733625L;
	public Logger logger = Logger.getLogger(this.getClass());
	
	protected void setAttr(String key,Object value) {
		ServletActionContext.getRequest().setAttribute(key, value);
	}
	
	protected void setSession(String key,Object value) {
		ServletActionContext.getRequest().getSession().setAttribute(key, value);
	}
	
	protected void executeAjax(Object obj) {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			PrintWriter pw = response.getWriter();
			response.setContentType("text/html;charset=utf-8");  
			pw.print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void executeAjaxString(String jsonStr) {
		this.executeAjax(jsonStr);
	}
	
	// 获取总页数
	protected int getTotalPage(int totalCount,int pageSize) {
		return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
	}
	
}
