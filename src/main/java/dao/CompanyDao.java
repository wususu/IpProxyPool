package dao;


import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.Configurable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.Company;


public class CompanyDao {

	private  SessionFactory sessionFactory ;

	
	public CompanyDao() {
		try{
			Configuration configuration = new Configuration ().configure().addAnnotatedClass(Company.class);
			System.out.println(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory();
		}catch (Exception e) {
			System.err.println("Failed to create sessionFactory object." + e);
			// TODO: handle exception
		}
	}
	
	public void destroy(){
        sessionFactory.close();
	}
	
	public void update(Company company){
		Session session = sessionFactory.openSession();
		try{
			session.update(company);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public Company get(Integer id){
		Session session = sessionFactory.openSession();
		try{
		return (Company)session.get(Company.class,id);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
	
	public void test() {
		get(1);
	}
	
	public void add(Company company){
		Session session = sessionFactory.openSession();
		try{
		Transaction tx = session.beginTransaction();
		session.save(company);
		tx.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
