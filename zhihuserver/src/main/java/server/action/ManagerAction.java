package server.action;

import com.opensymphony.xwork2.ActionSupport;
import server.model.Question;
import server.model.Story;
import server.service.QuestionService;
import server.service.StoryService;

import java.util.List;

public class ManagerAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private QuestionService questionService;

    private StoryService storyService;

    private List<Question> questions;

    private Question question;

    private List<Story> stories;

    public Story story;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public StoryService getStoryService() {
        return storyService;
    }

    public void setStoryService(StoryService storyService) {
        this.storyService = storyService;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public String listQuestions(){
        setQuestions(questionService.getAllQuestions());
        setStories(storyService.getAllStories());
        return SUCCESS;
    }

    public String closeQuestions(){
        Integer id = question.getId();
        questionService.updateState(id);
        setQuestions(questionService.getAllQuestions());
        setStories(storyService.getAllStories());
        return SUCCESS;
    }

    public String closeStory(){
        Integer id = story.getId();
        storyService.updateState(id);
        setQuestions(questionService.getAllQuestions());
        setStories(storyService.getAllStories());
        return SUCCESS;
    }
}
