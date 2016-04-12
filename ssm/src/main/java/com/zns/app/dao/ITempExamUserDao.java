package com.zns.app.dao;

import java.util.List;

import com.zns.app.bean.TempExamUser;

public interface ITempExamUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TempExamUser record);

    int insertSelective(TempExamUser record);

    TempExamUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TempExamUser record);

    int updateByPrimaryKey(TempExamUser record);
    
    List<TempExamUser> getExamUserList();
}