package com.zns.app.service;

import java.util.List;
import java.util.Map;

import com.zns.app.bean.Storage;


/**
 * 组托上架service
 * @author chenkaining
 *
 */
public interface IStorageService {

	/**
	 * 获取入库上架的信息
	 * @param map
	 * @return
	 */
	public List<Storage> getStorageList(Map<String , Object> map);
}
