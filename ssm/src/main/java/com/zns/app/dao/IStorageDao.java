package com.zns.app.dao;

import java.util.List;
import java.util.Map;

import com.zns.app.bean.Storage;

public interface IStorageDao {
    int insert(Storage record);

    int insertSelective(Storage record);
    
    List<Storage> selectByExamIdAndUser(Map<String , Object> map);
    
    List<Storage> selectByUserNo(String userNo);
}