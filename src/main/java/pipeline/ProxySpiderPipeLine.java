package pipeline;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Cache;
import entity.Proxy;
import service.ProxyCacheService;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * handle data from spider
 * 
 * @author janke
 */
@Component
public class ProxySpiderPipeLine implements Pipeline{

	@Autowired
	private ProxyCacheService proxyCacheService;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Map<String, String>> proxyList = (List<Map<String, String>>)resultItems.get("proxyList");
		Iterator<Map<String, String>> iterator = proxyList.iterator();
		while (iterator.hasNext()) {
			Map<String, String> map = (Map<String, String>) iterator.next();
			String ip = map.get("ip");
			int port = Integer.valueOf(map.get("port"));
			int verificationTime = formatVerificationTime(map.get("verificationTime"));
			double rate = formatRate(map.get("rate"));
			Proxy proxy = new Proxy(ip, port, verificationTime, rate);
			System.out.println(proxy);
			proxyCacheService.addCache(new Cache<Proxy>(proxy));
		}
	}
	
	private Double formatRate(String rate){
		return Double.valueOf(rate.replace("秒", " ").trim());
	}
	
	private Integer formatVerificationTime(String verificationTime){
		String regx = "";
		int timeMakeUp = 1;
		if (verificationTime.matches("(\\d)*分钟前")){
			regx = "分钟前";
			timeMakeUp = 60;
		}else if (verificationTime.matches("(\\d)*小时前")) {
			regx = "小时前";
			timeMakeUp = 3600;
		}else if (verificationTime.matches("(\\d)*秒前")) {
			regx = "秒前";
		}else {
			return null;
		}
		String time = verificationTime.replace(regx, " ").trim();
		int formatVerificationTime = Integer.valueOf(time) * timeMakeUp;
		return formatVerificationTime;
	}
}
