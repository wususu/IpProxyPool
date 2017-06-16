package pool;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

/**
 * 代理缓存池
 * @author janke
 *
 */
@Component
public class ProxyPool {
	
	public  static int PROXY_MAX_SIZE = 1000;

	// temp Queue
	public static  ArrayList<Proxy> ProxyTempCacheList = new ArrayList<>();
	
	// checked Queue
	public static  ArrayList<Proxy> ProxyCacheList = new ArrayList<>();
	
	public void showProxy(){
		for (Proxy proxy : ProxyCacheList) {
			System.out.println(proxy);
		}
	}
	
}