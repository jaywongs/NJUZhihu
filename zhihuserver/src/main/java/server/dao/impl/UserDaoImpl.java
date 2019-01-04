package server.dao.impl;

import org.springframework.stereotype.Repository;
import server.dao.UserDao;
import server.model.User;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoHibernate<User> implements UserDao{
	
	/**
	 * 根据姓名信息，检索返回符合条件的用户
	 * @author xxz
	 */
	@Override
	public User findByName(String name) {
		List<User> users = executeHQL("from User e where e.username = ?"
				, name);
			if (users!= null && users.size() >= 1)
			{
				return users.get(0);
			}
			return null;
	}
	
	/**
	 * 根据是否为名人的标签，检索返回符合条件的用户集
	 * @author xxz
	 */
	@Override
	public List<User> findByIsFamous(int isfamous) {
		List<User> users = executeHQL("from User e where e.isFamous = ?"
				, isfamous);
		return users;
	}
	
	/**
	 * 根据用户头像Url，检索返回符合条件的用户集
	 * @author xxz
	 */
	@Override
	public User findByImgUrl(String imgUrl) {
		List<User> users = executeHQL("from User e where e.avatarUrl = ?"
				, imgUrl);
			if (users!= null && users.size() >= 1)
			{
				return users.get(0);
			}
			return null;
	}
	
	/**
	 * 根据关联的用户实体ID，检索ta所关注的用户集
	 * @author xxz
	 */
	@Override
	public List<User> findLikedByUserid(int userid) {
		List<User> users = executeHQL("from User e,Follow d where e.id = d.followed_user_id"
				+ "  and d.user_id = ?"
				, userid);
		return users;
	}

	/**
	 * 根据姓名，模糊检索，返回符合条件的用户集
	 * @author xxz
	 */
	@Override
	public List<User> findBySearch(String name) {
		List<User> users = executeHQL("from User e where e.username like ?"
				, name);
		return users;
	}
	
	/**
	 * 根据用户ID，检索返回用户实体
	 * @author xxz
	 */
	@Override
	public User findByUserid(int userid) {
		List<User> users = executeHQL("from User e where e.id = ?"
				, userid);
			if (users!= null && users.size() >= 1)
			{
				return users.get(0);
			}
			return null;
	}


}
