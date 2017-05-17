package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import entity.Cache;
import entity.Proxy;

@Component
public class ProxyCacheService {
	
	private static List<Cache<Proxy>> cacheList = new ArrayList<Cache<Proxy>>();

	public Cache<Proxy> get(){
		return cacheList.get(0);
	}
	
	public void addCache(Cache<Proxy> cache){
		cacheList.add(cache);
	}
	
	public int size(){
		return cacheList.size();
	}
	
	public void print(){
		for (Cache<Proxy> cache : cacheList) {
			System.out.println(cache.getValue().getIp());
		}
	}
	
	public static List<Cache<Proxy>> getCachlList() {
		return cacheList;
	}

	public static void setCachlList(List<Cache<Proxy>> cachlList) {
		ProxyCacheService.cacheList = cachlList;
	}
}
