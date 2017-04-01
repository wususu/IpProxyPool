package dao;

import java.io.Serializable;

public interface BaseDao<T> {
	
	T get(Class<T> entity,int id);
	
	Serializable save(T entity);
	
	void update(T entity);
	
	void delete(T entity);
	
}