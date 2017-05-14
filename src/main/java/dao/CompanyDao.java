package dao;

import entity.Company;

/**
 *  CURD interface of company entity
 * 
 * @author janke
 */
public interface CompanyDao extends BaseDao<Company>{
	
	public Company get(String companyKey);
}
