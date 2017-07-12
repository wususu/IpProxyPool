package pool.manager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pool.ProxyProductor;
import pool.ProxyTempConsumer;

@Component
public class PoolManager {
	
	@Autowired
	private ProxyProductor proxyProductor;
	
	@Autowired
	private ProxyTempConsumer proxyTempConsumer;
	
	@Autowired
	private ProxyManager proxyManager;
	
	public void proxySpiderStart(){
		 ExecutorService executorService = Executors.newCachedThreadPool();  
		 try {
			 executorService.execute(proxyProductor);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			executorService.shutdown();
		}
	}


	public void verificateStart(){
		 ExecutorService executorService = Executors.newCachedThreadPool();  
		 try {
			 executorService.execute(proxyTempConsumer);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void saveToDataBase(){
		proxyManager.saveToDB();
	}
	
}
