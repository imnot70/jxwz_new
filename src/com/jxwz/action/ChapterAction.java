package com.jxwz.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jxwz.entity.Chapter;
import com.jxwz.entity.Section;
import com.jxwz.service.ChapterService;
import com.jxwz.service.SectionService;

public class ChapterAction extends BaseAction {

	private static final long serialVersionUID = 5804216765015550896L;

	@Autowired
	private ChapterService chapterService;
	@Autowired
	private SectionService sectionService;

	private Long chaId;
	private Long secId;
	private Section section;
	private Chapter chapter;
	
	public void saveCha() {
		logger.info("saveCha");
		JSONObject obj = new JSONObject();
		
		try {
			
			Chapter c = chapterService.findById(chapter.getId());
			if(c == null) {
				obj.put("success",false);
				executeAjax(obj);
				return ;
			}
			c.setTitle(chapter.getTitle());
			c.setSubTitle(chapter.getSubTitle());
			c.setSort(chapter.getSort());
			chapterService.saveOrUpdate(c);
		} catch (Exception e) {
			obj.put("success", false);
			e.printStackTrace();
		}
		
		obj.put("success", true);
		executeAjax(obj);
	}
	
	public void saveSec() {
		logger.info("saveSec");
		JSONObject obj = new JSONObject();
		
		
		try {
			Section s = sectionService.findById(section.getId());
			if(s == null) {
				obj.put("success",false);
				executeAjax(obj);
				return ;
			}
			
			Chapter c = chapterService.findById(chaId);
			section.setChapter(c);
			
			s.setTitle(section.getTitle());
			s.setSubTitle(section.getSubTitle());
			s.setSort(section.getSort());
			
			sectionService.saveOrUpdate(s);
		} catch (Exception e) {
			obj.put("success", false);
			e.printStackTrace();
		}
		
		obj.put("success", true);
		executeAjax(obj);
	}

	public void getChaDetail() {
		logger.info("getChaDetail,chaId:" + chaId);
		Chapter cha = chapterService.findById(chaId);
		JSONObject obj = new JSONObject();
		obj.put("chapter", cha);
		executeAjax(obj);
	}

	public void getSecDetail() {
		logger.info("getSecDerail,secId:" + secId);
		Section sec = sectionService.findById(secId);
		JSONObject obj = new JSONObject();
		obj.put("section", sec);
		executeAjax(obj);
	}

	public void deleteCha() {
		logger.info("deleteCha,chaId:" + chaId);
		JSONObject obj = new JSONObject();
		try {
			Chapter c = chapterService.findById(chaId);
			List<Section> secs = c.getSections();
			if(secs != null && secs.size() !=0) {
				for(Section s:secs) {
					sectionService.deleteById(s.getId());
				}
			}
			chapterService.deleteById(chaId);
			obj.put("success", true);
		} catch (Exception e) {
			obj.put("success", false);
			e.printStackTrace();
		}
		
		executeAjax(obj);
	}

	public void deleteSec() {
		logger.info("deleteCha,secId:" + secId);
		boolean result = sectionService.deleteById(secId);
		JSONObject obj = new JSONObject();
		if (result) {
			obj.put("success", true);
		} else {
			obj.put("success", false);
		}
		executeAjax(obj);
	}
	
	public void findAllChapters() {
		logger.info("findAllChapters");
		List<Chapter> chas = chapterService.findAll();
		
		JSONObject obj = new JSONObject();
		obj.put("chapters", chas);
		
		executeAjax(obj);
	}
	
	public void findSection() {
		logger.info("findSection,chaId:"+chaId);
		List<Section> secs = sectionService.findSectionsByChapterId(chaId);
		
		List<Section> sections = new ArrayList<Section>();
		if(secs != null && secs.size() != 0) {
			for(Section s:secs) {
				Section temp = new Section();
				temp.setId(s.getId());
				temp.setTitle(s.getTitle());
				sections.add(temp);
			}
		}
		
		JSONObject obj = new JSONObject();
		obj.put("sections", sections);
		
		executeAjax(obj);
	}

	// setter and getter
	public Long getChaId() {
		return chaId;
	}

	public void setChaId(Long chaId) {
		this.chaId = chaId;
	}

	public Long getSecId() {
		return secId;
	}

	public void setSecId(Long secId) {
		this.secId = secId;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

}
