package com.zns.app.controller;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zns.app.bean.ExamInfo;
import com.zns.app.bean.Material;
import com.zns.app.bean.Order;
import com.zns.app.bean.TempExamUser;
import com.zns.app.bean.ZutuoGoods;
import com.zns.app.service.IExamInfoService;
import com.zns.app.service.IMaterialService;
import com.zns.app.service.IOrderService;
import com.zns.app.service.ITempExamUserService;
import com.zns.app.service.IZutuoGoodsService;
import com.zns.app.util.BeanUtil;
import com.zns.app.util.JsonUtil;

@Controller
@RequestMapping("/exam")
public class ExamController {
	@Resource
	private IOrderService orderService;
	
	@Resource
	private ITempExamUserService examUserService;
	
	@Resource
	private IZutuoGoodsService zutuoGoodsService;
	
	@Resource
	private IMaterialService materialService;
	
	@RequestMapping("/selectOrderById")
	@ResponseBody
	public String selectOrderById(Integer examId, HttpServletRequest request,HttpServletResponse response){
		List<Order> orderList = orderService.getOrderList(examId);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(orderList != null) {
			resMap.put("status", "200");
			List<Map<String, String>> orderInfoList = new LinkedList<Map<String, String>>();
			Iterator<Order> it = orderList.iterator();
			while(it.hasNext()){
				Order temp = it.next();
				orderInfoList.add(BeanUtil.toMap(temp));
			}
			resMap.put("orderInfoList", orderInfoList);
		} else {
			resMap.put("status", "300");
		}
		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/selectGoodById")
	@ResponseBody
	public String selectGoodById(Integer examId){
		List<ZutuoGoods> goodsList = zutuoGoodsService.selectByExamId(examId);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(goodsList != null) {
			resMap.put("status", "200");
			List<Map<String, String>> goodsInfoList = new LinkedList<Map<String, String>>();
			Iterator<ZutuoGoods> it = goodsList.iterator();
			while(it.hasNext()){
				ZutuoGoods temp = it.next();
				goodsInfoList.add(BeanUtil.toMap(temp));
			}
			resMap.put("zutuoGoodsInfoList", goodsInfoList);
		} else {
			resMap.put("status", "300");
		}
		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/selectMaterialById")
	@ResponseBody
	public String selectMaterialById(Integer examId){
		List<Material> materialList = materialService.getMaterialListByExamId(examId);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(materialList != null) {
			resMap.put("status", "200");
			List<Map<String, String>> materialInfoList = new LinkedList<Map<String, String>>();
			Iterator<Material> it = materialList.iterator();
			while(it.hasNext()){
				Material temp = it.next();
				materialInfoList.add(BeanUtil.toMap(temp));
			}
			resMap.put("materialInfoList", materialInfoList);
		} else {
			resMap.put("status", "300");
		}
		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/updataScoreByNo")
	@ResponseBody
	public String updateExamUser(TempExamUser examUser){
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
}
