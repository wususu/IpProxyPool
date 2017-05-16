package spider;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class ProxySpider implements PageProcessor{

	private Site site = Site.me().setCharset("utf-8").addHeader("Referer","http://www.kuaidaili.com/proxylist/").setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
	
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		System.out.println(		page.getRequest());

		List<String> html = page.getHtml().css("tbody>tr").all();
		System.out.println(html);
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	
	public static void main() {
		Spider.create(new ProxySpider()).addUrl("http://www.kuaidaili.com/proxylist/1/").addUrl("http://www.kuaidaili.com/proxylist/2/").addUrl("http://www.kuaidaili.com/proxylist/3/").addUrl("http://www.kuaidaili.com/proxylist/4/").thread(4).start();
	}

}
