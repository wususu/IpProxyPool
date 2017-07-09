package pool.spider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pool.pipeline.ProxySpiderPipeLine;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

@Component
public class KuBoBoProxySpider extends ProxySpider implements Spiders{

	@Autowired
	private ProxySpiderPipeLine proxySpiderPipeLine;

	private static final String ORIGIN_URL = "http://www.coobobo.com/free-http-proxy/";
	
	private Site site = Site.me().
			setCharset("utf-8").
			setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
	
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		List<Map<String, String>> proxyList = new ArrayList<>();
		Html html = page.getHtml();
		List<Selectable> nodes = html.css("table>tbody>tr").nodes();
		for (Selectable selectable : nodes) {
			String port = selectable.xpath("/tr/td[2]/text()").get().trim();
			String ip = getMatchIp(selectable.xpath("/tr/td[1]/script").get().trim());
			if (!port.isEmpty() && !ip.isEmpty()) {
				proxyList.add(intoMap(ip, port));
			}
		}
		page.putField("proxyList", proxyList);
	}

	private String getMatchIp(String string){
		Pattern pattern = Pattern.compile("\"(\\d{1,3})\".+\"(\\d{1,3})\".+\"(\\.\\d{1,3}\\.\\d{1,3})\"");
		Matcher matcher =  pattern.matcher(string);
		if (matcher.find()) {
			String a = matcher.group(1);
			String b = matcher.group(2);
			String c = matcher.group(3);
			if (!a.isEmpty() && !b.isEmpty() &&!c.isEmpty()) {
				return a + "." + b + c;
			}
		}
		return null;
	}
	
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		List<String> urlList = new ArrayList<>();
		for(int i=1; i<=10; i++){
			urlList.add(ORIGIN_URL + i);
		}
		Spider spider = addUrls(Spider.create(new KuBoBoProxySpider()), urlList);
		spider.addPipeline(proxySpiderPipeLine).thread(2).start();
	}

}
