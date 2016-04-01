package com.zns.app.dao;

import java.util.List;

import com.zns.app.bean.ZutuoGoods;

public interface IZutuoGoodsDao {
    int deleteByPrimaryKey(String goodsno);

    int insert(ZutuoGoods record);

    int insertSelective(ZutuoGoods record);

    ZutuoGoods selectByPrimaryKey(String goodsno);

    int updateByPrimaryKeySelective(ZutuoGoods record);

    int updateByPrimaryKey(ZutuoGoods record);
    
    List<ZutuoGoods> selectByExamId(Integer examId);
}