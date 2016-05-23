package com.zns.app.sevice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zns.app.bean.AnalysisResult;
import com.zns.app.bean.Client;
import com.zns.app.bean.Line;
import com.zns.app.bean.LineCustomer;
import com.zns.app.bean.Material;
import com.zns.app.bean.Order;
import com.zns.app.bean.TempExamUser;
import com.zns.app.dao.IAnalysisResultDao;
import com.zns.app.dao.IClientDao;
import com.zns.app.dao.ILineCustomerDao;
import com.zns.app.dao.ILineDao;
import com.zns.app.dao.IMaterialDao;
import com.zns.app.dao.IOrderDao;
import com.zns.app.dao.ITempExamUserDao;
import com.zns.app.service.IAnalysisService;
import com.zns.app.util.JsonUtil;

@Service("analysisService")
public class AnalysisServiceImpl implements IAnalysisService{

	private TempExamUser temp;
	
	@Resource
	private IOrderDao orderDao;
	
	@Resource
	private IMaterialDao materialDao;
	
	@Resource
	private IClientDao clientDao;
	
	@Resource
	private ILineCustomerDao lineCustomerDao;
	
	@Resource
	private ILineDao lineDao;
	
	@Resource
	private ITempExamUserDao tempExamUserDao;
	
	@Resource
	private IAnalysisResultDao analysisResultDao;
	
	@Override
	public String analysisData() {
		
		String json_req = null;
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		List<LinkedHashMap<String, Object>> data_list = new ArrayList<LinkedHashMap<String, Object>>();
		LinkedHashMap<String, Object> data_map = null;
		map.put("status", "200");
		
		if(temp!=null){
			data_map = new LinkedHashMap<String, Object>();
			data_map.put("orderFrom", orderFrom(temp.getExaminationid()));
			data_map.put("goodsVo", goodsVo(temp.getExaminationid()));
			data_map.put("customer", customer(temp.getExaminationid()));
			//data_map.put("line", line(temp.getExaminationid()));
			data_map.put("lineCustom", lineCustom(temp.getExaminationid()));
		}
		
		Line line = line(temp.getExaminationid());
		
		data_map.put("line", line.getLinePic());
		
		if(data_map!=null){
			data_list.add(data_map);
			map.put("data", data_list);
			json_req = JsonUtil.Map2Json(map);
		}
		
		return json_req;
	}

	@Override
	public List<LinkedHashMap<String, Object>> orderFrom(Integer examId) {
//		orderFrom
		List<Order> list = orderDao.getOrderByExamId(examId);
		List<LinkedHashMap<String, Object>> json_map = new ArrayList<LinkedHashMap<String,Object>>();
		
		for(int i=0;i<list.size();i++){
			
			Map<String , Object> map = new LinkedHashMap<String,Object>();
			
			Order item = list.get(i);
			map.put("recordId", item.getOrderId());
			map.put("orderNo", item.getOrderNo()+"");
			map.put("orderType", item.getOrderType());
			map.put("customerName", item.getCustomerName());
			map.put("examInfoId", examId);
			
			json_map.add((LinkedHashMap<String, Object>) map);
			
		}
		
		return json_map;
	}

	@Override
	public List<LinkedHashMap<String, Object>> goodsVo(Integer examId) {
		
		List<Material> list = materialDao.getMaterialListByExamId(examId);
		List<LinkedHashMap<String, Object>> json_map = new ArrayList<LinkedHashMap<String,Object>>();
		
		for(int i=0;i<list.size();i++){
			
			Map<String , Object> map = new LinkedHashMap<String,Object>();
			
			Material item = list.get(i);
			map.put("goodsNo", item.getMaterialId()+"");
			map.put("goodsName", item.getMaterialName());
			map.put("goodsNum", item.getTotalNum());
			map.put("receptorderNo", item.getMaterialId()+"");
			map.put("spec", item.getMaterialSpecification());
			map.put("orderFromNo", item.getOrderFromNo());
			
			
			json_map.add((LinkedHashMap<String, Object>) map);
			
		}
		
		return json_map;
	}

	@Override
	public List<LinkedHashMap<String, Object>> customer(Integer examId) {
		List<Client> list = clientDao.getClientByExamId(examId);
		List<LinkedHashMap<String, Object>> json_map = new ArrayList<LinkedHashMap<String,Object>>();
		
		for(int i=0;i<list.size();i++){
			
			Map<String , Object> map = new LinkedHashMap<String,Object>();
			
			Client item = list.get(i);
			map.put("customerNo", item.getClientId()+"");
			map.put("customerName", item.getClientName());
			map.put("customerShortName", item.getClientShortName());
			map.put("type", item.getClientType());
			map.put("address", item.getClientAddress());
			map.put("tel", item.getClientTel());
//			map.put("contact", item.get);
			map.put("orderFromNo", item.getOrderFromNo());
			
			
			json_map.add((LinkedHashMap<String, Object>) map);
			
		}
		
		return json_map;
	}

	@Override
	public Line line(Integer examId) {
		
		Line line = lineDao.selectByPrimaryKey(examId);
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		if(line!=null)
			map.put("line", line.getLinePic());
		
		return line;
	}

	@Override
	public List<LinkedHashMap<String, Object>> lineCustom(Integer examId) {

		List<LineCustomer> list = lineCustomerDao.getLineCustomerByExamId(examId);
		List<LinkedHashMap<String, Object>> json_map = new ArrayList<LinkedHashMap<String,Object>>();
		
		for(int i=0;i<list.size();i++){
			
			Map<String , Object> map = new LinkedHashMap<String,Object>();
			
			LineCustomer item = list.get(i);
			map.put("customerNo", item.getCustomerNo());
			map.put("customerName", item.getCustomerName());
			
			
			json_map.add((LinkedHashMap<String, Object>) map);
			
		}
		
		return json_map;
	}

	@Override
	public String analysisData(String userNo, Integer examId) {
		temp = new TempExamUser();
		temp.setUserNo(userNo);
		temp.setExaminationid(examId);
		
		int res = tempExamUserDao.insert(temp);
		
		if(res>0){
			return analysisData();
		}else{
			//插入失败
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("status", 300);
			map.put("message", "选择题目失败");
			
			return JsonUtil.Map2Json(map);
		}
	}

	@Override
	public String analysisResult(String json) {
		
		String json_res = null;
		List<LinkedHashMap<String, Object>> list = JsonUtil.json2List(json);
		LinkedHashMap<String, Object> res_map = (LinkedHashMap<String, Object>) list.get(0).get("$mobileAnalyseResult");
		if(res_map!=null){
			String userNo = (String) res_map.get("userNo");
			String examId = (String) res_map.get("questioned");
			
			LinkedHashMap<String, Object> que_result = (LinkedHashMap<String, Object>) res_map.get("questionAnswer");
			
			Integer costSaving = Integer.parseInt((String)que_result.get("costSaving")); 
			String customerBasis = (String) que_result.get("customerBasis");
			String customerSequenceUser = (String) que_result.get("customerSequenceUser");
			String effectiveUser = (String) que_result.get("effectiveUser");
			String examinationUserAbc = (String) que_result.get("examinationUserAbc");
			String optimalLineUser = (String) que_result.get("optimalLineUser");
			
			AnalysisResult analysisResult = new AnalysisResult();
			
			analysisResult.setUserno(userNo);
			analysisResult.setQuestioneid(Integer.parseInt(examId));
			analysisResult.setCostSaving(costSaving);
			analysisResult.setCustomerBasis(customerBasis);
			analysisResult.setCustomerSequenceUser(customerSequenceUser);
			analysisResult.setEffectiveUser(effectiveUser);
			analysisResult.setExaminationUserAbc(examinationUserAbc);
			analysisResult.setOptimallineUser(optimalLineUser);
			
			//插入有效性信息到数据库
			String[] effictList = effectiveUser.split(",");
			for(String effictData:effictList){
				if(effictData != null && !effictData.equals("")){
					orderDao.updateEffect(Integer.parseInt(effictData));
				}
			}
			//插入物料类型
			String[] typeList  = examinationUserAbc.split(",");
			for(int i = 0; i < typeList.length; i++){
				String[] materialType = typeList[i].split("\\*");
				for(int j = 0; j < materialType.length; j++){
					Map<String, Object> record = new HashMap<String, Object>();
					switch(i){
					case 0:
						record.put("materialType", "A");
						break;
					case 1:
						record.put("materialType", "B");
						break;
					case 2:
						record.put("materialType", "C");
						break;
					}
					record.put("materialId", Integer.parseInt(materialType[j]));
					materialDao.updateType(record);
				}
			}
			
			int res = analysisResultDao.insert(analysisResult);
			if(res > 0){
				LinkedHashMap<String, Object> req = new LinkedHashMap<String, Object>();
				req.put("status", "200");
				req.put("message", "题目分析提交成功");
				List<LinkedHashMap<String, Object>> data = new ArrayList<LinkedHashMap<String, Object>>();
				LinkedHashMap<String, Object> data_map = new LinkedHashMap<String, Object>();
				data_map.put("outTaskNo", "PLN-BO68S40I");
				data.add(data_map);
				req.put("data", data);
				json_res = JsonUtil.Map2Json(req);
			}else{
				LinkedHashMap<String, Object> req = new LinkedHashMap<String, Object>();
				req.put("status", "300");
				req.put("message", "题目分析提交失败");
				
				json_res = JsonUtil.Map2Json(req);
			}
		}else{
			LinkedHashMap<String, Object> req = new LinkedHashMap<String, Object>();
			req.put("status", "300");
			req.put("message", "提交数据失败");
			
			json_res = JsonUtil.Map2Json(req);
		}
		
		return json_res;
	}

	@Override
	public List<AnalysisResult> getAnalysisResultByUserNo(String userNo) {
		// TODO Auto-generated method stub
		return analysisResultDao.getAnalysisListByUserNo(userNo);
	}

}
