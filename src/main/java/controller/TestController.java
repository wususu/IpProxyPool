package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import service.CompanyService;
import spider.IndexSpider;

/**
 * the controller for code test
 * 
 * @author janke
 */
@Controller
@RequestMapping(value="/test")
public class TestController {
	
	@Autowired
	IndexSpider IndexSpider;
	
	@Autowired
	@Qualifier("companyServiceImpl")
	private CompanyService companyService;
	
	@RequestMapping(value="/bean")
	@ResponseBody
	public Object TTCCLayout(){
		System.out.println(companyService);
		System.out.println(companyService.getCompanyDao());
		return companyService.get(1);
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
	
	
	
}
