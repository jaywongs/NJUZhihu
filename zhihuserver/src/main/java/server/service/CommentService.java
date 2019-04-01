package server.service;


import server.dao.CommentDao;
import server.model.Comment;
import server.model.Story;

import java.util.List;

public class CommentService {
	
	private CommentDao commentDao;

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
	public void addComment(Comment comment){
		commentDao.save(comment);
	}
	
	public List<Comment> listComments(Story story){
		return commentDao.findByStory(story);
	}
}
