package com.zns.app.dao;

import java.util.List;
import java.util.Map;

import com.zns.app.bean.Material;

public interface IMaterialDao {
    int deleteByPrimaryKey(Integer materialId);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(Integer materialId);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);
    
    List<Material> getMaterialListByExamId(Integer examId);
    
    List<Material> getMaterialList();
    
    int updateType(Map<String, Object> recocrd);
}