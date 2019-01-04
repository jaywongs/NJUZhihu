package server.dao;

import java.util.List;

/**
 * Created by xymark.wang on 2017/11/3.
 */
public interface BaseDao<T> {

	void save(T t);

	void update(T t);

	void delete(T t);

	T findOne(int id);

	List<T> findAll();

	List<T> executeHQL(String hql, Object... param);
}

