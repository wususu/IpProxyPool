package pool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import pool.spider.Spiders;

@Component
public class ProxyProductor implements Runnable{

	public static Boolean runSignal = true;
	
	@Autowired
	@Qualifier("xiciProxySpider")
	private Spiders xiciProxySpider;
	
	@Autowired
	@Qualifier("kuBoBoProxySpider")
	private Spiders kuBoBoProxySpider;
	
	@Autowired
	@Qualifier("kuaiProxySpider")
	private Spiders kuaiProxySpider;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(runSignal){
			runSignal = false;
			xiciProxySpider.start();
			kuBoBoProxySpider.start();
			kuaiProxySpider.start();
		}
		runSignal = true;
	}

}
