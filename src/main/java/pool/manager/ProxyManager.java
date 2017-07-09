package pool.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Proxy;
import pool.ProxyDao;
import pool.ProxyPool;
import tools.DateFormater;

@Component
public class ProxyManager {

	@Autowired
	private BaseManager<Proxy> baseProxyService;
	
	@Autowired
	private ProxyDao proxyDao;
	
	private List<Proxy> proxyQueue = ProxyPool.ProxyCacheList;
	
	/**
	 * 代理压栈
	 * @param proxy
	 * @return boolean
	 */
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
	
	/**
	 * 代理出栈
	 * @return Proxy
	 */
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
	
	synchronized public void saveToDB(){
		for (Proxy proxy : proxyQueue) {
			proxyDao.save(proxy);
		}
	}
	
	public void successUpdate(Proxy proxy){
		proxy.setLastVerificateTime(DateFormater.getTime());
		proxy.adcSuccsessTimes();
		pushToList(proxy);
	}
}
