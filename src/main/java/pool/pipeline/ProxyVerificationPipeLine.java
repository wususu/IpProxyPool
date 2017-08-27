package pool.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pool.entity.Proxy;
import pool.manager.ProxyManager;
import tools.DateFormater;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class ProxyVerificationPipeLine implements Pipeline{

	@Autowired
	ProxyManager proxyManager;
	
	private void setTime(Proxy proxy, String date){
		proxy.setLastVerificateTime(date);
	}
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub
		Proxy proxy = resultItems.get("proxy");
		proxy.setChecked(true);
		setTime(proxy, DateFormater.getTime());
		proxyManager.pushToList(proxy);
	}
}
