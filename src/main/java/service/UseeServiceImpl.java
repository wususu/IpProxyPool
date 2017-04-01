package service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UseeDao;
import entity.Usee;
@Service
@Transactional
public class UseeServiceImpl implements UseeService{

	@Autowired
	@Qualifier("useeDaoImpl")
	UseeDao useeDao;
	
	@Override
	public Usee get(Integer id) {
		return useeDao.get(Usee.class, id);
	}

	@Override
	public Serializable save(Usee entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Usee entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Usee entity) {
		// TODO Auto-generated method stub
		
	}

}
