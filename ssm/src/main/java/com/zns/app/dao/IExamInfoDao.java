package com.zns.app.dao;

import java.util.List;

import com.zns.app.bean.ExamInfo;

public interface IExamInfoDao {
    int deleteByPrimaryKey(Integer recordid);

    int insert(ExamInfo record);

    int insertSelective(ExamInfo record);

    ExamInfo selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(ExamInfo record);

    int updateByPrimaryKey(ExamInfo record);
    
    List<ExamInfo> queryExamInfoList();
}