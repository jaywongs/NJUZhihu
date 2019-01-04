package server.dao.impl;

import org.springframework.stereotype.Repository;
import server.dao.AnswerDao;
import server.model.Answer;
import server.model.Question;
import server.model.User;

import java.util.List;

@Repository
public class AnswerDaoImpl extends BaseDaoHibernate<Answer> implements AnswerDao {
	
	/**
	 * 根据问题获取对应的回答
	 * @author xxz
	 */
	@Override
	public Answer findByQuesId(Question question) {
		List<Answer> answers = executeHQL("select e from Answer e where e.question = ?0"
				, question);
			if (answers!= null && answers.size() >= 1)
			{
				return answers.get(0);
			}
			return null;
	}
	
	/**
	 * 根据内容检索对应的回答
	 * @author xxz
	 */
	@Override
	public Answer findByContent(String content) {
		List<Answer> answers = executeHQL("select e from Answer e where e.content = ?0"
				, content);
			if (answers!= null && answers.size() >= 1)
			{
				return answers.get(0);
			}
			return null;
	}
	
	/**
	 * 根据回答ID获取相应信息
	 * @author xxz
	 */
	@Override
	public Answer findById(int ansid) {
		List<Answer> answers = executeHQL("select e from Answer e where e.id = ?0"
				, ansid);
			if (answers!= null && answers.size() >= 1)
			{
				return answers.get(0);
			}
			return null;
	}

	/**
	 * 根据内容及关联用户检索对应的回答
	 * @author xxz
	 */
	@Override
	public Answer findByContentAndUser(String content, User user) {
		List<Answer> answers = executeHQL("select e from Answer e where  e.content = ?0  and e.user = ?1"
				, content,user);
			if (answers!= null && answers.size() >= 1)
			{
				return answers.get(0);
			}
			return null;
	}
	
	/**
	 * 根据关联用户检索对应的回答
	 * @author xxz
	 */
	@Override
	public List<Answer> findByUser(User user) {
		List<Answer> answers = executeHQL("from Answer e where e.user =?"
				, user);
		return answers;
	}

}
