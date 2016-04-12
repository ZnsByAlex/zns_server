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
	
	public List<ZutuoGoods> getZutuoGoodsList();
	/**
	 * ��̨�û�ȡȫ�����н���б�
	 * @param map
	 * @return
	 */
	
	public List<ZutuoGoods> getZutuoGoodsList(Map<String , String> map);
	
	/**
	 * ���н��
	 * @param json_result
	 * @return
	 */
	public boolean getZutuoResult(String json_result);
	
	public boolean deleteZutuoByIdAndNo(Integer examId, String goodsNo);
	
	public boolean updateZutuo(ZutuoGoods zutuoGoods);
	
	public ZutuoGoods getZutuoByIdAndNo(Integer examId, String goodsNo);
	
	public String insertZutuoGoods(ZutuoGoods zutuoGoods);
}
