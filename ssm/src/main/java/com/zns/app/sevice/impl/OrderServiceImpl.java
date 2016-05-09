package com.zns.app.sevice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zns.app.bean.Order;
import com.zns.app.dao.IOrderDao;
import com.zns.app.service.IOrderService;

@Service("orderService")
public class OrderServiceImpl implements IOrderService{

	@Resource
	private IOrderDao orderDao;
	
	@Override
	public List<Order> getOrderList(Integer examId) {
		// TODO Auto-generated method stub
		List<Order> list = orderDao.getOrderByExamId(examId);
		return list;
	}

	@Override
	public boolean deleteOrderById(Integer orderId) {
		// TODO Auto-generated method stub
		int result = orderDao.deleteByPrimaryKey(orderId);
		if (result == 1) return true;
		return false;
	}

	@Override
	public boolean updateOrder(Order order) {
		// TODO Auto-generated method stub
		int result = orderDao.updateByPrimaryKeySelective(order);
		if (result == 1) return true;
		return false;
	}

	@Override
	public Order selectOrderById(Integer orderId) {
		// TODO Auto-generated method stub
//		System.out.println(orderId);
		Order result = orderDao.selectByPrimaryKey(orderId);
		return result;
	}

	@Override
	public String insertOrder(Order order) {
		// TODO Auto-generated method stub
		int result = orderDao.insertSelective(order);
		if (result == 1) return "success";
		return "error";
	}

	@Override
	public List<Order> getAllOrderList() {
		// TODO Auto-generated method stub
		return orderDao.getAllOrder();
	}


}
