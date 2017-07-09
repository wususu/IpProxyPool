package service;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Company;
import spider.IndexSpider;

@Component
public class CompanyAssessResultService implements Callable<Company>{

	@Autowired
	private IndexSpider indexSpider;
	
	public String aa = "adsd";
	
	private Company companyAssessResult;
	
	private String companyKey;
	
	public void setCompanyKey(String companyKey) {
		this.companyKey = companyKey;
	}
	
	synchronized public void addResult(Company company){
		if (!company.getCompanyAssess().equals(null)) {
			this.companyAssessResult = company;
		}
	}
	
	@Override
	public Company call(){
		// TODO Auto-generated method stub
		companyAssessResult = null;
		while(companyAssessResult == null && companyKey != null){
			indexSpider.runCompanyAssessSpider(companyKey);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (companyAssessResult != null && companyAssessResult.getCompanyAssess() != null) {
				return companyAssessResult;
			}
		}
		return null;
	}
}
