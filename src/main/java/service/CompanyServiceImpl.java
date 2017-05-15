package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CompanyDao;
import entity.Company;

/**
 * Company Service Implement
 * 
 * @author janke
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	@Qualifier("companyDaoImpl")
	private CompanyDao companyDao;
	
	public Company get(Integer id) {
		return companyDao.get(Company.class, id);
	}
	
	public Company get(String companyKey){
		return companyDao.get(companyKey);
	}
	
	public  void update(Company company) {
		companyDao.update(company);
	}
	
	public void add(Company company){
		companyDao.save(company);
	}

	public List<Company> getCompanys(String companyName){
		return companyDao.getCompanys(companyName);
	}
	
}
