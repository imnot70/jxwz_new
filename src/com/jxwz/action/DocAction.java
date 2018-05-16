package com.jxwz.action;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jxwz.entity.Chapter;
import com.jxwz.entity.Document;
import com.jxwz.entity.Knowledge;
import com.jxwz.entity.Section;
import com.jxwz.entity.Video;
import com.jxwz.service.ChapterService;
import com.jxwz.service.DocumentService;
import com.jxwz.service.KnowledgeService;
import com.jxwz.service.SectionService;
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

	private Long docId;
	private Long chapterId;
	private Long secId;

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
		obj.put("docs", docs);
		int totalCount = documentService.queryCount();
		super.setAttr("totalCountDoc", totalCount);
		obj.put("totalCount", totalCount);
		executeAjax(obj);
	}

	// 上传文档
	public String uploadDoc() {
		String fileName = this.upload(Constants.DOC_PATH);
		if (doc == null) {
			doc = new Document();
		}
		doc.setDocUrl(fileName);
		System.out.println(Constants.URL_FREFIX + "/" + fileName);
		return "home";
	}

	// 删除文档
	public void deleteDoc() {
		logger.info("deleteDoc,docId:" + docId);
		boolean result = documentService.deleteById(docId);
		JSONObject obj = new JSONObject();
		if (result) {
			obj.put("error", 0);
			obj.put("msg", "删除成功");
		} else {
			obj.put("error", 1);
			obj.put("msg", "删除失败");
		}
		super.executeAjax(obj);
	}

	// ============ Knowledge =====================
	// 根据id获取知识点详情
	public String knowDetail() {
		return "detail";
	}

	// 下载文档
	public void downLoadKnow() {

	}

	// 获取知识点列表
	public void findKnows() {
		logger.info("findKnows,pageNum:" + getPageNum());
		JSONObject obj = new JSONObject();
		int startNum = ((getPageNum() == 0 ? 1 : getPageNum()) - 1) * getPageSize();
		List<Knowledge> knows = knowledgeService.findWithPage(startNum, getPageSize());
		if(knows != null && knows.size() != 0) {
			for(Knowledge k:knows) {
				Section s = sectionService.findById(k.getSection().getId());
				k.setSection(s);
				if(s!= null) {
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
		try {
			knowUrl = this.upload(Constants.KNOWLEDGE_PATH);
			if (know == null) {
				know = new Knowledge();
			}
			know.setUrl(knowUrl);
			
			Section sec = sectionService.findById(secId);
			know.setSection(sec);
			knowledgeService.saveOrUpdate(know);
		} catch (Exception e) {
			obj.put("success", false);
			executeAjax(obj);
			e.printStackTrace();
		}
		logger.info("uploadKnow,fileUrl:"+knowUrl);
		obj.put("success", true);
		executeAjax(obj);
	}

	// ============ Video =====================
	// 上传视频
	public void uploadVieo() {
		this.upload(Constants.VIDEO_PATH);
	}

	public void findVideos() {
		logger.info("findVides,pageNum:"+getPageNum());
		JSONObject obj = new JSONObject();
		int startNum = ((getPageNum() == 0 ? 1 : getPageNum()) - 1) * getPageSize();
		List<Video> videos = videoService.findWithPage(startNum,getPageSize());
		if(videos != null && videos.size()!=0) {
			for(Video v:videos) {
				Section s = sectionService.findById(v.getSection().getId());
				v.setSection(s);
				if(s != null) {
					Chapter c = chapterService.findById(s.getChapter().getId());
					s.setChapter(c);
				}
			}
		}
		obj.put("videos", videos);
		executeAjax(obj);
	}
	
	
	// ============ Others =====================
	public String upload(String pathName) {
		pathName = Constants.FILE_PATH + pathName;
		ServletContext servletContext = ServletActionContext.getServletContext();
		String realPath = servletContext.getRealPath("/file/" + pathName);
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		myfile.renameTo(new File(file, myfileFileName));
		return pathName + "/" + myfileFileName;
	}

	// 查询所有章
	public void getChapterAll() {
		logger.info("getChapterAll");
		JSONObject obj = new JSONObject();
		List<Chapter> chas = chapterService.findAll();
		if (chas != null && chas.size() != 0) {
			for (Chapter c : chas) {
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

}