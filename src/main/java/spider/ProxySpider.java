package spider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pipeline.ProxySpiderPipeLine;
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
public class ProxySpider implements PageProcessor{

	@Autowired
	private ProxySpiderPipeLine proxySpiderPipeLine;
	
	private Site site = Site.me().setCharset("utf-8").addHeader("Referer","http://www.kuaidaili.com/proxylist/").setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

	private static String  originUrl = "http://www.kuaidaili.com/proxylist/";

	private Map<String, String> intoMap(String ip, String port, String verificationTime, String rate){
		Map<String, String> proxyMap = new HashMap<String, String>();
		proxyMap.put("ip", ip);
		proxyMap.put("port", port);
		proxyMap.put("verificationTime", verificationTime);
		proxyMap.put("rate", rate);
		return proxyMap;
	}

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
			String rate = dom.xpath("/tr/td[@data-title='响应速度']/text()").get().trim();
			String verificationTime = dom.xpath("/tr/td[@data-title='最后验证时间']/text()").get().trim();
			if (ip.isEmpty() || port.isEmpty() || rate.isEmpty() || verificationTime.isEmpty())
				continue;
			proxyList.add(intoMap(ip, port, verificationTime, rate));
		}
		page.putField("proxyList", proxyList);
	}

	public Spider addUrls(Spider spider, List<String> urls){
		Spider temp = spider;
		for (String url : urls) {
			System.out.println(url);
			spider = temp.addUrl(url);
			temp = spider;
		}
		return temp;
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	public  void main() {
		List<String> urls = new ArrayList<String>();
		for(int i=1; i<=10; i++){
			String url = originUrl + i + "/";
			urls.add(url);
		}
		Spider spider = addUrls(Spider.create(new ProxySpider()),urls);
		spider.addPipeline(proxySpiderPipeLine).thread(4).start();
	}

}
