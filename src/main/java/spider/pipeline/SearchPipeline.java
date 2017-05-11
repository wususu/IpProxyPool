package spider.pipeline;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.CompanyDao;
import entity.Company;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class SearchPipeline implements Pipeline{
	
	private CompanyDao companyDao = new CompanyDao();

	@Override
	public void process(ResultItems resultItems, Task task) {
		Object companys = resultItems.get("companys");
		if (companys != null) {
			// 入库逻辑
			Iterator<Object> iterator =((JSONArray)companys).iterator();
			while (iterator.hasNext()) {
				JSONObject object = (JSONObject) iterator.next();
				Integer key = object.getInt("data");
				String name = object.getString("value");
				if (name != null && key.toString() != null ) {
//					System.out.println(name + "  " +key);
					companyDao.add(new Company(name, key.toString()));
				}
			}
			companyDao.destroy();
		}
	}
}
