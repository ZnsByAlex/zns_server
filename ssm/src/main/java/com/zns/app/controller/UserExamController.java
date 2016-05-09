package com.zns.app.controller;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zns.app.bean.AnalysisResult;
import com.zns.app.bean.Storage;
import com.zns.app.bean.TempExamUser;
import com.zns.app.bean.Tray;
import com.zns.app.service.IAnalysisService;
import com.zns.app.service.IStorageService;
import com.zns.app.service.ITempExamUserService;
import com.zns.app.service.ITrayService;
import com.zns.app.util.BeanUtil;
import com.zns.app.util.JsonUtil;

@Controller
@RequestMapping("/userExam")
public class UserExamController {

	@Resource
	private IAnalysisService analysisService;
	
	@Resource
	private ITrayService trayService;
	
	@Resource
	private IStorageService storageService;
	
	@Resource
	private ITempExamUserService examUserService;
	
	@RequestMapping("/selectAnalysisByNo")
	@ResponseBody
	public String selectAnalysisByNo (String userNo){
		List<AnalysisResult> analysisResultList = analysisService.getAnalysisResultByUserNo(userNo);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(analysisResultList != null) {
			resMap.put("status", "200");
			List<Map<String, String>> analysisResultInfoList = new LinkedList<Map<String, String>>();
			Iterator<AnalysisResult> it = analysisResultList.iterator();
			while(it.hasNext()){
				AnalysisResult temp = it.next();
				analysisResultInfoList.add(BeanUtil.toMap(temp));
			}
			resMap.put("analysisResultInfoList", analysisResultInfoList);
		} else {
			resMap.put("status", "300");
		}
		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/selectTrayByNo")
	@ResponseBody
	public String selectTrayListByNo(String userNo){
		List<Tray> trayList = trayService.getTrayListByUserNo(userNo);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(trayList != null) {
			resMap.put("status", "200");
			List<Map<String, String>> trayInfoList = new LinkedList<Map<String, String>>();
			Iterator<Tray> it = trayList.iterator();
			while(it.hasNext()){
				Tray temp = it.next();
				trayInfoList.add(BeanUtil.toMap(temp));
			}
			resMap.put("trayInfoList", trayInfoList);
		} else {
			resMap.put("status", "300");
		}
		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/selectStorageByNo")
	@ResponseBody
	public String selectStorageByNo(String userNo){
		List<Storage> storageList = storageService.getStorageListByUser(userNo);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(storageList != null) {
			resMap.put("status", "200");
			List<Map<String, String>> storageInfoList = new LinkedList<Map<String, String>>();
			Iterator<Storage> it = storageList.iterator();
			while(it.hasNext()){
				Storage temp = it.next();
				storageInfoList.add(BeanUtil.toMap(temp));
			}
			resMap.put("storageInfoList", storageInfoList);
		} else {
			resMap.put("status", "300");
		}
		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/updataScoreByNo")
	@ResponseBody
	public String updateScoreByNo(TempExamUser examUser){
		boolean result = examUserService.updateScoreByUserAndExam(examUser);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		System.out.println("UserExamController updatascoreByNo result:" + result);
		if(result) {
			resMap.put("status", "200");
		}else {
			resMap.put("status", "300");
		}
		return JsonUtil.Map2Json(resMap);
	}
}

