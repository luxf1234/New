package com.hush.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.hush.pojo.Community;
import com.hush.service.ComService;

@Controller
@RequestMapping("/comm")
public class ComController {
	
	@Autowired
	private ComService comService;

	@GetMapping("/comlist")
	@ResponseBody
	public List<Community> comlist(@RequestParam("comName") String comName) {
		
		return comService.comlist(comName);
		
	}
	@PostMapping("/add")
	@ResponseBody
	public String addByName(@RequestParam("comName") String comName) {
		if (StringUtils.isEmpty(comName)) {
			return "请输入要添加的小区";
		}
		return comService.add(comName);
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public String deleteByName(@RequestParam("comName") String comName) {
		if (StringUtils.isEmpty(comName)) {
			return "请输入要删除的小区";
		}
		return comService.deleteByName(comName);
	}
	
}
