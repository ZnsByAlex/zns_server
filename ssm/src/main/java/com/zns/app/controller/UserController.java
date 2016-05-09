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


import com.alibaba.druid.support.json.JSONUtils;
import com.zns.app.bean.AnalysisResult;
import com.zns.app.bean.ExamInfo;
import com.zns.app.bean.Storage;

import com.zns.app.bean.Tray;
import com.zns.app.bean.TempExamUser;
import com.zns.app.bean.User;
import com.zns.app.bean.ZutuoGoods;
import com.zns.app.service.IAnalysisService;
import com.zns.app.service.IExamInfoService;
import com.zns.app.service.IStorageService;
import com.zns.app.service.ITempExamUserService;
import com.zns.app.service.ITrayService;
import com.zns.app.service.IUserService;
import com.zns.app.service.IZutuoGoodsService;
import com.zns.app.util.BeanUtil;
import com.zns.app.util.JsonUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IStorageService storageService;
	
	@Resource
	private ITrayService trayService;
	
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
	public String login(@RequestParam("reqParameter") String jsonString , HttpServletResponse resp) {
//		int userId = Integer.parseInt(request.getParameter("id"));  
//		System.out.println(jsonString);
		
		List<LinkedHashMap<String, Object>> userInfoList = JsonUtil.json2List(jsonString);
		userInfoList = JsonUtil.json2List(jsonString);
		Map<String , Object> userLoginMap = new HashMap<String, Object>();
		LinkedHashMap<String, Object> user_map = (LinkedHashMap<String, Object>) userInfoList.get(0).get("$mobileLogin");
		String userNo = (String) user_map.get("userNo");
		String password = (String) user_map.get("password");
//		String userNo = "3299";
//		String password = "0000";
		
		
		boolean isExist = userService.isUserExist(userNo, password);
		
		Map<String , Object> LoginInfoMap = new HashMap<String, Object>();
		String userLoginJson="";
		
		if(isExist){
			
			List<ExamInfo> list = examInfoService.getExamInfoList();
			List<LinkedHashMap<String, Object>> json_map = new ArrayList<LinkedHashMap<String,Object>>();
			
			for(int i=0 ; i<list.size();i++){
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("recordId", list.get(i).getRecordid());
				map.put("examNo", list.get(i).getExaminationno());
				map.put("examTitle", list.get(i).getExaminationtitle());
				map.put("examInfoUrl", list.get(i).getExaminfourl());
				
				json_map.add(map);
				
			}
			
			LoginInfoMap.put("status", 200);
			
			LinkedHashMap<String, Object> temp = new LinkedHashMap<String, Object>();
			temp.put("examInfo", json_map);
			LoginInfoMap.put("examInfo", json_map);
			
			
			
			System.out.println(JsonUtil.toJSon(LoginInfoMap));
		}else{
			LoginInfoMap.put("message", "登录失败");
			LoginInfoMap.put("status", 300);
		}
		
		userLoginJson = JsonUtil.toJSon(LoginInfoMap);
		System.out.println(userLoginJson);
		return userLoginJson.replace("//", "/");
	}
	
	@RequestMapping("/sendanalysis")
	public @ResponseBody String toIndex(@RequestParam("reqParameter") String jsonString , HttpServletRequest request,Model model) {
//		return analysisService.analysisData("3299", 1);
		System.out.println(jsonString);
		
//		List<LinkedHashMap<String, Object>> analysis_info = JsonUtil.json2List(jsonString);
//		
//		LinkedHashMap<String, Object> res_map = (LinkedHashMap<String, Object>) analysis_info.get(0).get("$mobileAnalyseResult");
//		String userNo = (String) res_map.get("userNo");
//		String password = (String) res_map.get("password");
//		
//		LinkedHashMap<String, Object> anwser_map = (LinkedHashMap<String, Object>) res_map.get("questionAnswer");
		
		
		
		return analysisService.analysisResult(jsonString);
	}
	
	@RequestMapping("/zutuoGoods")
	public @ResponseBody String zutuo(@RequestParam("reqParameter") String jsonString ,HttpServletRequest request,Model model){
		
//		List<LinkedHashMap<String, Object>> userInfoList = JsonUtil.json2List(jsonString);
//		userInfoList = JsonUtil.json2List(jsonString);
		
		Map<String , String> req_map = new HashMap<String, String>();
	
		
		List<LinkedHashMap<String, Object>> list = JsonUtil.json2List(jsonString);
		list = JsonUtil.json2List(jsonString);
		Map<String , Object> ana_res_Map = new HashMap<String, Object>();
		LinkedHashMap<String, Object> ana_map = (LinkedHashMap<String, Object>) list.get(0).get("$mobileGetMaterials");
		String userNo = (String) ana_map.get("userNo");
		String questionId = (String) ana_map.get("questionId");
		req_map.put("userNo", userNo);
		req_map.put("questionId", questionId);
		
		List<ZutuoGoods> zutuo_list = zutuoGoodsService.getZutuoGoodsList(req_map);
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> map_2 = new LinkedHashMap<String, Object>();
		List<LinkedHashMap<String, Object>> goods_map = new ArrayList<LinkedHashMap<String, Object>>();
		List<LinkedHashMap<String, Object>> goods_list = new ArrayList<LinkedHashMap<String,Object>>();
		
		if(list.size()>0){

			map.put("status", "200");
			map.put("message", "获取组托物料成功");

			
			for(int i=0;i<list.size() ;i++){
				ZutuoGoods item = zutuo_list.get(i);
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
			
			map_2.put("goodsInfo", goods_list);
			goods_map.add(map_2);
			map.put("data", goods_map);
			
			
		}else{

			map.put("status", "300");
			map.put("message", "获取物料失败");

		}
		
		
		return JsonUtil.Map2Json(map).replace("/", "");
	}
	
	
	@RequestMapping("/zutuoResult")
	public @ResponseBody String zutuoResult(@RequestParam("reqParameter") String jsonString ,HttpServletRequest request,Model model){
		
//		Map<String, Object> jsonMap = new LinkedHashMap<String, Object>();
//		Map<String, Object> temp = new LinkedHashMap<String, Object>();
//		temp.put("userNo", "3299");
//		temp.put("questionId", "1");
//		List<LinkedHashMap<String, Object>> userInfoList = JsonUtil.json2List(jsonString);
//		userInfoList = JsonUtil.json2List(jsonString);
//		Map<String , Object> userLoginMap = new HashMap<String, Object>();
//		LinkedHashMap<String, Object> user_map = (LinkedHashMap<String, Object>) userInfoList.get(0).get("$mobileLogin");
//		
//		List<LinkedHashMap<String , Object>> list = new ArrayList<LinkedHashMap<String , Object>>();
//		
//		for(int i=0;i<3;i++){
//			Map<String, Object> tempMap = new LinkedHashMap<String, Object>();
//			
//			
//			tempMap.put("trayNo", "0000000"+i);
//			tempMap.put("goodsNo", "123456");
//			tempMap.put("goodsNum", "10");
//			tempMap.put("receptorderNo", "R20130625");
//			
//			
//			list.add((LinkedHashMap<String, Object>) tempMap);
//			
//			
//			System.out.println("teststststststst");
//			
//		}
//		
//		temp.put("trayInfo", list);
//		
//		String jsonString = JsonUtil.Map2Json(temp);
		
		boolean isSuc = zutuoGoodsService.getZutuoResult(jsonString);
		
		Map<String, Object> resp = new LinkedHashMap<String, Object>();
		if(isSuc){
			
			resp.put("status", "200");

			resp.put("message", "组托成功");
			resp.put("outTaskNo", "PLN-BO68S40I");

		}else{
			resp.put("status", "300");
			resp.put("message", "组托失败");
		}
		
		return JsonUtil.Map2Json(resp);
	}
	
	@RequestMapping("/inputstorage")
	@ResponseBody
	public String getInputStorage(@RequestParam("reqParameter") String jsonString ,HttpServletRequest request){
		Map<String , String> req_map = new HashMap<String, String>();
	
		
		List<LinkedHashMap<String, Object>> list = JsonUtil.json2List(jsonString);
		list = JsonUtil.json2List(jsonString);
		Map<String , Object> json_Map = new HashMap<String, Object>();
		LinkedHashMap<String, Object> ana_map = (LinkedHashMap<String, Object>) list.get(0).get("$mobileGetStorage");
		String userNo = (String) ana_map.get("userNo");
		String questionId = (String) ana_map.get("questionId");
		req_map.put("userNo", userNo);
		req_map.put("questionId", questionId);
		
		List<Storage> s_list =  storageService.getStorageList(req_map);
		List<Tray> t_list = zutuoGoodsService.getTrayList(req_map);
		
//		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
//		LinkedHashMap<String, Object> map_2 = new LinkedHashMap<String, Object>();
		List<LinkedHashMap<String, Object>> list_1 = new ArrayList<LinkedHashMap<String, Object>>();
		List<LinkedHashMap<String, Object>> list_2 = new ArrayList<LinkedHashMap<String,Object>>();
		
		if(s_list!=null){
			for(int i=0;i<s_list.size();i++){
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				Storage item = s_list.get(i);
				map.put("storageNo", item.getStorageno());
				if(!item.getGoodsname().equals("") || !item.getGoodsname().equals("null") 
						|| item.getGoodsname()!=null){
					map.put("goodsName", item.getGoodsname());
					map.put("goodsNum",item.getGoodsnum());
					map.put("goodsId",item.getGoodsid());
					map.put("goodsNo",item.getGoodsno());
					map.put("goodsUnit", item.getGoodsunit());
				}
				
				list_1.add(map);
				
			}
			
		}
		
		if(t_list!=null){
			for(int i=0;i<t_list.size();i++){
				LinkedHashMap<String, Object> map_2 = new LinkedHashMap<String, Object>();
				Tray item = t_list.get(i);
				map_2.put("trayNo", item.getTrayno());
				map_2.put("goodsName", item.getGoodsname());
				map_2.put("goodsNo", item.getZutuoGoodsNo());
				map_2.put("num", Integer.parseInt(item.getGoodsnum()));
				map_2.put("goodsId", item.getId());
				map_2.put("receptorderId", item.getId());
				
				list_2.add(map_2);
			}
		}
		
		List<LinkedHashMap<String, Object>> data_list = new ArrayList<LinkedHashMap<String,Object>>();
		LinkedHashMap<String, Object> s_map = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> t_map = new LinkedHashMap<String, Object>();
		
		s_map.put("storageInfo", list_1);
		t_map.put("trayInfo", list_2);
		
		data_list.add(s_map);
		data_list.add(t_map);
		
		json_Map.put("data", data_list);
		json_Map.put("status", "200");
		json_Map.put("message", "获取上架信息成功");
		
		return JsonUtil.Map2Json(json_Map);
	}
	
	@RequestMapping("/user")
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
	

	@RequestMapping("/sendStorage")
	@ResponseBody
	public String sendInput(@RequestParam("reqParameter") String jsonString ,HttpServletRequest request){
		
		Map<String , Object> req_map = new HashMap<String, Object>();
		
		List<LinkedHashMap<String, Object>> list = JsonUtil.json2List(jsonString);
		list = JsonUtil.json2List(jsonString);
		Map<String , Object> json_Map = new HashMap<String, Object>();
		LinkedHashMap<String, Object> ana_map = (LinkedHashMap<String, Object>) list.get(0).get("$mobilePostStorage");
		String userNo = (String) ana_map.get("userNo");
		String questionId = (String) ana_map.get("questionId");
		req_map.put("userNo", userNo);
		req_map.put("questionId", questionId);
		
		list = (List<LinkedHashMap<String, Object>>) ana_map.get("postStorage");
		
		for(int i=0;i<list.size();i++){
			LinkedHashMap<String, Object> temp = list.get(i);
			req_map.put("trayNo", temp.get("trayNo"));
			req_map.put("storageNo", temp.get("storageNo"));
			storageService.updateStorageList(req_map);
		}
		json_Map.put("status", "200");
		json_Map.put("message", "上架成功");
		return JsonUtil.Map2Json(json_Map);
	}

	

	@RequestMapping("/selectExam")
	@ResponseBody
	public String selectExam(String userNo, HttpServletRequest request,HttpServletResponse response){
		List<TempExamUser> result = examUserService.selectByUserNo(userNo);
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(result != null && result.size() != 0){
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
	
	@RequestMapping("selectExamInfo")
	public ModelAndView selectExamByNoAndId(String userNo, String examid){
		ModelAndView mav = new ModelAndView("userExam");
		List<Storage> storageList = storageService.getStorageListByUser(userNo);
		List<Tray> trayList = trayService.getTrayListByUserNo(userNo);
		List<AnalysisResult> analysisResultList = analysisService.getAnalysisResultByUserNo(userNo);
		mav.addObject("AnalysisResultInfo", analysisResultList);
		mav.addObject("TrayInfo", trayList);
		mav.addObject("StorageInfo", storageList);
		mav.addObject("examid", examid);
		mav.addObject("userNo", userNo);
		
		return mav;
	}
	
	@RequestMapping("/adminLogin")
	public String adminLogin(User record){
		Map<String, Object> resMap = new LinkedHashMap<String, Object>();
		if(userService.isUserExistAdmin(record)){
			resMap.put("status", "200");
		}else {
			resMap.put("status", "300");
		}
		System.out.println(JsonUtil.Map2Json(resMap));
		return JsonUtil.Map2Json(resMap);
	}
	
	@RequestMapping("/webLogin")
	public ModelAndView webLogin(){
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping("ajaxTest")
	public String ajaxTest(){
		return "ajaxTest";
	}
	
}
