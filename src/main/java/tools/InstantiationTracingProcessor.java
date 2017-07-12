package tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import pool.manager.PoolManager;

public class InstantiationTracingProcessor implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	PoolManager poolManager;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		if(event.getApplicationContext().getParent() == null){
			poolManager.verificateStart();
			poolManager.proxySpiderStart();
		}
	}
}
