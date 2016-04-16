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

import com.zns.app.bean.Material;
import com.zns.app.service.IMaterialService;
import com.zns.app.util.BeanUtil;
import com.zns.app.util.JsonUtil;

@Controller
@RequestMapping("/material")
public class MaterialController {

	@Resource
	private IMaterialService materialService;
	
	@RequestMapping("/getMaterialList")
	public ModelAndView getMaterialList(){
		List<Material> list = materialService.getMaterialList();
		if(list == null){
			System.out.println("null");
		}
//		
		Iterator it = list.iterator();
		while(it.hasNext()){
			Material tem = (Material) it.next();
			System.out.println(tem.getMaterialId());
			System.out.println(tem.getExamInfoId());
			System.out.print(tem.getMaterialName());
		}
		ModelAndView mav = new ModelAndView("testUser");
		mav.addObject("Material", list);
		return mav;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteMaterial(Integer materialId, HttpServletRequest request,HttpServletResponse response){
		System.out.println(materialId);
		boolean result = materialService.deleteMaterialById(materialId);
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
	public String updateMatertial(Material material){
		System.out.println(material.getMaterialId());
		boolean result = materialService.updateMaterial(material);
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
	public String selectMaterialById(Integer materialId,  HttpServletRequest request,HttpServletResponse response){
		Material material = materialService.selectMaterialById(materialId);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(material != null){
			resMap.put("status", "200");
			Map<String, String> temp = BeanUtil.toMap(material);
//			System.out.println(temp.get("materialId"));
//			System.out.println(temp.get("userNo"));
//			System.out.println(temp.get("examinationid"));
			resMap.put("materialInfo", temp);
		}else {
			resMap.put("status", "300");
		}
		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insertMaterial(Material material, HttpServletRequest request,HttpServletResponse response){
		String result = materialService.insertMaterial(material);
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
