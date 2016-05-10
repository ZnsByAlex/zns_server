package com.zns.app.dao;

import java.util.List;
import java.util.Map;

import com.zns.app.bean.Order;

public interface IOrderDao {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Order> getOrderByExamId(Integer examId);
    
    List<Order> getAllOrder();
    
    int updateEffect(Integer orderNo);
}