package spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pipeline.AssessPipeline;
import pipeline.SearchPipeline;
import us.codecraft.webmagic.Spider;

/**
 * controll all the spider
 * 
 * @author janke
 */
@Component
public class IndexSpider {

	@Autowired
	private CompanySearchSpider companySearchSpider;

	@Autowired
	private CompanyAssessSpider companyAssessSpider;
	
	@Autowired
	private SearchPipeline searchPipeline;
	
	@Autowired
	private AssessPipeline assessPipeline;
	
	private static final String companyAssessOriginUrl = "http://www.kanzhun.com/gso";
	
	private static final String companySearchOriginUrl = "http://www.kanzhun.com/autocomplete/searchkey.json?query=";
	
	private String companyAssessUrl;
	
	private String companySearchUrl;
	
	public void  setCompanySearchUrl(String companyName) {
		this.companySearchUrl = companySearchOriginUrl + companyName + "&type=2";
	}
	
	public String getCompanySearchUrl() {
		return companySearchUrl;
	}
	
	public String getCompanyAssessUrl() {
		return companyAssessUrl;
	}
	
	public void setCompanyAssessUrl(String companyKey) {
		this.companyAssessUrl = companyAssessOriginUrl + companyKey + ".html";
	}
	
	public void  runCompanySearchSpider(String companyName) {
		setCompanySearchUrl(companyName);
		Spider.create(companySearchSpider).addPipeline(searchPipeline).addUrl(getCompanySearchUrl()).start();
	}
	
	public void runCompanyAssessSpider(String companyKey){
		setCompanyAssessUrl(companyKey);
		assessPipeline.setCompanyKey(companyKey);
		Spider.create(companyAssessSpider).addPipeline(assessPipeline).addUrl(getCompanyAssessUrl()).start(); 
	}
}
