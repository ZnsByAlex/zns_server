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

import com.zns.app.bean.Client;
import com.zns.app.bean.TempExamUser;
import com.zns.app.service.IClientService;
import com.zns.app.util.BeanUtil;
import com.zns.app.util.JsonUtil;

@Controller
@RequestMapping("client")
public class ClientController {

	@Resource
	private IClientService clientService;
	
	@RequestMapping("/getClientList")
	public ModelAndView getClientList(){
		List<Client> list = clientService.getClientList();
		if(list == null){
			System.out.println("null");
		}
//		
		Iterator it = list.iterator();
		while(it.hasNext()){
			Client tem = (Client) it.next();
			System.out.println(tem.getClientName());
			System.out.println(tem.getClientTel());
			System.out.print(tem.getClientType());
		}
		ModelAndView mav = new ModelAndView("client");
		mav.addObject("Client", list);
		return mav;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteClient(Integer clientId, HttpServletRequest request,HttpServletResponse response){
		System.out.println(clientId);
		boolean result = clientService.deleteClientById(clientId);
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
	public String updateClient(Client client){
		System.out.println(client.getClientId());
		boolean result = clientService.updateClient(client);
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
	public String selectClientById(Integer clientId,  HttpServletRequest request,HttpServletResponse response){
		System.out.println(clientId);
		Client client = clientService.selectClientById(clientId);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(client != null){
			resMap.put("status", "200");
			Map<String, String> temp = BeanUtil.toMap(client);
			System.out.println(temp.get("clientId"));
			System.out.println(temp.get("clientName"));
			System.out.println(temp.get("clientAddress"));
			resMap.put("clientInfo", temp);
		}else {
			resMap.put("status", "300");
		}
		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insertUser(Client client, HttpServletRequest request,HttpServletResponse response){
		String result = clientService.insertClient(client);
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
