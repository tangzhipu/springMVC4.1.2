package ouc.jeep.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ouc.jeep.model.User;
import ouc.jeep.model.UserException;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private Map<String, User> users = new HashMap<String,User>();
	
	public UserController(){
		users.put("jeep", new User("jeep", "123", "jeep", "jeep@qq.com"));
		users.put("tzp", new User("tzp", "123", "tzp", "jeep@qq.com"));
		users.put("zdy", new User("zdy", "123", "zdy", "jeep@qq.com"));
		users.put("xd", new User("xd", "123", "xida", "jeep@qq.com"));
	}
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("users", users);
		
		return "user/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute(new User()); //start ModelDriven
		return "user/add";
	}
	
	//带文件的添加
	@RequestMapping(value="/add",method=RequestMethod.POST) 
	public String add(@Validated User user,BindingResult br,
			@RequestParam("attachs") MultipartFile[] attachs,HttpServletRequest req){ //一定要紧跟,数组不能够对应上
		if(br.hasErrors()){  //如果有错误
			return "user/add";
		}
		
		// file upload
		String realpath = req.getSession().getServletContext().getRealPath("/resources/upload");
		System.err.println(realpath); 
		
		for(MultipartFile attach:attachs){
			if(attach.isEmpty()) continue;
			File f = new File(realpath+"/"+attach.getOriginalFilename());
			try {
				FileUtils.copyInputStreamToFile(attach.getInputStream(), f);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.err.println(attach.getName()+", "+attach.getOriginalFilename()+", "+attach.getContentType());
		}
		
		users.put(user.getUsername(), user);
		return "redirect:/user/users";
	}
	
	@RequestMapping(value="/{username}",method=RequestMethod.GET)
	public String show(@PathVariable String username,Model model){
		model.addAttribute(users.get(username));
		return "user/show";
	}
	
	// return date json
	@RequestMapping(value="/{username}",method=RequestMethod.GET,params="json")
	@ResponseBody
	public User show(@PathVariable String username){
		System.err.println("json");
		return users.get(username); 
	}
	
	
	@RequestMapping(value="/{username}/update",method=RequestMethod.GET)
	public String update(@PathVariable String username,Model model){
		model.addAttribute(users.get(username));
		return "user/update";
	}
	
	@RequestMapping(value="/{username}/update",method=RequestMethod.POST)
	public String update(@Validated User user,BindingResult br,@PathVariable String username){ //一定要紧跟
		if(br.hasErrors()){  //如果有错误
			return "user/update";
		}
		users.put(username, user);
		return "redirect:/user/users";
	}
	
	@RequestMapping(value="/{username}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable String username){
		users.remove(username);
		return "redirect:/user/users";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password,HttpSession session){
		if(!users.containsKey(username)){
			throw new UserException("用户名不存在");
		}
		User user = users.get(username);
		if(!user.getPassword().equals(password)){
			throw new UserException("密码不正确");
		}
		session.setAttribute("loginUser", user);
		
		return "redirect:/user/users";
	}
	
	/**
	 * 局部异常处理,仅仅处理这个控制器的异常
	 */
/*	@ExceptionHandler(value={UserException.class})
	public String handleException(UserException e,HttpServletRequest req){
		req.setAttribute("e", e);
		return "error";
	}*/
	
	
}
