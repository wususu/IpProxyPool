package spider.pipeline;

import dao.CompanyDao;
import entity.Company;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class AssessPipeline implements Pipeline{
	
	private String companyKey;

	private CompanyDao companyDao = new CompanyDao();

	
	public AssessPipeline(String companyKey) {
		// TODO Auto-generated constructor stub
		this.companyKey = companyKey;
	}
	
	private Double getPercentageDouble(String percentage){
		Double result;
		String bb = percentage.replace('%', ' ').replace('-', ' ').trim();
//		System.out.println(bb);
		if (bb.length() == 0) {
			result = (double) 0;
		}else {
			if (bb.matches("(\\d*)")) {
				result = 1-Double.valueOf(bb)/100;
				return result;
			}else {
				throw new TypeNotPresentException("Number Match Error!", null);
			}
		}
		return result;
	}
	
	public void process(ResultItems resultItems, Task task){
//		System.out.println(companyKey);
		String percentage = resultItems.get("percentage");
//		System.out.println("1"+percentage+"1");
		Company company = companyDao.get(companyKey);
		if (company != null && !percentage.isEmpty()) {
			Double percentageDouble = getPercentageDouble(percentage);
			company.setCompanyAssess(percentageDouble);
			companyDao.update(company);
		}
	}
}
