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
	public String registerUser(User user) {
		// TODO Auto-generated method stub
		int result;
		if(user.getUserNo() == null || user.getUserNo().equals("")){
			return "UserNo can't be empty";
		}else if (user.getUserPwd() == null || user.getUserPwd().equals("")){
			return "UserPwd can't be empty";
		}
		result = userDao.insert(user);
		if(result == 1) return "success";
		return "other error";
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return userDao.getUserList();
	}

	@Override
	public boolean deleteUserById(Integer userid) {
		// TODO Auto-generated method stub
		int result = userDao.deleteByPrimaryKey(userid);
		if(result == 1) return true;
		return false;
	}

	@Override
	public boolean updateUserById(User user) {
		// TODO Auto-generated method stub
		int result = userDao.updateByPrimaryKeySelective(user);
		if(result ==1) return true;
		return false;
	}

	@Override
	public User getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = userDao.selectById(userId);
		return user;
	}
	
	
	

}
