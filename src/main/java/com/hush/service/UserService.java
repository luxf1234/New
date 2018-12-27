package com.hush.service;

import com.hush.pojo.User;

public interface UserService {

	User findById(Integer id);

	String insert(String userName, String password,String repasswords);

	String login(String userName, String password);

	String change(String userName,String password, String repassword);

	String getpassword(String userName);

	String delete(String userName, String password, String repassword);

}
