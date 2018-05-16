package com.jxwz.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jxwz.entity.Post;
import com.jxwz.entity.PostReply;
import com.jxwz.entity.User;
import com.jxwz.entity.Words;
import com.jxwz.entity.WordsReply;
import com.jxwz.service.PostReplyService;
import com.jxwz.service.PostService;

public class DiscuzAction extends BaseAction {

	private static final long serialVersionUID = 6222284681372440271L;

	@Autowired
	private PostService postService;
	@Autowired
	private PostReplyService postReplyService;

	private Post post;
	private PostReply postReply;
	private PostReply targetReply;
	private Long postId;
	private Words words;
	private Long wordsId;
	private WordsReply wordsReply;
	private int replyType;
	private Long userId;
	private Long replyId;
	private String content;
	private User user;

	public void newPost() {
		logger.info("newPost");
		JSONObject obj = new JSONObject();

		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user == null) {
			obj.put("success", false);
			return;
		}

		post.setUser(user);
		postService.saveOrUpdate(post);
		obj.put("success", true);
		executeAjax(obj);
	}

	public String toDetail() {
		logger.info("toPostDetail,postId:" + postId);
		Post post = postService.findById(postId);
		setAttr("detail", post);
		int pageNum = getPageNum();
		pageNum = pageNum <= 0 ? 1 : pageNum;
		int pageSize = getPageSize();
		pageSize = pageSize <= 0 ? 10 : pageSize;
		int startNum = (pageNum - 1) * getPageSize();
		List<PostReply> list = postReplyService.selectByPostId(postId, startNum, pageSize);
		setAttr("replys", list);
		return "postDetail";
	}

	public void savePostReply() {
		try {
			logger.info("savePostReply,postId:" + post.getId());
			JSONObject obj = new JSONObject();

			if (postReply == null) {
				logger.info("savePostReply,postReply is null");
				postReply = new PostReply();
			}

			postReply.setUser((User) ServletActionContext.getRequest().getSession().getAttribute("user"));
			int max = postReplyService.findMaxFloorByPostId(post.getId());
			postReply.setFloorNum(max + 1);

			// 设置主题
			Post p = postService.findById(post.getId());
			if (p == null) {
				obj.put("success", false);
				executeAjax(obj);
				return;
			}
			postReply.setPost(p);

			// 设置回复
			if (replyType != 1) {
				// 回复目标回复
				PostReply r = postReplyService.findById(targetReply.getId());
				if (r == null) {
					obj.put("success", false);
					executeAjax(obj);
					return;
				}
				postReply.setReply(r);
			}

			postReplyService.saveOrUpdate(postReply);
			obj.put("success", true);
			executeAjax(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String deletePost() {
		logger.info("deletePost,postId:" + postId);
		postService.deleteById(postId);
		return SUCCESS;
	}

	public String newWords() {
		return null;
	}

	// setter and getter
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
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

	public PostReply getPostReply() {
		return postReply;
	}

	public void setPostReply(PostReply postReply) {
		this.postReply = postReply;
	}

	public WordsReply getWordsReply() {
		return wordsReply;
	}

	public void setWordsReply(WordsReply wordsReply) {
		this.wordsReply = wordsReply;
	}

	public int getReplyType() {
		return replyType;
	}

	public void setReplyType(int replyType) {
		this.replyType = replyType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getReplyId() {
		return replyId;
	}

	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PostReply getTargetReply() {
		return targetReply;
	}

	public void setTargetReply(PostReply targetReply) {
		this.targetReply = targetReply;
	}

}
