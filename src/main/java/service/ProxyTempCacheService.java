package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import entity.Cache;
import entity.Proxy;

@Component
public class  ProxyTempCacheService {
	
	private static List<Cache<Proxy>> cacheList = new ArrayList<Cache<Proxy>>();

	public Cache<Proxy> get(){
		return cacheList.get(0);
	}
	
	public Cache<Proxy> get(int index){
		return cacheList.get(index);
	}
	
	public synchronized boolean addCache(Cache<Proxy> cache){
		if (cacheList.contains(cache)) {
			System.out.println("contains");
			return false;
		}
		cacheList.add(cache);
		return true;
	}
	
	public int size(){
		return cacheList.size();
	}
	
	public void print(){
		for (Cache<Proxy> cache : cacheList) {
			System.out.println(cache.getValue().getIp());
		}
	}
	
	public boolean contains(Cache<Proxy> bb){
		return cacheList.contains(bb) ? true : false;
	}
	
	public static List<Cache<Proxy>> getCachlList() {
		return cacheList;
	}

	public static void setCachlList(List<Cache<Proxy>> cachlList) {
		ProxyTempCacheService.cacheList = cachlList;
	}
}
