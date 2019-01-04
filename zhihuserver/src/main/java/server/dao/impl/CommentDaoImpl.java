package server.dao.impl;

import org.springframework.stereotype.Repository;
import server.dao.CommentDao;
import server.model.Comment;
import server.model.Story;

import java.util.List;

@Repository
public class CommentDaoImpl  extends BaseDaoHibernate<Comment>  implements  CommentDao{

	@Override
	public List<Comment> findByStory(Story story) {
		List<Comment> comments = executeHQL("from Comment e where e.story = ?"
				, story);
		return comments;
	}

}
