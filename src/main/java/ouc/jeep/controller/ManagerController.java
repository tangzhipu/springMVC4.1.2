package ouc.jeep.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ouc.jeep.model.Manager;
import ouc.jeep.service.ManagerService;

@Controller
@RequestMapping("manager")
public class ManagerController {

	    @Resource(name="managerService")
	    private ManagerService managerService;
	    
	    @RequestMapping(value="/manager",method=RequestMethod.GET)
	    public ModelAndView hello2(){
	        ModelAndView mv = new ModelAndView();
	        mv.addObject("message", "HelloMVC");
	        mv.setViewName("manager/managers");
	        return mv;
	    }
	    
	    @RequestMapping(value="/count",method=RequestMethod.GET)
	    public ModelAndView count(){
	    	System.err.println("xxx"); 
	        int c = managerService.managerCount();
	        ModelAndView mv = new ModelAndView();
	        mv.addObject("message", c);
 	        mv.setViewName("manager/managers");
	        return mv;
	    }
	
	    @RequestMapping(value="/add",method=RequestMethod.GET)
		public String add() {
			Manager manager = new Manager();
			manager.setAge(14);
			manager.setNiceName("jeep");
			manager.setUserName("jeep"); 
			managerService.add(manager);
			return "manager/managers";
		}
	    
		@RequestMapping(value="/{id}",method=RequestMethod.GET)
		@ResponseBody
		public Manager show(@PathVariable int id) {
			System.err.println("xxx "); 
			return managerService.load(id);
		}
	    
	    
	    
}


 