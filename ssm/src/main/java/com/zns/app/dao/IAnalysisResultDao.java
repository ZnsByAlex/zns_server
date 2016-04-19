package com.zns.app.dao;

import java.util.List;

import com.zns.app.bean.AnalysisResult;

public interface IAnalysisResultDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AnalysisResult record);

    int insertSelective(AnalysisResult record);

    AnalysisResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnalysisResult record);

    int updateByPrimaryKey(AnalysisResult record);
    
    List<AnalysisResult> getAnalysisListByUserNo(String userNo);
}