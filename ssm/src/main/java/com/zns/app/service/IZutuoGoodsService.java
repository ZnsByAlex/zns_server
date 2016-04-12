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
	
	public List<ZutuoGoods> getZutuoGoodsList();
	/**
	 * 后台用获取全部祖托结果列表
	 * @param map
	 * @return
	 */
	
	public List<ZutuoGoods> getZutuoGoodsList(Map<String , String> map);
	
	/**
	 * 组托结果
	 * @param json_result
	 * @return
	 */
	public boolean getZutuoResult(String json_result);
	
	public boolean deleteZutuoByIdAndNo(Integer examId, String goodsNo);
	
	public boolean updateZutuo(ZutuoGoods zutuoGoods);
	
	public ZutuoGoods getZutuoByIdAndNo(Integer examId, String goodsNo);
	
	public String insertZutuoGoods(ZutuoGoods zutuoGoods);
}
