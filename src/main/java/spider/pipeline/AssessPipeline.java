package spider.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class AssessPipeline implements Pipeline{
	
	private String companyKey;
	
	public AssessPipeline(String companyKey) {
		// TODO Auto-generated constructor stub
		this.companyKey = companyKey;
	}
	
	public void process(ResultItems resultItems, Task task){
		Object percentage = resultItems.get("percentage");
		System.out.println(companyKey + "  " + percentage);
		// // 入库逻辑
	}
}
