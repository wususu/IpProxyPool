package service;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import entity.Usee;


@Service
public interface UseeService {
	Usee get(Integer id);
	
	Serializable save(Usee entity);
	
	void update(Usee entity);
	
	void delete(Usee entity);
}