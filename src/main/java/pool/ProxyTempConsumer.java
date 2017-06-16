package pool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Proxy;
import pool.manager.ProxyTempManager;
import pool.spider.ProxyVerificationSpider;

@Component
public class ProxyTempConsumer  implements Runnable{

	@Autowired
	private ProxyTempManager proxyTempManager;
	
	@Autowired
	private ProxyVerificationSpider proxyVerificationSpider;
	
	@Override
	public void run() {
		while(true){
			Proxy proxy = proxyTempManager.popFromTempList();
			proxyVerificationSpider.verificate(proxy);
		}
		
	}
}
