package com.zns.app.sevice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zns.app.bean.Tray;
import com.zns.app.dao.ITrayDao;
import com.zns.app.service.ITrayService;

@Service("trayService")
public class TrayServiceImpl implements ITrayService{

	@Resource
	private ITrayDao trayDao;
	
	@Override
	public List<Tray> getTrayListByUserNo(String userNo) {
		// TODO Auto-generated method stub
		return trayDao.getTrayListByUserNo(userNo);
	}

}
