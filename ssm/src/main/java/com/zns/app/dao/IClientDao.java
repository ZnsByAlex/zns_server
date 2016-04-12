package com.zns.app.dao;

import java.util.List;

import com.zns.app.bean.Client;

public interface IClientDao {
    int deleteByPrimaryKey(Integer clientId);

    int insert(Client record);

    int insertSelective(Client record);

    Client selectByPrimaryKey(Integer clientId);

    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);
    
    List<Client> getClientByExamId(Integer examId);
    
    List<Client> getClientList();
    
}