package spider;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.http.HttpHost;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Proxy;
import pool.manager.ProxyManager;
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
	
	private Site site = Site.me().setCharset("utf8").setRetryTimes(3).setTimeOut(4000).addHeader("Accept", "application/json, text/javascript, */*; q=0.01").addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
	
	@Autowired 
	private ProxyManager proxyManager;
	
	private Proxy proxy; 
	
	@Override
	public void process(Page page){
		if (!proxy.equals(null)) {
			proxyManager.successUpdate(proxy);
		}
		String html = page.getJson().toString();
		JSONObject json = new JSONObject(html);
		JSONArray company =json.getJSONArray("suggestions");
		if (company.length() > 0) {
			page.putField("companys",company);
		}
	}

	public void setProxy(Proxy proxy){
		if (!proxy.equals(null)) {
			this.proxy = proxy;
			this.site = this.site.setHttpProxy(new HttpHost(proxy.getIp(), proxy.getPort()));
		}
	}
	
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
}
