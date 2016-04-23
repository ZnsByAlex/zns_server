package com.zns.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zns.app.bean.ExamInfo;
import com.zns.app.bean.TempExamUser;
import com.zns.app.bean.User;
import com.zns.app.bean.ZutuoGoods;
import com.zns.app.service.IAnalysisService;
import com.zns.app.service.IExamInfoService;
import com.zns.app.service.ITempExamUserService;
import com.zns.app.service.IUserService;
import com.zns.app.service.IZutuoGoodsService;
import com.zns.app.util.BeanUtil;
import com.zns.app.util.JsonUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private ITempExamUserService examUserService;
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IExamInfoService examInfoService;
	
	@Resource
	private IAnalysisService analysisService;
	
	@Resource
	private IZutuoGoodsService zutuoGoodsService;
	
	@RequestMapping(value= "/login" ,method= RequestMethod.POST)  
	@ResponseBody
	public String login(@RequestParam("jsonString") String jsonString , HttpServletResponse resp) {
//		int userId = Integer.parseInt(request.getParameter("id"));  
//		System.out.println(jsonString);
		
//		List<LinkedHashMap<String, Object>> userInfoList = JsonUtil.json2List(jsonString);
//		userInfoList = JsonUtil.json2List(jsonString);
		Map<String , Object> userLoginMap = new HashMap<String, Object>();
		
//		String userNo = (String) userInfoList.get(0).get("userNo");
//		String password = (String) userInfoList.get(0).get("password");
		String userNo = "3299";
		String password = "0000";
		
		
		boolean isExist = userService.isUserExist(userNo, password);
		
		Map<String , Object> LoginInfoMap = new HashMap<String, Object>();
		String userLoginJson="";
		
		if(isExist){
			
			List<ExamInfo> list = examInfoService.getExamInfoList();
			List<LinkedHashMap<String, Object>> json_map = new ArrayList<LinkedHashMap<String,Object>>();
			
//			for(int i=0 ; i<list.size();i++){
//				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
//				map.put("recordId", list.get(i).getRecordid());
//				map.put("examNo", list.get(i).getExaminationno());
//				map.put("examTitle", list.get(i).getExaminationtitle());
//				map.put("examInfoUrl", list.get(i).getExaminfourl());
//				
//				json_map.add(map);
//				
//			}
			
			for(int i=0 ; i<3;i++){
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("recordId", "sddasd");
				map.put("examNo", "221312");
				map.put("examTitle", "xxxxx");
				map.put("examInfoUrl", "www.xx.xcom");
				
				json_map.add(map);
				
			}
			
			LoginInfoMap.put("status", 200);
			
			LinkedHashMap<String, Object> temp = new LinkedHashMap<String, Object>();
			temp.put("examInfo", json_map);
			LoginInfoMap.put("data", temp);
			
			
			
			System.out.println(JsonUtil.toJSon(LoginInfoMap));
		}else{
			LoginInfoMap.put("status", 300);
		}
		
		userLoginJson = JsonUtil.toJSon(LoginInfoMap);
		
		return userLoginJson;
	}
	
	@RequestMapping("/showIndex")
	public @ResponseBody String toIndex(HttpServletRequest request,Model model) {
//		return analysisService.analysisData("3299", 1);
		
		LinkedHashMap<String, Object> res_map = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> anwser_map = new LinkedHashMap<String, Object>();
		res_map.put("userNo", "3299");
		res_map.put("questioned", "1");
		
		anwser_map.put("costSaving", 12);
		anwser_map.put("customerBasis", "XXXXXXX");
		anwser_map.put("customerSequenceUser", "CCCCCCCC");
		anwser_map.put("effectiveUser", "sssssssssss");
		anwser_map.put("examinationUserAbc", "wwwwwwwwwwwwwww");
		anwser_map.put("optimalLineUser", "hhhhhhhhhhhh");
		
		res_map.put("questionAnswer", anwser_map);
		
		return analysisService.analysisResult(JsonUtil.Map2Json(res_map));
	}
	
	@RequestMapping("/zutuoGoods")
	public @ResponseBody String zutuo(HttpServletRequest request,Model model){
		
		Map<String , String> req_map = new HashMap<String, String>();
		req_map.put("userNo", "3299");
		req_map.put("questionId", "1");
		List<ZutuoGoods> list = zutuoGoodsService.getZutuoGoodsList(req_map);
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> goods_map = new LinkedHashMap<String, Object>();
		List<LinkedHashMap<String, Object>> goods_list = new ArrayList<LinkedHashMap<String,Object>>();
		
		if(list.size()>0){
			map.put("status", 200);
			map.put("message", "閿熸枻鎷峰彇閿熸枻鎷烽敓杈冩垚鐧告嫹");
			
			for(int i=0;i<list.size() ;i++){
				ZutuoGoods item = list.get(i);
				LinkedHashMap<String, Object> mtemp = new LinkedHashMap<String, Object>();
				mtemp.put("goodsNo", item.getGoodsno());
				mtemp.put("goodsName", item.getGoodsname());
				mtemp.put("goodsNum", item.getGoodsnum());
				mtemp.put("receptorderNo", item.getReceptorderno());
				mtemp.put("orderFromNo", item.getOrderfromno());
				mtemp.put("goodsUnit", item.getGoodsunit());
				mtemp.put("spec", item.getSpec());
				
				goods_list.add(mtemp);
				
			}
			
			goods_map.put("goodsInfo", goods_list);
			map.put("data", goods_map);
			
			
		}else{
			map.put("status", 300);
			map.put("message", "閿熸枻鎷峰彇閿熸枻鎷烽敓鏂ゆ嫹澶遍敓鏂ゆ嫹");
		}
		
		return JsonUtil.Map2Json(map);
	}
	
	
	@RequestMapping("/zutuoResult")
	public @ResponseBody String zutuoResult(HttpServletRequest request,Model model){
		
//		Map<String, Object> jsonMap = new LinkedHashMap<String, Object>();
		Map<String, Object> temp = new LinkedHashMap<String, Object>();
		temp.put("userNo", "3299");
		temp.put("questionId", "1");
		
		
		List<LinkedHashMap<String , Object>> list = new ArrayList<LinkedHashMap<String , Object>>();
		
		for(int i=0;i<3;i++){
			Map<String, Object> tempMap = new LinkedHashMap<String, Object>();
			
			
			tempMap.put("trayNo", "0000000"+i);
			tempMap.put("goodsNo", "123456");
			tempMap.put("goodsNum", "10");
			tempMap.put("receptorderNo", "R20130625");
			
			
			list.add((LinkedHashMap<String, Object>) tempMap);
			
			
			System.out.println("teststststststst");
			
		}
		
		temp.put("trayInfo", list);
		
		String jsonString = JsonUtil.Map2Json(temp);
		
		boolean isSuc = zutuoGoodsService.getZutuoResult(jsonString);
		
		Map<String, Object> resp = new LinkedHashMap<String, Object>();
		if(isSuc){
			
			resp.put("status", "200");
			resp.put("message", "閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�");
			
		}else{
			resp.put("status", "300");
			resp.put("message", "閿熸枻鎷烽敓鏂ゆ嫹澶遍敓鏂ゆ嫹");
		}
		
		return JsonUtil.Map2Json(resp);
	}
	
	@RequestMapping("/testUser")
	public ModelAndView getAllUser(HttpServletRequest request,HttpServletResponse response){
		List<User> list = userService.getUserList();
		List<TempExamUser> examUserList = examUserService.getExamUserList();
		if(list == null){
			System.out.println("null");
		}
//		
		Iterator it = list.iterator();
		while(it.hasNext()){
			User tem = (User) it.next();
			System.out.println(tem.getId());
			System.out.print(tem.getUserName());
			System.out.println(tem.getUserNo());
		}
		ModelAndView mav = new ModelAndView("user");
		mav.addObject("User", list);
		mav.addObject("ExamUser", examUserList);
		return mav;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(User user, HttpServletRequest request,HttpServletResponse response){
		boolean result = userService.deleteUserById(user.getId());
		System.out.println(result);
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(result) {
			resMap.put("status", "200");
		}else {
			resMap.put("status", "300");
		}
//		response.setContentType("application/json");
//		PrintWriter out;
//		try {
//			out = response.getWriter();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return JsonUtil.Map2Json(resMap);
		
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(User user, HttpServletRequest request,HttpServletResponse response){
//		System.out.println(user.getId());
		boolean result = userService.updateUserById(user);
//		System.out.println(result);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(result) {
			resMap.put("status", "200");
		}else {
			resMap.put("status", "300");
		}
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("selectid")
	@ResponseBody
	public String slectByid(Integer id, HttpServletRequest request,HttpServletResponse response){
		User user = userService.getUserById(id);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(user != null){
			resMap.put("status", "200");
			Map<String, String> temp = BeanUtil.toMap(user);
			System.out.println(temp.get("id"));
			System.out.println(temp.get("userNo"));
			System.out.println(temp.get("userName"));
			resMap.put("userInfo", temp);
		}else {
			resMap.put("status", "300");
		}
//		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insertUser(User user, HttpServletRequest request,HttpServletResponse response){
		String result = userService.registerUser(user);
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
	
	@RequestMapping("/selectExam")
	@ResponseBody
	public String selectExam(String userNo, HttpServletRequest request,HttpServletResponse response){
		List<TempExamUser> result = examUserService.selectByUserNo(userNo);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(result != null){
			List<Map<String, Object>> tempList = new LinkedList<Map<String, Object>>();
			Iterator<TempExamUser> it = result.iterator();
			while(it.hasNext()){
				HashMap<String, Object> tempMap = new HashMap<String, Object>();
				TempExamUser temp = it.next();
				tempMap.put("score", temp.getScore());
				String examinationtitle = examInfoService.selectExamInfoById(temp.getExaminationid()).getExaminationtitle();
				tempMap.put("examinationtitle", examinationtitle);
				tempList.add(tempMap);
			}
			resMap.put("status", "200");
			resMap.put("examInfo", tempList);
			
		}else {
			resMap.put("status", "201");
		}
		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("ajaxTest")
	public String ajaxTest(){
		return "ajaxTest";
	}
	
}
