package pool.pipeline;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Proxy;
import pool.manager.ProxyTempManager;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * handle data from spider
 * 
 * @author janke
 */
@Component
public class ProxySpiderPipeLine implements Pipeline{

	@Autowired
	ProxyTempManager proxyTempManager;
	
	@Override
	public  void  process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub

		@SuppressWarnings("unchecked")
		List<Map<String, String>> proxyList = (List<Map<String, String>>)resultItems.get("proxyList");
		for (Map<String, String> map : proxyList) {
			String ip = map.get("ip");
			int port = Integer.valueOf(map.get("port"));
			

			Proxy proxy = new Proxy(ip, port);
			System.out.println(proxy);
			// 代理进入临时队列
			proxyTempManager.pushToTempList(proxy);
		}
	}
	
	
	private Double formatRate(String rate){
		String string = rate.replace("秒", " ").trim();
		if (string.equals("") && string.length() == 0) {
			return Double.valueOf(0);
		}
		return Double.valueOf(string);
	}
	
	private Integer formatVerificationTime(String verificationTime){
		String regx = "";
		int timeMakeUp = 1;
		if (verificationTime.matches("\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}")) {
			// 时间处理
			return 300;
		}
		
		if (verificationTime.matches("(\\d)*分钟前")){
			regx = "分钟前";
			timeMakeUp = 60;
		}else if (verificationTime.matches("(\\d)*小时前")) {
			regx = "小时前";
			timeMakeUp = 3600;
		}else if (verificationTime.matches("(\\d?)*秒前")) {
			regx = "秒前";
		}else {
			return null;
		}
		String time = verificationTime.replace(regx, " ").trim();
		int formatVerificationTime = Integer.valueOf(time) * timeMakeUp;
		return formatVerificationTime;
	}
}
