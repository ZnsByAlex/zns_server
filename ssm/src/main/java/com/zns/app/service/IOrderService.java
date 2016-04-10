package com.zns.app.service;

import java.util.List;

import com.zns.app.bean.Order;

public interface IOrderService {
	
	public List<Order> getOrderList(Integer examId);
	
	public boolean deleteOrderById(Integer orderId);
	
	public boolean updateOrder(Order order);
	
	public Order selectOrderById(Integer orderId);

}
