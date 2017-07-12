package pipeline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import entity.Company;
import service.CompanySearchResultService;
import service.CompanyService;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;


/**
 * handle data from the CompanySearchSpider
 * 
 * @author janke
 */
@Component
public class SearchPipeLine implements Pipeline{
	
	@Autowired
	@Qualifier("companyServiceImpl")
	private CompanyService companyService;
	
	@Autowired
	private CompanySearchResultService companySearchResultService;

	@Override
	public void process(ResultItems resultItems, Task task) {
		Object companys = resultItems.get("companys");
		if (companys != null) {
			// 入库逻辑
			List<Company> companyList = new ArrayList<>();
			Iterator<Object> iterator =((JSONArray)companys).iterator();
			while (iterator.hasNext()) {
				JSONObject object = (JSONObject) iterator.next();
				Integer key = object.getInt("data");
				String name = object.getString("value");
				if (name != null && key.toString() != null ) {
					Company company = new Company(name, key.toString());
					companyList.add(company);
					try{
					companyService.add(company);
					}catch (Exception e) {
						// TODO: handle exception
						continue;
					}
				}
			}
			try{
				System.out.println("搜索结果: " + companyList);
				companySearchResultService.addResult(companyList);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	}
}
