package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pool.ProxyPool;
import pool.ProxyTempConsumer;
import pool.manager.PoolManager;
import pool.spider.ProxySpider;

@Controller
public class TextController {
	
	@Autowired
	ProxySpider proxySpider;
	
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
