package pool.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Proxy;
import pool.ProxyPool;

@Component
public class ProxyTempManager {

	@Autowired
	private BaseManager<Proxy> baseProxyService;
	
	private List<Proxy> proxyQueue = ProxyPool.ProxyTempCacheList;
	
	synchronized public boolean pushToTempList(Proxy proxy){
		while(sizeFromTempList() > ProxyPool.PROXY_MAX_SIZE){
			System.out.println("插入等待");
			try{
				super.wait();
			}catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		super.notifyAll();
		System.out.println("插入 :" +proxy);
		return baseProxyService.put(proxy, proxyQueue);
	}
	
	synchronized public Proxy popFromTempList(){
		while(sizeFromTempList() <= 0){
			System.err.println("取出等待");
			try{
				super.wait();
			}catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		super.notifyAll();
		Proxy proxy = baseProxyService.get(proxyQueue);
		System.out.println("取出 : "+proxy);
		if (baseProxyService.remove(proxy, proxyQueue)) {
			return proxy;
		}
		return null;
	}
	
	synchronized public int sizeFromTempList(){
		return baseProxyService.size(proxyQueue);
	}
}
