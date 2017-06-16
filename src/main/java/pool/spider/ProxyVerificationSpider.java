package pool.spider;

import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Proxy;
import pool.pipeline.ProxyVerificationPipeLine;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class ProxyVerificationSpider implements PageProcessor{

	private Site site = Site.me().setCharset("utf-8").setTimeOut(3000).addHeader("Referer","http://www.kuaidaili.com/proxylist/").setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
	
	private final static String URL = "http://www.kanzhun.com/";
	
	private Proxy proxy;
	
	@Autowired
	private ProxyVerificationPipeLine proxyVerificationPipeLine;
	
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		int statusCode = page.getStatusCode();
		if (statusCode == 200) {
			proxy.setChecked(true);
			page.putField("proxy", proxy);
		}else {
			proxy.setChecked(false);
		}
	}

	public void setProxy(Proxy proxy){
		this.proxy = proxy;
	}
	
	public Proxy getProxy(){
		return proxy;
	}
	
	private Site addProxy(Site site){
		return proxy == null ? site : site.setHttpProxy(new HttpHost(proxy.getIp(), proxy.getPort()));
	}
	
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		Site site = addProxy(this.site);
		return site;
	}
	
	public void verificate(Proxy proxy){
		ProxyVerificationSpider spider = new ProxyVerificationSpider();
		spider.setProxy(proxy);
		Spider.create(spider).addUrl(URL).addPipeline(proxyVerificationPipeLine).start();
	}
}