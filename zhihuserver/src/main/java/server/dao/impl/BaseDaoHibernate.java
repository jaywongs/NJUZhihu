package server.dao.impl;


import org.hibernate.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import server.dao.BaseDao;

import javax.annotation.Resource;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Transactional
public class BaseDaoHibernate<T> implements BaseDao<T>
{

	private Class pClass;

	public BaseDaoHibernate() {
		Class clazz = this.getClass();
		Type type = clazz.getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType) type;
		Type[] types = pType.getActualTypeArguments();
		Class tClass = (Class) types[0];
		this.pClass = tClass;
	}

	@Resource
	protected SessionFactory sessionFactory;

	@Override
	public void save(T t) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(t);
		tx.commit();
		session.close();
	}

	@Override
	public void update(T t) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(t);
		tx.commit();
		session.close();
	}

	@Override
	public void delete(T t) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(t);
		tx.commit();
		session.close();
	}

	@Override
	@SuppressWarnings(value="unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public T findOne(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		T t = (T)session.get(pClass, id);
		tx.commit();
		session.close();
		return t;
	}

	@Override
	@SuppressWarnings(value="unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<T> findAll() {
		Session session = sessionFactory.openSession();
		List<T> list;
		try {
			Transaction tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(pClass);
			list = criteria.list();
			tx.commit();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<T> executeHQL(String hql, Object... param) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		for (int i = 0; i < param.length; i++){
			query.setParameter(i, param[i]);
		}
		List<T> result = query.getResultList();
		tx.commit();
		session.close();
		return result;
	}
}
