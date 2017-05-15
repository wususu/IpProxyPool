package dao;

import java.util.List;

import entity.Company;

/**
 *  CURD interface of company entity
 * 
 * @author janke
 */
public interface CompanyDao extends BaseDao<Company>{
	
	public Company get(String companyKey);
		
	public List<Company> getCompanys(String companyName);
}
