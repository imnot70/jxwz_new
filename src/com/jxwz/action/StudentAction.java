package com.jxwz.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jxwz.entity.Chapter;
import com.jxwz.entity.Document;
import com.jxwz.entity.Note;
import com.jxwz.entity.Question;
import com.jxwz.entity.Section;
import com.jxwz.entity.User;
import com.jxwz.entity.Words;
import com.jxwz.entity.WordsReply;
import com.jxwz.service.ChapterService;
import com.jxwz.service.DocumentService;
import com.jxwz.service.NoteService;
import com.jxwz.service.QuestionService;
import com.jxwz.service.SectionService;
import com.jxwz.service.UserService;
import com.jxwz.service.WordsReplyService;
import com.jxwz.service.WordsService;
import com.jxwz.util.Constants;

public class StudentAction extends BaseAction {

	private static final long serialVersionUID = -1405498545043083940L;
	@Autowired
	private UserService userSerivce;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private NoteService noteService;
	@Autowired
	private WordsService wordsService;
	@Autowired
	private WordsReplyService wordsReplyService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private ChapterService chapterService;
	@Autowired
	private DocumentService documentService;

	private Long userId;
	private Long queId;
	private Long noteId;
	private Long sectionId;
	private Long wordsId;

	private User user;
	private Note note;
	private Words words;

	private String noteTitle;
	private String noteContent;

	// modify information
	public void modifyInfo() {
		logger.info("student modifyInfo,id:" + userId);
		JSONObject obj = new JSONObject();

		try {
			userSerivce.saveOrUpdate(user);
		} catch (Exception e) {
			obj.put("success", false);
			executeAjax(obj);
			e.printStackTrace();
		}
	}

	// delete on question
	public void delIncor() {
		Long userId = (Long) ServletActionContext.getRequest().getSession().getAttribute("userId");
		logger.info("getIncors,studentId:" + userId + ",queId:" + queId);
		JSONObject obj = new JSONObject();

		try {
			questionService.deleteUserIncor(userId, queId);
		} catch (Exception e) {
			obj.put("success", false);
			executeAjax(obj);
			e.printStackTrace();
		}

		obj.put("success", true);
		executeAjax(obj);
	}

	// find oneself's incorrect questions
	public void getIncors() {
		Long userId = (Long) ServletActionContext.getRequest().getSession().getAttribute("userId");
		logger.info("getIncors,studentId:" + userId);

		JSONObject obj = new JSONObject();
		int pageNum = getPageNum() <= 0 ? 1 : getPageNum();
		int pageSize = getPageSize() <= 0 ? 10 : getPageSize();
		int startNum = (pageNum - 1) * pageSize;
		List<Question> tempList = questionService.findByUserId(userId, startNum, pageSize);
		List<Question> resultList = new ArrayList<Question>();
		int totalCount = questionService.queryCountByUserId(userId);
		if (tempList != null && tempList.size() != 0) {
			for (Question temp : tempList) {
				Question q = new Question();
				q.setAnswerCode(temp.getAnswerCode());
				q.setAuthor(temp.getAuthor());
				q.setCreateDateStr(temp.getCreateDateStr());
				q.setRemark(temp.getRemark());
				q.setContent(temp.getContent());
				q.setId(temp.getId());
				resultList.add(q);
			}
		}

		obj.put("ques", resultList);
		obj.put("totalCount", totalCount);

		executeAjax(obj);
	}

	// preview incorrect question's detail
	public void inocrrectDetail() {
		Question q = questionService.findById(queId);

		JSONObject obj = new JSONObject();
		obj.put("data", q);
		super.executeAjax(obj);
	}

	// find oneself's notes
	public void findNotes() {
		userId = (Long) ServletActionContext.getRequest().getSession().getAttribute("userId");
		logger.info("findNotes,userId:" + userId);
		JSONObject obj = new JSONObject();
		int pageNum = getPageNum() <= 0 ? 1 : getPageNum();
		int pageSize = getPageSize() <= 0 ? 10 : getPageSize();
		int startNum = (pageNum - 1) * pageSize;

		List<Note> notes = noteService.findByUserId(userId, startNum, pageSize);
		// int count = noteService.queryCount(userId);
		List<Note> resultList = new ArrayList<Note>();
		if (notes != null && notes.size() != 0) {
			for (Note temp : notes) {
				Note n = new Note();
				n.setContent(temp.getContent());
				n.setTitle(temp.getTitle());
				n.setId(temp.getId());
				if (temp.getSection() != null) {
					Section s = sectionService.findById(temp.getSection().getId());
					n.setSection(s);
					if (s.getChapter() != null) {
						Chapter c = chapterService.findById(s.getChapter().getId());
						s.setChapter(c);
					}
				}
				resultList.add(n);
			}
		}
		obj.put("notes", resultList);
		executeAjax(obj);
	}

	// create a note
	public void addNote() {
		logger.info("addNote");
		JSONObject obj = new JSONObject();
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		try {
			if (noteId == null) {
				// 新笔记
				Section sec = sectionService.findById(sectionId);
				if (sec == null) {
					obj.put("success", false);
					executeAjax(obj);
					return;
				}
				note.setSection(sec);
				note.setStudent(user);
				noteService.saveOrUpdate(note);
			} else {
				// 更新
				if (note == null) {
					note = new Note();
				}
				Note n = noteService.findById(note.getId());
				n.setContent(note.getContent());
				n.setTitle(note.getTitle());
				noteService.saveOrUpdate(note);
			}
		} catch (Exception e) {
			obj.put("success", false);
			executeAjax(obj);
			e.printStackTrace();
		}
		obj.put("success", true);
		executeAjax(obj);
	}

	// preview note detail information
	public void noteDetail() {
		logger.info("addNote");
		JSONObject obj = new JSONObject();
		note = noteService.findById(noteId);
		obj.put("note", note);
		executeAjax(obj);
	}

	// delete note by id
	public void delNote() {
		logger.info("delNote,noteId:" + noteId);
		JSONObject obj = new JSONObject();

		try {
			noteService.deleteById(noteId);
		} catch (Exception e) {
			obj.put("success", false);
			executeAjax(obj);
			e.printStackTrace();
		}

		obj.put("success", true);
		executeAjax(obj);
	}

	// 查询自己给老师的留言
	public void findWords() {
		Long userId = (Long) ServletActionContext.getRequest().getSession().getAttribute("userId");
		logger.info("findWords,studentId:" + userId);

		JSONObject obj = new JSONObject();
		int pageNum = getPageNum() <= 0 ? 1 : getPageNum();
		int pageSize = getPageSize() <= 0 ? 10 : getPageSize();
		int startNum = (pageNum - 1) * pageSize;
		List<Words> words = wordsService.findByUserId(userId, startNum, pageSize);
		List<Words> result = new ArrayList<Words>();
		if (words != null && words.size() != 0) {
			for (Words temp : words) {
				Words w = new Words();
				w.setContent(temp.getContent());
				w.setCreateDateStr(temp.getCreateDateStr());
				w.setId(temp.getId());
				w.setTitle(temp.getTitle());
				w.setState(temp.getState());
				result.add(w);
			}
		}

		obj.put("words", result);
		executeAjax(obj);
	}

	// add words
	public void addWords() {
		logger.info("addWords");
		JSONObject obj = new JSONObject();

		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user == null) {
			obj.put("success", false);
			executeAjax(obj);
			return;
		}
		user = userSerivce.findById(user.getId());
		User teacher = user.getTeacher();
		if (teacher == null) {
			obj.put("success", false);
			executeAjax(obj);
			return;
		}
		words.setStudent(user);
		words.setTeacher(teacher);

		try {
			wordsService.saveOrUpdate(words);
		} catch (Exception e) {
			obj.put("success", false);
			executeAjax(obj);
			e.printStackTrace();
		}

		obj.put("success", true);
		executeAjax(obj);
	}

	public void wordsDetail() {
		logger.info("wordsDetail,wordId:" + wordsId);
		JSONObject obj = new JSONObject();
		Words words = wordsService.findById(wordsId);
		WordsReply wp = wordsReplyService.findByWrodsId(wordsId);
		obj.put("words", words);
		obj.put("wp", wp);
		executeAjax(obj);
	}
	
	// get documents list
	public void getDocs() {
		logger.info("wordsDetail,wordId:" + wordsId);
		JSONObject obj = new JSONObject();
		Long userId = (Long) ServletActionContext.getRequest().getSession().getAttribute("userId");
		if(userId == null) {
			obj.put("success", false);
			executeAjax(obj);
			return;
		}
		
		User u = userSerivce.findById(userId);
		if(u == null || u.getTeacher() == null) {
			obj.put("success", false);
			executeAjax(obj);
			return;
		}
		
		int pageNum = getPageNum() <= 0 ? 1 : getPageNum();
		int pageSize = getPageSize() <=0 ? 10:getPageSize();
		int startNum = (pageNum - 1) * pageSize;
		List<Document> docs = documentService.findByTeacher(u.getTeacher().getId(),startNum,pageSize);
		List<Document> result = new ArrayList<Document>();
		if(docs != null && docs.size()!=0) {
			for(Document temp:docs) {
				Document d = new Document();
				d.setAuthor(temp.getAuthor());
				d.setCreateDateStr(temp.getCreateDateStr());
				d.setDocUrl(Constants.URL_FREFIX+temp.getDocUrl());
				d.setId(temp.getId());
				d.setSubTitle(temp.getSubTitle());
				d.setTitle(temp.getTitle());
				d.setTeacher(temp.getTeacher());
				result.add(d);
			}
		}
		obj.put("docs", result);
		executeAjax(obj);
	}
	
	// setter and getter
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getQueId() {
		return queId;
	}

	public void setQueId(Long queId) {
		this.queId = queId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Words getWords() {
		return words;
	}

	public void setWords(Words words) {
		this.words = words;
	}

	public Long getWordsId() {
		return wordsId;
	}

	public void setWordsId(Long wordsId) {
		this.wordsId = wordsId;
	}

}
