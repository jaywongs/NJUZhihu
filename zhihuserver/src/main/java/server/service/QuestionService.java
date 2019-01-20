package server.service;

import org.springframework.stereotype.Service;
import server.dao.QuestionDao;
import server.model.Question;
import server.model.User;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionService {

	@Resource
	private QuestionDao questionDao;

	public void addQuestion(Question question){
		questionDao.save(question);
	}
	
	public List<Question> getAllQuestions(){
		return questionDao.findAll();
	}
	
	public List<Question> getAllQuestionsByTime(){
		return questionDao.findAll();
	}
	
	public Question getQuestionById(int id){
		return questionDao.findById(id);
	}
	
	public Question getQuestionByContentAndUser(String content, User user){
		return questionDao.findByContentAndUser(content, user);
	}
	
	
	public List<Question> getQuestionsByTopic(String topic){
		return questionDao.findByTopic(topic);
	}
	
	public List<Question> getQuestionsByTopicAndUser(String topic,User user){
		return questionDao.findByTopicAndUser(topic, user);
	}
	
	public List<Question> getQuestionsByUser(User user){
		return questionDao.findByUser(user);
	}
	
	public List<Question> getQuestionsByTime(){
		return questionDao.getAllByTime();
	}

	public void updateState(Integer id){
		 Question q = questionDao.findById(id);
		 q.setIs_free(q.getIs_free() == 0 ? 1 : 0);
		 questionDao.update(q);
	}
	
}
