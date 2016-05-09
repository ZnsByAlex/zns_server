package com.zns.app.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.zns.app.bean.Line;

public interface IAnalysisService {
	/**题目分析数据的json*/
	public String analysisData(); 
	
	/**题目分析数据的json*/
	public String analysisData(String userNo , Integer examId); 
	
	/**订单*/
	public List<LinkedHashMap<String, Object>> orderFrom(Integer examId); 
	
	/**物料信息*/
	public List<LinkedHashMap<String, Object>> goodsVo(Integer examId);
	
	/**客户信息*/
	public List<LinkedHashMap<String, Object>> customer(Integer examId); 
	
	/**路线信息*/
	public Line line(Integer examId);
	
	
	/**路线客户信息*/
	public List<LinkedHashMap<String, Object>> lineCustom(Integer examId);
	
	/**题目分析结果*/
	public String analysisResult(String json);
	
	
	
}
