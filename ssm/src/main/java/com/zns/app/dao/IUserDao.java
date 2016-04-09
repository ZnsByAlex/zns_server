package com.zns.app.dao;

import java.util.List;
import java.util.Map;

import com.zns.app.bean.User;

public interface IUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Map<String , String> map);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User test();
    
    List<User> getUserList ();
    
    User selectById(Integer id);
    
    
}