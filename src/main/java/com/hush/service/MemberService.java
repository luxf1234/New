package com.hush.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.hush.pojo.Message;

public interface MemberService {

	String insert(Message message,HttpSession httpSession);

	List<Message> selectByUserName(String userName);
	int compareIdcard(String idcard);

}
