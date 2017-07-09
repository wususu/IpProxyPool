package service;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Company;
import spider.IndexSpider;

@Component
public class CompanySearchResultService implements Callable<List<Company>>{

	 private  List<Company> companySearchResult;
	
	 private String companyName;
	 
	 @Autowired
	 private IndexSpider indexSpider;
	 
	 public void setCompanyName(String companyName){
		 this.companyName = companyName;
	 }
	 
	 synchronized public void addResult(List<Company> companyList){
		 companySearchResult = companyList;
	 }
	 
	@Override
	public List<Company> call() {
		// TODO Auto-generated method stub
		companySearchResult = null;
		while(companySearchResult == null && !companyName.isEmpty()){
			indexSpider.runCompanySearchSpider(companyName);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (companySearchResult != null) {
				return companySearchResult;
			}
		}
		return null;
	}

}
