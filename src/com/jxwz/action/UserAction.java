package com.jxwz.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import com.alibaba.fastjson.JSONObject;
import com.jxwz.entity.Chapter;
import com.jxwz.entity.Post;
import com.jxwz.entity.Section;
import com.jxwz.entity.User;
import com.jxwz.service.AnnouncementService;
import com.jxwz.service.ChapterService;
import com.jxwz.service.DocumentService;
import com.jxwz.service.KnowledgeService;
import com.jxwz.service.NoteService;
import com.jxwz.service.PostService;
import com.jxwz.service.QuestionService;
import com.jxwz.service.SectionService;
import com.jxwz.service.UserService;
import com.jxwz.service.VideoService;
import com.jxwz.service.WordsService;
import com.jxwz.util.Constants;
import com.jxwz.util.EmailUtils;
import com.jxwz.util.Utils;

public class UserAction extends BaseAction {

	private static final long serialVersionUID = -6311519164721432184L;
	@Autowired
	private UserService userService;
	@Autowired
	private ChapterService chapterService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private KnowledgeService knowledgeService;
	@Autowired
	private VideoService videoService;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private NoteService noteService;
	@Autowired
	private WordsService wordsService;
	@Autowired
	private PostService postService;
	
	private String result;
	private InputStream inputStream;
	private User user;
	private int pageSize = 10;
	private int type;
	private Long userId;
	private Long teacherId;
	private String dest;

	public void importUser() {

	}

	public void saveUser() {
		logger.info("saveUser,tId:" + teacherId);
		if (user.getUserType() == 1 && teacherId != null && teacherId != 0) {
			User teacher = userService.findById(teacherId);
			user.setTeacher(teacher);
		}
		userService.saveOrUpdate(user);
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		executeAjax(obj);
	}

	public void findUserByType() {
		logger.info("findUserByType");
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			int pageNum = ServletRequestUtils.getIntParameter(req, "pageNum", 1);
			int type = ServletRequestUtils.getIntParameter(req, "type", 1);
			JSONObject obj = new JSONObject();
			logger.info("findUserByType,type:" + type + ",pageNum:" + pageNum);

			pageNum = pageNum <= 0 ? 1 : pageNum;
			int startNum = (pageNum - 1) * pageSize;

			List<User> userList = userService.findByTypeWithPage(type, startNum, pageSize);
			List<User> resultList = new ArrayList<User>();
			if (userList != null && userList.size() != 0) {
				for (User temp : userList) {
					User u = new User();
					u.setName(temp.getName());
					u.setId(temp.getId());
					u.setGender(temp.getGender());
					u.setUserCode(temp.getUserCode());
					resultList.add(u);
				}
			}

			obj.put("users", resultList);
			int totalCount = userService.countByType(type);
			obj.put("totalCount", totalCount);
			executeAjax(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void findUserCount() {
		logger.info("findUserCount,userType:" + type);
		JSONObject obj = new JSONObject();
		int totalCount = userService.countByType(type);
		obj.put("totalCount", totalCount);
		executeAjax(obj);
	}

	public void findAllTeacher() {
		logger.info("findAllTeacher");
		List<User> userList = userService.findByType(Constants.USER_TYPE_TEACHER);
		JSONObject obj = new JSONObject();
		obj.put("teachers", userList);
		executeAjaxString(obj.toJSONString());
	}

	public void findAllStudent() {
		logger.info("findAllStudent");
		List<User> userList = userService.findByType(Constants.USER_TYPE_STUDENT);
		setAttr("students", userList);
	}

	// 检测学号/编号唯一性
	public void checkCodeUnique() {
		String code = ServletActionContext.getRequest().getParameter("code");
		logger.info("checkCodeUnique,code:" + code + ",type:" + type);
		User user = userService.findByCode(code, type);
		JSONObject obj = new JSONObject();
		if (user == null) {
			obj.put("unique", true);
		} else {
			obj.put("unique", false);
		}
		executeAjax(obj);
	}

	// 检测用户email唯一性
	private boolean checkUserInfo(User user) {
		int count = userService.checkUnique(user.getEmail(), user.getUserType());
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	// 用户注册
	public void regUserSave() {
		logger.info("regUserSave");
		JSONObject obj = new JSONObject();

		boolean unique = this.checkUserInfo(user);
		if (unique) {
			String code = Utils.createCode();
			logger.info("regUser,code:" + code);
			user.setCode(code);
			userService.saveOrUpdate(user);
			try {
				EmailUtils.sendEmial(user.getEmail(), code);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			obj.put("error", 1);
			obj.put("msg", "注册成功，等待验证");
		} else {
			obj.put("error", 0);
			obj.put("msg", "邮箱已存在");
		}
		super.executeAjax(obj);
	}

	// 验证用户
	public void regUserVerify() {
		logger.info("regUserVerify");
		JSONObject obj = new JSONObject();
		User u = userService.findByEmailAndPw(user.getEmail(), null, user.getUserType());
		if (u == null) {
			obj.put("error", 0);
			obj.put("msg", "用户不存在");
		} else {
			if (u.getCode() != null && u.getCode().equals(user.getCode())) {
				u.setCode(null);
				u.setVerifyStatus(Constants.USER_VERIFY_STATUS_CHECKED);
				userService.saveOrUpdate(u);
				obj.put("error", 1);
				obj.put("msg", "验证通过");
			} else {
				obj.put("error", 2);
				obj.put("msg", "验证码错误");
			}
		}
		super.executeAjaxString(obj.toJSONString());
	}

	public String center() {
		logger.info("center");

		List<Chapter> chapters = chapterService.findAll();
		
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user != null) {
			if(user.getUserType() == 0) {
				// 查询学生,老师,公告,视频,资料,知识点总数,用于页面分页
				int totalCountStu = userService.countByType(Constants.USER_TYPE_STUDENT);
				int totalCountTea = userService.countByType(Constants.USER_TYPE_TEACHER);
				int totalCountAnno = announcementService.queryCount();
				int totalCountKnow = knowledgeService.queryCount();
				int totalCountVideo = videoService.queryCount();
				
				super.setSession("totalCountStu", totalCountStu);
				super.setSession("totalCountTea", totalCountTea);
				super.setSession("totalCountAnno", totalCountAnno);
				super.setSession("totalCountknow", totalCountKnow);
				super.setSession("totalCountVideo", totalCountVideo);
			}
			
			if(user.getUserType() == 1) {
				// 学生错题数,笔记数,留言数
				int totalCountIncors = questionService.queryCountByUserId(user.getId());
				int totalCountNotes = noteService.queryCountByUser(user.getId());
				int totalCountWords = wordsService.queryCountByUser(user.getId());
				int totalCountPostStu = postService.queryCountByUser(user.getId());
				int totalCountDocStu = documentService.queryCountByUser(user.getId());
				super.setSession("totalCountIncor", totalCountIncors);
				super.setSession("totalCountNote", totalCountNotes);
				super.setSession("totalCountWords", totalCountWords);
				super.setSession("totalCountPostStu", totalCountPostStu);
				super.setSession("totalCountDocStu", totalCountDocStu);
			}
			if(user.getUserType() == 2) {
				// 老师文档数,问题数,被留言数
				
			}

		}
		
		if (chapters != null && chapters.size() != 0) {
			for (Chapter c : chapters) {
				List<Section> secs = sectionService.findSectionsByChapterId(c.getId());
				c.setSections(secs);
			}
		}
		super.setAttr("chapters", chapters);

		return "userHome";
	}

	public void findUserById() {
		logger.info("findUserById,userId:" + userId);
		User user = userService.findById(userId);
		JSONObject obj = new JSONObject();
		obj.put("user", user);
		executeAjax(obj);
	}

	public void modifyPwd() {
		logger.info("modifyPwd");
		JSONObject obj = new JSONObject();
		User u = userService.findById(user.getId());
		if (user == null) {
			obj.put("success", false);
			executeAjax(obj);
			return;
		}
		u.setPassword(user.getPassword());
		userService.saveOrUpdate(u);
		obj.put("success", true);
		executeAjax(obj);
	}

	public void delUser() {
		logger.info("delUser,userId:" + userId);
		boolean res = userService.deleteById(userId);
		JSONObject obj = new JSONObject();

		if (res) {
			obj.put("success", true);
		} else {
			obj.put("success", false);
		}
		executeAjax(obj);
	}
	
	public void myPost() {
		int pageNum = getPageNum() <= 0?1 :getPageNum();
		int pageSize = getPageSize() <=0 ? 10 : getPageSize();
		int startNum = (pageNum - 1)* pageSize;
		JSONObject obj = new JSONObject();
		Long userId = (Long) ServletActionContext.getRequest().getSession().getAttribute("userId");
		List<Post> posts = postService.findByUser(userId,startNum,pageSize);
		obj.put("posts",posts);
		executeAjax(obj);
	}

	// ======= setter and getter ======
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

}
