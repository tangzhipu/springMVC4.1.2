package ouc.jeep.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ouc.jeep.model.User;

@Controller
public class TestController {

	@RequestMapping({"/test","/"})
	public String test(@RequestParam("username") String username,Map<String, Object> context){
		System.err.println("username is :" +username); 
		System.err.println("test"); 
		context.put("username", username);
		return "test";
	}
	
	@RequestMapping({"/welcome","/"})
	public String welcome(String username,Model model){
		System.err.println("username is :" +username); 
		model.addAttribute("username", username);
		return "hello";
	}
	
	@RequestMapping(value="/js")
    @ResponseBody
    public Map<String, Object> js() {
		System.err.println("xxxx"); 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status","ok");
        map.put("reason", "123");

        return map;
    }

	@RequestMapping(value="/jsonfeed")  
	public @ResponseBody Object getJSON() {  
		Map<String,Object> map = new HashMap<String, Object>();
	    List<User> tournamentList = new ArrayList<User>();  
	    tournamentList.add(new User("xiao1", "1234x", "xiaoyi", "xiaoyi@qq.com"));  
	    tournamentList.add(new User("xiao2", "1234x", "xiaoyi", "xiaoyi@qq.com"));  
	    map.put("items", tournamentList);  
	    map.put("status", 0);  
	    System.err.println("tournamentList");   
	    return map;  
	}  
	
	
	
}
