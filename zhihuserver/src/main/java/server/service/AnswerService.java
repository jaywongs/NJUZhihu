package server.service;


import org.springframework.stereotype.Service;
import server.dao.AnswerDao;
import server.model.Answer;
import server.model.Question;
import server.model.User;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnswerService {

	private Question question;

	@Resource
	private AnswerDao answerDao;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Answer findByQuesId(Question question){
		return answerDao.findByQuesId(question);
	}
	
	public Answer findByAnswerId(Integer ansid){
		return answerDao.findById(ansid);
	}
	
	public Answer findByContent(String content){
		return answerDao.findByContent(content);
	}
	
	public void updateAnswer(Answer answer){
		answerDao.update(answer);
	}
	
	public void addAnswer(Answer answer){
		answerDao.save(answer);
	}
	
	public Answer getQuestionByContentAndUser(String content, User user){
		return answerDao.findByContentAndUser(content, user);
	}
	
	public List<Answer> getAllAnswers(){
		return answerDao.findAll();
	}
	
	public List<Answer> getAnswersByUser(User user){
		return answerDao.findByUser(user);
	}
}
