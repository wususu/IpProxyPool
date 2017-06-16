package pool.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pool.Proxy;
import pool.ProxyPool;

@Component
public class PoolManager {

	@Autowired
	private BaseManager<Proxy> baseProxyService;
	
	private List<Proxy> proxyQueue = ProxyPool.ProxyCacheList;
	
	synchronized public boolean pushToList(Proxy proxy){
		while(sizeFromList() > ProxyPool.PROXY_MAX_SIZE){
			System.out.println("插入最终队列等待");
			try{
				super.wait();
			}catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		super.notifyAll();
		System.out.println("插入最终队列: " + proxy);
		return baseProxyService.put(proxy, proxyQueue);
	}
	
	synchronized public Proxy popFromList(){
		while(sizeFromList() <= 0){
			System.out.println("取出等待");
			try{
				super.wait();
			}catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		super.notify();
		Proxy proxy = baseProxyService.get(proxyQueue);
		System.out.println("取出: " + proxy);
		if (baseProxyService.remove(proxy, proxyQueue)) {
			return proxy;
		}
		return null;
	}
	
	synchronized public boolean delete(Proxy proxy){
		return baseProxyService.remove(proxy, proxyQueue);
	}
	
	synchronized public int sizeFromList(){
		return baseProxyService.size(proxyQueue);
	}
}
