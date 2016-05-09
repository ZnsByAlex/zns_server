package com.zns.app.service;

import java.util.List;

import com.zns.app.bean.TempExamUser;

public interface ITempExamUserService {
	
	public List<TempExamUser> getExamUserList();
	
	public boolean deleteExamUserById(Integer id);
	
	public boolean updateExamUser(TempExamUser examUser);
	
	public TempExamUser selectExamUserById(Integer id);
	
	public String insertExamUser(TempExamUser examuser);
	
	public List<TempExamUser> selectByUserNo(String userNo);
	
	public boolean updateScoreByUserAndExam(TempExamUser examUser);
}
