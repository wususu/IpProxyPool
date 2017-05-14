package dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDaoHibernate5<T> implements BaseDao<T>{
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public T get(Class<T> entity, int id) {
		Session session = sessionFactory.getCurrentSession();
		Serializable object = (Serializable)session.get(entity, id);
		return (T) object;
	}
	
	public Serializable save(T entity) {
		Session session = sessionFactory.getCurrentSession();
		Serializable object = (Serializable)session.save(entity);
		return object;
	}

	public void update(T entity) {
		Session session = sessionFactory.getCurrentSession();
		session.update(entity);
	}

	public void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}
	
	
}