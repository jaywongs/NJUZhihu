package server.service;


import org.springframework.stereotype.Service;
import server.dao.MessageDao;
import server.dao.UserDao;
import server.model.Message;
import server.model.User;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageService {

	@Resource
	private UserDao userDao;

	@Resource
	private MessageDao messageDao;
	
	private User user;
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public  List<Message>  findByUser(User user){
		return messageDao.findByUser(user);
	}
	
	public void addMessage(Message message){
		messageDao.save(message);
	}
	
}
