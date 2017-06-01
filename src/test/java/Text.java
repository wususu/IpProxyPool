import org.springframework.beans.factory.annotation.Autowired;

import entity.Cache;
import entity.Proxy;
import service.ProxyCacheService;
import service.ProxyTempCacheService;
import spider.ProxySpider;


public class Text {

	public static ProxyTempCacheService proxyTempCacheService = new ProxyTempCacheService();
	
public static void main(String[] args) {
	for ( int i=0; i < 10;i++) {
		Proxy proxy1 = new Proxy("1234", 34, 123, 1.1);
		Proxy proxy2= new Proxy("1234", 34, 123, 1.1);
		proxyTempCacheService.print();
		System.out.println(proxyTempCacheService.size());
		System.out.println("start");

		proxyTempCacheService.addCache(new Cache<Proxy>(proxy1));
		proxyTempCacheService.addCache(new Cache<Proxy>(proxy2));
//		proxyTempCacheService.print();
		if (new Cache<Proxy>(proxy1).equals(new Cache<Proxy>(proxy2))) {
			System.out.println(111);
		}
		System.out.println("END");
	}
	
}
}
