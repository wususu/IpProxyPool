package pool.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import pool.ProxyProductor;
import pool.ProxyTempConsumer;
import pool.spider.KuBoBoProxySpider;
import pool.spider.KuaiProxySpider;
import pool.spider.ProxySpider;
import pool.spider.Spiders;
import pool.spider.XiciProxySpider;

@Component
public class PoolManager {
	
	@Autowired
	private ProxyProductor proxyProductor;
	
	@Autowired
	private ProxyTempConsumer proxyTempConsumer;
	
	public void proxySpiderStart(){
		Thread proxySpiders = new Thread(proxyProductor);
		proxySpiders.start();
	}


	public void verificateStart(){
		Thread verificateProxy = new Thread(proxyTempConsumer);
		verificateProxy.start();
	}
	
}
