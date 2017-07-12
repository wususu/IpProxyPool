package service;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Company;
import spider.IndexSpider;

/**
 * 封装评价查询功能
 * @author janke
 *
 */
@Component
public class CompanyAssessResultService implements Callable<Company>{

	@Autowired
	private IndexSpider indexSpider;
		
	private Company companyAssessResult;
	
	private String companyKey;
	
	public void setCompanyKey(String companyKey) {
		this.companyKey = companyKey;
	}
	
	private Boolean flag = false;
	
	synchronized public void addResult(Company company){
		if (!company.getCompanyAssess().equals(null)) {
			flag = true;
			this.companyAssessResult = company;
		}
	}
	
	@Override
	public Company call(){
		// TODO Auto-generated method stub
		companyAssessResult = null;
		while(companyAssessResult == null && companyKey != null){
			synchronized (flag) {
				if (flag == false) {
					indexSpider.runCompanyAssessSpider(companyKey);
				}
			}
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (flag) {
				if (flag== true) {
					flag = false;
					return companyAssessResult;
				}
			}
		}
		return null;
	}
}
