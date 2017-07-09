package spider;

import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Proxy;
import pool.manager.ProxyManager;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * the spider to get company assess result from the internate by companyKey
 * 
 * @author janke
 */
@Component
public class CompanyAssessSpider implements PageProcessor{
	
	private Site site = Site.me().setCharset("utf8").setTimeOut(7000).setRetryTimes(3).addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8").addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
	
	@Autowired 
	private ProxyManager proxyManager;
	
	private Proxy proxy;
	
	@Override
	public void process(Page page) {
		if (!proxy.equals(null)) {
			proxyManager.successUpdate(proxy);
		}
		// TODO Auto-generated method stub
		String html = page.getHtml().css("div  >  span.cil_perc", "text").get();
		System.out.println(html);
		if (html != null) {
			page.putField("percentage", html);
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
