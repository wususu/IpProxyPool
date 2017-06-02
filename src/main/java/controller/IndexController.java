package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Company;
import entity.Finance;
import entity.LegalEvaluation;
import service.CompanyService;
import service.CredictRiskCaculaterService;
import service.ProxyCacheService;
import service.ProxyTempCacheService;
import spider.IndexSpider;
import spider.ProxySpider;
import spider.ProxyVerificationSpider;

/**
 * the controller for code test
 * 
 * @author janke
 */
@Controller
@RequestMapping(value="/test")
public class IndexController {
	
	@Autowired
	ProxyTempCacheService proxyTempCacheService;
	
	@Autowired
	ProxyCacheService proxyCacheService;
	
	@Autowired
	IndexSpider IndexSpider;
	
	@Autowired
	ProxySpider proxySpider;
	
	@Autowired
	ProxyVerificationSpider proxyVerificationSpider;
	
	@Autowired
	@Qualifier("companyServiceImpl")
	private CompanyService companyService;
	
	@Autowired
	@Qualifier("credictRiskCaculaterServiceImpl")
	private CredictRiskCaculaterService credictRiskCaculaterService;
	
	
	@RequestMapping(value="/index")
	public String credictRiskPage(){
		return "CredictRisk";
	}
	
	@RequestMapping(value="/spider/company/{name}")
	@ResponseBody
	public Object testCompanySearchSprider(@PathVariable String name){
		if (!name.isEmpty()) {
			IndexSpider.runCompanySearchSpider(name);
		}
		return null;
	}
	
	@RequestMapping(value="/spider/access/{key}")
	@ResponseBody
	public Object testCompanyAssessSpider(@PathVariable Integer key){
		if (key != null) {
			IndexSpider.runCompanyAssessSpider(key.toString());
		}
		return null;
	}
	
	@RequestMapping(value="/caculate/{id}")
	@ResponseBody
	public Object caculate(@ModelAttribute Finance finance, @PathVariable Integer id ){
		System.out.println(id);
		Company company = companyService.get(id);
		System.out.println(company.getCompanyName());
		System.out.println(finance);
		System.out.println(finance.getInventoryTurnover() + " " +finance.getNetProfit());
		credictRiskCaculaterService.caculater(finance, company, new LegalEvaluation());
		return credictRiskCaculaterService.caculate();
	}
	
}
