package server.dao.impl;

import org.springframework.stereotype.Repository;
import server.dao.StoryDao;
import server.model.Story;
import server.model.User;

import java.util.List;

@Repository
public class StoryDaoImpl  extends BaseDaoHibernate<Story> implements StoryDao{

	/**
	 * 根据分享的标题信息检索符合条件的分享实体
	 * @author xxz
	 */
	@Override
	public Story findStory(String title) {
		List<Story> stories = executeHQL("from Story e where e.title = ?"
				,title);
			if (stories!= null && stories.size() >= 1)
			{
				return stories.get(0);
			}
			return null;
	}

	@Override
	public Story findById(Integer id) {
		List<Story> stories = executeHQL("from Story e where e.id = ?"
				, id);
		if (stories!= null && stories.size() >= 1)
		{
			return stories.get(0);
		}
		return null;
	}

	/**
	 * 根据标题信息，模糊检索，返回符合条件的分享信息集
	 * @author xxz
	 */
	@Override
	public List<Story> findByTopic(String topic) {
		List<Story> stories = executeHQL("from Story e where e.title like ?"
				, topic);
		return stories;
	}
	
	/**
	 * 根据关联的用户实体，检索返回符合条件的分享信息集
	 * @author xxz
	 */
	@Override
	public List<Story> findByUser(User user) {
		List<Story> stories = executeHQL("from Story e where e.user = ?"
				, user);
		return stories;
	}

}
