package com.zns.app.sevice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zns.app.bean.User;
import com.zns.app.dao.IUserDao;
import com.zns.app.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService{

	@Resource
	private IUserDao userDao;

	@Override
	public User getUserByNo(String userNo) {
		
//		User user = userDao.selectByPrimaryKey(userNo);
		return null;
	}

	@Override
	public boolean isUserExist(String userNo , String password) {
		Map<String , String> map = new HashMap<String, String>();
		map.put("userNo", userNo);
		map.put("password", password);
		User user = userDao.selectByPrimaryKey(map);
		if(user != null) return true;
		return false;
	}

	@Override
	public boolean registerUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return userDao.getUserList();
	}
	
	
	

}
