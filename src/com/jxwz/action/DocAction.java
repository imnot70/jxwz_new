package com.jxwz.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jxwz.entity.Chapter;
import com.jxwz.entity.Document;
import com.jxwz.entity.Knowledge;
import com.jxwz.entity.Section;
import com.jxwz.entity.User;
import com.jxwz.entity.Video;
import com.jxwz.service.ChapterService;
import com.jxwz.service.DocumentService;
import com.jxwz.service.KnowledgeService;
import com.jxwz.service.SectionService;
import com.jxwz.service.UserService;
import com.jxwz.service.VideoService;
import com.jxwz.util.Constants;

public class DocAction extends BaseAction {

	private static final long serialVersionUID = 1769731851885763772L;

	@Autowired
	private DocumentService documentService;
	@Autowired
	private VideoService videoService;
	@Autowired
	private KnowledgeService knowledgeService;
	@Autowired
	private ChapterService chapterService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private UserService userService;

	private Long docId;
	private Long chapterId;
	private Long secId;
	private Long knowId;
	private Long videoId;
	private Long teacherId;

	private File myfile;
	private String myfileFileName;// 上传的文件名。上传字段名称+FileName 注意大小写
	private String myfileContentType;// 上传文件的MIME类型。上传字段名称+ContentType 注意大小写

	private Document doc;
	private Video video;
	private Knowledge know;

	// ============ Document =====================
	// 查询文档列表
	public void getDocs() {
		logger.info("getDocs");
		JSONObject obj = new JSONObject();
		List<Document> docs = documentService.getDocsWithPage(getStartNum(), getPageSize());
		List<Document> resultList = new ArrayList<Document>();
		if (docs != null && docs.size() != 0) {
			for (Document temp : docs) {
				Document d = new Document();
				d.setDocUrl(Constants.URL_FREFIX + temp.getDocUrl());
				d.setId(temp.getId());
				d.setSubTitle(temp.getSubTitle());
				d.setTitle(temp.getTitle());
				resultList.add(d);
			}
		}

		obj.put("docs", resultList);
		int totalCount = documentService.queryCount();
		super.setAttr("totalCountDoc", totalCount);
		obj.put("totalCount", totalCount);
		executeAjax(obj);
	}

	// 上传文档
	public void uploadDoc() {
		logger.info("uploadDoc");
		JSONObject obj = new JSONObject();
		String docUrl = this.upload(Constants.DOC_PATH);
		if (doc == null) {
			doc.setDocUrl(docUrl);
		}
		doc.setTitle(myfileFileName);
		User t = userService.findById(teacherId);
		if(t== null) {
			obj.put("success", false);
			executeAjax(obj);
		}
		doc.setTeacher(t);
		doc.setAuthor(t.getName());
		documentService.saveOrUpdate(doc);
		obj.put("success", true);
		executeAjax(obj);
	}

	// 删除文档
	public void deleteDoc() {
		logger.info("deleteDoc,docId:" + docId);
		boolean result = documentService.deleteById(docId);
		JSONObject obj = new JSONObject();
		if (result) {
			obj.put("success", false);
		} else {
			obj.put("success", false);
		}
		super.executeAjax(obj);
	}

	// ============ Knowledge =====================
	// 下载知识点
	public void downLoadKnow() {

	}

	// 获取知识点列表
	public void findKnows() {
		logger.info("findKnows,pageNum:" + getPageNum());
		JSONObject obj = new JSONObject();
		int startNum = ((getPageNum() == 0 ? 1 : getPageNum()) - 1) * getPageSize();
		List<Knowledge> knows = knowledgeService.findWithPage(startNum, getPageSize());
		if (knows != null && knows.size() != 0) {
			for (Knowledge k : knows) {
				k.setUrl(Constants.URL_FREFIX + k.getUrl());
				Section s = sectionService.findById(k.getSection().getId());
				k.setSection(s);
				if (s != null) {
					Chapter c = chapterService.findById(s.getChapter().getId());
					s.setChapter(c);
				}
			}
		}
		int totalCount = knowledgeService.queryCount();
		obj.put("knows", knows);
		obj.put("totalCountKnow", totalCount);
		executeAjax(obj);
	}

	// 管理员上传知识点
	public void uploadKnow() {
		JSONObject obj = new JSONObject();
		String knowUrl = null;

		if (!myfileFileName.endsWith(".pdf")) {
			obj.put("success", false);
			obj.put("msg", "请选择pdf文件");
			executeAjax(obj);
			return;
		}

		try {
			knowUrl = this.upload(Constants.KNOWLEDGE_PATH);
			if (know == null) {
				know = new Knowledge();
			}
			know.setUrl(knowUrl);
			know.setTitle(myfileFileName);
			Section sec = sectionService.findById(secId);
			know.setSection(sec);
			knowledgeService.saveOrUpdate(know);
		} catch (Exception e) {
			obj.put("success", false);
			obj.put("msg", "上传失败");
			executeAjax(obj);
			e.printStackTrace();
		}
		logger.info("uploadKnow,fileUrl:" + knowUrl);
		obj.put("success", true);
		executeAjax(obj);
	}

	// 删除视频
	public void delKnow() {
		logger.info("delKnow,id:" + knowId);
		boolean result = knowledgeService.deleteById(knowId);
		JSONObject obj = new JSONObject();
		if (result) {
			obj.put("success", true);
		} else {
			obj.put("success", false);
		}

		executeAjax(obj);
	}

	// ============ Video =====================
	// 上传视频
	public void uploadVideo() {
		JSONObject obj = new JSONObject();
		String videoUrl = null;

		if (!myfileFileName.endsWith(".mp4")) {
			obj.put("success", false);
			obj.put("msg", "请选择mp4文件");
			executeAjax(obj);
			return;
		}

		try {
			videoUrl = this.upload(Constants.VIDEO_PATH);
			if (video == null) {
				video = new Video();
			}
			video.setVideoUrl(videoUrl);
			video.setName(myfileFileName);
			Section sec = sectionService.findById(secId);
			video.setSection(sec);
			videoService.saveOrUpdate(video);
		} catch (Exception e) {
			obj.put("success", false);
			obj.put("msg", "上传失败");
			executeAjax(obj);
			e.printStackTrace();
		}
		logger.info("uploadVideo,fileUrl:" + videoUrl);
		obj.put("success", true);
		executeAjax(obj);
	}

	public void findVideos() {
		logger.info("findVides,pageNum:" + getPageNum());
		JSONObject obj = new JSONObject();
		int startNum = ((getPageNum() == 0 ? 1 : getPageNum()) - 1) * getPageSize();
		List<Video> videos = videoService.findWithPage(startNum, getPageSize());
		if (videos != null && videos.size() != 0) {
			for (Video v : videos) {
				Section s = sectionService.findById(v.getSection().getId());
				v.setSection(s);

				v.setVideoUrl(Constants.VIDEO_PATH + v.getVideoUrl());

				if (s != null) {
					Chapter c = chapterService.findById(s.getChapter().getId());
					s.setChapter(c);
				}
			}
		}
		obj.put("videos", videos);
		executeAjax(obj);
	}

	public void delVideo() {
		logger.info("delVideo,id:" + videoId);
		JSONObject obj = new JSONObject();
		boolean result = videoService.deleteById(videoId);
		if (result) {
			obj.put("success", true);
		} else {
			obj.put("success", false);
		}

		executeAjax(obj);
	}

	// ============ Others =====================
	public String upload(String pathName) {
		// String basePath = Constants.FILE_PATH + "/" + pathName;
		// ServletContext servletContext = ServletActionContext.getServletContext();
		// String basePath = servletContext.getRealPath("/");
		// String dirPath = basePath + "/files/" + pathName;
		String dirPath = Constants.FILE_PATH + "/" + pathName;
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		myfile.renameTo(new File(dirPath, myfileFileName));
		return pathName + "/" + myfileFileName;
	}

	// 查询所有章
	public void getChapterAll() {
		logger.info("getChapterAll");
		JSONObject obj = new JSONObject();
		List<Chapter> chas = chapterService.findAll();
		if (chas != null && chas.size() != 0) {
			for (Chapter c : chas) {
				System.out.println(JSONObject.toJSONString(c));
				c.setSections(null);
			}
		}

		obj.put("chas", chas);
		super.executeAjax(obj);
	}

	public void getSection() {
		logger.info("getChapterAll");
		JSONObject obj = new JSONObject();
		List<Section> secs = sectionService.findSectionsByChapterId(chapterId);
		obj.put("secs", secs);
		super.executeAjax(obj);
	}

	// getter and setter
	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public File getMyfile() {
		return myfile;
	}

	public void setMyfile(File myfile) {
		this.myfile = myfile;
	}

	public String getMyfileFileName() {
		return myfileFileName;
	}

	public void setMyfileFileName(String myfileFileName) {
		this.myfileFileName = myfileFileName;
	}

	public String getMyfileContentType() {
		return myfileContentType;
	}

	public void setMyfileContentType(String myfileContentType) {
		this.myfileContentType = myfileContentType;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Knowledge getKnow() {
		return know;
	}

	public void setKnow(Knowledge know) {
		this.know = know;
	}

	public Long getChapterId() {
		return chapterId;
	}

	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}

	public Long getSecId() {
		return secId;
	}

	public void setSecId(Long secId) {
		this.secId = secId;
	}

	public Long getKnowId() {
		return knowId;
	}

	public void setKnowId(Long knowId) {
		this.knowId = knowId;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

}