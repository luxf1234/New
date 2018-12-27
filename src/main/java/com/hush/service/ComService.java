package com.hush.service;

import java.util.List;

import com.hush.pojo.Community;

public interface ComService {

	List<Community> comlist(String comName);

	String add(String comName);

	String deleteByName(String comName);

}
