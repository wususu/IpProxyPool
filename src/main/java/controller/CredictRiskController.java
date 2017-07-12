package controller;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
import service.CompanyAssessResultService;
import service.CompanySearchResultService;
import service.CompanyService;
import service.CredictRiskCaculaterService;

/**
 * the controller for code test
 * 
 * @author janke
 */
@Controller
@RequestMapping(value="credict")
public class CredictRiskController {
	
	@Autowired
	@Qualifier("companyServiceImpl")
	private CompanyService companyService;
	
	@Autowired
	@Qualifier("credictRiskCaculaterServiceImpl")
	private CredictRiskCaculaterService credictRiskCaculaterService;
	
	@Autowired
	CompanySearchResultService companySearchResultService;
	
	@Autowired
	CompanyAssessResultService companyAssessResultService;
	
	@RequestMapping(value="/view")
	public String Index(){
		return "CredictRisk2";
	}
	
	@RequestMapping(value="/company/{name}")
	@ResponseBody
	public Object searchCompanys(@PathVariable String name){
		ExecutorService exec = Executors.newCachedThreadPool();
		companySearchResultService.setCompanyName(name);
		try {
			return exec.submit(companySearchResultService).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			exec.shutdown();
		}
		return null;
	}
	
	@RequestMapping(value="/assess/{companyKey}")
	@ResponseBody
	public Object searchCompanyAssess(@PathVariable String companyKey){
		if (companyKey.isEmpty()) {
			return null;
		}
		ExecutorService exec = Executors.newCachedThreadPool();
		companyAssessResultService.setCompanyKey(companyKey);
		try {
			return exec.submit(companyAssessResultService).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			exec.shutdown();
		}
		return null;
	} 
	
	@RequestMapping(value="/caculate/{id}")
	@ResponseBody
	public Object caculate(@ModelAttribute Finance finance, @PathVariable Integer id){
		Company company = companyService.get(id);
		credictRiskCaculaterService.caculater(finance, company, new LegalEvaluation());
		return credictRiskCaculaterService.caculate();
	}
	
}
