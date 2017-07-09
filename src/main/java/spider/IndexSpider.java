package spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Proxy;
import pipeline.AssessPipeLine;
import pipeline.SearchPipeLine;
import pool.manager.ProxyManager;
import us.codecraft.webmagic.Spider;

/**
 * controll all the spider
 * 
 * @author janke
 */
@Component
public class IndexSpider {
	
	@Autowired
	private ProxyManager proxyManager;

	@Autowired
	private CompanySearchSpider companySearchSpider;

	@Autowired
	private CompanyAssessSpider companyAssessSpider;
	
	@Autowired
	private SearchPipeLine searchPipeLine;
	
	@Autowired
	private AssessPipeLine assessPipeLine;
	
	private static final String companyAssessOriginUrlHead = "http://www.kanzhun.com/gsr";
	
	private static final String companySearchOriginUrlTail = "&type=2";
	
	private static final String companySearchOriginUrlHead = "http://www.kanzhun.com/autocomplete/searchkey.json?query=";
	
	private static final String companyAssessOriginUrlTail = ".html";
	
	private String companyAssessUrl;
	
	private String companySearchUrl;
	
	public void  setCompanySearchUrl(String companyName) {
		this.companySearchUrl = companySearchOriginUrlHead + companyName + companySearchOriginUrlTail;
	}
	
	public String getCompanySearchUrl() {
		return companySearchUrl;
	}
	
	public String getCompanyAssessUrl() {
		return companyAssessUrl;
	}
	
	public void setCompanyAssessUrl(String companyKey) {
		this.companyAssessUrl = companyAssessOriginUrlHead + companyKey + companyAssessOriginUrlTail;
	}
	
	public void  runCompanySearchSpider(String companyName) {
		setCompanySearchUrl(companyName);
		if (proxyManager.sizeFromList() > 0) {
			Proxy proxy =  proxyManager.popFromList();
			if (!proxy.equals(null)) {
				companyAssessSpider.setProxy(proxy);
				System.out.println(proxy);
			}
		}
		System.out.println("spider start: " + getCompanySearchUrl() + companyName);
		Spider.create(companySearchSpider).addPipeline(searchPipeLine).addUrl(getCompanySearchUrl()).start();
	}
	
	public void runCompanyAssessSpider(String companyKey){
		setCompanyAssessUrl(companyKey);
		assessPipeLine.setCompanyKey(companyKey);
		if (proxyManager.sizeFromList() > 0) {
			Proxy proxy =  proxyManager.popFromList();
			if (!proxy.equals(null)) {
				companyAssessSpider.setProxy(proxy);
				System.out.println(proxy);
			}
		}
		System.out.println("spider start: " + getCompanyAssessUrl() + companyKey);

		Spider.create(companyAssessSpider).addPipeline(assessPipeLine).addUrl(getCompanyAssessUrl()).start(); 
	}
}
