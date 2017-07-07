package pool.spider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.Spider;

public abstract class ProxySpider {
	
	abstract void main();
	
	protected Map<String, String> intoMap(String ip, String port){
		Map<String, String> proxyMap = new HashMap<String, String>();
		proxyMap.put("ip", ip);
		proxyMap.put("port", port);
		return proxyMap;
	}
	
	protected Spider addUrls(Spider spider, List<String> urls){
		Spider temp = spider;
		for (String url : urls) {
			spider = temp.addUrl(url);
			temp = spider;
		}
		return temp;
	}
}
