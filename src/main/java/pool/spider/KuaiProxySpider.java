package pool.spider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pool.pipeline.ProxySpiderPipeLine;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * get proxy from the internet
 * 
 * @author janke
 */
@Component
public class KuaiProxySpider extends ProxySpider implements Spiders{

	@Autowired
	private ProxySpiderPipeLine proxySpiderPipeLine;
	
	private Site site = Site.me().setCharset("utf-8").addHeader("Referer","http://www.kuaidaili.com/proxylist/").setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

	private static final String  ORIGIN_URL = "http://www.kuaidaili.com/free/inha/";

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		List<Map<String, String>>proxyList = null;
		if (proxyList == null) {
			proxyList = new ArrayList<Map<String, String>>();
		}
		List<Selectable> domList = page.getHtml().css("tbody>tr").nodes();
		for (Selectable dom: domList) {
			String ip = dom.xpath("/tr/td[@data-title='IP']/text()").get().trim();
			String port = dom.xpath("/tr/td[@data-title='PORT']/text()").get().trim();
			if (ip.isEmpty() || port.isEmpty())
				continue;
			proxyList.add(intoMap(ip, port));
		}
		page.putField("proxyList", proxyList);
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	@Override
	public  void start() {
		List<String> urls = new ArrayList<String>();
		for(int i=1; i<=3; i++){
			String url = ORIGIN_URL + i + "/";
			urls.add(url);
		}
		Spider spider = addUrls(Spider.create(new KuaiProxySpider()),urls);
		spider.addPipeline(proxySpiderPipeLine).thread(2).start();
	}
}
