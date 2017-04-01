package controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Usee;
import service.UseeService;



@Controller
@RequestMapping("/test")
public class UseeController {
	
	@Autowired
	@Qualifier("useeServiceImpl")
	UseeService useeService;

	@RequestMapping(value="/{id}")
	public @ResponseBody Usee getUsee(@PathVariable Integer id){
		return useeService.get(id);
	}
	
	
}