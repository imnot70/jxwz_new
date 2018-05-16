package com.jxwz.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import com.jxwz.entity.Announcement;
import com.jxwz.entity.Chapter;
import com.jxwz.entity.Knowledge;
import com.jxwz.entity.Post;
import com.jxwz.entity.Section;
import com.jxwz.service.AnnouncementService;
import com.jxwz.service.ChapterService;
import com.jxwz.service.KnowledgeService;
import com.jxwz.service.PostService;
import com.jxwz.service.SectionService;

public class HomePageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ChapterService chapterService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private PostService postService;
	@Autowired
	private KnowledgeService knowledgeService;

	private Long chapterId;
	private Long sectionId;

	// 主页
	public String toHome() {
		logger.info("toHome");
		// 所有章节
		List<Chapter> chapters = chapterService.findAll();

		if (chapters != null && chapters.size() != 0) {
			for (Chapter c : chapters) {
				List<Section> secs = sectionService.findSectionsByChapterId(c.getId());
				c.setSections(secs);
			}
		}

		super.setAttr("chapters", chapters);

		// 最新的十条公告
		List<Announcement> annos = announcementService.findLast();
		super.setSession("annos", annos);

		return "home";
	}

	// 根据sectionId查询本节知识点
	public String toKonwledges() {
		HttpServletRequest req = ServletActionContext.getRequest();

		Long secId = ServletRequestUtils.getLongParameter(req, "secId", 0l);
		Integer pageNum = ServletRequestUtils.getIntParameter(req, "pageNum", 1);
		Integer pageSize = ServletRequestUtils.getIntParameter(req, "pageSize", 10);
		logger.info("toKonwledges,secId:" + secId + ",pageNum:" + pageNum + ",pageSize:" + pageSize);
		// 计算其实数据条数
		pageNum = pageNum <= 0 ? 1 : pageNum;
		int startNum = (pageNum - 1) * pageSize;

		List<Knowledge> knowledges = knowledgeService.findBysectionId(secId, startNum, pageSize);
		int totalCount = knowledgeService.countBySectionId(secId);
		super.setAttr("knows", knowledges);
		super.setAttr("totalCount", totalCount);
		super.setAttr("totaPage", super.getTotalPage(totalCount, pageSize));
		super.setAttr("pageNum", pageNum);

		return "toKonwledges";
	}

	// 跳转至测试页面
	public String toTest() {
		logger.info("toTest");

		// 所有章节
		List<Chapter> chapters = chapterService.findAll();

		if (chapters != null && chapters.size() != 0) {
			for (Chapter c : chapters) {
				List<Section> secs = sectionService.findSectionsByChapterId(c.getId());
				c.setSections(secs);
			}
		}

		super.setAttr("chapters", chapters);
		return "toTest";
	}

	// 视频列表
	public String toVideoList() {
		logger.info("toVideoList");

		// 所有章节
		List<Chapter> chapters = chapterService.findAll();
		if (chapters != null && chapters.size() != 0) {
			for (Chapter c : chapters) {
				List<Section> secs = sectionService.findSectionsByChapterId(c.getId());
				c.setSections(secs);
			}
		}
		super.setAttr("chapters", chapters);

		return "videoList";
	}

	// 到发帖
	public String toDiscuz() {
		logger.info("toDiscuz");
		int startNum = ((getPageNum() <= 0 ? 1 : getPageNum()) - 1) * getPageSize();

		/*
		 * System.out.println(getPageNum()); System.out.println(startNum);
		 * System.out.println(getPageSize());
		 */

		List<Post> posts = postService.getPostWithPage(startNum, getPageSize());
		super.setAttr("posts", posts);

		int totalCount = postService.queryCount();
		super.setSession("totalCountPost", totalCount);
		super.setAttr("pageNum", getPageNum());

		return "toDiscuz";
	}

	/*
	 * // 到留言 public String toWords() { logger.info("toWords");
	 * 
	 * List<Words> wordsList = wordsService.findAll(); super.setAttr("wordsList",
	 * wordsList);
	 * 
	 * return "toWords"; }
	 */

	public Long getChapterId() {
		return chapterId;
	}

	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

}
