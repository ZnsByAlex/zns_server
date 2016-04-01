package com.zns.app.dao;

import java.util.List;

import com.zns.app.bean.LineCustomer;

public interface ILineCustomerDao {
    int insert(LineCustomer record);

    int insertSelective(LineCustomer record);
    
    List<LineCustomer> getLineCustomerByExamId(Integer examId);
}