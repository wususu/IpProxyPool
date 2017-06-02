package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import entity.Cache;
import entity.Proxy;


@Component
public class ProxyCacheService {
	
	private static List<Cache<Proxy>> cacheList = new ArrayList<Cache<Proxy>>();

	public static List<Cache<Proxy>> getCache() {
		return cacheList;
	}
	
	public boolean add(Cache<Proxy> cache){
		if (cacheList.contains(cache)) {
			System.out.println("contains");
			return false;
		}
		cacheList.add(cache);
		return true;
	}
	
	public boolean push(Cache< Proxy> cache) {
		if (cacheList.contains(cache)) {
			return false;
		}
		cacheList.add(cacheList.size(), cache);
		return true;
	}
	
	public Cache<Proxy> get(int i){
		if (i >= 0 && i < cacheList.size()) {
			return cacheList.get(i);
		}
		return null;
	}
	
	public Cache<Proxy> pop(){
		Cache< Proxy> cache = cacheList.get(0);
		cacheList.remove(0);
		return cache;
	}
	
	public boolean remove(int i){
		if (i < 0 || i>=cacheList.size()) {
			return false;
		}
		cacheList.remove(i);
		return true;
	}
	
	public boolean contains(Cache<Proxy> bb){
		return cacheList.contains(bb) ? true : false;
	}
	
	public Integer size(){
		return cacheList.size();
	}
	
	public void print(){
		for (Cache<Proxy> cache : cacheList) {
			Proxy proxy = cache.getValue();
					System.out.println(proxy.getIp() + " : " + proxy.getPort() + " " + proxy.getRate() + " " + proxy.getLastVerification());
		}
	}
}
