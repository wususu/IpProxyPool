package spider.pipeline;


import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class SearchPipeline implements Pipeline{
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		Object companys = resultItems.get("companys");
		if (companys != null) {
			System.out.println("Pipeline:" + companys);
			// 入库逻辑
		}
	}
}
