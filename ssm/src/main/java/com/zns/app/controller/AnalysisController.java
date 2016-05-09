package com.zns.app.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zns.app.service.IAnalysisService;
import com.zns.app.service.IClientService;
import com.zns.app.service.IMaterialService;
import com.zns.app.service.IOrderService;
import com.zns.app.util.JsonUtil;

@Controller
@RequestMapping("/analysis")
public class AnalysisController {

	@Resource
	private IAnalysisService analysisService;
	
	@RequestMapping("/getMaterialListByUser")
	@ResponseBody
	public String login(@RequestParam("reqParameter") String jsonString , HttpServletResponse resp) {
		List<LinkedHashMap<String, Object>> list = JsonUtil.json2List(jsonString);
		list = JsonUtil.json2List(jsonString);
		Map<String , Object> ana_res_Map = new HashMap<String, Object>();
		LinkedHashMap<String, Object> ana_map = (LinkedHashMap<String, Object>) list.get(0).get("$mobileConfirmQuestion");
		String userNo = (String) ana_map.get("userNo");
		String questionId = (String) ana_map.get("questionId");
		
		return analysisService.analysisData(userNo, Integer.parseInt(questionId));
	}
}
