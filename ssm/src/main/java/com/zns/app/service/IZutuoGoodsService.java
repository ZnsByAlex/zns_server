package com.zns.app.service;

import java.util.List;
import java.util.Map;

import com.zns.app.bean.ZutuoGoods;

public interface IZutuoGoodsService {
	/**
	 * ��ȡ��Ҫ���е������б�
	 * @param examId
	 * @return
	 */
	public List<ZutuoGoods> getZutuoGoodsList(Map<String , String> map);
	
	/**
	 * ���н��
	 * @param json_result
	 * @return
	 */
	public boolean getZutuoResult(String json_result);
	

}
