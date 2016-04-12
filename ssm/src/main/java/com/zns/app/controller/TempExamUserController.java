package com.zns.app.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zns.app.bean.Order;
import com.zns.app.bean.TempExamUser;
import com.zns.app.bean.User;
import com.zns.app.service.ITempExamUserService;
import com.zns.app.util.BeanUtil;
import com.zns.app.util.JsonUtil;

@Controller
@RequestMapping("/tempExamUser")
public class TempExamUserController {

	@Resource
	private ITempExamUserService examUserService;
	
	@RequestMapping("/getExamUserList")
	public ModelAndView getExamUserList(){
		List<TempExamUser> list = examUserService.getExamUserList();
		if(list == null){
			System.out.println("null");
		}
//		
		Iterator it = list.iterator();
		while(it.hasNext()){
			TempExamUser tem = (TempExamUser) it.next();
			System.out.println(tem.getId());
			System.out.println(tem.getUserNo());
			System.out.print(tem.getExaminationid());
		}
		ModelAndView mav = new ModelAndView("testUser");
		mav.addObject("TempExamUser", list);
		return mav;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteExamUser(Integer id, HttpServletRequest request,HttpServletResponse response){
		System.out.println(id);
		boolean result = examUserService.deleteExamUserById(id);
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(result) {
			resMap.put("status", "200");
		}else {
			resMap.put("status", "300");
		}
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String updateExamUser(TempExamUser examUser){
		System.out.println(examUser.getId());
		boolean result = examUserService.updateExamUser(examUser);
//		System.out.println(result);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(result) {
			resMap.put("status", "200");
		}else {
			resMap.put("status", "300");
		}
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/selectid")
	@ResponseBody
	public String selectExamUserById(Integer id,  HttpServletRequest request,HttpServletResponse response){
		TempExamUser examUser = examUserService.selectExamUserById(id);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(examUser != null){
			resMap.put("status", "200");
			Map<String, String> temp = BeanUtil.toMap(examUser);
			System.out.println(temp.get("id"));
			System.out.println(temp.get("userNo"));
			System.out.println(temp.get("examinationid"));
			resMap.put("examUserInfo", temp);
		}else {
			resMap.put("status", "300");
		}
		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insertUser(TempExamUser examUser, HttpServletRequest request,HttpServletResponse response){
		String result = examUserService.insertExamUser(examUser);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(result.equals("success")) {
			resMap.put("status", "200");
		}else {
			resMap.put("status", "300");
			resMap.put("info", result);
		}
		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("ajaxTest")
	public String ajaxTest(){
		return "ajaxTest";
	}
}
