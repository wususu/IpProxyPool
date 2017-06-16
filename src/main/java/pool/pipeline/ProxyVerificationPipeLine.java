package pool.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Proxy;
import pool.manager.PoolManager;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class ProxyVerificationPipeLine implements Pipeline{

	@Autowired
	PoolManager proxyManager;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub
		Proxy proxy = resultItems.get("proxy");
		proxyManager.pushToList(proxy);
	}
}
