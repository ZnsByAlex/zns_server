package com.zns.app.sevice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zns.app.bean.TempExamUser;
import com.zns.app.dao.ITempExamUserDao;
import com.zns.app.service.ITempExamUserService;

@Service("examUserService")
public class TempExamUserServiceImpl implements ITempExamUserService{
	
	@Resource
	private ITempExamUserDao examUserDao;

	@Override
	public List<TempExamUser> getExamUserList() {
		// TODO Auto-generated method stub
		return examUserDao.getExamUserList();
	}

	@Override
	public boolean deleteExamUserById(Integer id) {
		// TODO Auto-generated method stub
		int result = examUserDao.deleteByPrimaryKey(id);
		if (result == 1) return true;
		return false;
	}

	@Override
	public boolean updateExamUser(TempExamUser examUser) {
		// TODO Auto-generated method stub
		if(examUser.getId() != null){
			int result = examUserDao.updateByPrimaryKeySelective(examUser);
			if (result == 1) return true;
		}
		return false;
	}

	@Override
	public TempExamUser selectExamUserById(Integer id) {
		// TODO Auto-generated method stub
		TempExamUser examUser = examUserDao.selectByPrimaryKey(id);
		return examUser;
	}

	@Override
	public String insertExamUser(TempExamUser examuser) {
		// TODO Auto-generated method stub
		int result = examUserDao.insert(examuser);
		if(result == 1) return "success";
		return "error";
	}

	@Override
	public TempExamUser selectByUserNo(String userNo) {
		// TODO Auto-generated method stub
		return examUserDao.selectByUserNo(userNo);
	}

}
