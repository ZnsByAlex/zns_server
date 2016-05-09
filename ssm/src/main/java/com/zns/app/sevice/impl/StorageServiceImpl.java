package com.zns.app.sevice.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zns.app.bean.Storage;
import com.zns.app.dao.IStorageDao;
import com.zns.app.service.IStorageService;

@Service("storageService")
public class StorageServiceImpl implements IStorageService {

	@Resource
	private IStorageDao storageDao;
	
	@Override
	public List<Storage> getStorageList(Map<String, String> map) {
		return storageDao.selectByExamIdAndUser(map);
	}

	@Override
	public int updateStorageList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return storageDao.updateStorage(map);
	}
	
	

}
