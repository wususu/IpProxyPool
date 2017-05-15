package controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.Iterator;
import java.util.List;

import org.hibernate.annotations.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Company;
import junit.framework.Test;
import service.CompanyService;
import service.CompanyServiceImpl;
import spider.IndexSpider;

/**
 * the Controller for handling the request about mod five  (company assess)
 * 
 * @author janke
 */
@Controller
@RequestMapping(value="/company")
public class CreditRickController {

	@Autowired
	@Qualifier("companyServiceImpl")
	private CompanyService companyService;
	
	@Autowired
	private IndexSpider indexSpider;
	
	public Object test( String name){
		return companyService.getCompanys(name);
	}
	
	@RequestMapping(value="/search/{name}")
	@ResponseBody
	public Object testCompanySearchSprider(@PathVariable String name){
		if (!name.isEmpty()) {
			List<Company> companys = companyService.getCompanys(name);
			if (!companys.isEmpty()) {
				return companys;
			}else {
				indexSpider.runCompanySearchSpider(name);
				return "again";
				}
			}
		return null;
	}
	
	@RequestMapping(value="/assess/{key}")
	@ResponseBody
	public Object testCompanyAssessSpider(@PathVariable Integer key){
		if (key != null) {
			Company company = companyService.get(key.toString());
			if (company.getCompanyAssess() != null) {
				return company;
			}else {
				indexSpider.runCompanyAssessSpider(key.toString());
				return "again";
			}
		}
		return null;
	}
}
