package com.hush.controller;

import java.net.HttpCookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.hush.pojo.User;
import com.hush.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	
	@RequestMapping(value="/find",method=RequestMethod.GET)
	@ResponseBody
	public User findById(@RequestParam(value="id")Integer id) {
		return userService.findById(id);
	}
	
	@GetMapping("/register")
	@ResponseBody
	public String register(@RequestParam(value="userName")String userName,
			@RequestParam(value="password")String password,
			@RequestParam(value="repassword")String repassword,Model model) {
		
		return userService.insert(userName,password,repassword);
	}
	
	@GetMapping("/delete")
	@ResponseBody
	public String delete(@RequestParam(value="userName")String userName,
			@RequestParam(value="password")String password,
			@RequestParam(value="repassword")String repassword,Model model) {
		
		return userService.delete(userName,password,repassword);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam(value="userName")String userName,
			@RequestParam(value="password") String password,HttpSession httpSession,Model model) {
		String result = userService.login(userName,password);
		if (result.equals("登录成功")) {
			httpSession.setAttribute("userName", userName);
		}
		return result;	
	}
	
	@GetMapping("/update")
	@ResponseBody
	public String change(@RequestParam(value="userName")String userName,
			@RequestParam(value="password")String password,
  			@RequestParam(value="repassword")String repassword
  			) {
		return userService.change(userName,password,repassword);
	}

}
