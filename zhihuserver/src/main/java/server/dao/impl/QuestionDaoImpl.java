package server.dao.impl;

import org.springframework.stereotype.Repository;
import server.dao.QuestionDao;
import server.model.Question;
import server.model.User;

import java.util.List;

@Repository
public class QuestionDaoImpl extends BaseDaoHibernate<Question> implements QuestionDao{

	/**
	 * 根据话题信息检索符合条件的问题
	 * @author xxz
	 */
	@Override
	public List<Question> findByTopic(String topic) {
		List<Question> questions = executeHQL("from Question e where e.content like ?"
				, topic);
		return questions;
	}
	
	/**
	 * 根据用户实体，检索与ta相关的问题信息集
	 * @author xxz
	 */
	@Override
	public List<Question> findByUser(User user) {
		List<Question> questions = executeHQL("from Question e where e.user =?"
				, user);
		return questions;
	}
	
	/**
	 * 根据用户实体和话题信息，检索符合的通知信息集
	 * @author xxz
	 */
	@Override
	public List<Question> findByTopicAndUser(String topic, User user) {
		List<Question> questions = executeHQL("from Question e where e.content like ? and e.user =?"
				, topic,user);
		return questions;
	}
	
	/**
	 * 根据问题ID，检索对应的通知实体
	 * @author xxz
	 */
	@Override
	public Question findById(int quesid) {
		List<Question> questions = executeHQL("from Question e where e.id = ?"
				, quesid);
			if (questions!= null && questions.size() >= 1)
			{
				return questions.get(0);
			}
			return null;
	}
	
	/**
	 * 根据用户实体和问题内容，检索符合的通知实体
	 * @author xxz
	 */
	@Override
	public Question findByContentAndUser(String content, User user) {
		List<Question> questions = executeHQL("select e from Question e where e.content = ?  and e.user = ?"
				, content,user);
			if (questions!= null && questions.size() >= 1)
			{
				return questions.get(0);
			}
			return null;
	}
	
	/**
	 * 根据创建时间，检索符合的通知信息集
	 * @author xxz
	 */
	@Override
	public List<Question> getAllByTime() {
		List<Question> questions = executeHQL("from Question order by is_free desc");
		return questions;
	}

}
