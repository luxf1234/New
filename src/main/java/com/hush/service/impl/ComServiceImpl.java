package com.hush.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hush.mapper.ComMapper;
import com.hush.pojo.Community;
import com.hush.service.ComService;

@Service
public class ComServiceImpl implements ComService{

	@Autowired
	private ComMapper comMapper;
	
	
	@Override
	public List<Community> comlist(String comName) {
		
		return comMapper.find(comName);
	}


	@Override
	public String add(String comName) {
		Integer num = comMapper.findByCom(comName);
		if (num!=0) {
			return "添加成功";
		}else {
		  comMapper.insert(comName);
		  return "添加成功";
		}
		
	}


	@Override
	public String deleteByName(String comName) {
		Integer num = comMapper.findByCom(comName);
		if (num==0) {
			return "删除成功";
		}else {
		  comMapper.deleteByName(comName);
		  return "删除成功";
		}
	}

}
