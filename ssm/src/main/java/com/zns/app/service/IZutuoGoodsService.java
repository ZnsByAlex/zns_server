package com.zns.app.service;

import java.util.List;
import java.util.Map;

import com.zns.app.bean.ZutuoGoods;

public interface IZutuoGoodsService {
	/**
	 * 获取需要组托的物料列表
	 * @param examId
	 * @return
	 */
	public List<ZutuoGoods> getZutuoGoodsList(Map<String , String> map);
	
	/**
	 * 组托结果
	 * @param json_result
	 * @return
	 */
	public boolean getZutuoResult(String json_result);
}
