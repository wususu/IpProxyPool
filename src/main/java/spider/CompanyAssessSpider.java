package spider;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;


public class CompanyAssessSpider implements PageProcessor{
	
	private static String originUrl = "http://www.kanzhun.com/gso";
	
	private Site site = Site.me().setCharset("utf8").setRetryTimes(3).addHeader("Accept", "application/json, text/javascript, */*; q=0.01").addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
	
	
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		String html = page.getHtml().css("div  >  span.cil_perc").get();
		System.out.println(html);
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	
	public static void run(String companyCode){
		String url = originUrl + companyCode + ".html";
		Spider.create(new CompanyAssessSpider()).addUrl(url).run(); 
	}

}
