package com.hush.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.hush.panduan.panduan;
import com.hush.pojo.Message;
import com.hush.service.MemberService;

import java.util.HashMap;
import java.util.Map;
 
import org.json.JSONObject;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/insert")
	@ResponseBody
	public String insert(Message message,HttpSession httpSession) {
		if (message.getCom().length()>50||StringUtils.isEmpty(message.getCom())) {
			return "请正确输入小区";
		}else if (message.getUserName().length()>20||StringUtils.isEmpty(message.getUserName())) {
			return "请输入姓名";
		}else if(!panduan.isMobileNum(message.getTel())) {
			return "联系方式错误";
		}else if (!panduan.validate(message.getIdcard())) {
			return "请输入正确的身份证号";
		}else if (!(panduan.isCarNo(message.getCar())||StringUtils.isEmpty(message.getCar()))) {
			return "请输入正确的车牌号";
		}else if (message.getAddress().length()>50||StringUtils.isEmpty(message.getAddress())) {
			return "请输入正确的地址";
		}else if(!(StringUtils.equals("1", message.getFloatPeople())||
				StringUtils.equals("2", message.getFloatPeople())||
				StringUtils.equals("3", message.getFloatPeople())||
				StringUtils.equals("0", message.getFloatPeople()))){
			System.out.println(message.getFloatPeople());
			return "请选择人员居住类型";
		}else if(!(StringUtils.equals("1", message.getNah())||
				StringUtils.equals("0", message.getNah()))){
			return "请选择是否居住在本小区";
		}else if(!(StringUtils.equals("1", message.getPah())||
				StringUtils.equals("2", message.getPah())||
				StringUtils.equals("0", message.getPah()))){
			return "请选择户籍所在地";
		}else if(message.getRealtive().length()>20||
				StringUtils.isEmpty(message.getRealtive())){
			return "请输入合理的关系";
		}

		return memberService.insert(message,httpSession);
	}
	
	@GetMapping("/compare")
	@ResponseBody
	public Map<String,Object> compareIdcard(@RequestParam(value="idcard") String idcard) {
		Map<String,Object> result_map = new HashMap<String, Object>();
		int result=0;
		result=memberService.compareIdcard(idcard);
		result_map.put("code", "200");
		result_map.put("Msg", "查询成功");
		result_map.put("Status", result);
		return result_map;
	}
	
	@GetMapping("/memberlist")
	@ResponseBody
	public List<Message> memberlist(@RequestParam(value="userName")String userName) {
		return memberService.selectByUserName(userName);
	}
}
