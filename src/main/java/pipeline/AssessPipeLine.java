package pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Company;
import service.CompanyService;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * handle data from the CompanyAssessSpider 
 * 
 * @author janke
 */
@Component
public class AssessPipeLine implements Pipeline{
	
	@Autowired
	private CompanyService companyService;
	
	private String companyKey;

	public void setCompanyKey(String companyKey){
		this.companyKey = companyKey;
	}
	
	private Double getPercentageDouble(String percentage){
		Double result = null;
		String number = percentage.replace('%', ' ').replace('-', ' ').trim();
		if (number.length() == 0) {
			result = (double) 0;
		}else {
			if (number.matches("(\\d*)")) {
				result = 1-Double.valueOf(number)/100;
				return result;
			}
		}
		return result;
	}
	
	public void process(ResultItems resultItems, Task task){
		String percentage = resultItems.get("percentage");
		Company company = companyService.get(companyKey);
		if (company != null && !percentage.isEmpty()) {
			Double percentageDouble = getPercentageDouble(percentage);
			company.setCompanyAssess(percentageDouble);
			companyService.update(company);
		}
	}
}
