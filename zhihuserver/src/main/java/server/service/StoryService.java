package server.service;


import server.dao.StoryDao;
import server.dao.UserDao;
import server.model.Story;
import server.model.User;

import java.util.List;

public class StoryService {
	
	private StoryDao storyDao;
	
	private UserDao userDao;

	public StoryDao getStoryDao() {
		return storyDao;
	}

	public void setStoryDao(StoryDao storyDao) {
		this.storyDao = storyDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void addStory(Story story){
		storyDao.save(story);
	}
	
	public void updateStory(Story story){
		storyDao.update(story);
	}
	
	public List<Story> getAllStories(){
		return storyDao.findAll();
	}
	
	public Story  findStory(String title){
		return storyDao.findStory(title);
	}
	
	public List<Story> getStoriesByTopic(String topic){
		return storyDao.findByTopic(topic);
	}
	
	public List<Story> getStoriesByUser(User user){
		return storyDao.findByUser(user);
	}

	public void updateState(Integer id){
		Story s = storyDao.findById(id);
		s.setIs_free(s.getIs_free() == 0 ? 1 : 0);
		storyDao.update(s);
	}
}
