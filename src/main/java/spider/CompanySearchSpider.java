package spider;

import org.apache.http.HttpHost;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * the spider to get the search result of companys data from the internate by companyName
 * 
 *@author janke
 */
@Component
public class CompanySearchSpider implements PageProcessor{
	
	private Site site = Site.me().setCharset("utf8").setRetryTimes(3).setTimeOut(3000).addHeader("Accept", "application/json, text/javascript, */*; q=0.01").addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
	
	@Override
	public void process(Page page){
		System.out.println("IN");
		System.out.println(page);
		System.out.println(page.getRequest() +" " +page.getStatusCode());
		String html = page.getJson().toString();
		JSONObject json = new JSONObject(html);
		JSONArray company =json.getJSONArray("suggestions");
		if (company.length() > 0) {
			page.putField("companys",company);
		}
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
}
