package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import entity.Company;

/**
 *  CURD implement of company entity
 * 
 * @author janke
 */
@Repository
public class CompanyDaoImpl extends BaseDaoHibernate5<Company> implements CompanyDao{

	@Autowired
	private  SessionFactory sessionFactory ;
	
	public Company get(String companyKey){
		Session session = sessionFactory.getCurrentSession();
		 Criteria criteria= session.createCriteria(Company.class).add(Restrictions.eq("companyKey", companyKey));
		Company company= (Company)criteria.uniqueResult();
		return company;
	}
	
	public List<Company> getCompanys(String companyName) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Company.class).add(Restrictions.ilike("companyName","%"+ companyName + "%"));
		@SuppressWarnings("unchecked")
		List<Company> companys = (List<Company>)criteria.list();
		return companys;
	}
}
