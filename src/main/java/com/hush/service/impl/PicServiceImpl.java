package com.hush.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hush.mapper.PicMapper;
import com.hush.pojo.Pic;
import com.hush.service.PicService;

@Service
public class PicServiceImpl implements PicService{

	@Autowired
	private PicMapper picMapper;
	
	@Override
	public List<Pic> findImg(String com, String floatPeople, String nah, String pah,String realtive,
			String starttime,String endtime) {
		
		return picMapper.findImg(com,floatPeople,nah,pah,realtive,starttime,endtime);
	}

	@Override
	public List<Pic> download(String com, String floatPeople, String nah,
			String pah,String realtive,String starttime,String endtime,
			HttpServletResponse httpServletResponse) {

		return picMapper.findImg(com, floatPeople, nah, pah,realtive,starttime,endtime);
	}

}
