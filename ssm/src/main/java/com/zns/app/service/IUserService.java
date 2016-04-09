package com.zns.app.service;

import java.util.List;

import com.zns.app.bean.User;

public interface IUserService {
	public User getUserByNo(String userNo);  
	
	public boolean deleteUserById(Integer userid);
	
	public boolean isUserExist(String userNo , String password);
	
	public String registerUser(User user);
	
	public List<User> getUserList();
	
	public boolean updateUserById(User user);
	
	public User getUserById(Integer userId);
}
