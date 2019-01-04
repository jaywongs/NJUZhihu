package server.dao;

import server.model.Message;
import server.model.User;

import java.util.List;

public interface MessageDao extends BaseDao<Message>{
	
	//根据用户实体，检索与ta相关的通知信息集
	List<Message> findByUser(User user);
	
}
