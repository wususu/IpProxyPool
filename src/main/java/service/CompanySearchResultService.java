package service;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Company;
import spider.IndexSpider;

/**
 * 封装公司搜索功能
 * @author janke
 *
 */
@Component
public class CompanySearchResultService implements Callable<List<Company>>{

	 private  List<Company> companySearchResult;
	
	 private String companyName;
	 
	 private Boolean flag = false;
	 
	 @Autowired
	 private IndexSpider indexSpider;
	 
	 public void setCompanyName(String companyName){
		 this.companyName = companyName;
	 }
	 
	 synchronized public void addResult(List<Company> companyList){
		 flag = true;
		 companySearchResult = companyList;
	 }
	 
	@Override
	public List<Company> call() {
		// TODO Auto-generated method stub
		companySearchResult = null;
		while(companySearchResult == null && !companyName.isEmpty()){
			synchronized (flag) {
				if (flag == false) {
					indexSpider.runCompanySearchSpider(companyName);
				}
			}
			try {
				Thread.sleep(2200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (flag) {
				if (flag == true) {
					flag = false;
					return companySearchResult;
				}
			}
		}
		return null;
	}
}
