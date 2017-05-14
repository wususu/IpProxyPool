package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spider.IndexSpider;

/**
 * the Controller for handling the request about mod five  (company assess)
 * 
 * @author janke
 */
@Controller
@RequestMapping(value="/company")
public class CreditRickController {

	@Autowired
	private IndexSpider indexSpider;
	
	@RequestMapping(value="/search/{name}")
	@ResponseBody
	public Object testCompanySearchSprider(@PathVariable String name){
		if (!name.isEmpty()) {
			indexSpider.runCompanySearchSpider(name);
		}
		return "complete";
	}
	
	@RequestMapping(value="/assess/{key}")
	@ResponseBody
	public Object testCompanyAssessSpider(@PathVariable Integer key){
		if (key != null) {
			indexSpider.runCompanyAssessSpider(key.toString());
		}
		return "complete";
	}
}
