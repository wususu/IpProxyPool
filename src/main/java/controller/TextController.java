package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pool.ProxyPool;
import pool.ProxyTempConsumer;
import pool.manager.PoolManager;
import pool.spider.KuBoBoProxySpider;
import pool.spider.KuaiProxySpider;
import pool.spider.XiciProxySpider;

@Controller
public class TextController {
	
	@Autowired
	KuaiProxySpider proxySpider;
	
	@Autowired 
	KuBoBoProxySpider kuBoBoProxySpider;
	
	@Autowired
	XiciProxySpider xiciProxySpider;
	
	@Autowired
	ProxyTempConsumer proxyTempConsumer;
	
	@Autowired
	PoolManager poolManger;

	@RequestMapping(value="/proxyspider")
	public void text(){
		proxySpider.main();
		Thread  con= new Thread(proxyTempConsumer);
		con.start();

	}
	
	@RequestMapping(value="/testxici")
	public void XiciProxySpider(){
		System.out.println("test");
		xiciProxySpider.main();
		Thread  con= new Thread(proxyTempConsumer);
		con.start();
	}
	
	@RequestMapping(value="/testkubobo")
	public void KuBoBoProxySpider(){
		kuBoBoProxySpider.main();
		Thread  con= new Thread(proxyTempConsumer);
		con.start();
	}
	
	@ResponseBody
	@RequestMapping(value="/count")
	public Object count(){
		return ProxyPool.ProxyCacheList;
		
	}
	
	@RequestMapping(value="/proxysave")
	public void save(){
		poolManger.saveToDB();
	}
}
