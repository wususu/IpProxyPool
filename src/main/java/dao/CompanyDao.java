package dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;


import entity.Company;


public class CompanyDao {

	private  SessionFactory sessionFactory ;
	
	public CompanyDao() {
		try{
			sessionFactory = new Configuration().configure().addAnnotatedClass(Company.class).buildSessionFactory();
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
		Transaction tx = session.beginTransaction();
		try{
		session.update(company);
		tx.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
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
	
	public Company get(String companyKey){
		Session session = sessionFactory.openSession();
		try{
		 Criteria criteria= session.createCriteria(Company.class).add(Restrictions.eq("companyKey", companyKey));
		Company company= (Company)criteria.uniqueResult();
		return company;
		}finally {
			session.close();
		}
	}
	
	public void add(Company company) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
		session.save(company);
		tx.commit();
		}catch (ConstraintViolationException e) {
			// TODO: handle exception
			System.err.println("Already exists company:  " + e);
			tx.rollback();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
	}
	
}
