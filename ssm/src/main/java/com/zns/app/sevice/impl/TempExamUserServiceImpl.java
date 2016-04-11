package com.zns.app.sevice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zns.app.bean.TempExamUser;
import com.zns.app.service.ITempExamUserService;

@Service("examUserService")
public class TempExamUserServiceImpl implements ITempExamUserService{

	@Override
	public List<TempExamUser> getExamUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteExamUserById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateExamUserById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TempExamUser selectExamUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insertExamUserById(TempExamUser examuser) {
		// TODO Auto-generated method stub
		return null;
	}

}
