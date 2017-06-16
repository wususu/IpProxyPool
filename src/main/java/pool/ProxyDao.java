package pool;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.BaseDaoHibernate5;
import entity.Proxy;

@Transactional
@Component
public class ProxyDao{
		
	@Autowired
	BaseDaoHibernate5<Proxy> baseDao;
	
	public Proxy get(int id){
		return baseDao.get(Proxy.class, id);
	}
	
	public Serializable save(Proxy proxy){
		return baseDao.save(proxy);
	}
	
	public void update(Proxy proxy){
		baseDao.update(proxy);
	}
	
	public void delete(Proxy proxy) {
		baseDao.delete(proxy);
	}
}
