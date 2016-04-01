package com.zns.app.service;

import com.zns.app.bean.User;

public interface IUserService {
	public User getUserByNo(String userNo);  
	
	public boolean isUserExist(String userNo , String password);
	
	public boolean registerUser(User user);
}
