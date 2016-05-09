package com.zns.app.sevice.impl;

import java.util.ArrayList;
import java.util.HashMap;
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

@Service("zutuoGoodsService")
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
		
		
		List<LinkedHashMap<String, Object>> trayInfoList = JsonUtil.json2List(json_result);
		trayInfoList = JsonUtil.json2List(json_result);
//		Map<String , Object> userLoginMap = new HashMap<String, Object>();
		LinkedHashMap<String, Object> req_map = (LinkedHashMap<String, Object>) trayInfoList.get(0).get("$mobileGroupTray");
		
		String userNo = (String) req_map.get("userNo");
		String examId = (String) req_map.get("questionId");
		
		
		List<LinkedHashMap<String , Object>> temp = (List<LinkedHashMap<String, Object>>) req_map.get("trayInfo");
		List<Tray> list = new ArrayList<Tray>();
		for(int i=0;i<temp.size();i++){
			LinkedHashMap<String , Object> info = temp.get(i);
			
			Tray tray = new Tray();
			tray.setUserNo(userNo);
			tray.setExamId(Integer.parseInt(examId));
			tray.setTrayno((String)info.get("trayNo"));
			tray.setZutuoGoodsNo((String)info.get("goodsNo"));
			tray.setGoodsname(getZutuoByIdAndNo(Integer.parseInt(examId), (String)info.get("goodsNo")).getGoodsname());
			
			tray.setGoodsnum((String)info.get("goodsNum"));
			tray.setReceptorderno((String)info.get("receptorderNo"));
			
			list.add(tray);
		}
		
		int res = trayDao.insertTrayList(list);
		
		
		if(res>0)return true;
		
		return false;
	}

	@Override
	public List<ZutuoGoods> getZutuoGoodsList() {
		// TODO Auto-generated method stub
		return zutuoDao.getZutuoList();
	}
	
	

	@Override
	public boolean deleteZutuoByIdAndNo(Integer examId, String goodsNo) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("examId", examId);
		data.put("goodsNo", goodsNo);
		int result = zutuoDao.deleteByIdAndNo(data);
		if (result == 1) return true;
		return false;
	}

	@Override
	public boolean updateZutuo(ZutuoGoods zutuoGoods) {
		// TODO Auto-generated method stub
		int result = zutuoDao.updateByPrimaryKeySelective(zutuoGoods);
		if (result == 1) return true;
		return false;
	}

	@Override
	public ZutuoGoods getZutuoByIdAndNo(Integer examId, String goodsNo) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("examId", examId);
		data.put("goodsNo", goodsNo);
		ZutuoGoods result = zutuoDao.selectByIdAndNo(data);
		return result;
	}

	@Override
	public String insertZutuoGoods(ZutuoGoods zutuoGoods) {
		// TODO Auto-generated method stub
		if (zutuoGoods.getExamid() == null || zutuoGoods.getExamid().equals("")) 
			return "ExamId can not be empty";
		if (zutuoGoods.getGoodsno() == null || zutuoGoods.getGoodsno().equals(""))
			return "GoodsId can not be empty";
		int result = zutuoDao.insertSelective(zutuoGoods);
		if (result == 1) return "success";
		return "Unknown Error";
	}

	@Override
	public List<Tray> getTrayList(Map<String, String> map) {
		List<Tray> list = trayDao.selectTrayByIdAndUserNo(map);
		return list;
	}
	public List<ZutuoGoods> selectByExamId(Integer examId) {
		// TODO Auto-generated method stub
		return zutuoDao.selectByExamId(examId);
	}
}
