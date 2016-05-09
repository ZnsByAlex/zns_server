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

import com.zns.app.bean.TempExamUser;
import com.zns.app.bean.ZutuoGoods;
import com.zns.app.service.IZutuoGoodsService;
import com.zns.app.util.BeanUtil;
import com.zns.app.util.JsonUtil;

@Controller
@RequestMapping("/zutuoGoods")
public class ZutuoGoodsController {

	@Resource
	private IZutuoGoodsService zutuoGoodsService;
	
	@RequestMapping("/getZutuoList")
	public ModelAndView getZutuoList(){
		List<ZutuoGoods> list = zutuoGoodsService.getZutuoGoodsList();
		if(list == null){
			System.out.println("null");
		}
//		
		Iterator it = list.iterator();
		while(it.hasNext()){
			ZutuoGoods tem = (ZutuoGoods) it.next();
			System.out.println(tem.getExamid());
			System.out.println(tem.getGoodsno());
			System.out.print(tem.getGoodsname());
		}
		ModelAndView mav = new ModelAndView("goods");
		mav.addObject("ZutuoGoods", list);
		return mav;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteZutuoGoods(Integer examid, String goodsno, HttpServletRequest request,HttpServletResponse response){
		System.out.println(examid);
		System.out.println(goodsno);
		boolean result = zutuoGoodsService.deleteZutuoByIdAndNo(examid, goodsno);
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
	public String updateZutuoGoods(ZutuoGoods zutuoGoods){
		System.out.println(zutuoGoods.getGoodsno());
		System.out.println(zutuoGoods.getExamid());
		boolean result = zutuoGoodsService.updateZutuo(zutuoGoods);
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
	public String selectZutuoGoods(Integer examid, String goodsno, HttpServletRequest request,HttpServletResponse response){
		ZutuoGoods zutuoGoods = zutuoGoodsService.getZutuoByIdAndNo(examid, goodsno);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(zutuoGoods != null){
			resMap.put("status", "200");
			Map<String, String> temp = BeanUtil.toMap(zutuoGoods);
			System.out.println(temp.get("examid"));
			System.out.println(temp.get("goodsno"));
			System.out.println(temp.get("goodsname"));
			resMap.put("zutuoGoodsInfo", temp);
		}else {
			resMap.put("status", "300");
		}
		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insertZutuoGoods(ZutuoGoods zutuoGoods, HttpServletRequest request,HttpServletResponse response){
		String result = zutuoGoodsService.insertZutuoGoods(zutuoGoods);
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
