package com.hush.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hush.mapper.MemberMapper;
import com.hush.pojo.Message;
import com.hush.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public String insert(Message message,HttpSession httpSession) {

		Integer MessageId = memberMapper.findIdByIdcard(message.getIdcard());
		String dateDir="";
		Integer creattime=null;
		try {
			 dateDir= new SimpleDateFormat("yyyyMMdd").format(new Date());
			 creattime=Integer.parseInt(dateDir);
			 message.setCreattime(creattime);
			if (MessageId==null) {
				
				memberMapper.insert(message);
			}else {
				memberMapper.update(message);
			}
		} catch (Exception e) {
			return "网络异常，请稍后重试";
		}
		
				
		
		return "信息插入成功";
	}

	@Override
	public List<Message> selectByUserName(String userName) {

		List<Message> message = memberMapper.findByUserName(userName);

		if(message==null) {
			return null;
		}
		return message;
	}
	
	@Override
	public int compareIdcard(String idcard) {
		int result=0;
		result=memberMapper.compareIdcard(idcard);
		return result;
	}

}
