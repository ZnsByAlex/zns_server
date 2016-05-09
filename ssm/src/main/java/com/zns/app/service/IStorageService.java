package com.zns.app.service;

import java.util.List;
import java.util.Map;

import com.zns.app.bean.Storage;


/**
 * �����ϼ�service
 * @author chenkaining
 *
 */
public interface IStorageService {

	/**
	 * ��ȡ����ϼܵ���Ϣ
	 * @param map
	 * @return
	 */
	public List<Storage> getStorageList(Map<String , String> map);
	
	
	public int updateStorageList(Map<String , Object> map);
}
