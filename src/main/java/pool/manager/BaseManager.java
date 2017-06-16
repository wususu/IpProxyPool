package pool.manager;

import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class BaseManager <T> {
	
	public T get(List<T> list){
		return list.get(0);
	}
	
	public boolean put(T item, List<T> list){
		if (!contains(item, list)){
			return list.add(item);
		}
		return false;
	}
	
	public boolean remove(T item, List<T> list){
		if (list.contains(item)) {
			return list.remove(item);
		}
		return false;
	}
	
	public boolean contains(T item, List<T> list){
		return list.contains(item);
	}
	
	public int size(List<T> list){
		return list.size();
	}
}
