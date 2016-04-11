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
import com.zns.app.bean.User;
import com.zns.app.service.IOrderService;
import com.zns.app.util.BeanUtil;
import com.zns.app.util.JsonUtil;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Resource
	private IOrderService orderService;
	
	@RequestMapping("/getOrderList")
	public ModelAndView getOrderList(Integer examId, HttpServletRequest request,HttpServletResponse response){
		List<Order> list = orderService.getOrderList(new Integer(1));
		if(list == null){
			System.out.println("null");
		}
//		
//		Iterator it = list.iterator();
//		while(it.hasNext()){
//			User tem = (User) it.next();
//			System.out.println(tem.getId());
//			System.out.print(tem.getUserName());
//			System.out.println(tem.getUserNo());
//		}
		ModelAndView mav = new ModelAndView("testUser");
		mav.addObject("Order", list);
		return mav;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String deleteOrder(Order order, HttpServletRequest request,HttpServletResponse response){
		boolean result = orderService.deleteOrderById(order.getOrderId());
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
	public String updateOrder(Order order, HttpServletRequest request,HttpServletResponse response){
		boolean result = orderService.updateOrder(order);
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(result) {
			resMap.put("status", "200");
		}else {
			resMap.put("status", "300");
		}
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/selectid")
	@ResponseBody
	public String selectOrderById(Integer orderId,  HttpServletRequest request,HttpServletResponse response){
		Order order = orderService.selectOrderById(orderId);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(order != null){
			resMap.put("status", "200");
			Map<String, String> temp = BeanUtil.toMap(order);
			System.out.println(temp.get("orderId"));
			System.out.println(temp.get("examInfoId"));
			System.out.println(temp.get("customerName"));
			resMap.put("orderInfo", temp);
		}else {
			resMap.put("status", "300");
		}
//		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insertOrder(Order order, HttpServletRequest request,HttpServletResponse response){
		System.out.println(order.getExamInfoId());
		System.out.println(order.getOrderNo());
		System.out.println(order.getCustomerName());
		String result = orderService.insertOrder(order);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if (result.equals("success")){
			resMap.put("status", "200");
		} else {
			resMap.put("status", "300");
			resMap.put("info", result);
		}
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("ajaxTest")
	public String ajaxTest(){
		return "ajaxTest";
	}
}
