package com.hush.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.hush.mapper.UserMapper;
import com.hush.pojo.User;
import com.hush.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.findById(id);
	}

	@Override
	public String insert(String userName, String password,String repassword) {
		if(password.length()<6||password.length()>8) {
		
			return "密码长度为6-8位";
		}else if (!StringUtils.equals(password, repassword)) {
			return "两次密码请输入一致";
		}else { 
			Integer num = userMapper.selectByName(userName);
			if (num==0) {
				userMapper.insert(userName, repassword);
				return "注册成功";
			}else {
				return "用户名已被占用";
			}
		}
		
	}

	@Override
	public String login(String userName, String password) {
		User user = userMapper.login(userName,password);
		if (user==null) {
			return "用户名或密码不正确";
		}else {
			
			return "登录成功";
		}
		
	}

	@Override
	public String change(String userName, String password, String repassword) {
		User user = userMapper.login(userName, password);
		if (user==null) {
			return "用户名或者密码不正确";
		}else if (repassword.length()<6||repassword.length()>8) {
			return "密码长度为6-8位";
		}else {
			userMapper.change(userName,password,repassword);
			return "密码修改成功";
		}
		
	}

	@Override
	public String getpassword(String userName) {
		return userMapper.getadmin(userName);
	}

	@Override
	public String delete(String userName, String password, String repassword) {
		User user = userMapper.login(userName, password);
		if (user==null) {
			return "用户名或者密码不正确";
		}else if (!StringUtils.equals(password, repassword)) {
			return "两次密码不一致";
		}else {
			userMapper.delete(userName,password);
			return "删除成功";
		}
	}

}
