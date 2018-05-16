package com.jxwz.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jxwz.entity.Question;
import com.jxwz.entity.Section;
import com.jxwz.entity.TestResult;
import com.jxwz.entity.User;
import com.jxwz.service.QuestionService;
import com.jxwz.service.SectionService;
import com.jxwz.service.UserService;

public class TestAction extends BaseAction {

	private static final long serialVersionUID = -3980837919652561294L;

	@Autowired
	private SectionService sectionService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private UserService userService;

	private Long chapterId;
	private Long sectionId;
	private Long userId;
	private Long questionId;
	private String resultStr;

	// 根据chapterId获取section
	public void getSetcions() {
		JSONObject obj = new JSONObject();
		List<Section> sections = sectionService.findSectionsByChapterId(chapterId);
		obj.put("sections", sections);
		super.executeAjaxString(obj.toJSONString());
	}

	// 根据sectionId获取该节的练习题
	public String toTestList() {
		List<Question> questions = questionService.findQuestion(sectionId);

		// 如果问题数量小于5,则全部返回,如果大于5,则随机返回5个问题
		if (questions != null && questions.size() > 5) {
			questions = this.convertList(questions);
		}

		super.setAttr("ques", questions);
		return "quesList";
	}

	private List<Question> convertList(List<Question> questions) {
		if (questions == null) {
			return null;
		}

		List<Question> list = new ArrayList<Question>();
		List<Question> resultList = new ArrayList<Question>();
		int size = list.size();
		Random rand = new Random();
		for (int i = 0; i < 5;) {
			int index = rand.nextInt(size);
			Question q = list.get(index);
			if (!resultList.contains(q)) {
				resultList.add(q);
				i++;
			}
		}
		return resultList;
	}

	// 添加错题
	public void addIncorrect() {
		JSONObject obj = new JSONObject();
		Question q = questionService.findById(questionId);
		User user = userService.findById(userId);
		if (user == null) {
			obj.put("error", 0);
			obj.put("msg", "请登录后再添加");
			obj.put("queId", questionId);
			super.executeAjaxString(obj.toJSONString());
		}

		if (user.getIncrrects().contains(q)) {
			obj.put("error", 1);
			obj.put("msg", "已添加过该问题");
		} else {
			user.getIncrrects().add(q);
			userService.saveOrUpdate(user);
			obj.put("error", 2);
			obj.put("msg", "添加成功");
		}
		super.executeAjaxString(obj.toJSONString());
	}

	// 获取测试结果
	public void getResult() {
		JSONObject obj = new JSONObject();
		if(resultStr == null || resultStr.length() == 0) {
			logger.info("resultStr is null or length is 0,return");
			obj.put("success", false);
			executeAjax(obj);
			return ;
		}
		String[] strs = resultStr.split(",");
		List<TestResult> resultList = new ArrayList<TestResult>();
		for(int i=0;i<resultStr.length();i++) {
			Long id = Long.valueOf(strs[i]);
			i++;
			String code = strs[i];
			
			Question q = questionService.findById(id);
			if(q == null) {
				continue;
			}
			if(!code.equals(q.getAnswerCode())) {
				TestResult tr = new TestResult();
				tr.setCode(q.getAnswerCode());
				tr.setQueId(q.getId());
				tr.setRemark(q.getRemark());
				resultList.add(tr);
			}
			
		}
		obj.put("result", resultList);
		obj.put("success", true);
		executeAjax(obj);
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

}
