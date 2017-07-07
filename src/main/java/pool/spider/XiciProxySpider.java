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

@Component
public class XiciProxySpider extends ProxySpider implements PageProcessor{

	@Autowired
	private ProxySpiderPipeLine proxySpiderPipeLine;
	
	private Site site = Site.me().
			setCharset("utf-8").
			setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

	private final static String ORIGIN_URL = "http://www.xicidaili.com/nn/";
	
	private String formatVerficateTime(String time){
		if (!time.equals(null)) {
			return "20" + time + ":00";
		}
		return null;
	}
	
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		List<Map<String, String>> proxyList = new ArrayList<Map<String, String>>();
		List<Selectable> nodes = page.getHtml().css("tbody>tr").nodes();
		for (int i=1; i< nodes.size(); i++) {
			Selectable selectable = nodes.get(i);
			String ip = selectable.xpath("/tr/td[2]/text()").get();
			String port = selectable.xpath("/tr/td[3]/text()").get();
			if (ip.isEmpty() || port.isEmpty()) {
				continue;
			}
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
	public void main(){
		List<String> urlList = new ArrayList<String>();
		for(int i=1; i<=5; i++){
			urlList.add(ORIGIN_URL + i);
		}
		Spider spider = addUrls(Spider.create(new XiciProxySpider()), urlList);
		spider.thread(2).addPipeline(proxySpiderPipeLine).start();
	}
	
}
