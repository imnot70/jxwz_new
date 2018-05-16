package com.jxwz.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import com.alibaba.fastjson.JSONObject;
import com.jxwz.entity.Announcement;
import com.jxwz.service.AnnouncementService;

public class AnnoAction extends BaseAction {

	private static final long serialVersionUID = -3396617869818214970L;

	@Autowired
	private AnnouncementService announcementService;

	private Announcement anno;
	private Long annoId;

	// 根据id获取公告详情
	public void annoDetail() {
		Long annoId = ServletRequestUtils.getLongParameter(ServletActionContext.getRequest(), "annoId", 0l);
		logger.info("annoDetail,annoId:" + annoId);

		JSONObject obj = new JSONObject();
		Announcement anno = announcementService.findById(annoId);
		obj.put("anno", anno);
		super.executeAjax(obj);
	}

	public void saveAnno() {
		logger.info("saveAnno");
		announcementService.saveOrUpdate(anno);
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		executeAjax(obj);
	}
	
	public void delAnno() {
		logger.info("delAnnoi,id:"+annoId);
		JSONObject obj = new JSONObject();
		boolean res = announcementService.deleteById(annoId);
		if(res) {
			obj.put("success", true);
		}else {
			obj.put("success", false);
		}
		executeAjax(obj);
	}
	
	public void getAnnos() {
		logger.info("getAnnos");
		JSONObject obj = new JSONObject();
		List<Announcement> list = announcementService.findWithPage(getStartNum(), pageSize);
		obj.put("annos", list);
		int totalCount = announcementService.queryCount();
//		super.setSession("totalCountAnno", totalCount);
		obj.put("totalCountAnno",totalCount);
		executeAjax(obj);
	}

	public Announcement getAnno() {
		return anno;
	}

	public void setAnno(Announcement anno) {
		this.anno = anno;
	}

	public Long getAnnoId() {
		return annoId;
	}

	public void setAnnoId(Long annoId) {
		this.annoId = annoId;
	}

	
}
