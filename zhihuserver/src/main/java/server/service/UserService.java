package server.service;


import org.springframework.stereotype.Service;
import server.dao.AnswerDao;
import server.dao.MessageDao;
import server.dao.QuestionDao;
import server.dao.UserDao;
import server.model.User;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

	@Resource
	private UserDao userDao;

	
	public void addUser(User user){
		userDao.save(user);
	}

	public void updateUser(User user){
		userDao.update(user);
	}
	
	public User  findByName(String name){
		return userDao.findByName(name);
	}
	
	public User  findById(int userid){
		return userDao.findByUserid(userid);
	}
	
	public List<User> findAllByIsFamous(int isfamous){
		return userDao.findByIsFamous(isfamous);
	}
	
	public  List<User>  findLikedByUserid(int userid){
		return userDao.findLikedByUserid(userid);
	}
	
	public  List<User>  findBySearch(String name){
		return userDao.findBySearch(name);
	}
	
	public User  findByImgUrl(String url){
		return userDao.findByImgUrl(url);
	}
	
}
