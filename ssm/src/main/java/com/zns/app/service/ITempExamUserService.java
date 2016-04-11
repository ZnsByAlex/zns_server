package com.zns.app.service;

import java.util.List;

import com.zns.app.bean.TempExamUser;

public interface ITempExamUserService {
	
	public List<TempExamUser> getExamUserList();
	
	public boolean deleteExamUserById(Integer id);
	
	public boolean updateExamUserById(Integer id);
	
	public TempExamUser selectExamUserById(Integer id);
	
	public String insertExamUserById(TempExamUser examuser);
}
