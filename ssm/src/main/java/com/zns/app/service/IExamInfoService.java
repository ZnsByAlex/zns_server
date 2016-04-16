package com.zns.app.service;

import java.util.List;

import com.zns.app.bean.ExamInfo;

public interface IExamInfoService {
	
	public List<ExamInfo> getExamInfoList();
	
	public boolean deleteExamInfoById(Integer recordid);
	
	public boolean updateExamInfo(ExamInfo examInfo);
	
	public ExamInfo selectExamInfoById(Integer recordid);
	
	public String insertExamInfo(ExamInfo examInfo);
}
