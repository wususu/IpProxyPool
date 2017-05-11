package spider;


import org.json.JSONArray;
import org.json.JSONObject;

import spider.pipeline.SearchPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;


public class CompanySearchSpider implements PageProcessor{
	
	private Site site = Site.me().setCharset("utf8").setRetryTimes(3).addHeader("Accept", "application/json, text/javascript, */*; q=0.01").addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
	
	private static final String originUrl = "http://www.kanzhun.com/autocomplete/searchkey.json?query=";
	
	
	@Override
	public void process(Page page){
		String html = page.getJson().toString();
		JSONObject json = new JSONObject(html);
		JSONArray company =json.getJSONArray("suggestions");
		if (company.length() > 0) {
			page.putField("companys",company);
		}
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	
	public static void run(String value){
		String url = originUrl+ value + "&type=2";
		Spider.create(new CompanySearchSpider()).addPipeline(new SearchPipeline()).addUrl(url).start();
	}
	
	public static void main() {
		run("网易");
	}

}
