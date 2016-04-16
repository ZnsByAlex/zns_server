package com.zns.app.sevice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zns.app.bean.ExamInfo;
import com.zns.app.dao.IExamInfoDao;
import com.zns.app.service.IExamInfoService;

@Service("examInfoService")
public class ExamInfoServiceImpl implements IExamInfoService {

	@Resource
	private IExamInfoDao examInfoDao;
	
	@Override
	public List<ExamInfo> getExamInfoList() {
		List<ExamInfo> list = examInfoDao.queryExamInfoList();
		return list;
	}

	@Override
	public boolean deleteExamInfoById(Integer recordid) {
		// TODO Auto-generated method stub
		int result = examInfoDao.deleteByPrimaryKey(recordid);
		if (result == 1) return true;
		return false;
	}

	@Override
	public boolean updateExamInfo(ExamInfo examInfo) {
		// TODO Auto-generated method stub
		int result = examInfoDao.updateByPrimaryKeySelective(examInfo);
		if (result == 1) return true;
		return false;
	}

	@Override
	public ExamInfo selectExamInfoById(Integer recordid) {
		// TODO Auto-generated method stub
		ExamInfo examInfo = examInfoDao.selectByPrimaryKey(recordid);
		return examInfo;
	}

	@Override
	public String insertExamInfo(ExamInfo examInfo) {
		// TODO Auto-generated method stub
		if (examInfo.getRecordid() != null)
			return "Error: can't insert recordId";
		int result = examInfoDao.insertSelective(examInfo);
		if (result == 1) return "success";
		return "Unknown Error";
	}

}
