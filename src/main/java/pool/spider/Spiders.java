package pool.spider;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public interface Spiders extends PageProcessor{
	
	void start();
	
	Map<String, String> intoMap(String ip, String port);
	
	Spider addUrls(Spider spider, List<String> urls);
}
