package pool.pipeline;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pool.entity.Proxy;
import pool.manager.ProxyTempManager;
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
	ProxyTempManager proxyTempManager;
	
	@Override
	public  void  process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub

		@SuppressWarnings("unchecked")
		List<Map<String, String>> proxyList = (List<Map<String, String>>)resultItems.get("proxyList");
		for (Map<String, String> map : proxyList) {
			String ip = map.get("ip");
			int port = Integer.valueOf(map.get("port"));
			Proxy proxy = new Proxy(ip, port);
			// 代理进入临时队列
			proxyTempManager.pushToTempList(proxy);
		}
	}
}
