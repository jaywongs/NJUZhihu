package server.dao.impl;

import org.springframework.stereotype.Repository;
import server.dao.MessageDao;
import server.model.Message;
import server.model.User;

import java.util.List;

@Repository
public class MessageDaoImpl extends BaseDaoHibernate<Message> implements MessageDao {
	
	/**
	 * 根据用户实体，检索与ta相关的通知信息集
	 * @author xxz
	 */
	@Override
	public List<Message> findByUser(User user) {
		System.out.println("..."+user.getSimpledesc());
		List<Message> messages = executeHQL("from Message e where e.user = ?"
				, user);
		return messages;
	}
}
