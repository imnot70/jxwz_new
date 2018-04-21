package com.jxwz.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jxwz.entity.Announcement;
import com.jxwz.entity.Chapter;
import com.jxwz.entity.Post;
import com.jxwz.entity.Question;
import com.jxwz.entity.Words;
import com.jxwz.service.AnnouncementService;
import com.jxwz.service.ChapterService;
import com.jxwz.service.PostService;
import com.jxwz.service.QuestionService;
import com.jxwz.service.VideoService;
import com.jxwz.service.WordsService;

public class HomePageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ChapterService chapterService;
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private VideoService vdieoService;
	@Autowired
	private WordsService wordsService;
	@Autowired
	private PostService postService;

	private Long chapterId;
	private Long sectionId;

	public String toHome() {
		logger.info("toHome");
		List<Chapter> chapters = chapterService.findAll();
		super.setAttr("chapters", chapters);

		List<Announcement> annos = announcementService.findAll();
		super.setSession("annos", annos);

		return "home";
	}

	// 跳转至测试页面
	public String toTest() {
		logger.info("toTest");

		List<Question> questions = questionService.findAll();
		super.setAttr("questions", questions);

		return "toTest";
	}

	// 视频列表(可以与doc合并)
	public String toVidoList() {
		logger.info("toVideoList");

		// List<Video> videos = vdieoService.findAll();

		return "videoList";
	}

	// 到发帖
	public String toDiscuz() {
		logger.info("toDiscuz");

		List<Post> posts = postService.findAll();
		super.setAttr("posts", posts);

		return "toDiscuz";
	}

	// 到留言
	public String toWords() {
		logger.info("toWords");

		List<Words> wordsList = wordsService.findAll();
		super.setAttr("wordsList", wordsList);

		return "toWords";
	}

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
