package service;

import java.util.List;

import org.springframework.stereotype.Service;

import dao.CompanyDao;
import entity.Company;

/**
 * Company Service Interface
 * 
 * @author janke
 */
@Service
public interface CompanyService {

	public Company get(Integer id);
	
	public Company get(String companyKey);
	
	public void update(Company company);
	
	public void add(Company company);
	
	public List<Company> getCompanys(String companyName);
	
}
