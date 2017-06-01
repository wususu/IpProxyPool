package pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import entity.Cache;
import entity.Proxy;
import service.ProxyCacheService;
import service.ProxyTempCacheService;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class ProxyVerificationPipeLine implements Pipeline{

	@Autowired
	ProxyCacheService proxyCacheService;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub
		Proxy proxy = resultItems.get("proxy");
		proxy.setChecked(true);
		proxyCacheService.add(new Cache<Proxy>(proxy));
	}

}
