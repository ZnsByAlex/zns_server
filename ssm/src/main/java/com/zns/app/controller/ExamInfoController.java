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

import com.zns.app.bean.ExamInfo;
import com.zns.app.bean.TempExamUser;
import com.zns.app.service.IExamInfoService;
import com.zns.app.util.BeanUtil;
import com.zns.app.util.JsonUtil;

@Controller
@RequestMapping("/examInfo")
public class ExamInfoController {

	@Resource
	private IExamInfoService examInfoService;
	
	@RequestMapping("/getExamInfoList")
	public ModelAndView getExamInfoList(){
		List<ExamInfo> list = examInfoService.getExamInfoList();
		if(list == null){
			System.out.println("null");
		}
//		
		Iterator it = list.iterator();
		while(it.hasNext()){
			ExamInfo tem = (ExamInfo) it.next();
			System.out.println(tem.getRecordid());
			System.out.println(tem.getExaminationno());
			System.out.print(tem.getExaminationtitle());
		}
		ModelAndView mav = new ModelAndView("testUser");
		mav.addObject("ExamInfo", list);
		return mav;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteExamInfo(Integer recordid, HttpServletRequest request,HttpServletResponse response){
		System.out.println(recordid);
		boolean result = examInfoService.deleteExamInfoById(recordid);
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
	public String updateExamInfo(ExamInfo examInfo){
		System.out.println(examInfo.getRecordid());
		boolean result = examInfoService.updateExamInfo(examInfo);
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
	public String selectExamInfoById(Integer recordid,  HttpServletRequest request,HttpServletResponse response){
		ExamInfo examInfo = examInfoService.selectExamInfoById(recordid);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(examInfo != null){
			resMap.put("status", "200");
			Map<String, String> temp = BeanUtil.toMap(examInfo);
			System.out.println(temp.get("recordid"));
			System.out.println(temp.get("examinationno"));
			System.out.println(temp.get("examinationtitle"));
			resMap.put("examInfoInfo"
					+ "", temp);
		}else {
			resMap.put("status", "300");
		}
		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insertExamInfo(ExamInfo examInfo, HttpServletRequest request,HttpServletResponse response){
		String result = examInfoService.insertExamInfo(examInfo);
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
