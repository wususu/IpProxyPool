package pool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pool.manager.ProxyTempManager;
import pool.spider.ProxyVerificationSpider;

@Service
public class ProxyTempConsumer  implements Runnable{

	@Autowired
	private ProxyTempManager proxyTempManager;
	
	@Autowired
	private ProxyVerificationSpider proxyVerificationSpider;
	
	@Override
	public void run() {
		int a=100;
		while(a-->0){
			Proxy proxy = proxyTempManager.popFromTempList();
			proxyVerificationSpider.verificate(proxy);
		}
		
	}
}
