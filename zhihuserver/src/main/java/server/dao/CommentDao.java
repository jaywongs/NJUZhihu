package server.dao;

import server.model.Comment;
import server.model.Story;

import java.util.List;

public interface CommentDao   extends BaseDao<Comment>{
	
	//根据分享内容实体检索对应的评论集
	List<Comment> findByStory(Story story);
}
