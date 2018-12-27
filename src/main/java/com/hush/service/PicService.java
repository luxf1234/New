package com.hush.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hush.pojo.Pic;

public interface PicService {

	List<Pic> findImg(String com, String floatPeople, String nah, String pah,String realtive,
			String starttime,String endtime);

	List<Pic> download(String com, String floatPeople, String nah,
			String pah,String realtive,
			String starttime,String endtime,HttpServletResponse response);

}
