package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.CompanyDao;
import spider.CompanyAssessSpider;
import spider.CompanySearchSpider;

@Controller
@RequestMapping(value="/test")
public class TestController {

	
	@RequestMapping(value="/sql")
	public void testHbernate(){
		CompanyDao aa = new CompanyDao();
		System.out.println(aa.get(1));
	}
	
	@RequestMapping(value="/spider/company/{name}")
	@ResponseBody
	public Object testCompanySearchSprider(@PathVariable String name){
		if (!name.isEmpty()) {
			CompanySearchSpider.run(name);
		}
		return null;
	}
	
	@RequestMapping(value="/spider/access/{key}")
	@ResponseBody
	public Object testCompanyAssessSpider(@PathVariable Integer key){
		if (key != null) {
			CompanyAssessSpider.run(key.toString());
		}
		return null;
	}
	
}
