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

}
