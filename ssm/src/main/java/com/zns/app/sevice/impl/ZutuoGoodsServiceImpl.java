package com.zns.app.sevice.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zns.app.bean.Tray;
import com.zns.app.bean.ZutuoGoods;
import com.zns.app.dao.ITrayDao;
import com.zns.app.dao.IZutuoGoodsDao;
import com.zns.app.service.IZutuoGoodsService;
import com.zns.app.util.JsonUtil;

@Service("zutuoService")
public class ZutuoGoodsServiceImpl implements IZutuoGoodsService {

	@Resource
	private IZutuoGoodsDao zutuoDao;
	
	@Resource
	private ITrayDao trayDao;
	
	@Override
	public List<ZutuoGoods> getZutuoGoodsList(Map<String, String> map) {
		
		String userNo = map.get("userNo");
		String examId = map.get("questionId");
		
		List<ZutuoGoods> list = zutuoDao.selectByExamId(Integer.parseInt(examId));
		
		return list;
	}

	@Override
	public boolean getZutuoResult(String json_result) {
		
		Map<String , Object> req_map = JsonUtil.Json2Map(json_result);
		String userNo = (String) req_map.get("userNo");
		String examId = (String) req_map.get("questionId");
		
		
		List<LinkedHashMap<String , Object>> temp = (List<LinkedHashMap<String, Object>>) req_map.get("trayInfo");
		List<Tray> list = new ArrayList<Tray>();
		for(int i=0;i<temp.size();i++){
			LinkedHashMap<String , Object> info = temp.get(i);
			
			Tray tray = new Tray();
			tray.setUserNo((String)info.get("userNo"));
			tray.setExamId(Integer.parseInt((String) info.get("questionId")));
			tray.setTrayno((String)info.get("trayNo"));
			tray.setZutuoGoodsNo((String)info.get("goodsNo"));
			tray.setGoodsnum((String)info.get("goodsNum"));
			tray.setReceptorderno((String)info.get("R20130625"));
			
			list.add(tray);
		}
		
		int res = trayDao.insertTrayList(list);
		
		
		if(res>0)return true;
		
		return false;
	}
}
