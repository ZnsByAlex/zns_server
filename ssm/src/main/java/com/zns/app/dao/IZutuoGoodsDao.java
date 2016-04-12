package com.zns.app.dao;

import java.util.List;
import java.util.Map;

import com.zns.app.bean.ZutuoGoods;

public interface IZutuoGoodsDao {
    int deleteByPrimaryKey(String goodsno);

    int insert(ZutuoGoods record);

    int insertSelective(ZutuoGoods record);

    ZutuoGoods selectByPrimaryKey(String goodsno);

    int updateByPrimaryKeySelective(ZutuoGoods record);

    int updateByPrimaryKey(ZutuoGoods record);
    
    List<ZutuoGoods> selectByExamId(Integer examId);
    
    //下面的方法用于获取后台
    
    List<ZutuoGoods> getZutuoList();
    
    int deleteByIdAndNo(Map<String, Object> data);
    
    ZutuoGoods selectByIdAndNo(Map<String, Object> data);
}