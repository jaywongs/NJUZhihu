package server.service;


import org.springframework.stereotype.Service;
import server.dao.FollowDao;
import server.dao.UserDao;
import server.model.Follow;

import javax.annotation.Resource;

@Service
public class FollowService {

	@Resource
	public FollowDao followDao;

	@Resource
	public UserDao userDao;

	public void addFollow(Follow follow){
		followDao.save(follow);
	}
	
	public Follow findFollow(Integer followed_id,Integer follow_id){
		return followDao.findFollow(followed_id, follow_id);
	}
	
	public void deleteFollow(Integer followed_userid,Integer follow_userid){
		followDao.deleteFollow(followed_userid, follow_userid);
	}
}
